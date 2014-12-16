package Commands;

import Controller.Client;

public class MoveErrorCommand extends Command<Client> 
{
	public void execute(Client executeOn) 
	{
		executeOn.moveError();
	}

}
