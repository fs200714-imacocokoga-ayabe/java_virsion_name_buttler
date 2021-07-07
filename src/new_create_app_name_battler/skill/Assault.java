package new_create_app_name_battler.skill;

import new_create_app_name_battler.party.IPlayer;

public class Assault extends BaseUseSkill{

  @Override
  public int effect(IPlayer attacker, IPlayer defender){

    super.effect(attacker, defender);

    if (random.nextInt(100) + 1 <= skillData.getInvocationRate()) {// 発動率25%

      System.out.printf("%sの捨て身の突撃！\n", attacker.getName());

    return 2;

    }else{

      System.out.printf("%sの捨て身の突撃はかわされた！\n", attacker.getName());
    }

    return 0;

  }

  @Override
  public void initSkill(){
    this.skillData = SkillData.ASSAULT;
  }


}