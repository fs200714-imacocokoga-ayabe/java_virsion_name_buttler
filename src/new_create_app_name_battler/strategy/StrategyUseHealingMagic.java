package new_create_app_name_battler.strategy;

import java.util.List;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.party.BasePlayer;

public class StrategyUseHealingMagic extends BaseStrategy {// 回復優先の作戦

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {

    if (0 < party2.size()) {
      player2 = party2.get(random.nextInt(party2.size()));
    }

    if (player1 instanceof IRecoveryMagic) {
      player = player1;

      for (int i = 0; i < party1.size(); i++) {// HPの低い味方を選ぶ

        if ((player.getMaxHp() - player.getHp() < party1.get(i).getMaxHp() - party1.get(i).getHp())) {
          player = party1.get(i);
        }
      }
      player1.healingMagic(player);

    } else {// player1が僧侶ではない場合
      player1.normalAttack(player2);
    }

    return player2.getIdNumber();
  }
}
