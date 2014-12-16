package Commands;

import Controller.Client;

public class CommandErrorCommand extends Command<Client> 
{
	private static final long serialVersionUID = -4619271238038593096L;

	public void execute(Client executeOn) 
	{
		executeOn.CommandError();
	}
} // end of class CommandErrorCommand
