package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IOwnMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.magic.magictype.Fire;
import new_create_app_name_battler.magic.magictype.Thunder;
import new_create_app_name_battler.skill.IOwnSkill;

public class JobWizard extends BasePlayer implements IOwnMagic, IOwnSkill, IWizard {

  /**
   * コンストラクタ
   * @param name : プレイヤー名
   */
  public JobWizard(String name) {

    super(name);
    initMagics();
    initSkills();
  }

  @Override
  public void initJob(){
    jobData = JobData.WIZARD;
  }

  @Override
  public void initMagics() {
    magics.add(new Fire());
    magics.add(new Thunder());

  }

  @Override
  public void initSkills(){


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

    attackType = "M";

    if (random.nextInt(100) + 1 <= MagicData.FIREELEMENTAL.getInvocationRate()) {// 25％で発動

      System.out.printf("%sは魔法陣を描いて%sを召還した\n%sの攻撃！\n", getName(), MagicData.FIREELEMENTAL.getName(),
          MagicData.FIREELEMENTAL.getName());

      super.damageProcess(attackType, this, defender, MagicData.FIREELEMENTAL.getMinDamage());// ダメージ処理

    } else {// 75%で不発

      System.out.printf("%sの攻撃だがスキルは発動しなかった！\n", getName());
    }
    knockedDownCheck(defender);
  }

  @Override
  public void magicAttack(BasePlayer defender) {

    magic = choiceMagic();
    attackType = "M";
    damage =  magic.effect(this, defender);
    super.damageProcess(attackType, this, defender, damage);
    knockedDownCheck(defender);

//      System.out.printf("%sは魔法を唱えようとしたが、MPが足りない！！\n", getName());
//      normalAttack(defender);
//    }

  }

  @Override
  public void eatGrass() {
    super.eatGrass();
    knockedDownCheck(this);
  }

//  @Override
//  public int effect(BasePlayer defender) {
//
//    if (20 <= getMp()) {// MPが20以上の場合
//
//      if (random.nextInt(2) == 0) {// 0の場合サンダーを使用
//
//        damage = useThunder();
//
//      } else {// 1の場合ファイアを使用
//
//        damage = useFire();
//      }
//
//    } else if (10 <= this.getMp() && this.getMp() < 20) {// MPが10以上20未満の場合ファイアを使用する
//
//      damage = useFire();// ファイアを使用
//    }
//
//    return damage;
//  }
//
//  private int useThunder() {
//
//    System.out.printf("%sは%sを唱えた！\n雷が地面を這っていく！\n", getName(), MagicData.THUNDER.getName());
//
//    damage =
//        random.nextInt(MagicData.THUNDER.getMaxDamage() - MagicData.THUNDER.getMinDamage())
//            + MagicData.THUNDER.getMinDamage();// 乱数20～50
//
//    this.mp = this.getMp() - MagicData.THUNDER.getMpcost();// MPを消費
//
//    return damage;
//  }
//
//  private int useFire() {
//    System.out.printf("%sは%sを唱えた！\n炎が渦を巻いた！\n", getName(), MagicData.FIRE.getName());
//
//    damage =
//        random.nextInt(MagicData.FIRE.getMaxDamage() - MagicData.FIRE.getMinDamage())
//            + MagicData.FIRE.getMinDamage();// 乱数10～30
//
//    this.mp = this.getMp() - MagicData.FIRE.getMpcost();// MPを消費
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
//    }
//  }
}
