package new_create_app_name_battler.strategy;

import java.util.List;
import new_create_app_name_battler.party.BasePlayer;

public class EnemyFighterStrategy extends StrategyEnemyPattern {

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> playerParty,
      List<BasePlayer> aiParty) {

    this.playerParty.addAll(playerParty);
    this.aiParty.addAll(aiParty);
    this.player2 = selectLowerHP();// HPの低い相手を呼び出す

    if (player1.isPoison()) {// 毒状態の場合

      player1.eat();

      // 攻撃力の1/2が相手の防御力より大きい場合
    } else if (player2.getDef() < (player1.getStr() / 2)) {

      player1.normalAttack(player2);

    } else {// 防御力の方が大きい場合

      player1.skillAttack(player2);

    }
    return player2.getIdNumber();// 自身のIDと相手playerID
  }
}
