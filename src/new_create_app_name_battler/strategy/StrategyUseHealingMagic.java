package new_create_app_name_battler.strategy;

import java.util.ArrayList;
import java.util.List;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.party.BasePlayer;

public class StrategyUseHealingMagic extends BaseStrategy {// 回復優先の作戦

  List<BasePlayer> _party1 = new ArrayList<BasePlayer>();
  List<BasePlayer> _party2 = new ArrayList<BasePlayer>();

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {

    if (player1.isMark()) {// player1がtrueの場合
      _party1.addAll(party1);// _party1にparty1を入れる
      _party2.addAll(party2);// _party2にparty2を入れる

    } else {// player1がfalseの場合
      _party1.addAll(party2);// party1にparty2を入れる
      _party2.addAll(party1);// party2にparty1を入れる
    }

    if (0 < _party2.size()) {
      player2 = _party2.get(random.nextInt(_party2.size()));
      id = player2.getIdNumber();
    }

    if (player1 instanceof IRecoveryMagic) {
      player = player1;

      for (int i = 0; i < _party1.size(); i++) {// HPの低い味方を選ぶ

        if ((player.getMaxHp() - player.getHp() < _party1.get(i).getMaxHp()
            - _party1.get(i).getHp())) {
          player = _party1.get(i);
        }
      }
      player1.healingMagic(player);

    } else {// player1が僧侶ではない場合
      player1.normalAttack(player2);
    }
    _party1.clear();// _party1をクリア
    _party2.clear();// _party2をクリア

    return id;
  }
}
