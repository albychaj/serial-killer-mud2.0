package Commands;

import Controller.Client;

public class RequestDeniedCommand extends Command<Client> 
{
	private static final long serialVersionUID = 1500287707152848312L;
	private String recipient;

	public RequestDeniedCommand(String recipient) 
	{
		this.recipient = recipient;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.requestRejected(recipient);
	}

}
