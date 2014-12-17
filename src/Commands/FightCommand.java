package Commands;

import Controller.Client;
import MOBs.MOB;
import Players.Player;

public class FightCommand extends Command<Client>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -323416027927576365L;
	private MOB argument;
	private Player player;
	private Boolean isGoingToFight;
	
	public FightCommand(MOB opponent, Player player, Boolean isGoingToFight){
		this.argument = opponent;
		this.player = player;
		this.isGoingToFight = isGoingToFight;
	}

	@Override
	public void execute(Client executeOn) {
		executeOn.fight(argument, player, isGoingToFight);
	}

}
