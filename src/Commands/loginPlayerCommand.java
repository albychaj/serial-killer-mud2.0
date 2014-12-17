package Commands;

import Controller.Server;

public class loginPlayerCommand extends Command<Server>
{
	private static final long serialVersionUID = 7793108927529676523L;
	private String username;

	public loginPlayerCommand(String username) 
	{
		this.username = username;
	}

	@Override
	public void execute(Server executeOn) 
	{
		executeOn.LoginExistingPlayer(username);
	}

}
