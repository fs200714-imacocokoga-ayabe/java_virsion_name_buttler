package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.IPlayer;

public class Heal extends BaseUseMagic{

  int healValue;

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    super.effect(attacker, defender);

    System.out.printf("%sはヒールを唱えた！\n光が%sを包んだ\n", attacker.getName(), defender.getName(),
        defender.getName());

    healValue = recoveryProcess(defender, magicData.getRecoveryValue());

    attacker.downMp(this.magicData.getMpcost());

    return  healValue;

  }

  public int recoveryProcess(IPlayer defender, int healValue) {

    healValue = Math.min(defender.getMaxHp(), defender.getHp() + healValue);
    System.out.printf("%sはHPが%d回復した！\n", defender.getName(), healValue - defender.getHp());
    defender.recovery(healValue - defender.getHp());
    return healValue - defender.getHp();
  }

  @Override
  public void initMagic(){
    this.magicData = MagicData.HEAL;

  }

}
