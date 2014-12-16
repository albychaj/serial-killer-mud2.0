package Commands;

import Controller.Client;

public class GetRequestRecievedCommand extends Command<Client> 
{
	private static final long serialVersionUID = 506556991127968920L;
	private String sender;
	private String itemName;

	public GetRequestRecievedCommand(String sender, String itemName) 
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
