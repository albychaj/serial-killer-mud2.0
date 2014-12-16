package Commands;

import Controller.Client;

public class GiveRequestSentCommand extends Command<Client> 
{
	private static final long serialVersionUID = -6461992092018201752L;
	private String recipient;
	private String itemName;

	public GiveRequestSentCommand(String recipient, String itemName) 
	{
		this.recipient = recipient;
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.sentGiveRequest(recipient, itemName);
	}

}
