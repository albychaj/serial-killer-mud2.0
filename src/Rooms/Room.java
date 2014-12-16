package Rooms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Items.Item;
import MOBs.MOB;
import Players.Player;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public abstract class Room implements Serializable
{
	private static final long serialVersionUID = -392554011670131427L;
	private String name; // the name of the room
	private String description; // description of the room
	private List<Player> players; // list of players currently in the room
	private List<MOB> mobs; // list of mobs currently in the room
	private List<Item> items; // list of items currently in the room
	private Room northRoom, southRoom, eastRoom, westRoom;
	
	public Room(String name, String description)
	{
		this.name = name;
		this.description = description;
		this.players = new ArrayList<Player>();
		this.mobs = new ArrayList<MOB>();
		this.items = new ArrayList<Item>();
		
		// initially, a Room does not have any adjacent Rooms
		northRoom = null;
		southRoom = null;
		eastRoom = null;
		westRoom = null;	
	}
	
	public String getRoomName() { return name; }
	
	public String getRoomDescription() { return description; }
	
	public List<Player> getPlayers() { return players; }
	
	public List<MOB> getMOBs() { return mobs; }
	
	public List<Item> getItems() { return items; }
	
	public void addPlayerToRoom(Player aPlayer) { players.add(aPlayer); }
	
	public Player removePlayerFromRoom(String username) 
	{ 
		Player result = null;
		Iterator<Player> it = players.iterator();
		
		while (it.hasNext())
		{
			Player p = it.next();
			if (p.getUsername().equals(username))
			{
				result = p;
				it.remove();
			}
		}
		
		return result;
	}	
	
	public void addMOBToRoom(MOB anMOB) { mobs.add(anMOB); }
	
	public void removeMOBFromRoom(MOB anMOB) { mobs.remove(anMOB); }
	
	public void addItemToRoom(Item anItem) { items.add(anItem); }
	
	public Item removeItemFromRoom(String itemName) 
	{ 
		Item result = null;
		
		Iterator<Item> it = items.iterator();
		
		while(it.hasNext())
		{
			Item i = it.next();
			if (i.getName().equalsIgnoreCase(itemName))
			{
				result = i;
				it.remove();
			}
		}
		
		return result;
	}
	
	public void setNorthRoom(Room aRoom) { northRoom = aRoom; }
	
	public Room getNorthRoom() { return northRoom; }
	
	public void setSouthRoom(Room aRoom) { southRoom = aRoom; }
	
	public Room getSouthRoom() { return southRoom; }
	
	public void setEastRoom(Room aRoom) { eastRoom = aRoom; }
	
	public Room getEastRoom() { return eastRoom; }
	
	public void setWestRoom(Room aRoom) { westRoom = aRoom; }
	
	public Room getWestRoom() { return westRoom; }
	
	
	public boolean hasItem(String argument)
	{
		for (Item i: items)
		{
			if (i.getName().equalsIgnoreCase(argument))
				return true;
		}
		
		return false;
	}
	
	public List<String> getNamesOfPlayersInRoom()
	{
		List<String> names = new ArrayList<String>();
		
		for(Player p : players)
			names.add(p.getUsername());

		return names;
	}
	
	// again probably don't need this one but we'll see
	public String getNamesOfMOBsInRoom()
	{
		String names = new String();
		
		for(MOB m : mobs)
			names += "\t" + m.getIdentity() + "\n";
		
		return names;
	}

	// don't need but etc....
	public String getNamesOfItemsInRoom()
	{
		String names = new String();
		
		for(Item i : items)
			names += "\t" + i.getName() + "\n";

		return names;
	}

	public String whatDoesPlayerWantToLookAt(String argument)
	{
		// Check to see if the player wants to look at an item
		// in their current room
		for (Item i: items)
		{
			if (i.getName().equalsIgnoreCase(argument))
				return "item";
		}
		
		// Check to see if the player wants to look at another
		// player in their current room
		for (Player p: players)
		{
			if (p.getUsername().equalsIgnoreCase(argument))
				return "player";
		}
		
		// Check to see if the player wants to look at an
		// mob in their current room
		for (MOB m: mobs)
		{
			if (m.getIdentity().equalsIgnoreCase(argument))
				return "mob";
		}
		
		// If it has reached this point, this means that what the player 
		// is trying to look at cannot be found in their current room
		return "error";
	}

	public Item getItem(String item)
	{
		for (Item i: items)
		{
			if (i.getName().equalsIgnoreCase(item))
				return i;
		}
		
		return null;
	}

	public Player getPlayer(String player)
	{
		for (Player p: players)
		{
			if (p.getUsername().equalsIgnoreCase(player))
				return p;
		}
		
		return null;
	}

	public MOB getMOB(String mob)
	{
		for (MOB m: mobs)
		{
			if (m.getIdentity().equalsIgnoreCase(mob))
				return m;
		}
		
		return null;
	}

	public boolean containsPlayer(String username)
	{
		Iterator<Player> it = players.iterator();
		while(it.hasNext())
		{
			Player p = it.next();
			if (p.getUsername().equalsIgnoreCase(username))
				return true;
		}
		
		return false;
	}
	
	public boolean containsMOB(MOB mob){
		Iterator<MOB> it = mobs.iterator();
		while(it.hasNext())
		{
			MOB m = it.next();
			if(m.getIdentity().equalsIgnoreCase(mob.getIdentity())){
				return true;
			}
			
		}
		return false;
	}

	public String wholeRoomDescription() 
	{
		String result = new String();
		
		result += "You are now in... " + name + "\n";
		result += "Room Description: " + description + "\n";
		
		// Gather names of players in the room.
		result += "Players that are currently in this room:\n";
		String playerNames = new String();
		
		Iterator<Player> pl = players.iterator();
		while(pl.hasNext())
		{
			Player p = pl.next();
			playerNames += "          " + p.getUsername() + "\n";
		}
		
		result += playerNames;
		
		//Gather names of MOBs in room
		result += "MOBs that are currently in this room:\n";
		String mobNames = new String();
		
		Iterator<MOB> m = mobs.iterator();
		while(m.hasNext())
		{
			MOB mob = m.next();
			mobNames += "            " +mob.getIdentity() + "\n";
		}
		result += mobNames;
		
		// Gather names of items in the room.
		result += "Items that are currently in this room:\n";
		String itemNames = new String();
		
		Iterator<Item> itemIterator = items.iterator();
		
		while(itemIterator.hasNext())
		{
			Item i = itemIterator.next();
			itemNames += "          " + i.getName() + "\n";
		}
		
		if (itemNames.equals(""))
			result += "          There are no items in this room at this time.\n";
		
		else
			result += itemNames;
		
		// Print out names of adjacent rooms.
		result += "Adjacent Rooms:\n";
		
		if (northRoom != null)
			result += "          North Room: " + northRoom.getRoomName() + "\n";
		
		if (southRoom != null)
			result += "          South Room: " + southRoom.getRoomName() + "\n";
		
		if (eastRoom != null)
			result += "          East Room: " + eastRoom.getRoomName() + "\n";
		
		if (westRoom != null)
			result += "          West Room: " + westRoom.getRoomName() + "\n";
		
		result += "\n";
				
		return result;
	}

	public boolean validMoveDirection(String direction) 
	{
		if (direction.equalsIgnoreCase("north") && northRoom != null)
			return true;
		
		else if (direction.equalsIgnoreCase("south") && southRoom != null)
			return true;
		
		else if (direction.equalsIgnoreCase("east") && eastRoom != null)
			return true;
		
		else if (direction.equalsIgnoreCase("west") && westRoom != null)
			return true;
		
		else
			return false;
	}

	public Room getRoom(String direction) 
	{
		if (direction.equalsIgnoreCase("north"))
			return northRoom;
		
		else if (direction.equalsIgnoreCase("south"))
			return southRoom;
		
		else if (direction.equalsIgnoreCase("east"))
			return eastRoom;
		
		else
			return westRoom;
	}

	public boolean hasEast() {
		if(eastRoom != null){
			return true;
		}
		return false;
	}
	
	public boolean hasWest(){
		if(westRoom != null){
			return true;
		}
		return false;
	}
	
	public boolean hasNorth(){
		if(northRoom != null){
			return true;
		}
		return false;
	}
	
	public boolean hasSouth(){
		if(southRoom != null){
			return true;
		}
		return false;
	}
}
