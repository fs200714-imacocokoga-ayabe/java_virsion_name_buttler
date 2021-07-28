package new_create_app_name_battler.strategy;

import java.util.List;
import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.party.BasePlayer;

public class StrategyUseMagic extends BaseStrategy {// 魔法を使用

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {

    this.player1 = player1;

    if (player1.isMark()) {// player1がtrueの場合
      party.addAll(party2);// partyにparty2を入れる

    } else {// player1がfalseの場合
      party.addAll(party1);// partyにparty1を入れる
    }
    player2 = party.get(random.nextInt(party.size()));
   
    if (player1 instanceof IOwnMagic) {
      player1.magicAttack(player2);

    } else {
      player1.normalAttack(player2);
    }
    party.clear();

    return player2.getIdNumber();
  }
}
