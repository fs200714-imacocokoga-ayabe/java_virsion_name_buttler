package new_create_app_name_battler.strategy;

import java.util.List;

import new_create_app_name_battler.party.IPlayer;

public class Context {

	IStrategy istrategy;
	IPlayer player1 = null;
	IPlayer player2 = null;
	List<IPlayer> party1 = null;
	List<IPlayer> party2 = null;

	public Context(IStrategy istrategy) {

		this.istrategy = istrategy;
	}

	public void attackStrategy(IPlayer player1, List<IPlayer> party1, List<IPlayer> party2) {

		this.istrategy.attackStrategy(player1, party1, party2);
	}
}
