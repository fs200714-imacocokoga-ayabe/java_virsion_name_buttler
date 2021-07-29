package new_create_app_name_battler.magic.magictype;

import new_create_app_name_battler.magic.BaseUseMagic;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.party.IPlayer;

public class Heal extends BaseUseMagic implements IRecoveryMagic {

  int healValue;

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    if (hasEnoughMp(attacker.getMp())) {
      System.out.printf("%sはヒールを唱えた！\n光が%sを包んだ\n", attacker.getName(), defender.getName());
      healValue = recoveryProcess(defender, magicData.getRecoveryValue());
      attacker.downMp(this.magicData.getMpcost());

      return healValue;

    } else {
      System.out.printf("%sは術を唱えようとしたが、MPが足りない！！\n", attacker.getName());
    }
    return NO_MP;
  }

  public int recoveryProcess(IPlayer defender, int healValue) {
    healValue = Math.min(defender.getMaxHp(), defender.getHp() + healValue);
    System.out.printf("%sはHPが%d回復した！\n", defender.getName(), healValue - defender.getHp());
    defender.recovery(healValue - defender.getHp());
    return healValue - defender.getHp();
  }

  @Override
  public void initMagic() {
    this.magicData = MagicData.HEAL;
  }

  @Override
  public boolean hasEnoughMp(int mp) {
    return super.hasEnoughMp(mp);
  }

}
