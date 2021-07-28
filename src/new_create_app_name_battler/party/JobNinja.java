package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.magic.magictype.FireRoll;
import new_create_app_name_battler.skill.skilltype.Swallow;

public class JobNinja extends BasePlayer implements  IOwnMagic, INinja {

  public JobNinja(String name) {
    super(name);
    initMagics();
    initSkills();
  }

  public void initJob() {
    jobData = JobData.NINJA;
  }

  public void initMagics() {
    magics.add(new FireRoll());
  }

  public void initSkills() {
    skills.add(new Swallow());
  }

  @Override
  public void normalAttack(BasePlayer defender) {
    isPhysicalAttack = true;
    ninjaAttackMessage(this);
    damage = calcDamage(defender); // 与えるダメージを求める
    damageProcess(isPhysicalAttack, this, defender, damage);// ダメージ処理
    knockedDownCheck(defender);
  }

  @Override
  public void skillAttack(BasePlayer defender) {
    isPhysicalAttack = true;
    skill = skills.get(0);
    damage = calcDamage(defender);// 攻撃処理
    damage = damage * skill.effect(this, defender);// ダメージ2倍
    super.damageProcess(isPhysicalAttack, this, defender, damage);// ダメージ処理
    knockedDownCheck(defender);
  }

  @Override
  public void magicAttack(BasePlayer defender) {

    magic = choiceMagic();
    damage = magic.effect(this, defender);

    if (damage != 0) {
      isPhysicalAttack = false;
      super.damageProcess(isPhysicalAttack, this, defender, damage);
      knockedDownCheck(defender);

    } else {
      this.normalAttack(defender);
    }
  }

  @Override
  public void eat() {
    super.eat();
    knockedDownCheck(this);
  }
}
