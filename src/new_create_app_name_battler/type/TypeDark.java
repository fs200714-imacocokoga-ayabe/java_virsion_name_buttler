package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public class TypeDark implements IType {


  int r = random.nextInt(100) + 1;

  private int processedvalue;

  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {


    if (r <= TypeData.DARK.getInvocationRate()) {// 呪いのダメージを10受ける

      System.out.println(TypeData.DARK.getMessage());// 属性のメッセージ

      processedvalue = (int) (TypeData.DARK.getCollectionValue());// 属性処理を加えてダメージを求める

      attacker.setHp(attacker.getHp() - processedvalue);

      System.out.printf("%sは%dのダメージを受けた！\n", defender.getName(), processedvalue);

    };

    return damage;
  }

}
