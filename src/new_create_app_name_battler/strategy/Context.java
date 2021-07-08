package new_create_app_name_battler.strategy;

import java.util.List;
import new_create_app_name_battler.party.BasePlayer;

public class Context {

  IStrategy istrategy;
  BasePlayer player1 = null;
  BasePlayer player2 = null;
  List<BasePlayer> party1 = null;
  List<BasePlayer> party2 = null;

  public Context(IStrategy istrategy) {

    this.istrategy = istrategy;
  }

  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {
    return this.istrategy.attackStrategy(player1, party1, party2);
  }
}
