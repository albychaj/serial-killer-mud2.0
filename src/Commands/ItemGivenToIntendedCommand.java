package Commands;

import Controller.Client;

public class ItemGivenToIntendedCommand extends Command<Client> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3925344813199174947L;
	private String recipient;
	private String itemName;

	public ItemGivenToIntendedCommand(String recipient, String itemName) 
	{
		this.recipient = recipient;
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.itemAccepted(recipient, itemName);
	}

}
