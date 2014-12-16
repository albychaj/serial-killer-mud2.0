package Commands;

import Controller.Client;

public class RejectionSentCommand extends Command<Client> 
{
	private static final long serialVersionUID = -6343609745034181481L;
	private String senderToReject;

	public RejectionSentCommand(String senderToReject) 
	{
		this.senderToReject = senderToReject;
	}

	public void execute(Client executeOn) 
	{
		executeOn.rejectionSent(senderToReject); 
	}
}
