package Commands;

import Controller.Server;
import Enums.Commands;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class ForServerCommand extends Command<Server>
{
	private static final long serialVersionUID = -4617032470436109312L;
	private String clientName;
	private Commands command;
	private String argument;
	
	public ForServerCommand(String clientName, Commands command, String argument)
	{
		this.clientName = clientName;
		this.command = command;
		this.argument = argument;
	}

	@Override
	public void execute(Server executeOn) 
	{
		executeOn.PrintToClient(clientName, command, argument);
	}
} // end of class ForServerCommand
