package new_create_app_name_battler.type;

import java.util.Random;
import new_create_app_name_battler.party.BasePlayer;

public class TypeHoly implements IType {

  Random random = new Random();

  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    int r = random.nextInt(100) + 1;

    if (r <= TypeData.HOLY.getInvocationRate() && attackType.equals("M")) {// 1/4で魔法攻撃を無効にする

      System.out.println(TypeData.HOLY.getMessage());// 属性のメッセージ

      damage = (int) (damage * TypeData.HOLY.getCollectionValue());// 属性処理を加えてダメージを求める

    };

    return damage;
  }

}
