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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Commands.Command;
import Commands.DisconnectCommand;
import Items.Item;
import MOBs.MOB;
import Players.Player;
import View.LoginView;
import View.MainView;
import View.Map;

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
		
		
	} // end of constructor Client

	/**
	 * This class reads and executes commands sent from the server
	 */
	public class ServerHandler implements Runnable
	{
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
	 * @param messages The current log of messages to display
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
		String welcomeMessage = "Hello " + username + " and welcome to SAVE YO ASS.\n\n"
				+ "For a list of commands at your disposal, type: commands\n";
		
		return welcomeMessage;
	}

	/**
	 * lists all of the commands a player can use during game play.
	 * calls the update function on the command side of the gui.
	 */
	public void listCommands() 
	{
		String listOfCommands = "Here are the commands: \nMAP\n     An interactive map will appear.\nMOVE <direction>\n     Move into the room to the <direction>"
				+ "\nCOMMANDS\n     List all of the available commands\nOOC <message>\n     Send <message> to all players\n"
				+ "WHO\n     Lists all of the current players\nSCORE\n     Lists your current score\nGET <item>\n     Retrieves an item"
				+ "from the room and adds it to your backpack\nINVENTORY\n     Lists all of the items in your backpack"
				+ "\nDROP <item>\n     Removes the item from your backpack\nLOOK\n     provides a 360 description of your surroundings"
				+ "\nLOOK <argument>\n     provides in depth description of specified argument\nQUIT\n     quits the game and closes the window\n";
		commandMessages.add(listOfCommands);
		mainView.updateCommandLog(commandMessages);
	}

	/**
	 * lists all of the players currently online. updates the command side of the gui.
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
	 * lists all of the items in a player's backpack
	 * @param playersInventory 
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
	 * allows the player to pick up the specified item
	 * @param argument - the string name of the item
	 */
	public void pickUp(String itemName)
	{
		String pickUp = new String();
		
		if(itemName.equals(""))
			pickUp = "You cannot pick up this specified item.\n";
		
		else
			pickUp = "You have picked up item <" + itemName + "> and added it to your inventory.\n";
			
		commandMessages.add(pickUp);
		mainView.updateCommandLog(commandMessages);
	}
	
	/*
	 * The following two methods have to do with the MOVE command
	 */
	
	/**
	 * moves the player in the specified direction
	 * @param room - string the direction
	 */
	public void movePlayer(String roomDescription) 
	{
		String successfulMoveMessage = "You have moved rooms.";
		commandMessages.add(successfulMoveMessage);
		lookAtWholeRoom(roomDescription);
	}
	
	public void moveError() 
	{ 
		String moveErrorMessage = "Sorry, can't move in this direction. For an overview of the rooms adjacent to your "
				+ "current room, simply type: look. Remember, you can only move \nnorth, south, east, or west.\n\n";
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
	public void lookAtMOBInRoom(MOB mob)
	{
		commandMessages.add(mob.wholeMOBDescription());
		mainView.updateCommandLog(commandMessages);
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
		String lookErrorMessage = "You are attempting to view an item/mob/player that does not exist or is \nnot "
				+ "currently in this room. For an overview of the items/mobs/players \nthat are currently in this "
				+ "room, simply type: look\n\n";
		commandMessages.add(lookErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	/**
	 * Associates this client with a player account. Called 
	 * by LoginView.
	 * 
	 * @param player The player this client is associated with. 
	 */
	public void setPlayer(String username)
	{
		clientName = username;
	}

	/**
	 * Finishes setting up the player as well as displaying the main 
	 * view of the MUD. Called by LoginView.
	 * @param newPlayer 
	 */
	public void finishSettingUpPlayer(Player newPlayer)
	{
		try
		{
			
			commandMessages = new ArrayList<String>();
			chatMessages = new ArrayList<String>();
			commandMessages.add(welcomeMessage(clientName));
			
			// Write out player associated with this client
			out.writeObject(newPlayer);
			
			// Add a listener that sends a disconnect command to when closing
			this.addWindowListener(new WindowAdapter()
			{
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
			playah.incrementAttackPoints(5);
			
			
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
		String tellErrorMessage = "You are attempting to message a player that does not exist or is currently" + "\n" 
				+ "not " + "online. For a list of the players that are currently online, simply type:" + "\n" 
				+ "who\n\n";
		commandMessages.add(tellErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void giveOrGetError() 
	{
		String tellErrorMessage = "Fucked up trade man. Please input a valid player's username to trade with or a "
				+ "valid item that you would like to trade.\n";
		commandMessages.add(tellErrorMessage);
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
		String sentGiveRequest = "Player <" + recipient + "> has been informed of your desire to trade. "
				+ "Now we wait...\n";
		commandMessages.add(sentGiveRequest);
		mainView.updateCommandLog(commandMessages);
	}

	public void CommandError() 
	{
		String commandErrorMessage = "This is not a valid command. For a list of valid commands, type: commands\n";
		commandMessages.add(commandErrorMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void acceptedItem(String sender, String itemName) 
	{
		String acceptedItemMessage = "You have sucessfully accepted item <" + itemName + "> from " + sender + "! For an updated look of the items "
				+ "currently stored in your backpack, type: inventory\n";
		commandMessages.add(acceptedItemMessage);
		mainView.updateCommandLog(commandMessages);
	}

	public void itemAccepted(String recipient, String itemName) 
	{
		String sentGiveRequestAccepted = "Success! " + recipient + " has accepted item <" + itemName + ">. For an updated look of the items "
				+ "currently stored in your backpack, type: inventory\n";
		commandMessages.add(sentGiveRequestAccepted);
		mainView.updateCommandLog(commandMessages);
	}

	public void rejectionSent(String sender) 
	{
		String sentRejection = "<" + sender + "> has been notified of your rejection. Hope you know what you're doing." ;
		commandMessages.add(sentRejection);
		mainView.updateCommandLog(commandMessages);
	}
	
	public void requestRejected(String recipient) 
	{
		String rejectedRequestMessage = "So sorry but <" + recipient + "> has not accepted your request to transfer "
				+ "an item. Better luck next time, kid." ;
		commandMessages.add(rejectedRequestMessage);
		mainView.updateCommandLog(commandMessages);
	}
}
