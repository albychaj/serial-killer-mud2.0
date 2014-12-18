package Commands;

import Controller.Client;

public class MOBGetErrorCommand extends Command<Client> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6535558938477802564L;
	private String recipient;
	
	public MOBGetErrorCommand(String recip){
		this.recipient = recip;
	}

	@Override
	public void execute(Client executeOn) {
		executeOn.getFromMOBError(recipient);
	}

}
