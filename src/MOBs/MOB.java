package MOBs;

//import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import Items.Item;
import Players.Player;
import Rooms.Room;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 * 
 */

public class MOB {
	private String identity;
	private ArrayList<Item> pocket;
	private Room currentLocation;
	private ArrayList<String> speeches;
	private int health;
	private Random randomGenerator;
	private int attackPoints;

	public MOB(String identity, ArrayList<Item> items, ArrayList<String> stuffToSay) {
		randomGenerator = new Random();
		this.identity = identity;
		pocket = new ArrayList<Item>();
		this.speeches = stuffToSay;
		//currentLocation = startLocation;
		attackPoints = -10;
	}
	
	public int getAttackPoints(){
		return attackPoints;
	}
	
	public String getIdentity(){
		return identity;
	}

	public void speak(int x) {
		System.out.println(speeches.get(x));
	}

	public void addItemToPocket(Item newItem) {
		pocket.add(newItem);
	}

	public void removeItemFromPocket(Item toBeRemoved) {
		pocket.remove(toBeRemoved);
	}

	public void changeRoom(Room newRoom) {
		currentLocation.removeMOBFromRoom(this); 		// erase self from room and add self to new room
		currentLocation = newRoom;
	}

	public Room getCurrentLocation() {
		return currentLocation;
	}

	public void giveItemToPlayer() {
		// give item to player
	}

	public void attack(Player thePlayer) {
		int points = randomGenerator.nextInt(10);
		thePlayer.incrementHealth(points);
	}

	public void run() {
		//exit to next nearest room
//		if(currentLocation.getNorthRoom() != null)
//			change
	}
	
	public void whoAmI() {
		// TODO Auto-generated method stub
		System.out.println("I am" + identity);
	}
	
	public void death(){
		//drop all holding items
		//leave note telling last words/curses/additional information still useful
	}
	
	public void interactWithPlayer(Player player){
		//speak to player
		//choose whether to fight to sit back
	}

	public String wholeMOBDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
