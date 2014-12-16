package Commands;

import Controller.Client;

public class CommandsCommand extends Command<Client>
{
	private static final long serialVersionUID = 6501790673164686410L;

	public void execute(Client executeOn)
	{
		executeOn.listCommands();
	}
} // end of class CommandsCommand
