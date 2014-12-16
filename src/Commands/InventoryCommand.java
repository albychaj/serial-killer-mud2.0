package Commands;

import java.util.List;

import Controller.Client;

public class InventoryCommand extends Command<Client>
{
	private static final long serialVersionUID = -4184462915452723529L;
	private List<String> playersInventory;

	public InventoryCommand(List<String> playersInventory) 
	{
		this.playersInventory = playersInventory;
	}

	public void execute(Client executeOn)
	{
		executeOn.listInventory(playersInventory);
	}
} // end of class InventoryCommand
