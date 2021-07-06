package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.IPlayer;

public class Thunder extends BaseUseMagic{

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    super.effect(attacker, defender);

    System.out.printf("%sは%sを唱えた！\n雷が地面を這っていく！\n", attacker.getName(), MagicData.THUNDER.getName());

    attacker.downMp(this.magicData.getMpcost());

    damage = random.nextInt(magicData.getMaxDamage() - magicData.getMinDamage())
            + magicData.getMinDamage();// 乱数20～50

    return damage;

  }

  @Override
  public void initMagic(){
    this.magicData = MagicData.THUNDER;
  }

}
