package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.IPlayer;

public class FireRoll extends BaseUseMagic{

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    super.effect(attacker, defender);

    System.out.printf("%sは%sを唱えた！\n火の球が飛んでいく！\n", attacker.getName(), magicData.getName());

    attacker.downMp(this.magicData.getMpcost());

    damage = random.nextInt(magicData.getMaxDamage() - magicData.getMinDamage())
            + magicData.getMinDamage();// 乱数10～30

    return damage;

  }

  @Override
  public void initMagic(){
    this.magicData = MagicData.FIREROLL;

  }

}
