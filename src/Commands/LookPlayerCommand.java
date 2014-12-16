package Commands;

import Controller.Client;
import Players.Player;

public class LookPlayerCommand extends Command<Client>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7452266257612201635L;
	private Player player;
	
	public LookPlayerCommand(Player player)
	{
		this.player = player;
	}
	
	public void execute(Client executeOn)
	{
		executeOn.lookAtPlayerInRoom(player);
	}
} // end of class LookPlayerCommand
