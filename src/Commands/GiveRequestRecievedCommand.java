package Commands;

import Controller.Client;

public class GiveRequestRecievedCommand extends Command<Client> 
{
	private static final long serialVersionUID = 1421651418209302958L;
	private String sender;
	private String itemName;

	public GiveRequestRecievedCommand(String sender, String itemName) 
	{
		this.sender = sender;
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.receivedGiveRequest(sender, itemName);
	}
} // end of class ReceiveGiveRequestCommand
