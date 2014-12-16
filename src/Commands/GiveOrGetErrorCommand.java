package Commands;

import Controller.Client;

public class GiveOrGetErrorCommand extends Command<Client> 
{
	private static final long serialVersionUID = 5552393075139132823L;

	public void execute(Client executeOn) 
	{
		executeOn.giveOrGetError();
	}

}
