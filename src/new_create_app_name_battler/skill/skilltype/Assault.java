package new_create_app_name_battler.skill.skilltype;

import new_create_app_name_battler.party.IPlayer;
import new_create_app_name_battler.skill.BaseUseSkill;
import new_create_app_name_battler.skill.SkillData;

public class Assault extends BaseUseSkill {

  final int ASSAULT_POWER = 2;

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    super.effect(attacker, defender);

    if (random.nextInt(100) + 1 <= skillData.getInvocationRate()) {// 発動率25%

      System.out.printf("%sの捨て身の突撃！\n", attacker.getName());

      return  ASSAULT_POWER;

    } else {

      System.out.printf("%sの捨て身の突撃はかわされた！\n", attacker.getName());
    }
    return 0;

  }

  @Override
  public void initSkill() {
    this.skillData = SkillData.ASSAULT;
  }


}
