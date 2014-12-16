package Commands;

import Controller.Client;

public class TradeRequestSentCommand extends Command<Client> 
{
	private static final long serialVersionUID = -6461992092018201752L;
	private String recipient;
	private String itemName;

	public TradeRequestSentCommand(String recipient, String itemName) 
	{
		this.recipient = recipient;
		this.itemName = itemName;
	}

	@Override
	public void execute(Client executeOn) 
	{
		executeOn.tradeRequestSent(recipient, itemName);
	}

}
