package Commands;

import Controller.Client;

public class AcceptedItemCommand extends Command<Client> 
{
	private static final long serialVersionUID = 7422635713461642401L;
	private String sender;
	private String itemName;

	public AcceptedItemCommand(String sender, String itemName) 
	{
		this.sender = sender;
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.acceptedItem(sender, itemName);
	}

}
