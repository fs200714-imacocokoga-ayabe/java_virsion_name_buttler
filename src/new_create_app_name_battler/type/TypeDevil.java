package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public class TypeDevil extends BaseUseType {

  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    int r = random.nextInt(100) + 1;

    if (r <= typeData.getInvocationRate() && attackType.equals("M")) {// 1/2で魔法攻撃を半減する

      System.out.println(typeData.getMessage());// 属性のメッセージ
      damage = (int) (damage * typeData.getCollectionValue());// 属性処理を加えてダメージを求める
    };
    return damage;
  }

  @Override
  public void initType() {
    this.typeData = TypeData.DEVIL;

  }

}
