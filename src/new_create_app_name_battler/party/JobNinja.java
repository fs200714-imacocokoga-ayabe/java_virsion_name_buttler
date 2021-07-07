package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.magic.magictype.FireRoll;
import new_create_app_name_battler.skill.IOwnSkill;
import new_create_app_name_battler.skill.skilltype.Swallow;

public class JobNinja extends BasePlayer implements IOwnSkill, IOwnMagic, INinja {

  /**
   * コンストラクタ
   *
   * @param name: プレイヤー名
   */
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

  public void initSkills(){
    skills.add(new Swallow());
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

    // if (hasEnoughMp()) {
    magic = choiceMagic();
    attackType = "M";
    damage = magic.effect(this, defender);
    super.damageProcess(attackType, this, defender, damage);
    knockedDownCheck(defender);

    // } else {

    // System.out.printf("%sは術を唱えようとしたが、MPが足りない！！\n", getName());
    // normalAttack(defender);
    // }
  }

  @Override
  public void eatGrass() {
    super.eatGrass();
    knockedDownCheck(this);
  }

  // @Override
  // public boolean hasEnoughMp() {
  //
  // if (10 <= this.getMp()) {
  //
  // return true;
  //
  // } else {
  //
  // return false;
  //
  // }
  // }
}
