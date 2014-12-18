package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Commands.AcceptedItemCommand;
import Commands.Command;
import Commands.CommandErrorCommand;
import Commands.CommandsCommand;
import Commands.DisconnectCommand;
import Commands.DropCommand;
import Commands.FightCommand;
import Commands.GetCommand;
import Commands.GetRequestReceivedCommand;
import Commands.GiveOrGetErrorCommand;
import Commands.ItemGivenToIntendedCommand;
import Commands.GiveRequestRecievedCommand;
import Commands.InventoryCommand;
import Commands.LookErrorCommand;
import Commands.LookItemCommand;
import Commands.LookMOBCommand;
import Commands.LookPlayerCommand;
import Commands.LookRoomCommand;
import Commands.MOBGetErrorCommand;
import Commands.MapCommand;
import Commands.MoveCommand;
import Commands.MoveErrorCommand;
import Commands.QuitCommand;
import Commands.RejectionSentCommand;
import Commands.RequestDeniedCommand;
import Commands.ScoreCommand;
import Commands.TellErrorCommand;
import Commands.TradeRequestSentCommand;
import Commands.UpdateChatLogCommand;
import Commands.UseCommand;
import Commands.WhoCommand;
import Enums.Commands;
import Items.Item;
import MOBs.MOB;
import Model.SerialKillerMud;
import Players.Player;
import Rooms.Room;
import View.MOBdescription;

/**
 * The class is the server side of the Serial Killer MUD. The server
 * communicates with clients, sends and receives commands, etc.
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 * 
 */
public class Server {
	private ServerSocket socket; // the server socket
	private HashMap<String, ObjectOutputStream> outputs; // map of all connected
															// user's output
															// streams

	private SerialKillerMud mud;
	private Timer t;
	private Timer t2;
	private Random randomGenerator;

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		// chatMessages = new ArrayList<String>(); // create the chat log
		outputs = new HashMap<String, ObjectOutputStream>(); // setup the map
		mud = new SerialKillerMud(); // setup the model
		t = new Timer(10000, new SayListener());
		t.start();
		t2 = new Timer(20000, new MoveListener());
		t2.start();
		randomGenerator = new Random();

		try {
			// start a new server on port 9001
			socket = new ServerSocket(9001);
			System.out.println("MUD Server started on port 9001");

			// spawn a client accepter thread
			new Thread(new ClientAcceptor()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of constructor Server

	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (MOB mob : mud.getMOBs()) {
				int r = randomGenerator.nextInt(4);
				switch (r) {
				case 0:
					if (mud.getMOBCurrLocation(mob).hasEast()) {
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has left the room.**");
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob)
								.getRoomName(), 0, mob);
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has entered the room.**");
						// System.out.println(mob.getIdentity() +
						// " new location: " +
						// mud.getMOBCurrLocation(mob).getRoomName());
					}
					break;
				case 1:
					if (mud.getMOBCurrLocation(mob).hasWest()) {
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has left the room.**");
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob)
								.getRoomName(), 1, mob);
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has entered the room.**");
						// System.out.println(mob.getIdentity() +
						// "new location: " +
						// mud.getMOBCurrLocation(mob).getRoomName());

					}
					break;
				case 2:
					if (mud.getMOBCurrLocation(mob).hasNorth()) {
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has left the room.**");
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob)
								.getRoomName(), 2, mob);
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has entered the room.**");
						// System.out.println(mob.getIdentity() +
						// "new location: " +
						// mud.getMOBCurrLocation(mob).getRoomName());

					}
					break;
				case 3:
					if (mud.getMOBCurrLocation(mob).hasSouth()) {
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has left the room.**");
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob)
								.getRoomName(), 3, mob);
						updateClientsChatLogInSameRoomAsMOB(mob,
								"**" + mob.getIdentity() + " has entered the room.**");
						// System.out.println(mob.getIdentity() +
						// "new location: " +
						// mud.getMOBCurrLocation(mob).getRoomName());

					}
					break;
				default:
					System.out.println("MOB didn't move");
					break;
				}

			}
			System.out.println();
		}

	}

	private class SayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 10; i++) {
				for (String p : mud.getPlayersOnline()) {

					int r = randomGenerator
							.nextInt(mud.getMOBMessages().size());
					updateClientsInSameRoomAsMOB(mud.getMOBs().get(i), mud
							.getMOBMessages().get(r));
					// System.out.println(mud.getMOBs().get(i).getIdentity() +
					// " to you: " + mud.getMOBMessages().get(r));

				}
			}
		}

	}

	/**
	 * This thread listens for and sets up connections to new clients
	 */
	public class ClientAcceptor implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					// Accept a new client then get its output/input streams.
					Socket s = socket.accept();
					ObjectOutputStream output = new ObjectOutputStream(
							s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(
							s.getInputStream());

					// The first thing the server needs to do is send the client
					// a list of the
					// current players. This is so when someone is logging in,
					// the system is able
					// to determine whether the user is already logged in,
					// thereby disallowing
					// the same user to be logged in multiple times.
					output.writeObject(mud.getAllExistingPlayerAccounts());
					output.writeObject(mud.getPlayersOnline());

					// Read in the name of the user and
					String playerName = (String) input.readObject();

					// Spawn a thread to handle communication with this client.
					new Thread(new ClientHandler(input)).start();

					// Map the player's username to the output stream.
					outputs.put(playerName, output);

					// Add a notification message to the chat log
					updateAllClientsChatLog(playerName + " connected");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // end of private class ClientAccepter

	private class ClientHandler implements Runnable {
		private ObjectInputStream input; // the input stream from the client

		public ClientHandler(ObjectInputStream input) {
			this.input = input;
		}

		@SuppressWarnings("unchecked")
		public void run() {
			try {
				while (true) {
					// Read a command from the client, execute on the server.
					Command<Server> command = (Command<Server>) input
							.readObject();
					command.execute(Server.this);

					// Terminate if client is disconnecting
					if (command instanceof DisconnectCommand) {
						input.close();
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // end of private class ClientHandler

	public void AddNewPlayerToTheGame(Player player) {
		// Add the player to the MUD.
		mud.addNewPlayerToGame(player);
	}

	public void LoginExistingPlayer(String username) {
		mud.addExistingPlayerToGame(username);
	}

	public void updateAllClientsChatLog(String chatMessage) {
		// make an UpdatedClientsCommand, write to all connected users
		UpdateChatLogCommand update = new UpdateChatLogCommand(chatMessage);

		try {
			for (ObjectOutputStream out : outputs.values())
				out.writeObject(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method updateClients

	private void updateClientsChatLogInSameRoom(String username,
			String chatMessage) {
		List<String> playersInSameRoom = mud.getPlayersInSameRoom(username);
		// make an UpdatedClientsCommand, write to all connected users
		UpdateChatLogCommand update = new UpdateChatLogCommand(chatMessage);

		try {
			if (playersInSameRoom == null) {
				
			}
			else{
				for (String playerName : playersInSameRoom) {
					ObjectOutputStream out = outputs.get(playerName);
					out.writeObject(update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateClientsChatLogInSameRoomAsMOB(MOB mob, String message){
		List<String> playersInSameRoomAsMOB = mud.getPlayersInSameRoomAsMOB(mob);
		UpdateChatLogCommand update = new UpdateChatLogCommand(message);

		try {
			if (playersInSameRoomAsMOB == null) {
				
			}
			else{
				for (String playerName : playersInSameRoomAsMOB) {
					ObjectOutputStream out = outputs.get(playerName);
					out.writeObject(update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateClientsChatLogInSameRoomBesidesPlayer(String username,
			String chatMessage) {
		List<String> playersInSameRoom = mud.getPlayersInSameRoom(username);

		// make an UpdatedClientsCommand, write to all connected users
		UpdateChatLogCommand update = new UpdateChatLogCommand(chatMessage);

		try {
			for (String playerName : playersInSameRoom) {
				if (!playerName.equalsIgnoreCase(username)) {
					ObjectOutputStream out = outputs.get(playerName);
					out.writeObject(update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateClientsInSameRoomAsMOB(MOB mob, String chatMessage) {
		String mobMessage = mob.getIdentity() + ": " + chatMessage;
		List<String> playersInSameRoom = mud.getPlayersInSameRoomAsMOB(mob);
		UpdateChatLogCommand update = new UpdateChatLogCommand(mobMessage);

		try {
			for (String p : playersInSameRoom) {
				ObjectOutputStream out = outputs.get(p);
				out.writeObject(update);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateASpecificChatLog(String sender, String argument) {
		String recipient = new String();
		String chatMessage = new String();

		if (argument.indexOf(" ") > 0) {
			String[] splitArgument = argument.split(" ", 2);
			recipient = splitArgument[0];
			chatMessage = splitArgument[1];
		}

		else
			recipient = argument;

		// Check to see if the user that the sender is trying to message
		// is online. Iterate through the list of players online - if
		// the player isn't online or doesn't exist, give an error message
		// to the sender of the message. Otherwise, write out the message
		// to both the sender and intended recipient.
		boolean playerOnline = mud.playersIsOnline(recipient);
		Command<Client> update;

		try {
			if (playerOnline) {
				update = new UpdateChatLogCommand(sender + " to " + recipient
						+ ": " + chatMessage);

				ObjectOutputStream out = outputs.get(sender);
				out.writeObject(update);

				out = outputs.get(recipient.toLowerCase());
				out.writeObject(update);
			}

			else {
				update = new TellErrorCommand();
				ObjectOutputStream out = outputs.get(sender);
				out.writeObject(update);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect(String clientName) {
		try {
			outputs.get(clientName).close(); // close outputs stream
			outputs.remove(clientName); // remove from map

			Player playerToRemove = null;

			
			mud.disconnectPlayer(clientName);

			// add notification message
			updateAllClientsChatLog(clientName + " disconnected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of method disconnect

	public void PrintToClient(String clientName, Commands command,
			String argument) {
		if (command == Commands.OOC)
			updateAllClientsChatLog(clientName + ": " + argument);

		else if (command == Commands.SAY)
			updateClientsChatLogInSameRoom(clientName, clientName + ": "
					+ argument);

		else if (command == Commands.TELL)
			updateASpecificChatLog(clientName, argument);

		else if (command == Commands.SHUTDOWN) {
			closeAllClientsAndServer(clientName);
		}

		else {
			try {
				SimpleCommandFactory factory = new SimpleCommandFactory();
				Command<Client> update = factory.createCommand(clientName,
						command, argument);

				if (update != null) {
					ObjectOutputStream out = outputs.get(clientName);
					out.writeObject(update);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void closeAllClientsAndServer(String username) {
		if (username.equalsIgnoreCase("admin")) {
			try {
				// Make a QuitCommand and send to all clients of the server,
				// thereby disconnecting
				// their ports and closing their GUIs. When that is done, close
				// down the server.
				QuitCommand update = new QuitCommand();

				for (ObjectOutputStream out : outputs.values())
					out.writeObject(update);

				System.exit(0);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		else
			throw new IllegalArgumentException();
	}	
	
	public void sendRejectionOfGiveOrGetToSender(String sender, String recipient) 
	{
		try 
		{
			Command<Client> update = new RequestDeniedCommand(recipient);
			ObjectOutputStream outs = outputs.get(sender);
			outs.writeObject(update);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void sendConfirmationOfGiveToSender(String sender, String recipient, String itemName) 
	{
		try 
		{
			Command<Client> update = new ItemGivenToIntendedCommand(recipient, itemName);
			ObjectOutputStream outs = outputs.get(sender);
			outs.writeObject(update);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void sendConfirmationOfGetToSender(String senderOfRequest, String recipientOfRequest, String itemName)
	{
		try 
		{
			Command<Client> update = new AcceptedItemCommand(recipientOfRequest, itemName);
			ObjectOutputStream outs = outputs.get(senderOfRequest);
			outs.writeObject(update);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	public void sendGiveRequestToRecipient(String sender, String recipient, String itemName) 
	{
		try 
		{
			Command<Client> update = new GiveRequestRecievedCommand(sender, itemName);
			ObjectOutputStream outs = outputs.get(recipient);
			outs.writeObject(update);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void sendGetRequestToRecipient(String sender, String recipient, String itemName) 
	{
		try 
		{
			Command<Client> update = new GetRequestReceivedCommand(sender, itemName);
			ObjectOutputStream outs = outputs.get(recipient);
			outs.writeObject(update);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private class SimpleCommandFactory
	{
		public Command<Client> createCommand(String username, Commands command, String argument)
		{
			Command<Client> result = null;
			Room currRoom = mud.getRoomPlayerIsCurrIn(username);
			String roomName = mud.getPlayerRoomName(username);

			switch (command) {
			case ERROR:
				result = new CommandErrorCommand();
				break;

			case MAP:
				result = new MapCommand();
				break;

			case MOVE:

				if (currRoom.validMoveDirection(argument)) {
					if ((currRoom.getRoomName().equalsIgnoreCase("The Lawn") && mud
							.getPlayer(username).hasItem("key"))
							|| !currRoom.getRoomName().equalsIgnoreCase(
									"The Lawn")) {
						updateClientsChatLogInSameRoomBesidesPlayer(username,
								"<" + username + " has left the room>");
						String newRoomDescription = mud.movePlayerToNewRoom(
								roomName, argument, username);
						updateClientsChatLogInSameRoomBesidesPlayer(username,
								"<" + username + " has moved into " + roomName + ">");
						result = new MoveCommand(newRoomDescription);
					}

				}

				else
					result = new MoveErrorCommand();

				break;

			case LOOK: // completely done except for mob. Just needs some
						// sprucing up
				if (argument.equals(""))
					result = new LookRoomCommand(mud.getRoomPlayerIsCurrIn(
							username).wholeRoomDescription());

				else {
					// Check to see whether the user is asking for an item,
					// player, or MOB description
					String x = currRoom.whatDoesPlayerWantToLookAt(argument);
					switch (argument.toLowerCase()) {
					case "jeffery dahmer":
					case "dahmer":
					case "lawrence bittaker":
					case "roy norris":
					case "bittaker":
					case "norris":
					case "richard ramirez":
					case "ramirez":
					case "night stalker":
					case "andrei chikatilo":
					case "chikatilo":
					case "red ripper":
					case "richard trenton chase":
					case "richard chase":
					case "chase":
					case "henry lee lucas":
					case "henry lucas":
					case "lucas":
					case "ed gein":
					case "gein":
					case "psycho":
					case "hannibal lecter":
					case "hannibal":
					case "lecter":
					case "henry howard holmes":
					case "holmes":
					case "hhh":
						x = "mob";
						break;
					default:
						break;
					}

					// If the user is asking to look at an item...
					if (x.equals("item")) {
						Item item = currRoom.getItem(argument);
						result = new LookItemCommand(item);
					}

					// Else if the user is asking to look at a player...
					else if (x.equals("player")) {
						Player playa = currRoom.getPlayer(argument);
						result = new LookPlayerCommand(playa);
					}

					// Else if the user is asking to look at an MOB...
					else if (x.equals("mob")) {
						// MOB mob = currRoom.getMOB(argument);
						result = new LookMOBCommand(argument.toLowerCase());
					}

					// Else the user was asking for something that doesn't exist
					// or that does exist but is not in the same room that the
					// player is currently in.
					else
						result = new LookErrorCommand();
				}

				break;

			case COMMANDS:
				result = new CommandsCommand();
				break;

			case WHO:
				result = new WhoCommand(mud.getPlayersOnline());
				break;

			case SCORE: // should work
				Player playa = mud.getPlayer(username);
				result = new ScoreCommand(playa);
				break;

			case GIVE: // not done yet - needs to work now with an mob
				// First check to see if the user has another transaction
				// pending. If they do, then
				// they have to complete the previous transaction before they
				// can start a new one.
				if (!mud.transactionPending(username)) {
					// Now check to see if the argument is composed of the
					// username of the recipient
					// as well as the name of the item the sender intends to
					// give. If it isn't,
					// then an error will be returned to the sender.
					if (argument.indexOf(" ") > 0) {
						String[] splitArgument = argument.split(" ", 2);
						String itemName = splitArgument[0];
						String recipient = splitArgument[1];

						// Now check to see if the player is online and/or
						// exists. Also check to see if the
						// player has the item. Otherwise, an error will be
						// returned to the sender.
						if (mud.playersIsOnline(recipient)
								&& mud.playerHasItem(username, itemName)) {
							mud.setPendingGiveTransaction(recipient, username,
									itemName);

							// Send message to the sender letting them know that
							// their trade request has been sent.
							result = new TradeRequestSentCommand(recipient,
									itemName);

							// Send message to the recipient letting them know
							// that someone would like to
							// give them an item
							Server.this.sendGiveRequestToRecipient(username,
									recipient, itemName);
						}

						else
							result = new GiveOrGetErrorCommand();
					}
				}

				else
					result = new GiveOrGetErrorCommand();

				break;

			case GET: // not done yet - needs to work now with an mob
				// If true, then the user is trying to get an item from another
				// player/MOB
				if (argument.indexOf(" ") > 0) {
					// Now check to see if the user has another transaction
					// pending. If they do, then
					// they have to complete the previous transaction before
					// they can start a new one.
					if (!mud.transactionPending(username)) {
						String[] splitArgument = argument.split(" ", 2);
						String itemName = splitArgument[0].toLowerCase();
						String recipient = splitArgument[1].toLowerCase();
						List<MOB> mobs = mud.getMOBs();
						boolean isMOB = false;
						for (MOB m : mobs) {
							if (m.getIdentity().equalsIgnoreCase(recipient)) {
								isMOB = true;
								result = new MOBGetErrorCommand(recipient);
								break;
							}

						}
						if (isMOB == true)
							break;

						// Now check to see if the player is online and/or
						// exists. Also check to see if the
						// player has the item. Otherwise, an error will be
						// returned to the sender.
						if (mud.playersIsOnline(recipient)
								&& mud.playerHasItem(recipient, itemName)) {
							mud.setPendingGetTransaction(recipient, username,
									itemName);

							result = new TradeRequestSentCommand(recipient,
									itemName);
							Server.this.sendGetRequestToRecipient(username,
									recipient, itemName);
						}

						else
							result = new GiveOrGetErrorCommand();
					}

					else
						result = new GiveOrGetErrorCommand();
				}

				// If true, then the user is trying to get an item from the room
				else if (currRoom.hasItem(argument)) {
					Item item;
					if (!argument.equalsIgnoreCase("key")) {
						item = mud.removeItemFromRoom(currRoom.getRoomName(),
								argument);
					} else {
						item = mud.getItemFromName(argument);
					}
					mud.giveItemToPlayer(username, item);
					result = new GetCommand(argument);
				}

				// Else the argument is invalid
				else
					result = new GetCommand(new String());
				break;

			case INVENTORY:
				List<String> playersInventory = mud
						.getPlayerInventoryDescription(username);
				result = new InventoryCommand(playersInventory);
				break;

			case DROP:
				Player currPlayer = mud.getPlayer(username);

				if (currPlayer.hasItem(argument)) {
					Item item = mud.removeItemFromPlayerBackpack(username,
							argument);
					mud.addItemToRoom(currRoom.getRoomName(), item);
					result = new DropCommand(argument);
				}

				else
					result = new DropCommand(new String());

				break;

			case USE: // don't really know what this is supposed to do....
				Player playah = mud.getPlayer(username);
				if (playah.hasItem(argument)) {
					result = new UseCommand(argument, playah);
					Item item = mud.removeItemFromPlayerBackpack(username,
							argument);
					mud.addItemToRoom(currRoom.getRoomName(), item);
				}
				break;

			case QUIT:
				result = new QuitCommand();
				break;

			case SHUTDOWN: // should work
				closeAllClientsAndServer(username);
				break;

			case FIGHT:
				Player player = mud.getPlayer(username);
				MOB opponent = mud.getMOBFromName(argument);
				Boolean go = false;
				// if(mud.getPlayerRoomName(player).equals(opponent.getCurrentLocation().getRoomName()))
				if (mud.getRoomPlayerIsCurrIn(username).equals(
						mud.getMOBCurrLocation(opponent)))
					go = true;
				result = new FightCommand(opponent, player, go);
				break;

			case ACCEPT:
				// If the user has a transacting pending, then they can use this
				// command.
				// Otherwise, they are not allowed to use this command.
				if (mud.transactionPending(username)) {
					String senderOfRequest = mud.getSenderOfRequest(username);
					String typeOfTransaction = mud
							.completeTransaction(username);

					// The user was a recipient of a give request (i.e. they are
					// getting an item)
					if (typeOfTransaction.equals("give")) {
						// Send a message to the recipient of the give request
						// letting them know that they got an item
						String itemTransferred = mud
								.getItemTransferred(username);
						result = new AcceptedItemCommand(senderOfRequest,
								itemTransferred);

						// Send a message to sender of the give request letting
						// them know that they gave an item
						Server.this.sendConfirmationOfGiveToSender(
								senderOfRequest, username, itemTransferred);
					}

					// The user was a recipient of a get request (i.e. they are
					// giving an item)
					else {
						// Send a message to the sender of the get request
						// letting them know that they received an item
						String itemTransferred = mud
								.getItemTransferred(senderOfRequest);
						Server.this.sendConfirmationOfGetToSender(
								senderOfRequest, username, itemTransferred);

						// Send a message to the recipient of the get request
						// them know that they gave an item
						result = new ItemGivenToIntendedCommand(
								senderOfRequest, itemTransferred);
					}
				}

				else
					result = new CommandErrorCommand();

				break;

			case DENY:
				// If the user has a transacting pending, then they can use this
				// command.
				// Otherwise, they are not allowed to use this command.
				if (mud.transactionPending(username)) {
					String senderOfRequest = mud.getSenderOfRequest(username);
					String typeOfTransaction = mud.cancelTransaction(username);

					// The user was a recipient of a give request (i.e. they are
					// getting an item)
					if (typeOfTransaction.equals("give")) {
						// Send a message to the recipient letting them know
						// that the rejection went through
						result = new RejectionSentCommand(senderOfRequest);

						// And send a message to the sender letting them know
						// that they were rejected
						Server.this.sendRejectionOfGiveOrGetToSender(
								senderOfRequest, username);
					}

					// The user was a recipient of a get request (i.e. they are
					// giving an item)
					else {
						result = new RejectionSentCommand(senderOfRequest);

						// And send a message to the sender letting them know
						// that they were rejected
						Server.this.sendRejectionOfGiveOrGetToSender(
								senderOfRequest, username);
					}
				}

				else
					result = new CommandErrorCommand();

				break;

			default:
				result = new CommandErrorCommand();
				break;
			}

			return result;
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * stuff that has to do with persistance
	 */

	public void testing() {
		// Ask the user if they want to load data
		int answer = JOptionPane.showConfirmDialog(null,
				"Start with previous saved state?", "Select an Option",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.NO_OPTION || !loadData()) {
			// initialize accounts
			mud = new SerialKillerMud();
		}

	}

	public boolean loadData() {
		try {
			FileInputStream inStream = new FileInputStream(new File(
					"accounts.dat"));
			ObjectInputStream inObject = new ObjectInputStream(inStream);
			mud = (SerialKillerMud) inObject.readObject();
			inObject.close();
		} catch (Exception e) {
			// errorLabel.setText("Unable to load data");
			return false;
		}
		return true;
	}

	public void saveData() {
		try {
			FileOutputStream outStream = new FileOutputStream(new File(
					"accounts.dat"));
			ObjectOutputStream outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(mud);
			outObject.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end of class Server
