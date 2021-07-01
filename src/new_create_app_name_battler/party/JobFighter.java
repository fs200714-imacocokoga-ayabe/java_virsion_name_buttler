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

  public void initJob(){
    jobData = JobData.FIGHTER;
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
