package new_create_app_name_battler.magic.magictype;

import new_create_app_name_battler.magic.BaseUseMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.party.IPlayer;

public class Fire extends BaseUseMagic{

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    super.effect(attacker, defender);

    System.out.printf("%sは%sを唱えた！\n炎が渦を巻いた！\n", attacker.getName(), magicData.getName());

    attacker.downMp(this.magicData.getMpcost());

    damage = random.nextInt(magicData.getMaxDamage() - magicData.getMinDamage())
            + magicData.getMinDamage();// 乱数10～30

    return damage;

  }

  @Override
  public void initMagic(){
    this.magicData = MagicData.FIRE;

  }

}