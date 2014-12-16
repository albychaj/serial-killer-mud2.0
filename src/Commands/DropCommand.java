package Commands;

import Controller.Client;

public class DropCommand extends Command<Client>
{
	private static final long serialVersionUID = -6239777504515291261L;
	private String itemName; 
	
	public DropCommand(String itemName)
	{
		this.itemName = itemName;
	}
	
	public void execute(Client executeOn)
	{
		executeOn.dropItem(itemName);
	}
} // end of class DropCommand
