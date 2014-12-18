package Commands;

import Controller.Client;
import Items.Item;

public class LookItemCommand extends Command<Client>
{
	private static final long serialVersionUID = 1232626470258058254L;
	private Item item;

	public LookItemCommand(Item item)
	{
		this.item = item;
	}
	
	@Override
	public void execute(Client executeOn)
	{
		executeOn.lookAtItemInRoom(item);
	}
} // end of class LookItemCommand
