package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import Commands.Command;
import Commands.CommandsCommand;
import Commands.DisconnectCommand;
import Commands.DropCommand;
import Commands.GetCommand;
import Commands.GiveErrorCommand;
import Commands.InventoryCommand;
import Commands.LookErrorCommand;
import Commands.LookItemCommand;
import Commands.LookMOBCommand;
import Commands.LookPlayerCommand;
import Commands.LookRoomCommand;
import Commands.MapCommand;
import Commands.MoveCommand;
import Commands.MoveErrorCommand;
import Commands.QuitCommand;
import Commands.ScoreCommand;
import Commands.TellErrorCommand;
import Commands.UpdateChatLogCommand;
import Commands.UseCommand;
import Commands.WhoCommand;
import Enums.Commands;
import Items.Item;
import MOBs.MOB;
import Model.SerialKillerMud;
import Players.Player;
import Rooms.Room;

/**
 * The class is the server side of the Serial Killer MUD. The server communicates with clients,
 * sends and receives commands, etc.
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class Server 
{
	private ServerSocket socket; // the server socket
	private HashMap<String, ObjectOutputStream> outputs; // map of all connected user's output streams
	
	private SerialKillerMud mud;
	private Timer t;
	private Timer t2;
	
	public static void main(String [] args)
	{
		new Server();
	}
	
	
	public Server()
	{
		//chatMessages = new ArrayList<String>(); // create the chat log
		outputs = new HashMap<String, ObjectOutputStream>(); // setup the map
		mud = new SerialKillerMud(); // setup the model
		t = new Timer(5000, new SayListener());
		t.start();
		t2 = new Timer(20000, new MoveListener());
		t2.start();
		
		try
		{
			// start a new server on port 9001
			socket = new ServerSocket(9001);
			System.out.println("MUD Server started on port 9001");
			
			// spawn a client accepter thread
			new Thread(new ClientAccepter()).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	} // end of constructor Server
	
	private class MoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Random random = new Random();
						
			
			for (MOB mob : mud.getMOBs()){
				int r = random.nextInt(4);
				switch(r){
				case 0:
					if(mud.getMOBCurrLocation(mob).hasEast()){
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob).getRoomName(), 0, mob);
						System.out.println(mob.getIdentity() + " new location: " + mud.getMOBCurrLocation(mob).getRoomName());
					}
					break;
				case 1:
					if(mud.getMOBCurrLocation(mob).hasWest()){
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob).getRoomName(), 1, mob);
						System.out.println(mob.getIdentity() + "new location: " + mud.getMOBCurrLocation(mob).getRoomName());

					}
					break;
				case 2:
					if(mud.getMOBCurrLocation(mob).hasNorth()){
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob).getRoomName(), 2, mob);
						System.out.println(mob.getIdentity() + "new location: " + mud.getMOBCurrLocation(mob).getRoomName());

					}
					break;
				case 3:
					if(mud.getMOBCurrLocation(mob).hasSouth()){
						mud.moveMOBToNewRoom(mud.getMOBCurrLocation(mob).getRoomName(), 3, mob);
						System.out.println(mob.getIdentity() + "new location: " + mud.getMOBCurrLocation(mob).getRoomName());

					}
					break;
				default:
					System.out.println("Didn't move");
					break;
				}
				
			}
			System.out.println();
		}
		
	}
	
	private class SayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 9; i++){
				for(String p : mud.getPlayersOnline())
				{
					Random random = new Random();
					int r = random.nextInt(mud.getMOBMessages().size());
					//System.out.println(mud.getMOBs().get(i).getIdentity());
					//PrintToClient(mud.getMOBs().get(i).getIdentity(), Commands.SAY, mud.getMOBMessages().get(r));
					System.out.println(mud.getMOBs().get(i).getIdentity() + " to you: " + mud.getMOBMessages().get(r));
					
				}
			}
		}
		
	}
	
	/**
	 * This thread listens for and sets up connections to new clients
	 */
	public class ClientAccepter implements Runnable
	{

		@Override
		public void run() 
		{
			try
			{
				while (true)
				{
					// Accept a new client then get its output/input streams.
					Socket s = socket.accept();
					ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(s.getInputStream());
					
					// The first thing the server needs to do is send the client a list of the 
					// current players. This is so when someone is logging in, the system is able 
					// to determine whether the user is already logged in, thereby disallowing 
					// the same user to be logged in multiple times. 
					output.writeObject(mud.getAllExistingPlayerAccounts());
					output.writeObject(mud.getPlayersOnline());
					// Read in the information of the player associated with this client.
					Player player = (Player)input.readObject();
					
					// Add the player to the MUD. 
					mud.addPlayerToGame(player);
					
					// Map the player's username to the output stream.
					outputs.put(player.getUsername(), output);
					
					// Spawn a thread to handle communication with this client. 
					new Thread(new ClientHandler(input)).start();
					
					// Add a notification message to the chat log
					updateAllClientsChatLog(player.getUsername() + " connected");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}	
	} // end of private class ClientAccepter
	
	private class ClientHandler implements Runnable
	{
		private ObjectInputStream input; // the input stream from the client
		
		public ClientHandler(ObjectInputStream input)
		{
			this.input = input;
		}

		@SuppressWarnings("unchecked")
		public void run() 
		{
			try
			{
				while(true)
				{
					// Read a command from the client, execute on the server.
					Command<Server> command = (Command<Server>)input.readObject();
					command.execute(Server.this);
					
					// Terminate if client is disconnecting
					if (command instanceof DisconnectCommand)
					{
						input.close();
						return;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	} // end of private class ClientHandler

//	public void addMessageToEveryone(String message) 
//	{
//		chatMessages.add(message);
//		updateAllClientsChatLog();
//	} // end of method addMessage
	
	public void addMessageToCertainPeople(String message)
	{
		
	}

	public void updateAllClientsChatLog(String chatMessage)
	{
		// make an UpdatedClientsCommand, write to all connected users
		UpdateChatLogCommand update = new UpdateChatLogCommand(chatMessage);
		
		try
		{
			for(ObjectOutputStream out : outputs.values())
				out.writeObject(update);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} // end of method updateClients
	
	private void updateClientsChatLogInSameRoom(String username, String chatMessage) 
	{
		List<String> playersInSameRoom = mud.getPlayersInSameRoom(username);
		
		// make an UpdatedClientsCommand, write to all connected users
		UpdateChatLogCommand update = new UpdateChatLogCommand(chatMessage);
				
		try
		{
			for (String playerName: playersInSameRoom)
			{
				ObjectOutputStream out = outputs.get(playerName);
				out.writeObject(update);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void updateASpecificChatLog(String sender, String argument) 
	{
		String recipient = new String();
		String chatMessage = new String();
		
		if (argument.indexOf(" ") > 0)
		{
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
		boolean playerOnline = false;
		Command<Client> update;
		
		for (String activeUser: mud.getPlayersOnline())
		{
			if (recipient.equalsIgnoreCase(activeUser))
			{
				recipient = activeUser; // just in case sender input name wrong
				playerOnline = true;
			}
		}
		
		try
		{	
			if (playerOnline)
			{
				update = new UpdateChatLogCommand(sender + "to " + recipient + ": " + chatMessage);
				
				ObjectOutputStream out = outputs.get(sender);
				out.writeObject(update);
				
				out = outputs.get(recipient);
				out.writeObject(update);
			}
			
			else
			{
				update = new TellErrorCommand();
				ObjectOutputStream out = outputs.get(sender);
				out.writeObject(update);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void disconnect(String clientName)
	{
		try
		{
			outputs.get(clientName).close(); // close outputs stream
			outputs.remove(clientName); // remove from map
			Player playerToRemove = null;
			
			mud.disconnectPlayer(clientName);
			
			// add notification message
			updateAllClientsChatLog(clientName + " disconnected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} // end of method disconnect

	public void PrintToClient(String clientName, Commands command, String argument) 
	{	
		if (command == Commands.OOC)
			updateAllClientsChatLog(clientName + ": " + argument);
		
		else if (command == Commands.SAY)
			updateClientsChatLogInSameRoom(clientName, clientName + ": " + argument);
		
		else if (command == Commands.TELL)
			updateASpecificChatLog(clientName, argument);
		
		else
		{
			try
			{
				SimpleCommandFactory factory = new SimpleCommandFactory();
				Command<Client> update = factory.createCommand(clientName, command, argument);
				ObjectOutputStream out = outputs.get(clientName);
				out.writeObject(update);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void closeAllClientsAndServer(String username)
	{
		if (username.equalsIgnoreCase("admin")) 
		{
				try
				{
					// Make a QuitCommand and send to all clients of the server, thereby disconnecting
					// their ports and closing their GUIs. When that is done, close down the server. 
					QuitCommand update = new QuitCommand();
					
					for(ObjectOutputStream out : outputs.values())
						out.writeObject(update);
					
					System.exit(0);
				}
				catch (Exception e)
				{
					//e.printStackTrace();
				}
		}
		
		else
				throw new IllegalArgumentException();
	}
	
	private class SimpleCommandFactory
	{
		public Command<Client> createCommand(String username, Commands command, String argument)
		{
			Command<Client> result = null;
			Room currRoom = mud.getRoomPlayerIsCurrIn(username);
			String roomName = mud.getPlayerRoomName(username);
			
			switch(command)
			{
			case MAP: 
				result = new MapCommand();
				break;
				
			case MOVE: 
				if (currRoom.validMoveDirection(argument))
				{
					String newRoomDescription = mud.movePlayerToNewRoom(roomName, argument, username);
					result = new MoveCommand(newRoomDescription);
				}
				
				else
					result = new MoveErrorCommand();
				
				break;
				
			case LOOK: // completely done except for mob. Just needs some sprucing up
				if (argument.equals(""))
					result = new LookRoomCommand(mud.getRoomPlayerIsCurrIn(username).wholeRoomDescription());
				
				else
				{
					// Check to see whether the user is asking for an item, 
					// player, or MOB description
					String x = currRoom.whatDoesPlayerWantToLookAt(argument);
					
					// If the user is asking to look at an item...
					if (x.equals("item"))
					{
						Item item = currRoom.getItem(argument);
						result = new LookItemCommand(item);
					}
					
					// Else if the user is asking to look at a player...
					else if (x.equals("player"))
					{
						Player playa = currRoom.getPlayer(argument);
						result = new LookPlayerCommand(playa);
					}
					
					// Else if the user is asking to look at an MOB...
					else if (x.equals("mob"))
					{
						MOB mob = currRoom.getMOB(argument);
						result = new LookMOBCommand(mob);
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
				
				// First off, the argument should be composed of the username of the recipient
				// as well as the name of the item the sender intends to give. If it isn't,
				// then an error will be returned to the sender. 
				if (argument.indexOf(" ") > 0)
				{
					String recipient = new String();
					String itemName = new String();
					
					String[] splitArgument = argument.split(" ", 2);
					recipient = splitArgument[0]; 
					itemName = splitArgument[1];
					
					// Now check to see if the player is online and/or exists. Also check to see if the
					// item exists. Otherwise, an error will be returned to the sender. 
					if (mud.playersIsOnline(recipient) && mud.playerHasItem(username, itemName))
					{
						
					}
					
					else
						result = new GiveErrorCommand();
				}
				
				else
				{
					result = new GiveErrorCommand();
				}
				
				break;
				
			case GET: // not done yet - still needs to take two arguments
				if (currRoom.hasItem(argument))
				{
					Item item = mud.removeItemFromRoom(currRoom.getRoomName(), argument);
					mud.giveItemToPlayer(username, item);
					result = new GetCommand(argument);
				}
				
				else
					result = new GetCommand(new String());
				break;
				
			case INVENTORY:
				List<String> playersInventory = mud.getPlayerInventoryDescription(username);
				result = new InventoryCommand(playersInventory);
				break;
				
			case DROP:
				Player currPlayer = mud.getPlayer(username);
				
				if (currPlayer.hasItem(argument))
				{
					Item item = mud.removeItemFromPlayerBackpack(username, argument);
					mud.addItemToRoom(currRoom.getRoomName(), item);
					result = new DropCommand(argument);
				}
				
				else
					result = new DropCommand(new String());
				
				break;
				
			case USE: // don't really know what this is supposed to do....
				Player playah = mud.getPlayer(username);
				result = new UseCommand(argument, playah);
				if (playah.hasItem(argument))
				{
					Item item = mud.removeItemFromPlayerBackpack(username, argument);
					mud.addItemToRoom(currRoom.getRoomName(), item);
					//result = new DropCommand(argument);
				}
				break;
				
			case QUIT:
				result = new QuitCommand();
				break;
				
			case SHUTDOWN: // should work
				closeAllClientsAndServer(username);
				break;
				
			default:
				break;
			}
			
			return result;
		}
	}
} // end of class Server
