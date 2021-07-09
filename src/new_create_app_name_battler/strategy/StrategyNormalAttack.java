package new_create_app_name_battler.strategy;

import java.util.List;
import new_create_app_name_battler.party.BasePlayer;

public class StrategyNormalAttack extends BaseStrategy {// 通常攻撃

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {

    this.player1 = player1;

    if (player1.isMark()) {// player1がtrueの場合

      party.addAll(party2);// partyにparty2を入れる

    } else {// player1がfalseの場合

      party.addAll(party1);// partyにparty1を入れる
    }

    player2 = party.get(random.nextInt(party.size()));

    id = player2.getIdNumber();

    player1.normalAttack(player2);

    party.clear();

    return id;
  }
}
