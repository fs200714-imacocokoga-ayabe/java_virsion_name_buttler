package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.magic.magictype.Heal;
import new_create_app_name_battler.magic.magictype.Paralysis;
import new_create_app_name_battler.magic.magictype.Poison;
import new_create_app_name_battler.skill.IOwnSkill;
import new_create_app_name_battler.skill.skilltype.OpticalElemental;

public class JobPriest extends BasePlayer implements IOwnSkill, IRecoveryMagic, IOwnMagic, IPriest {

  boolean isHeal;

  /**
   * コンストラクタ
   *
   * @param name: プレイヤー名
   */
  public JobPriest(String name) {
    super(name);
    initMagics();
    initSkills();
  }

  @Override
  public void initJob(){
    jobData = JobData.PRIEST;
  }

  @Override
  public void initMagics() {
    magics.add(new Poison());
    magics.add(new Paralysis());
    magics.add(new Heal());
  }

  @Override
  public void initSkills(){
    skills.add(new OpticalElemental());
  }


  @Override
  public void normalAttack(BasePlayer defender) {

    attackType = "A";
    System.out.printf("%sの攻撃！\n錫杖で突いた！\n", getName());
    damage = calcDamage(defender);// 与えるダメージを求める
    damageProcess(attackType, this, defender, damage);// ダメージ処理
    knockedDownCheck(defender);
  }

  @Override
  public void skillAttack(BasePlayer defender) {

    skill = skills.get(0);
    attackType = "M";
    skill.effect(this, defender);

    knockedDownCheck(this);
  }

  @Override
  public void magicAttack(BasePlayer defender) {

    // if (hasEnoughMp()) {

    magic = choiceMagic();
    attackType = "M";
    damage =  magic.effect(this, defender);
   // super.damageProcess(attackType, this, defender, damage);
    knockedDownCheck(defender);

    // } else {
    //
    // System.out.printf("%sは魔法を唱えようとしたが、MPが足りない！！\n", getName());
    // normalAttack(defender);
    //
  }

  @Override
  public void healingMagic(BasePlayer defender) {

    magic = magics.get(2);
    damage =  magic.effect(this, defender);
    knockedDownCheck(this);

//    this.isHeal = true;
//
//    if (hasEnoughMp()) {// MPが20以上の場合ヒールを使用
//
//      this.mp = this.getMp() - MagicData.HEAL.getMpcost();// MP消費
//
//      System.out.printf("%sはヒールを唱えた！\n光が%sを包んだ\n", getName(), defender.getName(),
//          defender.getName());
//
//      recoveryProcess(defender, MagicData.HEAL.getRecoveryValue());
//      knockedDownCheck(this);
//
//    } else {// MPが20未満の場合
//
//      System.out.printf("%sはヒールを唱えようとしたが、MPが足りない！！\n", getName());
//      System.out.printf("%sの攻撃！\n錫杖を振りかざした！\n", getName());
//      damage = calcDamage(defender);// 与えるダメージを求める
//      super.damageProcess(attackType, this, defender, damage);// ダメージ処理
//      knockedDownCheck(defender);
//    }
//
//    this.isHeal = false;

  }

  @Override
  public void eatGrass() {
    super.eatGrass();
    knockedDownCheck(this);
  }

//  public int effect(BasePlayer defender) {
//
//    if (!defender.isPoison() && !defender.isParalysis()) {// 相手が毒,麻痺状態にかかっていない場合
//
//      if (random.nextInt(2) == 0) {// 乱数1の場合パライズを使用
//
//        useParalysis(defender);
//
//      } else {// 乱数2の場合ポイズンを使用
//
//        usePoison(defender);
//      }
//
//    } else if (defender.isPoison() || defender.isParalysis()) {// 相手が毒または麻痺にかかっている場合
//
//      if (defender.isPoison()) {// 相手が毒にかかっている場合パライズを使う
//
//        useParalysis(defender);
//
//      } else if (defender.isParalysis()) {// 相手が麻痺にかかっている場合パライズを使う
//
//        usePoison(defender);
//      }
//    }
//
//    return damage;
//  }
//
//  private void usePoison(IPlayer defender) {
//
//    this.mp = this.getMp() - MagicData.POISON.getMpcost();// MP消費
//
//    System.out.printf("%sは%sを唱えた！\n瘴気が相手を包んだ！\n", getName(), MagicData.POISON.getName());
//    defender.setPoison(true);// 相手に毒をセット
//    System.out.printf("%sは毒状態になった！\n", defender.getName());
//
//  }
//
//  private void useParalysis(IPlayer defender) {
//
//    this.mp = this.getMp() - MagicData.PARALYSIS.getMpcost();// MP消費
//
//    System.out.printf("%sは%sを唱えた！\n蒼い霧が相手を包んだ！\n", getName(), MagicData.PARALYSIS.getName());
//
//    if (random.nextInt(100) + 1 <= MagicData.PARALYSIS.getContinuousRate()) {// 乱数がPARALYSISの値以下の場合麻痺状態になる
//
//      defender.setParalysis(true);// 相手に麻痺をセット
//      System.out.printf("%sは麻痺を受けた！\n", defender.getName());
//
//    } else {// 麻痺を状態にならなかった場合
//
//      System.out.printf("%sは麻痺を受けなかった！\n", defender.getName());
//    }
//  }

 // @Override
  public boolean hasEnoughMp() {

    if (10 <= this.getMp() && !isHeal) {

      return true;

    } else if (20 <= this.getMp() && isHeal) {

      return true;

    } else {

      return false;
    }
  }

}
