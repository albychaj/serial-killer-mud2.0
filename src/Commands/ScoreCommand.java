package Commands;

import Controller.Client;
import Players.Player;

public class ScoreCommand extends Command<Client>
{
	private static final long serialVersionUID = 1L;
	private Player playa;
	
	public ScoreCommand(Player playa){
		this.playa = playa;
	}

	public void execute(Client executeOn)
	{
		executeOn.listScore(playa);
	}
} // end of class ScoreCommand
