package Commands;

import Controller.Client;

public class MoveCommand extends Command<Client>
{
	private static final long serialVersionUID = -1921247521510499218L;
	private String roomDescription;
	
	public MoveCommand(String roomDescription)
	{
		this.roomDescription = roomDescription;
	}
	
	@Override
	public void execute(Client executeOn)
	{
		executeOn.movePlayer(roomDescription);
	}
} // end of class MoveCommand
