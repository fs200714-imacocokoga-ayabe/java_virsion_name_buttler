package new_create_app_name_battler.skill;

import java.util.Random;
import new_create_app_name_battler.party.IPlayer;

public class BaseUseSkill implements IUseSkill {

  protected Random random = new Random();
  protected SkillData skillData;
  protected int damage;

  public BaseUseSkill() {
    this.initSkill();
  }

  @Override
  public void initSkill() {}

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {
    return 0;
  }
}
