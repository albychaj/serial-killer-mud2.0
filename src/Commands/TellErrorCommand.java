package Commands;

import Controller.Client;

public class TellErrorCommand extends Command<Client>
{
	private static final long serialVersionUID = -1850108792796700430L;

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.tellError();
	}
} // end of class TellErrorCommand
