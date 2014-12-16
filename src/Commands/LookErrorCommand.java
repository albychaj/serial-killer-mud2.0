package Commands;

import Controller.Client;

public class LookErrorCommand extends Command<Client>
{
	private static final long serialVersionUID = -3278728470244955855L;

	public void execute(Client executeOn) 
	{
		executeOn.lookError();
	}
} // end of class LookErrorCommand
