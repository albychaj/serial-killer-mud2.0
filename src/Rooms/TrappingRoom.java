package Rooms;

import java.util.List;
import java.util.Random;

import MOBs.MOB;
import Players.Player;

public class TrappingRoom extends Room 
{
	//need a rooom abstract class with TR and SR subclasses
	private static final long serialVersionUID = 4686921958139749488L;
	private String name;
	private String description;
	private List<Player> players;
	private List<MOB> mobs;
	private SceneRoom onlyWayOut;
	
	
	public TrappingRoom(String name, String description, SceneRoom onlyWayOut)
	{
		super(name, description);
		this.onlyWayOut = onlyWayOut;
		
	}
	//the room is escapable if the randomly generated value is
	//even. If it's not escapable, they will die in the room.
	public boolean canEscape()
	{
		Random ran = new Random();
		int generatedVal = ran.nextInt(10);
		if(generatedVal % 2 == 0)
		{
			return true;
		}
		else
			return false;
	}
//	@Override
//	public Player removePlayerFromRoom(Player player)
//	{
//		if(canEscape())
//		{
//			players.remove(player);
//		}
//		else
//		{
//			//you lose?
//		}
//		return player;
//	}
}
