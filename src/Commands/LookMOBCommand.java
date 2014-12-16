package Commands;

import Controller.Client;
import MOBs.MOB;

public class LookMOBCommand extends Command<Client>
{
	private static final long serialVersionUID = -6081758707850390721L;
	private MOB mob;
	
	public LookMOBCommand(MOB mob)
	{
		this.mob = mob;
	}

	public void execute(Client executeOn)
	{
		executeOn.lookAtMOBInRoom(mob);
	}
} // end of class LookMOBCommand
