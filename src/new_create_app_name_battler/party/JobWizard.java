package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.magic.magictype.Fire;
import new_create_app_name_battler.magic.magictype.Thunder;
import new_create_app_name_battler.skill.IOwnSkill;
import new_create_app_name_battler.skill.skilltype.FireElemental;

public class JobWizard extends BasePlayer implements IOwnMagic, IOwnSkill, IWizard {

  public JobWizard(String name) {
    super(name);
    initMagics();
    initSkills();
  }

  @Override
  public void initJob() {
    jobData = JobData.WIZARD;
  }

  @Override
  public void initMagics() {
    magics.add(new Fire());
    magics.add(new Thunder());
  }

  @Override
  public void initSkills() {
    skills.add(new FireElemental());
  }

  @Override
  public void initTypes(int typeNumber){

    super.initTypes(typeNumber);
  }

  @Override
  public void normalAttack(BasePlayer defender) {
    attackType = "A";
    System.out.printf("%sの攻撃！\n%sは杖を投げつけた！\n", getName(), getName());
    damage = calcDamage(defender);// 与えるダメージを求める
    damageProcess(attackType, this, defender, damage);
    knockedDownCheck(defender);
  }

  @Override
  public void skillAttack(BasePlayer defender) {
    skill = skills.get(0);
    attackType = "M";
    super.damageProcess(attackType, this, defender, skill.effect(this, defender));// ダメージ処理
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







