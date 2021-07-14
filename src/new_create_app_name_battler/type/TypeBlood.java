package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public class TypeBlood extends BaseUseType {


  int r = random.nextInt(100) + 1;

  int heal;

  // 属性の処理をする
  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    if (r <= typeData.getInvocationRate() && attackType.equals("A")) {// 1/5で物理攻撃を1/2吸収される

      System.out.println(typeData.getMessage());// 属性のメッセージ
      heal = (int) (damage * typeData.getCollectionValue());// 属性補正値を加えて回復値を求める


    } else {// 発動しなかった場合

      heal = 0;

    }

    if (heal != 0) {// 属性が発動した場合表示

      System.out.printf("%sはHPが%d回復した！\n", defender.getName(), heal);
      defender.setHp(defender.getHp() + heal);// 対象相手HPが回復する
    }

    damage = damage - heal;// 吸収された分の計算

    return damage;
  }

  @Override
  public void initType() {
    this.typeData = TypeData.BLOOD;
  }


}
