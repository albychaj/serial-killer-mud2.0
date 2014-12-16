package Commands;

import Controller.Client;

/**
 * Updates a client with the current log of chat messages
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class UpdateChatLogCommand extends Command<Client> 
{
	private static final long serialVersionUID = 4222014184904080846L;
	private String chatMessage;
	
	/**
	 * Creates a new UpdateClientCommand with the given log of messages
	 * 
	 * @param chatMessage The current log of messages
	 */
	public UpdateChatLogCommand(String chatMessage)
	{
		this.chatMessage = chatMessage;
	}
	
	/**
	 * Updates the client
	 */
	public void execute(Client executeOn) 
	{
		executeOn.updateChatLog(chatMessage);
	}
}
