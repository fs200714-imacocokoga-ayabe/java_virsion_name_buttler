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
}
