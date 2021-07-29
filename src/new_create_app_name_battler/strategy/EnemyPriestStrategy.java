package new_create_app_name_battler.strategy;

import java.util.List;
import new_create_app_name_battler.party.BasePlayer;

public class EnemyPriestStrategy extends StrategyEnemyPattern {

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> playerParty,
      List<BasePlayer> aiParty) {

    this.playerParty.addAll(playerParty);
    this.aiParty.addAll(aiParty);
    this.player2 = selectLowerHP();// HPの低い相手を呼び出す
    this.player = lifeImpotance();// HPの低い味方を入れる

    if (player1.isPoison()) {// playerが毒状態の場合

      player1.eat();

    } else if (player1.getHp() < (player1.getMaxHp() / 4)) {

      player1.eat();

    } else if (PRIEST_MP <= player1.getMp()) {

      if(0 == random.nextInt(2)){

      player1.magicAttack(player2);// 魔法を使う

      }else{

        player1.healingMagic(player);
      }

    } else if (player2.getDef() < (player1.getStr() / 2)) {

      player1.normalAttack(player2);

    } else {

      player1.skillAttack(player2);

    }

    id = player2.getIdNumber();

    return id;
  }
}
