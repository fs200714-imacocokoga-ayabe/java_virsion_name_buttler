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
  public void normalAttack(BasePlayer defender) {
    isPhysicalAttack = true;
    wizardAttackMessage(this);
    damage = calcDamage(defender);
    damageProcess(isPhysicalAttack, this, defender, damage);
    knockedDownCheck(defender);
  }

  @Override
  public void skillAttack(BasePlayer defender) {
    isPhysicalAttack = false;
    skill = skills.get(0);
    damageProcess(isPhysicalAttack, this, defender, skill.effect(this, defender));
    knockedDownCheck(defender);
  }

  @Override
  public void magicAttack(BasePlayer defender) {
    magic = choiceMagic();
    damage = magic.effect(this, defender);

    if (damage != 0) {
      isPhysicalAttack = false;
      damageProcess(isPhysicalAttack, this, defender, damage);
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







