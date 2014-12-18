package Commands;

import Controller.Client;

public class MapCommand extends Command<Client>
{
	private static final long serialVersionUID = 563594890061230301L;

	@Override
	public void execute(Client executeOn)
	{
		executeOn.showMap();
	}
} // end of class MapCommand
