package Commands;

import Controller.Server;

public class UpdateFightStatsCommand extends Command<Server>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1553233134811765427L;
	private String player;
	private String result;
	
	public UpdateFightStatsCommand(String p, String result){
		this.player = p;
		this.result = result;
	}

	@Override
	public void execute(Server executeOn) {
		executeOn.updateFightResults(player, result);
	}

}
