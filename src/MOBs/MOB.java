package MOBs;

//import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import Items.Item;
import Rooms.Room;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 * 
 */

public class MOB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6947835557717812826L;
	private String identity;
	private ArrayList<Item> pocket;
	private Room currentLocation;
	private ArrayList<String> speeches;
	private int health;
	private Random randomGenerator;
	private int attackPoints;

	public MOB(String identity, ArrayList<Item> items) {
		randomGenerator = new Random();
		this.identity = identity;
		pocket = new ArrayList<Item>();
		//currentLocation = startLocation;
		attackPoints = -10;
		health = 100;
	}
	
	public int getHealth(){ return health; }
	
	public int getAttackPoints(){ return attackPoints; }
	
	public String getIdentity(){ return identity; }

	public void speak(int x) { System.out.println(speeches.get(x)); }

	public void addItemToPocket(Item newItem) { pocket.add(newItem); }

	public void removeItemFromPocket(Item toBeRemoved) { pocket.remove(toBeRemoved); }
	
	public Room getCurrentLocation() { return currentLocation; }
	
	public void incrementHealth(int hit) {
		health += hit;
	}
	public void changeRoom(Room newRoom) {
		currentLocation.removeMOBFromRoom(this); 		// erase self from room and add self to new room
		currentLocation = newRoom;
	}
	
	public void death(){
		//drop all holding items
		//leave note telling last words/curses/additional information still useful
	}

}
