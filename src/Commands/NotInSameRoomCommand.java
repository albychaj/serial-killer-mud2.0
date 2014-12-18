package Commands;

import Controller.Client;

public class NotInSameRoomCommand extends Command<Client> 
{
	private static final long serialVersionUID = 76210393094634370L;

	public void execute(Client executeOn) 
	{
		executeOn.notInSameRoomError();
	}

}
