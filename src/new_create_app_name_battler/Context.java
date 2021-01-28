package new_create_app_name_battler;

import java.util.List;

public class Context {

	IStrategy istrategy;
	IPlayer player1 = null;
	IPlayer player2 = null;
	List<IPlayer> party1 = null;
	List<IPlayer> party2 = null;

	public Context(IStrategy istrategy) {

		this.istrategy = istrategy;
	}

	public int[] attackStrategy(IPlayer player1, List<IPlayer> party1, List<IPlayer> party2) {

		return this.istrategy.attackStrategy(player1, party1, party2);
	}
}
