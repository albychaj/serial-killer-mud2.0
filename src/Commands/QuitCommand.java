package Commands;

import Controller.Client;

public class QuitCommand extends Command<Client>
{
	private static final long serialVersionUID = -5232758634055430417L;

	public void execute(Client executeOn)
	{
		executeOn.closeByInput();
	}
} // end of class QuitCommand
