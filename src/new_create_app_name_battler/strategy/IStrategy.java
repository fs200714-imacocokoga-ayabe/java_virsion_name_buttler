package new_create_app_name_battler.strategy;

import java.util.List;

import new_create_app_name_battler.party.IPlayer;

public interface IStrategy {

	  int attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2);
}

