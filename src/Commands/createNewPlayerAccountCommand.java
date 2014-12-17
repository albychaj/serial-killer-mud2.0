package Commands;

import Controller.Server;
import Players.Player;

public class createNewPlayerAccountCommand extends Command<Server>
{
	private static final long serialVersionUID = -7565198951850071747L;
	private Player newPlayer;

	public createNewPlayerAccountCommand(Player newPlayer) 
	{
		this.newPlayer = newPlayer;
	}

	@Override
	public void execute(Server executeOn) 
	{
		executeOn.AddNewPlayerToTheGame(newPlayer);
	}
}
