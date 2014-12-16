package Commands;

import java.io.Serializable;

/**
 * This abstract class defines a serializable command that can be sent
 * and executed on either a client or server.
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite-Wolf-Squadron
 *
 */
public abstract class Command<T> implements Serializable
{
	private static final long serialVersionUID = -4108090210147391566L;
	
	/**
	 * Executes the command on the given argument.
	 * 
	 * @param executeOn Object to execute command on
	 */
	public abstract void execute(T executeOn);
} // end of abstract class Command
