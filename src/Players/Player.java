package Players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Items.Item;
import MOBs.MOB;
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
    private List<Item> backpack;
    private int health;
    private final static int MAXHEALTH = 100;
    private final static int MAX_ITEMS = 5;
    private int attackPoints;
    private String giveRecipient, getRecipient, giveItem, getItem, senderOfRequest;
    private String itemToGive, itemReceived; // The item to give
    private boolean transactionPending;
    
     
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        giveRecipient = new String();
        getRecipient = new String();
        giveItem = new String();
        getItem = new String();
        itemReceived = new String();
        transactionPending = false;
        itemToGive = new String();
        backpack = new ArrayList<Item>();
        health = 50;
        attackPoints = -30;
    }
    
	public boolean matches(char[] entered)
	{
		return new String(entered).equals(password); 
	}
    
    public String getUsername() { return username; }
    
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
    
    public void fight(){
    	
    }
    
    /**
     * 
     * @param newItem
     */
    public void pickUpItem(Item newItem)
    {
    	if(backpack.size() < 5)
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

	public void setGiveRecipient(String recipient) 
	{
		giveRecipient = recipient;
	}

	public void setGiveItem(String itemName) 
	{
		giveItem = itemName;
	}

	public String getGiveItem() 
	{
		return giveItem;
	}

	public void resetGiveFields() 
	{
		giveRecipient = new String();
		giveItem = new String();
	}

	public String getGiveRecipient() 
	{
		return giveRecipient;
	}

	public String getRecipientOfGet() 
	{
		return getRecipient;
	}

	public void setSenderOfRequest(String senderName) 
	{
		senderOfRequest = senderName;
	}

	public void setTradingItem(String itemName) 
	{
		itemToGive = itemName;
	}
	
	public String getTradingItem()
	{
		return itemToGive;
	}

	public boolean transactionPending() 
	{
		return transactionPending;
	}

	public String senderOfRequest() 
	{
		return senderOfRequest;
	}

	public boolean hasTradingItem() 
	{
		if (itemToGive.equals(""))
			return false;
		
		return true;
	}

	public void resetTradeFields() 
	{
		transactionPending = false;
		itemToGive = new String();
		senderOfRequest = new String();
	}
	
	public void itemReceived(String tradingItemName) 
	{
		itemReceived = tradingItemName;
	}

	public String getNameOfItemReceived() 
	{
		return itemReceived;
	}

	public void resetNameOfItemReceived() 
	{
		itemReceived = new String();
	}

	public void setTransactionPending() 
	{
		transactionPending = true;
	}
}//end of class Player
