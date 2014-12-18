package Items;
import java.io.Serializable;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public abstract class Item implements Serializable
{
	private static final long serialVersionUID = -6308308364879333586L;
	private String name;
	private String description;
	private boolean isVisible;
	private boolean isPickedUp;
	
	
	/**
	 * 
	 * @param name Name of the item
	 * @param description Description of the item
	 * @param currentLocation The room in which the item is currently located
	 * @param isVisible Indicates whether item is currently visible
	 */

	public Item(String name, String description, boolean isVisible, boolean isPickedUp)
	{
		this.name = name;
		this.description = description;
		//this.currentLocation = currentLocation;
		this.isPickedUp = isPickedUp;
		this.isVisible = isVisible;
	}
	
	/**
	 * Sets the location of the item. 
	 * 
	 * @param room The room in which the item is currently located
	 */
/*	public void setLocation(SceneRoom room)
	{
		currentLocation = room;
	}*/
	
	/**
	 * Hides item from players.
	 */
//	public void hide()
//	{
//		isVisible = false;
//	}
	
	public abstract boolean isEnergyBoost();
	
	/**
	 * Allows players to see the item. 
	 */
//	public void reveal()
//	{
//		isVisible = true;
//	}
//	
	public abstract void use();
	
/*	public Room getLocation()
	{
		return currentLocation;
	}*/
	
	/*public void setLocation(Room room)
	{
		currentLocation = room;
	}*/
	
//	public boolean getIsVisible()
//	{
//		return isVisible;
//	}
//	public boolean getIsPickedUp()
//	{
//		return isPickedUp;
//	}
//	
//	public void setIsPickedUp(boolean isPickedUp)
//	{
//		this.isPickedUp = isPickedUp;
//	}
	
	public String getName(){
		return name;
	}

	public String wholeItemDescription() 
	{
		String result = new String();
		
		result += "You are looking at the item... " + name + "\n";
		result += "Description: " + description + "\n";
		
		result += "What kind of item is this you ask? Well...\n";
		
		if (this instanceof FightingItem)
			result += "This is a fighting item. When you use this item, you increase\n"
					+ "your attack points. This is to your benefit the greater the\n"
					+ "attack points the more damage you cause on your opponent.\n\n";
		
		else if (this instanceof EnergyBoostItem)
			result += "This is an energy boost item. When you use this item, you\n"
					+ "increase your health. Remember you have to keep track of your\n"
					+ "health. You don't want to die do you?\n\n";
		
		else
			result += "This is a reuseable item. The item will self generate in which\n"
					+ "ever room it is present.\n\n";
		
		return result;
	}
	
//	public String toString()
//	{
//		String toReturn = "";
//		toReturn += name;
//		return toReturn;
//				
//	}
	
	
		
	
	
} // end of class Item
