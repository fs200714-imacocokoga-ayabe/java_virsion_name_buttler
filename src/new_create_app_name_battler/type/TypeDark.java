package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public class TypeDark extends BaseUseType {

  int r = random.nextInt(100) + 1;

  private int processedvalue;

  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    if (r <= typeData.getInvocationRate()) {// 呪いのダメージを10受ける

      System.out.println(typeData.getMessage());// 属性のメッセージ
      processedvalue = (int) (typeData.getCollectionValue());// 属性処理を加えてダメージを求める
      attacker.setHp(attacker.getHp() - processedvalue);
      System.out.printf("%sは%dのダメージを受けた！\n", defender.getName(), processedvalue);
    };
    return damage;
  }

  @Override
  public void initType() {
    this.typeData = TypeData.DARK;
  }

}
