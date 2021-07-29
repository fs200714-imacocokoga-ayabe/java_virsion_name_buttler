package new_create_app_name_battler.strategy;

import java.util.ArrayList;
import java.util.List;
import new_create_app_name_battler.party.BasePlayer;
import new_create_app_name_battler.party.IFighter;
import new_create_app_name_battler.party.INinja;
import new_create_app_name_battler.party.IPriest;
import new_create_app_name_battler.party.IWizard;

 public class StrategyEnemyPattern extends BaseStrategy {

  Context enemyContext;
  protected List<BasePlayer> playerParty = new ArrayList<BasePlayer>();
  protected List<BasePlayer> aiParty = new ArrayList<BasePlayer>();
  protected BasePlayer player2;

  final int NINJA_MP = 10;
  final int PRIEST_MP = 10;
  final int HEAL_MP = 20;

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {
    this.player1 = player1;
    playerParty.addAll(party1);
    aiParty.addAll(party2);

    if (player1 instanceof IFighter) {

      enemyContext = new Context(new EnemyFighterStrategy());

    } else if (player1 instanceof IWizard) {

      enemyContext = new Context(new EnemyWizardStrategy());

    } else if (player1 instanceof IPriest) {

      enemyContext = new Context(new EnemyPriestStrategy());

    } else if (player1 instanceof INinja) {

      enemyContext = new Context(new EnemyNinjaStrategy());

    }
    return enemyContext.attackStrategy(player1, playerParty, aiParty);
  }

  public BasePlayer selectLowerHP() {

    player2 = playerParty.get(0);// 敵パーティから1人player2に入れる

    for (int i = 1; i < playerParty.size(); i++) {

      if (playerParty.get(i).getHp() < player2.getHp()) {// player2よりHPが低い場合

        player2 = playerParty.get(i);// HPのひくい敵をplayer2に入れる
      }
    }
    playerParty.clear();

    return player2;
  }

  public BasePlayer lifeImpotance() {

    player = aiParty.get(0);

    for (int i = 0; i < aiParty.size(); i++) {// HPの低い味方を選ぶ

      if ((player.getMaxHp() - player.getHp() < aiParty.get(i).getMaxHp()
          - aiParty.get(i).getHp())) {
        player = aiParty.get(i);
      }
    }
    aiParty.clear();

    return player;

  }
}
