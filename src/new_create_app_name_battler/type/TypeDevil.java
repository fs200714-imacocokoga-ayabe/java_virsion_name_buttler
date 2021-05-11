package new_create_app_name_battler.type;

import java.util.Random;
import new_create_app_name_battler.party.BasePlayer;

public class TypeDevil implements IType {

  Random random = new Random();

  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    int r = random.nextInt(100) + 1;

    if (r <= TypeData.DEVIL.getInvocationRate() && attackType.equals("M")) {// 1/2で魔法攻撃を半減する

      System.out.println(TypeData.DEVIL.getMessage());// 属性のメッセージ

      damage = (int) (damage * TypeData.DEVIL.getCollectionValue());// 属性処理を加えてダメージを求める

    };
    return damage;
  }

}
