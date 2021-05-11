package new_create_app_name_battler.strategy;

import java.util.List;

import new_create_app_name_battler.party.BasePlayer;

public interface IStrategy {

	  int attackStrategy(BasePlayer player1, List<BasePlayer> party1,
			List<BasePlayer> party2);
}

