package Commands;

import java.util.List;

import Controller.Client;

public class WhoCommand extends Command<Client>
{
	private static final long serialVersionUID = -8978150445909455283L;
	private String players;

	public WhoCommand(List<String> playersLoggedIn) 
	{
		players = new String();
		
		for (String player: playersLoggedIn)
			players += player + "\n";
	}

	public void execute(Client executeOn) 
	{
		executeOn.listWho(players);
	}
} // end of class WhoCommand
