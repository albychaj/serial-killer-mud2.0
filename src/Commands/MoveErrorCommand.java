package Commands;

import Controller.Client;

public class MoveErrorCommand extends Command<Client> 
{
	@Override
	public void execute(Client executeOn) 
	{
		executeOn.moveError();
	}

}
