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
  public void initTypes(int typeNumber){

    super.initTypes(typeNumber);
  }

  @Override
  public void normalAttack(BasePlayer defender) {
    attackType = "A";
    System.out.printf("%sの攻撃！\n手裏剣を投げつけた！\n", getName());
    damage = calcDamage(defender); // 与えるダメージを求める
    damageProcess(attackType, this, defender, damage);// ダメージ処理
    knockedDownCheck(defender);
  }

  @Override
  public void skillAttack(BasePlayer defender) {
    attackType = "A";
    skill = skills.get(0);
    damage = calcDamage(defender);// 攻撃処理
    damage = damage * skill.effect(this, defender);// ダメージ2倍
    super.damageProcess(attackType, this, defender, damage);// ダメージ処理
    knockedDownCheck(defender);
  }

  @Override
  public void magicAttack(BasePlayer defender) {

    magic = choiceMagic();
    attackType = "M";
    damage = magic.effect(this, defender);

    if (damage != 0) {
      super.damageProcess(attackType, this, defender, damage);
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
