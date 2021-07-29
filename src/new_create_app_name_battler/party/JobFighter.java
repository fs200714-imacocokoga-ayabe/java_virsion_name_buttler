package new_create_app_name_battler.party;

import new_create_app_name_battler.skill.IOwnSkill;
import new_create_app_name_battler.skill.skilltype.Assault;
import new_create_app_name_battler.type.IOwnType;

public class JobFighter extends BasePlayer implements IOwnType, IOwnSkill, IFighter {

  public JobFighter(String name) {
    super(name);
    initSkills();
  }

  public void initJob() {
    jobData = JobData.FIGHTER;
  }

  @Override
  public void initSkills() {
    skills.add(new Assault());
  }

  @Override
  public void normalAttack(BasePlayer defender) {
    isPhysicalAttack = true;
    fighterAttackMessage(this);
    damage = calcDamage(defender); // 与えるダメージを求める
    damageProcess(isPhysicalAttack, this, defender, damage);
    knockedDownCheck(defender);
  }

  public void skillAttack(BasePlayer defender) {
    isPhysicalAttack = true;
    skill = skills.get(0);
    damage = calcDamage(defender); // 与えるダメージを求める
    damage = damage * skill.effect(this, defender);// ダメージ2倍
    damageProcess(isPhysicalAttack, this, defender, damage);
    knockedDownCheck(defender);
  }

  @Override
  public void eat() {
    super.eat();
    knockedDownCheck(this);
  }
}
