package Commands;

import Controller.Client;

public class GetRequestReceivedCommand extends Command<Client> 
{
	private static final long serialVersionUID = 506556991127968920L;
	private String sender;
	private String itemName;

	public GetRequestReceivedCommand(String sender, String itemName) 
	{
		this.sender = sender;
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.receivedGetRequest(sender, itemName);
	}

}
