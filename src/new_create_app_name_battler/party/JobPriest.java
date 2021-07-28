package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.magic.magictype.Heal;
import new_create_app_name_battler.magic.magictype.Paralysis;
import new_create_app_name_battler.magic.magictype.Poison;
import new_create_app_name_battler.skill.IOwnSkill;
import new_create_app_name_battler.skill.skilltype.OpticalElemental;

public class JobPriest extends BasePlayer implements IOwnSkill, IRecoveryMagic, IOwnMagic, IPriest {

  public JobPriest(String name) {
    super(name);
    initMagics();
    initSkills();
  }

  @Override
  public void initJob() {
    jobData = JobData.PRIEST;
  }

  @Override
  public void initMagics() {
    magics.add(new Poison());
    magics.add(new Paralysis());
    magics.add(new Heal());
  }

  @Override
  public void initSkills() {
    skills.add(new OpticalElemental());
  }

  @Override
  public void normalAttack(BasePlayer defender) {
    isPhysicalAttack = true;
    priestAttackMessage(this);
    damage = calcDamage(defender);// 与えるダメージを求める
    damageProcess(isPhysicalAttack, this, defender, damage);// ダメージ処理
    knockedDownCheck(defender);
  }

  @Override
  public void skillAttack(BasePlayer defender) {
    skill = skills.get(0);
    skill.effect(this, defender);
    knockedDownCheck(this);
  }

  @Override
  public void magicAttack(BasePlayer defender) {
    magic = choiceMagic();
    damage = magic.effect(this, defender);

    if (damage != 0) {
      knockedDownCheck(defender);
    } else {
      this.normalAttack(defender);
    }
  }

  @Override
  public void healingMagic(BasePlayer defender) {
    magic = magics.get(2);
    damage = magic.effect(this, defender);
    knockedDownCheck(this);
  }

  @Override
  public void eat() {
    super.eat();
    knockedDownCheck(this);
  }
}
