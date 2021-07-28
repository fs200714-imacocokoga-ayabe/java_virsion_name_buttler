package new_create_app_name_battler.skill.skilltype;

import new_create_app_name_battler.party.IPlayer;
import new_create_app_name_battler.skill.BaseUseSkill;
import new_create_app_name_battler.skill.SkillData;

public class OpticalElemental extends BaseUseSkill{

  int healValue;

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    if (random.nextInt(100) + 1 <= skillData.getInvocationRate()) {

      System.out.printf("%s祈りを捧げて%sを召還した\n%sの祝福を受けた！\n", attacker.getName(),
          skillData.getName(), skillData.getName());

      healValue = recoveryProcess(attacker, skillData.getRecoveryValue());

      return healValue;

    } else {
      System.out.printf("%sの攻撃だがスキルは発動しなかった！\n", attacker.getName());
    }
    return 0;

  }

  public int recoveryProcess(IPlayer attacker, int healValue) {

    healValue = Math.min(attacker.getMaxHp(), attacker.getHp() + healValue);
    System.out.printf("%sはHPが%d回復した！\n", attacker.getName(), healValue - attacker.getHp());
    attacker.recovery(healValue - attacker.getHp());
    return healValue - attacker.getHp();
  }

  @Override
  public void initSkill(){
    this.skillData = SkillData.OPTICALELEMENTAL;
  }
}
