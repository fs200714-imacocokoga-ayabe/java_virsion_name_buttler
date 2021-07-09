package java_virsion_name_battler.herb;

import java.util.Random;
import new_create_app_name_battler.party.BasePlayer;

public interface IEat {

  Random random = new Random();
  final int HERB_RECOVERY_VALUE = 30;
  BasePlayer attacker = null;

  default public void selectEat(BasePlayer attacker) {

    switch (random.nextInt(3)) {

      case 0:
       eatPoisonousGrass(attacker);
        break;

      case 1:
        eatHerb(attacker);
        break;

      case 2:
       eatGrass(attacker);
        break;
    }
  }

  default public void eatPoisonousGrass(BasePlayer attacker) {
    System.out.printf("%sは革袋の中にあった草を食べた！\n", attacker.getName());
    if (attacker.isPoison()) {// 毒状態の場合
      System.out.printf("%sは毒が消えた！\n", attacker.getName());
      attacker.setPoison(false);
    } else {
      System.out.printf("%sは何も起こらなかった！\n", attacker.getName());
    }
  }

 default public void eatHerb(BasePlayer attacker) {
    System.out.printf("%sは革袋の中にあった草を食べた！\n", attacker.getName());
    int healValue = Math.min(attacker.getMaxHp(), attacker.getHp() + HERB_RECOVERY_VALUE);
    System.out.printf("%sはHPが%d回復した！\n", attacker.getName(), healValue - attacker.getHp());
    attacker.recovery(healValue - attacker.getHp());
  }

 default public void eatGrass(BasePlayer attacker) {
   System.out.printf("%sは革袋の中にあった草を食べた！\n", attacker.getName());
   System.out.printf("%sは何も起こらなかった！\n", attacker.getName());
 }

}
