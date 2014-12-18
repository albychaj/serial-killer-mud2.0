package Commands;

import Controller.Client;

public class LookMOBCommand extends Command<Client>
{
	private static final long serialVersionUID = -6081758707850390721L;
	private String mobName;
	
	public LookMOBCommand(String mobName)
	{
		this.mobName = mobName;
	}

	public void execute(Client executeOn)
	{
		executeOn.lookAtMOBInRoom(mobName);
	}
} // end of class LookMOBCommand
