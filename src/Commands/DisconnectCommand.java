package Commands;

import Controller.Server;

/**
 * This command is sent by a client that is disconnecting
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public class DisconnectCommand extends Command<Server>
{
	private static final long serialVersionUID = 5769509282342294151L;
	private String clientName; // client who is disconnecting
	
	/**
	 * Creates a disconnect command for the given client
	 * 
	 * @param username Username of client to disconnect
	 */
	public DisconnectCommand(String username)
	{
		clientName = username;
	}

	@Override
	public void execute(Server executeOn) 
	{
		executeOn.disconnect(clientName);
	}

}
