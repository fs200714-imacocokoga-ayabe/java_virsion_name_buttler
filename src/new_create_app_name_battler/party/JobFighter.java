package new_create_app_name_battler.party;

import new_create_app_name_battler.skill.IOwnSkill;
import new_create_app_name_battler.skill.skilltype.Assault;

public class JobFighter extends BasePlayer implements IOwnSkill, IFighter {

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
    attackType = "A";
    System.out.printf("%sの攻撃！\n%sは剣で斬りつけた！\n", getName(), getName());
    damage = calcDamage(defender); // 与えるダメージを求める
    damageProcess(attackType, this, defender, damage);
    knockedDownCheck(defender);
  }

  public void skillAttack(BasePlayer defender) {
    attackType = "A";
    skill = skills.get(0);
    damage = calcDamage(defender); // 与えるダメージを求める
    damage = damage * skill.effect(this, defender);// ダメージ2倍
    damageProcess(attackType, this, defender, damage);
    knockedDownCheck(defender);
  }

  @Override
  public void eatGrass() {
    super.eatGrass();
    knockedDownCheck(this);
  }
}
