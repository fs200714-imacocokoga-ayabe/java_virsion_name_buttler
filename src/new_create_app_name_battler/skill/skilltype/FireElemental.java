package new_create_app_name_battler.skill.skilltype;

import new_create_app_name_battler.party.IPlayer;
import new_create_app_name_battler.skill.BaseUseSkill;
import new_create_app_name_battler.skill.SkillData;

public class FireElemental extends BaseUseSkill{

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {


    if (random.nextInt(100) + 1 <= skillData.getInvocationRate()) {

      System.out.printf("%sは魔法陣を描いて%sを召還した\n%sの攻撃！\n", attacker.getName(), skillData.getName(),
          skillData.getName());

      return skillData.getMaxDamage();

    } else {

      System.out.printf("%sの攻撃だがスキルは発動しなかった！\n", attacker.getName());
    }

    return skillData.getMinDamage();

  }

  @Override
  public void initSkill(){
    this.skillData = SkillData.FIREELEMENTAL;
  }

}
