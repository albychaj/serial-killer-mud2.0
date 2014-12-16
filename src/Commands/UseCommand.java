package Commands;

import Controller.Client;
import Players.Player;

public class UseCommand extends Command<Client>
{
	private static final long serialVersionUID = -3475690984149712433L;
	private String argument;
	private Player playah;
	
	public UseCommand(String argument, Player playah)
	{
		this.argument = argument;
		this.playah = playah;
	}
	
	public void execute(Client executeOn)
	{
		executeOn.useItem(argument, playah);
	}
} // end of class UseCommand
