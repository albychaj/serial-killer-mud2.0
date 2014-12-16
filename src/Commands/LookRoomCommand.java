package Commands;

import Controller.Client;

public class LookRoomCommand extends Command<Client>
{
	private static final long serialVersionUID = 4750185401178793253L;
	private String roomDescription;

	public LookRoomCommand(String room)
	{
		this.roomDescription = room;
	}
	public void execute(Client executeOn)
	{
		executeOn.lookAtWholeRoom(roomDescription);
	}
} // end of class LookCommand
