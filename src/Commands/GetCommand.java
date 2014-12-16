package Commands;

import Controller.Client;

public class GetCommand extends Command<Client>
{
	private static final long serialVersionUID = 853680469911913921L;
	private String itemName;
	
	public GetCommand(String itemName)
	{
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn)
	{
		executeOn.pickUp(itemName);
	}
} // end of class GetCommand
