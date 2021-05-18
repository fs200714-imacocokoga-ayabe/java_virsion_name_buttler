package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.Skill;

public class JobFighter extends BasePlayer implements IFighter {

  /**
   * コンストラクタ
   * 
   * @param name : プレイヤー名
   */
  public JobFighter(String name) {
    super(name);
  }

  @Override
  protected void makeCharacter() {

    this.job = "戦士";
    this.setHp(getNumber(0, 200) + 100);// 100-300
    this.mp = getNumber(1, 0);// 0
    this.str = getNumber(2, 70) + 30;// 30-100
    this.def = getNumber(3, 70) + 30;// 30-100
    this.luck = getNumber(4, 99) + 1;// 1-100
    this.agi = getNumber(5, 49) + 1;// 1-50
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

    if (random.nextInt(100) + 1 <= Skill.ASSAULT.getInvocationRate()) {// 発動率25%

      System.out.printf("%sの捨て身の突撃！\n", getName());
      damage = calcDamage(defender); // 与えるダメージを求める
      damage = damage * 2;// ダメージ2倍
      damageProcess(attackType, this, defender, damage);

    } else {

      System.out.printf("%sの捨て身の突撃はかわされた！\n", getName());
    }
    knockedDownCheck(defender);
  }

  @Override
  public void eatGrass() {
    super.eatGrass();
    knockedDownCheck(this);
  }
}
