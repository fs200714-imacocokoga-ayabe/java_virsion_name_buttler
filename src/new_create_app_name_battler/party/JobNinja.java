package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.FireRoll;
import new_create_app_name_battler.magic.Skill;

public class JobNinja extends BasePlayer implements INinja {

  /**
   * コンストラクタ
   *
   * @param name: プレイヤー名
   */
  public JobNinja(String name) {
    super(name);
    initMagics();
  }

  public void initJob(){
    jobData = JobData.NINJA;
  }

  public void initMagics(){
    magics.add(new FireRoll());
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
    if (random.nextInt(100) + 1 <= Skill.SWALLOW.getInvocationRate()) {// 25%で発動

      System.out.printf("%sは目にも止まらぬ速さで攻撃した！\n", getName());

      for (int i = 1; i <= 2; i++) {

        System.out.printf("%d回目の攻撃\n", i);
        damage = calcDamage(defender);// 攻撃処理

        super.damageProcess(attackType, this, defender, damage);// ダメージ処理

        if (defender.getHp() <= 0) {// 倒れた判定

          break;
        }
      }

    } else {// 75%で不発

      System.out.printf("%sは転んだ！\n", getName());
    }
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

//  @Override
//  public int effect(BasePlayer defender) {
//
//    damage =
//        random.nextInt(MagicData.FIREROLL.getMaxDamage() - MagicData.FIREROLL.getMinDamage())
//            + MagicData.FIREROLL.getMinDamage();// 乱数10～30
//
//    this.mp = this.getMp() - MagicData.FIREROLL.getMpcost();// MP消費
//
//    System.out.printf("%sは%sを唱えた！\n火の球が飛んでいく！\n", getName(), MagicData.FIREROLL.getName());
//
//    return damage;
//  }
//
//  @Override
//  public boolean hasEnoughMp() {
//
//    if (10 <= this.getMp()) {
//
//      return true;
//
//    } else {
//
//      return false;
//
//    }
//  }
}
