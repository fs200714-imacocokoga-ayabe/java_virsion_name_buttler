package new_create_app_name_battler;

import java.util.List;

public interface IStrategy {

	public int[] attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2);
}
