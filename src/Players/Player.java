package Players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Items.Item;
import MOBs.MOB;
import Rooms.Room;
import View.Death;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class Player implements Serializable
{
	private static final long serialVersionUID = 273382826880053009L;
	private String username;
    private String password;
    private Room currentLocation;
    private List<Item> backpack;
    private int health;
    private final static int MAXHEALTH = 100;
    private final static int MAX_ITEMS = 5;
    private int attackPoints;
    
     
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        backpack = new ArrayList<Item>();
        health = 50;
        attackPoints = -30;
    }
    
    public void setLocation(Room aRoom)
    {
    	currentLocation = aRoom;
    }
    
	public boolean matches(char[] entered)
	{
		return new String(entered).equals(password); 
	}
    
    public String getUsername() { return username; }
     
    public void changeRoom(Room room){
    	//erase self from room and add self to new room
        currentLocation = room;
    }
    
    public Room getLocation() { return currentLocation; }
    
    public List<Item> getItems() { return backpack; }
    
    public int getHealth() { return health; }
    
    public int getAttackPoints() { return attackPoints; }
    
    public void setAttackPoints(int amount) 
    {
    	attackPoints = amount;
    }
    
    public void incrementHealth(int amount){
    	int newHealth = health + amount;
    	if(newHealth > MAXHEALTH)
    		health = 100;
    	else if(newHealth <= 0)
    		death();
    	else
    		health = newHealth;
    }
    
    public void incrementAttackPoints(int amount){
    	int newAP = attackPoints + amount;
    	attackPoints = newAP;
    	
    }
    
    public void death(){
    	//create window to state death //deactivate button listener for commands
    	new Death();
    }
    
    /**
     * 
     * @param newItem
     */
    public void pickUpItem(Item newItem)
    {
    	backpack.add(newItem);
    }
    
    /**
     * 
     * @param itemToGetRidOf
     */
    public boolean dropItem(Item itemToGetRidOf)
    {
    	if(backpack.contains(itemToGetRidOf)){
    		backpack.remove(itemToGetRidOf);
    		return true;
    	}
    	return false;
    }
    
    /**
     * 
     */
    public void interactWithOtherPlayer(Player thePlayer)
    {
         //talk to other player determine if going to fight or what
    	//fight()
    }
    
    /**
     * 
     */
    public void InteractWithMOB(MOB theMOB)
    {
         //mob talks and states what items it holds, good or bad, etc
    	//fight()
    }
    
    /**
     * 
     */
    public void fight()
    {
         
    }
    
    /**
     * 
     */
    public void defend()
    {
         
    }
    
    /**
     * 
     */
    public void run()
    {
         
    }

	public boolean hasItem(String item)
	{
		for (Item i: backpack)
		{
			if (i.getName().equalsIgnoreCase(item))
				return true;
		}
		
		return false;
	}

	public Item removeItemFromBackpack(String itemName)
	{
		Item result = null;
		
		for (Item i: backpack)
		{
			if (i.getName().equalsIgnoreCase(itemName))
			{
				result = i;
				backpack.remove(i);
				return i;
			}
		}
		
		return result;
	}

	public String wholePlayerDescription() 
	{
		String result = new String();
		
		result += "You are looking at the player... " + username + "\n";
		
		result += "How are they faring you ask? Well currently, their health level is " + health + "%\n\n";
		
		return result;
	}
    
//    public void setBackpack(List<Item> list)
//    {
//    	backpack = list;
//    }

//	public void setHealth(int health) {
//		this.health = health;
//	}
}//end of class Player
