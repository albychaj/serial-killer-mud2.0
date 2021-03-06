package Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Commands.Command;
import Commands.DisconnectCommand;
import Commands.UpdateFightStatsCommand;
import Commands.createNewPlayerAccountCommand;
import Commands.loginPlayerCommand;
import Items.Item;
import MOBs.MOB;
import Players.Player;
import View.LoginView;
import View.MOBdescription;
import View.MainView;
import View.Map;
import View.Saved;

/**
 * The client side of Serial Killer MUD. This class displays the chat log
 * and sends...
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class Client extends JFrame
{	
	private static final long serialVersionUID = 7356738763172150406L;
	
	private MainView mainView;
	private List<String> commandMessages; // the command log
	private List<String> chatMessages; // the chat log
	
	private Socket server; // connection to the server
	//private Player player;
	private String clientName; // name of client
	private ObjectOutputStream out; // output stream
	private ObjectInputStream in; // input stream
	private Timer t;
			
	public static void main (String []args)
	{
		new Client();
	} // end of method main

	/**
	 * Sets up the connection between the server and the
	 * client. Sets up the GUI.
	 */
	@SuppressWarnings("unchecked")
	public Client()
	{
		String host = JOptionPane.showInputDialog("Host address:", "localhost");
		String port = JOptionPane.showInputDialog("Host port:", "9001");
		
		if (host == null || port == null)
			return;
		
		try
		{
			// Open a connection to the server
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			
			// Once the connection is open, the client will read in from the
			// server the current list of players. It will then use this list
			// to determine whether the motherheffer trying to access the game
			// is allowed the privilege. If not, there's no fucking point in 
			// letting them in...
			ConcurrentHashMap<String, Player> playerAccounts = (ConcurrentHashMap<String, Player>)in.readObject();
			List<String> playersOnline = (ArrayList<String>)in.readObject();
			
			// From here, the LoginView will take the reins.
			new LoginView(Client.this, playerAccounts, playersOnline);	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		//t = new Timer(1000, new keepTrackOfTimeListener());
		
	} // end of constructor Client

	/**
	 * This class reads and executes commands sent from the server
	 */
	public class ServerHandler implements Runnable
	{
		@Override
		@SuppressWarnings("unchecked")
		public void run()
		{
			try
			{
				while (true)
				{
					// read a command from server and execute it
					Command<Client> c = (Command<Client>)in.readObject();
					c.execute(Client.this);
				}
			}
			catch (SocketException e)
			{
				return; // "gracefully" terminate after disconnect
			}
			catch (EOFException e)
			{
				return; // "gracefully" terminate
			}
			catch (Exception e)
			{
				e.printStackTrace();
			} // end of try/catch statements
		} // end of method run
	} // end of private class ServerHandler
	
	/**
	 * Updates the view with the updated message log
	 * 
	 * @param chatMessage The current log of messages to display
	 */
	public void updateChatLog(String chatMessage) 
	{
		chatMessages.add(chatMessage);
		mainView.updateChatLog(chatMessages, commandMessages);
	}
	/**
	 * welcomMessage prints out a welcoming message as the player's client begins
	 * @param username - player's username
	 * @return the string message
	 */
	private String welcomeMessage(String username)
	{
		String introStory = "Warning this game is for mature audiences only.\n\n"
				+ "Welcome " + username +  "!!\n\n"
				+ "You have chosen to enter a world that most turn and run\n"
				+ "from. You have a brave soul but bravery and pride only go so\n"
				+ "far in this world. Be prepared to walk in the shoes of many who\n"
				+ "have been unjustly murdered. Victims who have been tortured\n"
				+ "and slayed in cruel and unimaginable ways. Many of the people\n"
				+ "who committed such horrid and unforgivable crimes have died.\n"
				+ "That�s a good thing right if there dead then they can�t kill\n"
				+ "you. Wrong! This world is Serial Killer purgatory. A place\n"
				+ "in which serial killers have risen from the dead and can\n"
				+ "continue their murder spree; starting off with your ass\n"
				+ "first. This place will test your skills in survival. And\n"
				+ "unfortunately to save yo own ass you will have to become\n"
				+ "someone you will grow to hate. Murderers lurk in every corner\n"
				+ "in this world, you are never safe and you are never alone.\n"
				+ "Are you scared yet? You should be! There are others just\n"
				+ "like you. They too want to survive. It�s up to you and the\n"
				+ "other sane people to kill all  evil that exists within this\n"
				+ "world. Leave all your fears at the entrance to the murder\n"
				+ "castle. As a gift to you I will tell you that you are on the\n"
				+ "front lawn of the murder castle. I cannot guarantee your\n"
				+ "safety killers can be hidden in this area right now.\n\n"
				+ "If you ever need assistance navigating through this world\n"
				+ "type in commands and press enter. Those commands will be\n"
				+ "of great use through the game. I suggest looking at the map.\n\n"
				+ "I now send you off with a fair warning � watch your back!!\n"
				+ "Go at once and don't forget to save yo ass.\n\n";
//		String welcomeMessage = "Hello " + username + " and welcome to SAVE YO ASS.\n\n"
//				+ "For a list of commands at your disposal, type: commands\n";
		
		return introStory;
	}

	/**
	 * lists all of the commands a player can use during game play.
	 * calls the update function on the command side of the gui.
	 */
	public void listCommands() 
	{
		String listOfCommands = "Here are the commands: \n"
				+ "MAP\n     An interactive map will appear.\n"
				+ "MOVE <direction>\n     Move into the room to the <direction>\n"
				+ "COMMANDS\n     List all of the available commands\n"
				+ "OOC <message>\n     Send <message> to all players\n"
				+ "SAY <message>\n     Send a <message> to all players in the same room\n"
				+ "TELL <player> <message>\n     Send a <message> to a specific player\n"
				+ "WHO\n     Lists all of the current players\n"
				+ "SCORE\n     Lists your current score\n"
				+ "GET <item>\n     Retrieves an item from the room and adds it to your backpack\n"
				+ "GET <item> <target>\n     Retrieves an item from the a player/MOB\n"
				+ "GIVE <item> <target>\n     Give an item in you backpack to another player\n"
				+ "INVENTORY\n     Lists all of the items in your backpack\n"
				+ "DROP <item>\n     Removes the item from your backpack\n"
				+ "USE <item>\n     Use an item in your backpack\n"
				+ "LOOK\n     provides a 360 description of your surroundings\n"
				+ "LOOK <argument>\n     provides in depth description of specified argument\n"
				+ "FIGHT <MOB>\n     Fight an MOB that is currently in the same room\n"
				+ "QUIT\n     quits the game and closes the window\n";
		commandMessages.add(listOfCommands);
		mainView.updateCommandLog(commandMessages);
	}

	/**
	 * lists all of the players currently online.  updates the command side of the gui.
	 * @param playersLoggedIn - list of current players
	 */
	public void listWho(String playersLoggedIn) 
	{
		String listOfPlayers = "Here are the players currently online: \n";
		listOfPlayers += playersLoggedIn;
		
	
		commandMessages.add(listOfPlayers);
		mainView.updateCommandLog(commandMessages);	
	}
	
	/**
	 * closes the gui and creates a new DisconnectCommand for that player.
	 */
	public void closeByInput()
	{
		try 
		{
			out.writeObject(new DisconnectCommand(clientName));
			out.close();
			in.close();
			System.exit(0);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void dropItem(String itemName) 
	{
		String dropped = new String();
		
		if (itemName.equals(""))
			dropped = "You did not have this item to drop.\n";
		
		else
			dropped = "Success! You no longer have <" + itemName + "> in your inventory.\n";
		
		// Update the command log
		commandMessages.add(dropped);
		mainView.updateCommandLog(commandMessages);
		
		
	}

	/**
	 * List of the shit in players backpack
	 * @param playersInventory The player's backpack
	 */
	public void listInventory(List<String> playersInventory) 
	{
		String inventory = new String();
		
		if (playersInventory.isEmpty())
			inventory = "You currently have no items in your backpack.\n";
		
		else
		{
			inventory = "These are the items that are currently in your backpack:\n";
			
			for (String itemName: playersInventory)
			{
				inventory += "\t" + itemName + "\n";
			}
		}
		
		commandMessages.add(inventory);
		mainView.updateCommandLog(commandMessages);
	}

	/**
	 * Allows the player to pick up the specified item
	 * @param itemName The item the user wants to pick up
	 */
	public void pickUp(String itemName)
	{
		String pickUp = new String();
		
		if(itemName.equals(""))
			pickUp = "You cannot pick up this specified item.\n";
		
		else
			pickUp = "You have picked up item <" + itemName + "> and added it to your\ninventory.\n";
			
		commandMessages.add(pickUp);
		mainView.updateCommandLog(commandMessages);
	}
	
	/*
	 * The following two methods have to do with the MOVE command
	 */
	
	/**
	 * Moves the player in the specified direction
	 * @param roomDescription The description of the room
	 */
	public void movePlayer(String roomDescription) 
	{
		String successfulMoveMessage = "You have moved rooms.";
		commandMessages.add(successfulMoveMessage);
		lookAtWholeRoom(roomDescription);
	}
	
	public void moveError() 
	{ 
		String moveErrorMessage = "Sorry, can't move in this direction. For an overview of the\nrooms adjacent to your "
				+ "current room, simply type: look.\nRemember, you can only move \nnorth, south, east, or west.\n\n";
		commandMessages.add(moveErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}
	
	/*
	 * The following five methods have to do with the LOOK command
	 */

	public void lookAtWholeRoom(String roomDescription) 
	{
		commandMessages.add(roomDescription);
		mainView.updateCommandLog(commandMessages);
	}
	
	// This has to be implemented by Lisa. To do this, all you have to modify is the wholeMOBDescription method
	// in the MOB class. 
	public void lookAtMOBInRoom(String mobName)
	{
		new MOBdescription(mobName);
		/*commandMessages.add(mob.wholeMOBDescription());
		mainView.updateCommandLog(commandMessages);*/
	}
	
	// Right now all this prints out is the username and health of the player. Do y'all want other people to be
	// able to see the contents of another player's backpack? Maybe we could also have a bio command where a player
	// is allowed to create a bio for themselves. If you're fine with just the username and health printing out, then
	// LOOK <PLAYER> is done. Otherwise, if you want more to be printed out when this is used, go into the player class
	// and modify the wholePlayerDescription method.
	public void lookAtPlayerInRoom(Player playa)
	{
		commandMessages.add(playa.wholePlayerDescription());
		mainView.updateCommandLog(commandMessages);
	}
	
	// Right now all this prints out is the name of the item and it's description. If you want to print out other
	// things like its uses, etc. etc, then modify the wholeItemDescription() method in the Item abstract class.
	// Just a reminder, if you want to print out something like that's specific to a certain subclass of item
	// then you have to add those specific parameters to the superclass Item in order for it to print. Even if you
	// don't want to add anything else, y'all still need to finish this cause I don't know what a fighting item or
	// reuseable item or blah blah blah does. 
	public void lookAtItemInRoom(Item item)
	{
		commandMessages.add(item.wholeItemDescription());
		mainView.updateCommandLog(commandMessages);
	}
	
	public void lookError()
	{
		String lookErrorMessage = "You are attempting to view an item/mob/player that does not\nexist or is not "
				+ "currently in this room. For an overview of\nthe items/mobs/players that are currently in this "
				+ "room, simply type: look\n\n";
		commandMessages.add(lookErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	/**
	 * Associates this client with a player account. Called 
	 * by LoginView.
	 * 
	 * @param username The player this client is associated with. 
	 */
	public void setPlayer(String username)
	{
		clientName = username;
	}

	/**
	 * Finishes setting up the player as well as displaying the main 
	 * view of the MUD. Called by LoginView.
	 * @param newPlayer The new player
	 */
	public void finishSettingUpPlayer(Player newPlayer)
	{
		try
		{
			clientName = newPlayer.getUsername();
			commandMessages = new ArrayList<String>();
			chatMessages = new ArrayList<String>();
			commandMessages.add(welcomeMessage(clientName));
			
			// Write out player associated with this client
			out.writeObject(clientName);
			
			// Send command to the server letting it know to set up the new client in the mud
			out.writeObject(new createNewPlayerAccountCommand(newPlayer));
			
			// Add a listener that sends a disconnect command to when closing
			this.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent arg0) 
				{
					try 
					{
						out.writeObject(new DisconnectCommand(clientName));
						out.close();
						in.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			});
						
			setupGUI();
			
			// Start a thread for handling server events.
			new Thread(new ServerHandler()).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} // end of try/catch statement
	} // end of method finishSettingUpPlayer
	
	public void loginExistingPlayer(String username) 
	{
		try
		{
			
			clientName = username;
			String returnMessage = "Didn�t think you would show again. Welcome back " + clientName + "!!\n\n"
					+ "Remember if you ever need assistance navigating through this\n"
					+ "world or have forgotten how to perform a certain task just type\n"
					+ "commands and a list of commands are at your disposal.\n\n"
					+ "Good luck on your journey " + clientName + "!\n\n";
			commandMessages = new ArrayList<String>();
			chatMessages = new ArrayList<String>();
			commandMessages.add(returnMessage);
			
			// Write out player associated with this client
			out.writeObject(clientName);
			
			out.writeObject(new loginPlayerCommand(username));
			
			// Add a listener that sends a disconnect command to when closing
			this.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(WindowEvent arg0) 
				{
					try 
					{
						out.writeObject(new DisconnectCommand(clientName));
						out.close();
						in.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			});
						
			setupGUI();
			
			// Start a thread for handling server events.
			new Thread(new ServerHandler()).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} // end of try/catch statement
	}
	
	private void setupGUI()
	{
		// create an instance of the mainView and add it the this frame
		mainView = new MainView(clientName, out);
		this.add(mainView);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}  // end of method setupGUI

	public void showMap() 
	{
		new Map();
	}

	public void listScore(Player playa) {
		int health = playa.getHealth();
		int attack = playa.getAttackPoints();
		String toPrint = "";
		toPrint += "Health Level: " + health + "%.\n";
		toPrint += "Attack Points: " + attack + ".\n";
		commandMessages.add(toPrint);
		mainView.updateCommandLog(commandMessages);
	}
	
	public void useItem(String argument, Player playah) {
		String toPrint = "";
		
		switch (argument.toLowerCase()) {
		case "water":
		case "food":
		case "energy boost":
			playah.incrementHealth(5);
			toPrint = "You have now used <" + argument
					+ ">. Your health has been incremented. \n";
		break;
		case "money":
		case "knife":
		case "sword":
		case "gun":
		case "handcuffs":
		case "stick":
			playah.incrementAttackPoints(-5);
			
			
		break;	
		default:
			toPrint = "You have now used <" + argument + ">.\n";
			break;
		}
		
		commandMessages.add(toPrint);
		mainView.updateCommandLog(commandMessages);
		listScore(playah);
	}

	public void tellError() 
	{
		String tellErrorMessage = "You are attempting to message a player that does not exist or is\ncurrently" + "" 
				+ "not " + "online. For a list of the players that are\ncurrently online, simply type:" + "" 
				+ "who\n\n";
		commandMessages.add(tellErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void giveOrGetError() 
	{
		String tellErrorMessage = "Fucked up trade man. Please input a valid player's username to\ntrade with or a "
				+ "valid item that you would like to trade.\n";
		commandMessages.add(tellErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void fight(MOB opponent, Player player, Boolean isGoingToFight) {
		Random randomGenerator = new Random();
		
		String theFight = "";
		
		if(isGoingToFight){
			theFight = "Wat is this???\nIt looks like it is gonna be a fight!\n\n";
			while (player.getHealth() > 0  && opponent.getHealth() > 0) {
				int playerRoll = randomGenerator.nextInt(6);
				int oppenentRoll = randomGenerator.nextInt(6);
				if (playerRoll >= oppenentRoll) {
					theFight += "You hit him!\n\n";
					opponent.incrementHealth(player.getAttackPoints());
				} else {
					theFight += "Ouch! He got you.\nDon't worry, it's only a flesh wound!\n\n";
					player.incrementHealth(opponent.getAttackPoints());
				}
			}
			if (player.getHealth() > 0) {
				theFight += "rightous! won't be seeing him any time soon. that's for sure.";
				new Saved();
			} else
				theFight += "I guess it wasn't just a flesh wound after all.\n Tell lucy I've always loved her...";
		} 
		else
			theFight = opponent.getIdentity() + " is not around at the moment. Maybe wait a while.";
		
		String fightResults = new String();
		if(player.getHealth() > 0){
			fightResults += "OOOOOOHHHHHH " + player.getUsername() + " demolished " + opponent.getIdentity()+ "!!!";
		}
		else{
			fightResults += "OUCH, " + opponent.getIdentity() + " killed " + player.getUsername() + ". Sorry, " + player.getUsername() + " yo ass is DEAD.";
		}
		try {
			out.writeObject(new UpdateFightStatsCommand(player.getUsername(), fightResults));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		commandMessages.add(theFight);
		mainView.updateCommandLog(commandMessages);
		
		
	}	
	public void receivedGiveRequest(String sender, String itemName) 
	{
		String receivedGiveRequest = "Attention! The player " + sender + " would like to give you item <" + itemName 
				+ ">. If you would like to accept this item, please type: Accept. Otherwise, if you would like to "
				+ "deny the kindness (like an asshole), please type: Deny.\n";
		commandMessages.add(receivedGiveRequest);
		mainView.updateCommandLog(commandMessages);
	}
	
	public void receivedGetRequest(String sender, String itemName) 
	{
		String receivedGetRequest = "Attention! The player <" + sender + "> requests that you hand over "
				+ "item <" + itemName + ">. If you would like to give this item to player <" + sender + ">, "
				+ "please type: Accept. Otherwise, if you would like to keep the item (which could save yo ass "
				+ "later), please type: Deny.\n";
		commandMessages.add(receivedGetRequest);
		mainView.updateCommandLog(commandMessages);
	}

	public void tradeRequestSent(String recipient, String itemName) 
	{
		String sentGiveRequest = "Player <" + recipient + "> has been informed of your desire to trade.\n"
				+ "Now we wait...\n";
		commandMessages.add(sentGiveRequest);
		mainView.updateCommandLog(commandMessages);
	}

	public void CommandError() 
	{
		String commandErrorMessage = "This is not a valid command. For a list of valid commands,\ntype: commands\n";
		commandMessages.add(commandErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void acceptedItem(String sender, String itemName) 
	{
		String acceptedItemMessage = "You have sucessfully accepted item <" + itemName + "> from " + sender + "!\nFor an updated look of the items "
				+ "currently stored in your backpack,\ntype: inventory\n";
		commandMessages.add(acceptedItemMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void itemAccepted(String recipient, String itemName) 
	{
		String sentGiveRequestAccepted = "Success! " + recipient + " has accepted item <" + itemName + ">.\nFor an updated look of the items "
				+ "currently stored in your backpack, type: inventory\n";
		commandMessages.add(sentGiveRequestAccepted);
		mainView.updateCommandLog(commandMessages);
	}

	public void rejectionSent(String sender) 
	{
		String sentRejection = "<" + sender + "> has been notified of your rejection.\nHope you know what you're doing." ;
		commandMessages.add(sentRejection);
		mainView.updateCommandLog(commandMessages);
	}
	
	public void requestRejected(String recipient) 
	{
		String rejectedRequestMessage = "So sorry but <" + recipient + "> has not accepted your\nrequest to transfer "
				+ "an item. Better luck next time, kid.\n" ;
		commandMessages.add(rejectedRequestMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void getFromMOBError(String recipient) 
	{
		String rejection = "Excuse me, but " + recipient + " does not give items to plebians like yourself. Now get outta here before"
				+ " I kick yo ass.\n";
		commandMessages.add(rejection);
		mainView.updateCommandLog(commandMessages);
	}

	public void notInSameRoomError() 
	{
		String rejection = "What the fuck are you trying to do? Y'all are clearly not in the same room. "
				+ "What? Were you just planning on throwing shit \nacross all the rooms? Dumbass.";
		commandMessages.add(rejection);
		mainView.updateCommandLog(commandMessages);
	}
}
