package new_create_app_name_battler.skill.skilltype;

import new_create_app_name_battler.party.IPlayer;
import new_create_app_name_battler.skill.BaseUseSkill;
import new_create_app_name_battler.skill.SkillData;

public class Swallow extends BaseUseSkill {
  
  final int SWALLOW_POWER = 2;

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    super.effect(attacker, defender);

    if (random.nextInt(100) + 1 <= skillData.getInvocationRate()) {

      System.out.printf("%sは目にも止まらぬ速さで2回攻撃した！\n", attacker.getName());

      return SWALLOW_POWER;

    } else {

      System.out.printf("%sは転んだ！\n", attacker.getName());
    }
    return 0;
  }


  @Override
  public void initSkill() {
    this.skillData = SkillData.SWALLOW;
  }
}
