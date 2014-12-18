package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Items.EnergyBoostItem;
import Items.FightingItem;
import Items.Item;
import Items.ReusableItem;
import MOBs.MOB;
import Players.Player;
import Rooms.Room;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class SerialKillerMud implements Serializable
{
	private static final long serialVersionUID = 8453581806535931723L;
	private ConcurrentHashMap<String, Player> playerAccounts; // all players accounts
	private List<String> playersOnline; // list of usernames of players online
	private final static Player admin = new Player("admin", "0000");
	
	private List<Room> rooms; // just here to hold all the rooms, not really used
	private ConcurrentHashMap<String, Room> roomsMap;
	private Room entrance, lawn, bonus, woods, basement, castle, farmhouse, factory, motel, hospital, dakotaApts, kitchen;
	private Room mansonHouse, jail, policeStation, sorority, dahmerApt, cemetery, bank, casino, adventureLand, alley;
	private Room spain, paris, dubai, airport, streets, foxHollowFarm, cleveland, bigRig, desert;
	
	private List<Item> items; // list of items in game
	private Item water, food, bandaid, aidKit, energyBoost, stick, knife, gun, sword, shovel, rope;
	private Item handcuffs, flashlight, nightVisionGoggles, key, money, disguise;
	
	private List<MOB> mobs;
	private List<String> mobMessages;
	
	private MOB hannibalLecter, jefferyDahmer, lawrenceBittaker, royNorris, richardRamirez, andreiChikatilo, richardTrentonChase;
	private MOB henryHowardHolmes, edGein, henryLeeLucas;
	
	public SerialKillerMud()
	{
		playerAccounts = new ConcurrentHashMap<String, Player>();
		playerAccounts.put("admin", admin); // add the admin automatically to the player accounts
		playersOnline = new ArrayList<String>();
	
		
		instantiateRooms();
		setupRoomLayout();
		instantiateItems();
		addItemsToRooms();
		instantiateMOBS();
		addMOBSToRoom();
	}
	
	public List<String> getPlayersOnline() 
	{
		return playersOnline;
	}
	
	public ConcurrentHashMap<String, Player> getAllExistingPlayerAccounts()
	{
		return playerAccounts;
	}
	
	public List<String> getPlayersInSameRoomAsMOB(MOB mob){
		Iterator it = roomsMap.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			Room room = (Room) pairs.getValue();
			
			if(room.containsMOB(mob)){
				return room.getNamesOfPlayersInRoom();
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> getPlayersInSameRoom(String username) 
	{
		Iterator it = roomsMap.entrySet().iterator();
		
		while (it.hasNext())
		{
			Map.Entry pairs = (Map.Entry)it.next();
			Room room = (Room) pairs.getValue();
			
			if (room.containsPlayer(username))
			{
				return room.getNamesOfPlayersInRoom();
			}
		}
		
		return null;
	}
		
	public void addNewPlayerToGame(Player player)
	{		
		// Add the new player to the collection of existing players
		playerAccounts.put(player.getUsername(), player);
		
		// Add the new player to the collection of player online.
		playersOnline.add(player.getUsername());
		
		// Add the player to the rooms. Initially, every new player
		// will start out in the same location.
		entrance.addPlayerToRoom(player);
	}
	
	public void addExistingPlayerToGame(String username) 
	{
		// Add the existing player to the collection of players online
		playersOnline.add(username);
		
		Player player = playerAccounts.get(username);
		String lastLocation = player.getLastLocation();
		Room room = roomsMap.get(lastLocation);
		room.addPlayerToRoom(player);
		roomsMap.put(lastLocation, room);
	}
	
	public Room getRoomPlayerIsCurrIn(String username)
	{
		Iterator<Room> it = rooms.iterator();
		
		while (it.hasNext())
		{
			Room currRoom = it.next();
			if (currRoom.containsPlayer(username))
				return currRoom;
		}
		
		// Should never have to reach this because if this method has been called, 
		// then clearly the player exists so it return a valid room since the 
		// player is obviously somewhere in the game.
		return null;
	}

	public Player getPlayer(String username)
	{
		return playerAccounts.get(username);
	}

	public void addItemToRoom(String roomName, Item item)
	{
		for(Room r: rooms)
		{
			if (r.getRoomName().equalsIgnoreCase(roomName))
				r.addItemToRoom(item);
		}
	}

	public void giveItemToPlayer(String username, Item item)
	{
		Player player = playerAccounts.get(username);
		
		player.pickUpItem(item);
		
		//playerAccounts.replace(username, player);
	}

	public Item removeItemFromRoom(String roomName, String itemName)
	{
		Item result = null;
		
		Iterator<Room> it = rooms.iterator();
		
		while(it.hasNext())
		{
			Room r = it.next();
			if (r.getRoomName().equals(roomName))
			{
				return r.removeItemFromRoom(itemName);
			}
		}
		
		return null;
	}

	public Item removeItemFromPlayerBackpack(String username, String itemName)
	{	
		Player player = playerAccounts.get(username);
		
		Item item = player.removeItemFromBackpack(itemName);
		
		//playerAccounts.replace(username, player);
		
		return item;
	}
	
	public void updateRoom(Room currRoom) 
	{
		for (Room r: rooms)
		{
			if (r.getRoomName().equals(currRoom.getRoomName()))
			{
				int index = rooms.indexOf(r);
				rooms.set(index, currRoom);
			}
		}
	}

	public String getPlayerRoomName(String username) 
	{
	Iterator<Room> it = rooms.iterator();
		
		while (it.hasNext())
		{
			Room currRoom = it.next();
			if (currRoom.containsPlayer(username))
				return currRoom.getRoomName();
		}
		
		// Should never have to reach this because if this method has been called, 
		// then clearly the player exists so it return a valid room since the 
		// player is obviously somewhere in the game.
		return null;
	}
	
	public void moveMOBToNewRoom(String roomName, int caseNum, MOB mob){
		Room oldRoom = roomsMap.get(roomName);
		
		oldRoom.removeMOBFromRoom(mob);
		
		String newRoomName = new String();
		
		if(caseNum == 0){
			newRoomName = oldRoom.getEastRoom().getRoomName();
		}
		else if(caseNum == 1){
			newRoomName = oldRoom.getWestRoom().getRoomName();
		}
		else if(caseNum == 2){
			newRoomName = oldRoom.getNorthRoom().getRoomName();
		}
		else if(caseNum == 3){
			newRoomName = oldRoom.getSouthRoom().getRoomName();
		}
		
		Room newRoom = roomsMap.get(newRoomName);
		newRoom.addMOBToRoom(mob);
	}

	public String movePlayerToNewRoom(String roomName, String direction, String username) 
	{
		Room oldRoom = roomsMap.get(roomName);
		
		Player player = oldRoom.removePlayerFromRoom(username);
		
		String newRoomName = new String();
		
		if (direction.equalsIgnoreCase("north"))
		{	
			newRoomName = oldRoom.getNorthRoom().getRoomName();
		}
		
		else if (direction.equalsIgnoreCase("south"))
		{
			newRoomName = oldRoom.getSouthRoom().getRoomName();
		}
		
		else if (direction.equalsIgnoreCase("east"))
		{
			newRoomName = oldRoom.getEastRoom().getRoomName();
		}
		
		else
			newRoomName = oldRoom.getWestRoom().getRoomName();
		
		Room newRoom = roomsMap.get(newRoomName);
		newRoom.addPlayerToRoom(player);
		
		return newRoom.wholeRoomDescription();	
	}
	

	public void disconnectPlayer(String playerName) 
	{
		// Remove the player from the list of players online
		playersOnline.remove(playerName);
		
		// Remove the player from the room it is currently in
		Room currRoom = this.getRoomPlayerIsCurrIn(playerName);
		currRoom.removePlayerFromRoom(playerName);
		
		// Update the room in the HashMap
		roomsMap.put(currRoom.getRoomName(), currRoom);
		
		// Keep an reference to the location the player was in when they
		// left the game. Only new players should start on the lawn. That
		// way, when a player with an existing account logs back in, they will
		// start where they left off in the game.
		Player playerDisconnecting = playerAccounts.get(playerName);
		playerDisconnecting.setLastLocation(currRoom.getRoomName());
		playerAccounts.put(playerName, playerDisconnecting);
	}

	public List<String> getPlayerInventoryDescription(String username) 
	{
		Player player = playerAccounts.get(username);
		
		List<String> backpack = new ArrayList<String>();
		
		for (Item i: player.getItems())
			backpack.add(i.getName());
		
		return backpack;
	}
	
	public List<MOB> getMOBs(){
		return mobs;
	}
	
	public Room getMOBCurrLocation(MOB mob){
		Iterator<Room> it = rooms.iterator();
		
		while (it.hasNext())
		{
			Room currRoom = it.next();
			if (currRoom.containsMOB(mob))
				return currRoom;
		}return null;
	}
	
	public List<String> getMOBMessages(){
		return mobMessages;
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
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	private void instantiateRooms()
	{
		lawn = new Room("The Lawn", "There is no escaping now! The lawn is home-\n"
				      + "base for all players. You are surrounded by thousands upon\n"
				      + "thousands of acres of dead grass and trees. Daylight does\n"
				      + "not exist in this area and anything can happen. There is\n"
			          + "only one way out. Find the key and enter the Murder Castle.\n"
			          + "You can stay but your chances of survival are slime to none\n"
			          + "Do yourself a favor if you're on the lawn, leave at once\n"
			          + "and save yo ass.\n");
		bonus = new Room("The Bonus Room", "Congratulations you have made to the bonus\n"
				       + "room. There isn't much to this room. The floor is navy blue\n"
				       + "and the walls are painted white. There is a table to the\n"
				       + "north of this room which hold a few items for your\n"
				       + "collection. Take advantage of the unique item that can only\n"
				       + "be found in this room! There isn't much to do here and it's\n"
				       + "not a safe zone so leave once you are done.\n");
		woods = new Room("The Dark Woods", "AAAAAOOOOOOWWWWWWWW. Watch out for\n"
				       + "those deathly predators hidden in the brush. Their eyes\n"
				       + "glow with a lively flourish that juxtaposes your inevitable\n"
				       + "fate. There isn't much to see here since it is dark.\n"
				       + "Perhaps you'll find a flashlight hidden amongst the trees.\n\n"
	                   + "Word of advice... Watch your back!!\n");
		basement = new Room("The Basement of the Murder Castle", "Welcome to the deepest trenches\n"
				          + "of the murder castle. Beware the piles of corpses. That\n"
				          + "stench isn't just your feet. Besides the eeriness feeling\n"
				          + "this room gives you there isn't much within the space\n"
				          + "enclosed by these nicely painted red walls.There are no\n"
				          + "windows to light up your path. The floor is a little\n"
				          + "slippery as well it's better that you don't ask and\n"
				          + "continue to explore if you please.\n");
		castle = new Room("The Murder Castle", "Welcome to the cozy home of Sir HH\n"
				        + "Holmes. There's no need to be afraid. Unless HH come home\n"
				        + "The \"Castle\" is located 601-603 W. 63rd St. Chicago. It's three\n"
				        + "stories and a block long. The ground floor contains Dr. Holmes\n"
				        + "drugstore. The upper two floors consist of 100 windowless"
				        + "rooms with doorways opening to brick walls, oddly angles hallways, and\n"
				        + "stairways to nowhere. Sir HH only had one thing in mind when he\n"
				        + "built this place ... to murder.\n");
		farmhouse = new Room("The Wisconsin Farmhouse of Horrors", "Welcome to Ed Gein's farmhouse.\n"
				           + "Pay no mind to the human paraphernalia, Ed Gein\n"
				           + "definitely does not want to scare you away. The\n"
				           + "house is in pristine shape but a little out dated.\n"
				           + "I wouldn't touch anything Ed wouldn't like that. He\n"
				           + "should be arriving shortly. If I were you get what\n"
				           + "you need and leave.\n");
		factory = new Room("The Abandoned Factory", "Creeeeeaak. Low-hanging pipes and boarded windows haunt this desolate place. Beware of dangerous machinery. Some murders can be made to look like "
	                               + "accidents. Spiders aren't the only thing you should be afraid of in this place.");
		motel = new Room("The Roach Motel", "You have found a safe place. This place is pretty dirty. The walls are moldy with odd colored stains. No one can attack you here, so go ahead. "
	                       + "Grab a roach filled cot and get some sleep. Just keep your mouth closed.");
		hospital = new Room("The Save Yo Self Hospital", "Got wounds? Need some patching up? You've come to the right place. This hospital is a bit crowded. It's better to do the job yourself "
                                     + "if you can. If you can't, make sure you check your doctor's credentials. Or else you'll have a one way ticket to the morgue.");
		dakotaApts = new Room("The Dakota Apartments", "Welcome to northwest corner of 72nd Street and Central Park West in New York City. This place is also "
	                                  + "known to be the famous murder place of John Lennon. You are currently standing in a vacant apartment but is nicely "
	                                  + "furnished. The walls are accented with gold and to east of the room is a shiny object go explore if you please.");
		kitchen = new Room("Hannibal's Kitchen", "You're in for a treat. Pull up a chair and prepare to be served the finest finest white meat you'll consume. Make sure you try the ribs. "
                             + "If your not up for a meal just watch your step. There are odd looking things on the floor rather squishy looking. Word of advice don't "
                             + "eat anything Hannibal gives you.");
		mansonHouse = new Room("The Manson Family Murder House", "This is a really nice home but its best that you don�t make yourself comfortable. Some really messed up murders were planned here."
                                             + "");
		jail = new Room("The Dead End Jail", "Bars and Stripes. Welcome to jail. This place is a bit small but it does its job. I can't say you are safe here. There may be a murderer or two in the same "
                         + "cell as you. Also you can say goodbye to the items in your backpack they have been taken into custody. You have lost all your items and won't be getting them back "
                         + ":( sorry.");
		policeStation = new Room("The Police Station", "Got a crime to report? Of course you do, you snitch. This is a safe zone so you are safe... for now. There is a front held desk counter right as you enter "
                                   + "the station. the the left and right of the counter are seats.");
		sorority = new Room("Chi Omega Sorority House at FSU", "Wild parties, catty drama, and psycho murders. Come for fun, because this is a party you'll "
	                                          + "never forget. As you can imagine this place is filthy. There are red solo cups everywhere and practically "
	                                          + "a keg in every room. Watch your step that could be vomit.");
		dahmerApt = new Room("Jeffrey Dahmer's Apartment", "How is it in Milwaukee? I hope Jeffrey hasn�t marked you as his next victim. Last time I heard none of his male "
	                                      + "confidants made it out in one piece. Do yourself a favor and leave apartment 213 unless you like to clean dishes "
	                                      + "because this place could use some cleaning up.");
		cemetery = new Room("Memorial Cemetery", "Tombstones and coffins and dead people sleeping. These are a few of my fa-vor-ite things. "
	                            + "Pick up a shovel and bury your victims, before someone buries you.");
		bank = new Room("Kingsfield Bank", "Out of money? You've come to the right place. Finance your bribes here. This bank is pretty fancy, "
						+ "so don't try anything snappy with the guards. The are cameras in every corner and floors are made of marble. I wouldn't stare "
						+ "at the vault it may give people the wrong impression.");
		casino = new Room("Casino Especial", "WOW!! This casino is like no other. Slot machines galore, navy blue carpet with a swirly design. Gamble all your problems away!");
		adventureLand = new Room("Adventure Land Theme Park", "Step right up and claim your prize. This is no Disney land. The rides here are for adults only. "
											 + "I recommend you check out Devil's Flight before you reach your final destination. Keep your hands "
											 + "and feet in the ride at all times and don't forget to kiss yo ass goodbye. ");
		alley = new Room("The Dark Alley", "The best place for illegal transactions. But watch out this alley is pretty narrow and might I add dark. Not all sharks live in the ocean.");
		spain = new Room("Spain", "Ole ... Welcome to Spain! Beware of the bulls that roam the street. They pack a punch if you get "
	             + "hit by one. It is best not to stay here unless you want to die.");
		paris = new Room("Paris", "Finally a place of relaxation. Nothing bad can happen to you here but you cannot stay forever. For now enjoy "
	             + "your million dollar view.");
		dubai = new Room("Paris", "Welcome to Paris! Visit the crypts under the city and get lost in the Louvre. You wish right? Too bad you are restricted to "
	             + "the catacombs which lie right beneath the heart of Paris. This historic labyrinth contains the remains of at least six million "
	             + "Parisians kept at a chilling 14 degrees Celsius. Try not to lean on the wall made of bones");
		airport = new Room("The International Airport", "Need to get away or do some international business? Travel to Paris, Spain, or Dubai! The airport "
                                + "is a safe zone! It's pretty big. You can watch the planes on the strip getting reading to take off.");
		streets = new Room("The Streets of Detroit", "These streets are dangerous you probably don't want to roaming around here at night. Watch yo self.");
		foxHollowFarm = new Room("Chamber Station", "This elegant Tudor-style farm house comes four furnished bedrooms, indoor swimming pool, and a riding "
	                               + "stable. You have plenty of privacy since it lies on eighteen and half acres of land. Not too shabby right? "
	                               + "Just ignore the fact that the remains of 11 men lie scattered around the land.");
		cleveland = new Room("The Cleveland Strangler's Murder House", "You are currently standing in the Cleveland Strangler's living room. It is here where two bodies were "
	                                                  + "found during the time of the Strangler's arrest. Don't worry he won't be coming for you but someone "
	                                                  + "else could be. There is a couch to the north of the room and a television. The room is pretty empty "
	                                                  + "the Strangler wasn't too keen on indoor decorating.");
		bigRig = new Room("Robert Ben Rhodes' Big Rig", "This may look like a normal Big Rig, but look closer. You probably don�t want to be in this mobile torture chamber.");
		desert = new Room("The Deadly Desert", "I hope you brought plenty of water. And watch out for those scorpions too.  Stay here too long, and yo ass "
	                          + "will suffer death by dehydration.");

		// Set the entrance of the game to be the lawn
		entrance = lawn;

		// Add all the rooms to the list of rooms
		rooms = new ArrayList<Room>();
		rooms.add(lawn);
		rooms.add(bonus);
		rooms.add(woods);
		rooms.add(basement);
		rooms.add(castle);
		rooms.add(farmhouse);
		rooms.add(factory);
		rooms.add(motel);
		rooms.add(hospital);
		rooms.add(dakotaApts);
		rooms.add(kitchen);
		rooms.add(mansonHouse);
		rooms.add(jail);
		rooms.add(policeStation);
		rooms.add(sorority);
		rooms.add(dahmerApt);
		rooms.add(cemetery);
		rooms.add(bank);
		rooms.add(casino);
		rooms.add(adventureLand);
		rooms.add(alley);
		rooms.add(spain);
		rooms.add(paris);
		rooms.add(dubai);
		rooms.add(airport);
		rooms.add(streets);
		rooms.add(foxHollowFarm);
		rooms.add(cleveland);
		rooms.add(bigRig);
		rooms.add(desert);
		
		roomsMap = new ConcurrentHashMap<String, Room>();
		roomsMap.put(lawn.getRoomName(), lawn);
		roomsMap.put(bonus.getRoomName(), bonus);
		roomsMap.put(woods.getRoomName(), woods);
		roomsMap.put(basement.getRoomName(), basement);
		roomsMap.put(castle.getRoomName(), castle);
		roomsMap.put(farmhouse.getRoomName(), farmhouse);
		roomsMap.put(factory.getRoomName(), factory);
		roomsMap.put(motel.getRoomName(), motel);
		roomsMap.put(hospital.getRoomName(), hospital);
		roomsMap.put(dakotaApts.getRoomName(), dakotaApts);
		roomsMap.put(kitchen.getRoomName(), kitchen);
		roomsMap.put(mansonHouse.getRoomName(), mansonHouse);
		roomsMap.put(jail.getRoomName(), jail);
		roomsMap.put(policeStation.getRoomName(), policeStation);
		roomsMap.put(sorority.getRoomName(), sorority);
		roomsMap.put(dahmerApt.getRoomName(), dahmerApt);
		roomsMap.put(cemetery.getRoomName(), cemetery);
		roomsMap.put(bank.getRoomName(), bank);
		roomsMap.put(casino.getRoomName(), casino);
		roomsMap.put(adventureLand.getRoomName(), adventureLand);
		roomsMap.put(alley.getRoomName(), alley);
		roomsMap.put(spain.getRoomName(), spain);
		roomsMap.put(paris.getRoomName(), paris);
		roomsMap.put(dubai.getRoomName(), dubai);
		roomsMap.put(airport.getRoomName(), airport);
		roomsMap.put(streets.getRoomName(), streets);
		roomsMap.put(foxHollowFarm.getRoomName(), foxHollowFarm);
		roomsMap.put(cleveland.getRoomName(), cleveland);
		roomsMap.put(bigRig.getRoomName(), bigRig);
		roomsMap.put(desert.getRoomName(), desert);

		// Set the entrance of the game to be the lawn
		entrance = lawn;
		
		// Add all the rooms to the list of rooms
		
		rooms = new ArrayList<Room>();
		
		rooms.add(lawn);
		rooms.add(bonus);
		rooms.add(woods);
		rooms.add(basement);
		rooms.add(castle);
		rooms.add(farmhouse);
		rooms.add(factory);
		rooms.add(motel);
		rooms.add(hospital);
		rooms.add(dakotaApts);
		rooms.add(kitchen);
		rooms.add(mansonHouse);
		rooms.add(jail);
		rooms.add(policeStation);
		rooms.add(sorority);
		rooms.add(dahmerApt);
		rooms.add(cemetery);
		rooms.add(bank);
		rooms.add(casino);
		rooms.add(adventureLand);
		rooms.add(alley);
		rooms.add(spain);
		rooms.add(paris);
		rooms.add(dubai);
		rooms.add(airport);
		rooms.add(streets);
		rooms.add(foxHollowFarm);
		rooms.add(cleveland);
		rooms.add(bigRig);
		rooms.add(desert);
	}
	
	private void setupRoomLayout()
	{
		lawn.setNorthRoom(castle);
		
		bonus.setNorthRoom(factory);
		
		woods.setNorthRoom(hospital);
		woods.setEastRoom(basement);
		
		basement.setNorthRoom(dakotaApts);
		basement.setEastRoom(castle);
		basement.setWestRoom(woods);
		
		castle.setNorthRoom(kitchen);
		castle.setEastRoom(farmhouse);
		castle.setSouthRoom(lawn);
		castle.setWestRoom(basement);
		
		farmhouse.setNorthRoom(mansonHouse);
		farmhouse.setWestRoom(castle);
		
		factory.setSouthRoom(bonus);
		factory.setEastRoom(motel);
		
		motel.setWestRoom(factory);
		motel.setNorthRoom(jail);
		motel.setEastRoom(hospital);
		
		hospital.setNorthRoom(policeStation);
		hospital.setEastRoom(dakotaApts);
		hospital.setSouthRoom(woods);
		hospital.setWestRoom(motel);
		
		dakotaApts.setNorthRoom(sorority);
		dakotaApts.setEastRoom(kitchen);
		dakotaApts.setSouthRoom(basement);
		dakotaApts.setWestRoom(hospital);
		
		kitchen.setNorthRoom(dahmerApt);
		kitchen.setEastRoom(mansonHouse);
		kitchen.setSouthRoom(castle);
		kitchen.setWestRoom(dakotaApts);
		
		mansonHouse.setNorthRoom(cemetery);
		mansonHouse.setSouthRoom(farmhouse);
		mansonHouse.setWestRoom(kitchen);
		
		jail.setSouthRoom(motel);
		jail.setEastRoom(policeStation);
		
		policeStation.setEastRoom(sorority);
		policeStation.setSouthRoom(hospital);
		policeStation.setWestRoom(jail);
		
		sorority.setNorthRoom(bank);
		sorority.setEastRoom(dahmerApt);
		sorority.setSouthRoom(dakotaApts);
		sorority.setWestRoom(policeStation);
		
		dahmerApt.setNorthRoom(casino);
		dahmerApt.setEastRoom(cemetery);
		dahmerApt.setSouthRoom(mansonHouse);
		dahmerApt.setWestRoom(sorority);
		
		cemetery.setNorthRoom(adventureLand);
		cemetery.setSouthRoom(mansonHouse);
		cemetery.setWestRoom(dahmerApt);
		
		bank.setEastRoom(casino);
		bank.setSouthRoom(sorority);
		
		casino.setNorthRoom(alley);
		casino.setEastRoom(adventureLand);
		casino.setSouthRoom(dahmerApt);
		casino.setWestRoom(bank);
		
		adventureLand.setSouthRoom(cemetery);
		adventureLand.setWestRoom(casino);
		
		alley.setNorthRoom(streets);
		alley.setSouthRoom(casino);
		
		spain.setNorthRoom(airport);
		
		paris.setEastRoom(airport);
		
		dubai.setSouthRoom(airport);
		
		airport.setNorthRoom(dubai);
		airport.setEastRoom(streets);
		airport.setSouthRoom(spain);
		
		streets.setEastRoom(foxHollowFarm);
		streets.setSouthRoom(alley);
		streets.setWestRoom(airport);
		
		foxHollowFarm.setNorthRoom(bigRig);
		foxHollowFarm.setEastRoom(cleveland);
		foxHollowFarm.setWestRoom(streets);
		
		cleveland.setNorthRoom(desert);
		cleveland.setWestRoom(foxHollowFarm);
		
		bigRig.setEastRoom(desert);
		bigRig.setSouthRoom(foxHollowFarm);
		
		desert.setSouthRoom(cleveland);
		desert.setWestRoom(bigRig);
	}
	
	/**
	 * Instantiates all the items and adds them to the list of items.
	 */
	private void instantiateItems()
	{
		// Instantiate all the various items
		water = new EnergyBoostItem("water", "you drink it to stay alive", true, false);
		food = new EnergyBoostItem("food", "you eat it to stay alive", true, false);
		bandaid = new EnergyBoostItem("bandaid", "patch up your wound", true, false);
		aidKit = new EnergyBoostItem("firstaid-kit", "patch up your wound", true, false);
		energyBoost = new EnergyBoostItem("energy-boost", "You look a little tired, use this to increase your energy.", true, false);
		stick = new FightingItem("stick","Use this fine piece of wood to protect yourself in anyway possible. It's more powerful than you think.", true, false, false);
		knife = new FightingItem("knife", "You can stab people with it to stay alive", true, false, false);
		gun = new FightingItem("gun", "Use this to kill enemies/victims.", true, false, false);
		sword = new FightingItem("sword", "Use this sleek piece of weaponry to fight any evil\nMOB's that stand in your way.", true, false, false);
		shovel = new FightingItem("shovel", "Use this to digg wholes or to whack MOB's upside\nthere heads.", true, false, false);
		rope = new FightingItem("rope", "Need to tie up a victim?", true, false, false);
		handcuffs = new ReusableItem("handcuffs", "Use this to save yourself some time. MOB's will struggle to get free from this restraint.", true, false, false);
		flashlight = new ReusableItem("flashlight", "Use this item to light up your night.", true, false, false);
		nightVisionGoggles = new ReusableItem("night-vision-goggles", "Use these to see in dark places to stay alive", true, false, false);
		key = new ReusableItem("key", "Use this to unlock doors to run away to stay alive.", true, false, false);
		money = new ReusableItem("money", "Use this to buy energy boosts", true, true, true); 
		disguise = new ReusableItem("disguise", "This is a rare find. Use this to hide your face from\nyour enemies.", true, true, true); 
		
		// Add all the items to the item list
		items = new ArrayList<Item>();
		items.add(water);
		items.add(food);
		items.add(bandaid);
		items.add(aidKit);
		items.add(energyBoost);
		items.add(stick);
		items.add(knife);
		items.add(gun);
		items.add(sword);
		items.add(shovel);
		items.add(rope);
		items.add(handcuffs);
		items.add(flashlight);
		items.add(nightVisionGoggles);
		items.add(key);
		items.add(money);
		items.add(disguise);
	}
	
	/**
	 * When a new game is started, the items will start out in specific
	 * rooms. This method accomplishes this task.
	 */
	private void addItemsToRooms()
	{
		lawn.addItemToRoom(key);
		lawn.addItemToRoom(shovel);
		
		bonus.addItemToRoom(aidKit);
		bonus.addItemToRoom(gun);
		bonus.addItemToRoom(money);
		bonus.addItemToRoom(energyBoost);
		bonus.addItemToRoom(disguise);
		
		woods.addItemToRoom(stick);
		woods.addItemToRoom(flashlight);
		
		basement.addItemToRoom(money);
		basement.addItemToRoom(nightVisionGoggles);
		
		castle.addItemToRoom(food);
		castle.addItemToRoom(water); 
		castle.addItemToRoom(knife);
		
		farmhouse.addItemToRoom(rope);
		farmhouse.addItemToRoom(shovel);
		
		factory.addItemToRoom(money);
		
		motel.addItemToRoom(energyBoost);
		
		hospital.addItemToRoom(aidKit);
		hospital.addItemToRoom(energyBoost);
		
		dakotaApts.addItemToRoom(sword);
		
		kitchen.addItemToRoom(food);
		kitchen.addItemToRoom(water);
		
		mansonHouse.addItemToRoom(handcuffs);
		
		policeStation.addItemToRoom(energyBoost);
		
		sorority.addItemToRoom(bandaid);
		
		dahmerApt.addItemToRoom(energyBoost);
		
		cemetery.addItemToRoom(shovel);
		
		bank.addItemToRoom(money);
		
		casino.addItemToRoom(money);
		
		streets.addItemToRoom(knife);
		streets.addItemToRoom(gun);
		streets.addItemToRoom(sword);
		
		foxHollowFarm.addItemToRoom(nightVisionGoggles);
		
		cleveland.addItemToRoom(food);
		cleveland.addItemToRoom(water);
		cleveland.addItemToRoom(energyBoost);
	}
	
	public void instantiateMOBS()
	{
		mobs = new ArrayList<MOB>();
		mobMessages = new ArrayList<String>();
		ArrayList<String> mobMessages = new ArrayList<String>();
		this.mobMessages.add("Hello there.");
		this.mobMessages.add("Who are you lookin' at? Do you wanna fight?");
		this.mobMessages.add("Get outta here.");
		this.mobMessages.add("Want to go for a ride in my van?");
		this.mobMessages.add("mwahahahhaha");
		this.mobMessages.add("Prepare to die.");
		this.mobMessages.add("You would look good tied up in my basement.");
		jefferyDahmer = new MOB("Jeffery Dahmer", new ArrayList<Item>());
		lawrenceBittaker = new MOB("Lawrence Bittaker", new ArrayList<Item>());
		royNorris = new MOB("Roy Norris", new ArrayList<Item>());
		richardRamirez = new MOB("Richard Ramirez", new ArrayList<Item>());
		andreiChikatilo = new MOB("Andrei Chikatilo", new ArrayList<Item>());
		richardTrentonChase = new MOB("Richard Trenton Chase", new ArrayList<Item>());
		henryLeeLucas = new MOB("Henry Lee Lucas", new ArrayList<Item>());
		edGein = new MOB("Ed Gein", new ArrayList<Item>());
		hannibalLecter = new MOB("Hannibal Lecter", new ArrayList<Item>());
		henryHowardHolmes = new MOB("Henry Howard Holmes", new ArrayList<Item>());
		
		//victim = new MOB("Victim", new ArrayList<Item>());
		//foodStandGuy = new MOB("Food Stand Guy", new ArrayList<Item>());
		
		
		mobs.add(jefferyDahmer);
		mobs.add(lawrenceBittaker);
		mobs.add(royNorris);
		mobs.add(richardRamirez);
		mobs.add(andreiChikatilo);
		mobs.add(richardTrentonChase);
		mobs.add(henryLeeLucas);
		mobs.add(edGein);
		mobs.add(hannibalLecter);
		mobs.add(henryHowardHolmes);
	}
	
	public void addMOBSToRoom()
	{
		dahmerApt.addMOBToRoom(jefferyDahmer);
		lawn.addMOBToRoom(royNorris);
		lawn.addMOBToRoom(lawrenceBittaker);
		streets.addMOBToRoom(richardRamirez);
		spain.addMOBToRoom(andreiChikatilo);
		woods.addMOBToRoom(richardTrentonChase);
		factory.addMOBToRoom(henryLeeLucas);
		farmhouse.addMOBToRoom(edGein);
		kitchen.addMOBToRoom(hannibalLecter);
		castle.addMOBToRoom(henryHowardHolmes);
		
	}

	public boolean playersIsOnline(String recipient) 
	{
		for (String username: playersOnline)
		{
			if (username.equalsIgnoreCase(recipient))
				return true;
		}
		return false;
	}

	public boolean playerHasItem(String username, String itemName) 
	{
		Player player = playerAccounts.get(username);
		
		return player.hasItem(itemName);
	}

	public MOB getMOBFromName(String argument) {
		
		switch(argument.toLowerCase()){
			case "jeffery dahmer":
			case "jeffery":
			case "dahmer":
				return mobs.get(0);
			case "lawrence bittaker":
			case "lawrence":
			case "bittaker":
				return mobs.get(1);
			case "roy norris":
			case "roy":
			case "norris":
				return mobs.get(2);
			case "richard ramirez":
			case "ramirez":
			case "night stalker":
				return mobs.get(3);
			case "andre chikatilo":
			case "andre":
			case "chikatilo":
			case "red ripper":
				return mobs.get(4);
			case "richard trenton chase":
			case "richard chase":
			case "chase":
				return mobs.get(5);
			case "henry lee lucus":
			case "henry lucus":
			case "lucus":
				return mobs.get(6);
			case "ed gein":
			case "ed":
			case "gein":
			case "psycho":
				return mobs.get(7);
			case "hannibal lecter":
			case "hannibal":
			case "lecter":
				return mobs.get(8);
			case "henry howard holmes":
			case "holmes":
			case "hhh":
				return mobs.get(9);
			default:
				break;
		}
		return null;
	}
	
	public String completeTransaction(String recipientName) 
	{
		// First get players involved in transaction
		String senderName = getSenderOfRequest(recipientName);
		Player senderOfRequest = playerAccounts.get(senderName);
		Player recipientOfRequest = playerAccounts.get(recipientName);
		
		String typeOfTransaction = new String();
		String tradingItemName = new String();
		
		// If it was a give request, then the sender would have a reference to the item being
		// transferred
		if (senderOfRequest.hasTradingItem())
		{
			// Update type of transaction
			typeOfTransaction = "give";
			
			// Get item from sender
			tradingItemName = senderOfRequest.getTradingItem();
			Item item = senderOfRequest.removeItemFromBackpack(tradingItemName);
			
			// Give item to recipient
			recipientOfRequest.pickUpItem(item);
			recipientOfRequest.itemReceived(tradingItemName);
		}
		
		// If it was a get request, then the recipient would have a reference to the item being
		// transferred
		else
		{
			// Update type of transaction
			typeOfTransaction = "get";
			
			// Get item from recipient
			tradingItemName = recipientOfRequest.getTradingItem();
			Item item = recipientOfRequest.removeItemFromBackpack(tradingItemName);
			
			// Give item to sender
			senderOfRequest.pickUpItem(item);
			senderOfRequest.itemReceived(tradingItemName);
			
		}
		
		// Reset the fields related to the transaction. This way, they can now trade again
		senderOfRequest.resetTradeFields();
		recipientOfRequest.resetTradeFields();
		
		// Push the updated player accounts back onto the HashMap
		playerAccounts.put(senderName, senderOfRequest);
		playerAccounts.put(recipientName, recipientOfRequest);
		
		// Return type of transaction
		return typeOfTransaction;
	}
	
	public String cancelTransaction(String recipientName) 
	{
		// First get players involved in transaction
		String senderName = getSenderOfRequest(recipientName);
		Player senderOfRequest = playerAccounts.get(senderName);
		Player recipientOfRequest = playerAccounts.get(recipientName);
				
		String typeOfTransaction = new String();
		String tradingItemName = new String();
		
		// If it was a give request, then the sender would have a reference to the item being
		// transferred
		if (senderOfRequest.hasTradingItem())
		{
		   // Update type of transaction
			typeOfTransaction = "give";
		}
				
		// If it was a get request, then the recipient would have a reference to the item being
		// transferred
		else
		{
			// Update type of transaction
			typeOfTransaction = "get";	
		}
				
		// Reset the fields related to the transaction. This way, they can now trade again
		senderOfRequest.resetTradeFields();
		recipientOfRequest.resetTradeFields();
				
		// Push the updated player accounts back onto the HashMap
		playerAccounts.put(senderName, senderOfRequest);
		playerAccounts.put(recipientName, recipientOfRequest);
				
		// Return type of transaction
		return typeOfTransaction;
	}

	public void setSenderOfRequest(String recipientName, String senderName) 
	{
		Player recipient  = playerAccounts.get(recipientName);
		recipient.setSenderOfRequest(senderName);
		playerAccounts.put(recipientName, recipient);
	}

	public void setTradingItem(String recipientName, String itemName) 
	{
		Player recipient  = playerAccounts.get(recipientName);
		recipient.setTradingItem(itemName);
		playerAccounts.put(recipientName, recipient);
	}

	public boolean transactionPending(String recipientName) 
	{
		Player recipient = playerAccounts.get(recipientName);
		
		return recipient.transactionPending();
	}

	public String getSenderOfRequest(String recipientName) 
	{
		Player recipient = playerAccounts.get(recipientName);
		
		return recipient.senderOfRequest();
	}

	public String getItemTransferred(String username) 
	{
		Player player = playerAccounts.get(username);
		
		String itemName = player.getNameOfItemReceived();
		player.resetNameOfItemReceived();
		
		playerAccounts.put(username, player);
		
		return itemName;
	}

	public void setPendingGiveTransaction(String recipientName, String senderName, String itemName) 
	{
		Player senderOfRequest = playerAccounts.get(senderName);
		Player recipientOfRequest = playerAccounts.get(recipientName);
		
		recipientOfRequest.setSenderOfRequest(senderName);
		senderOfRequest.setTradingItem(itemName);
		
		recipientOfRequest.setTransactionPending();
		senderOfRequest.setTransactionPending();
		
		playerAccounts.put(senderName, senderOfRequest);
		playerAccounts.put(recipientName, recipientOfRequest);
	}
	
	public void setPendingGetTransaction(String recipientName, String senderName, String itemName) 
	{
		Player senderOfRequest = playerAccounts.get(senderName);
		Player recipientOfRequest = playerAccounts.get(recipientName);
		
		recipientOfRequest.setSenderOfRequest(senderName);
		recipientOfRequest.setTradingItem(itemName);
		
		recipientOfRequest.setTransactionPending();
		senderOfRequest.setTransactionPending();
		
		playerAccounts.put(senderName, senderOfRequest);
		playerAccounts.put(recipientName, recipientOfRequest);
	}

	public Item getItemFromName(String argument) {
		for(Item i : items){
			if(i.getName().equalsIgnoreCase(argument)){
				return i;
			}
		}
		return null;
	}

	public void resetPlayersOnline() 
	{
		playersOnline = new ArrayList<String>();
	}
} // end of class SerialKillerMud
