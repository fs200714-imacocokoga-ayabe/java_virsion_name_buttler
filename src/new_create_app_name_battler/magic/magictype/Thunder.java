package new_create_app_name_battler.magic.magictype;

import new_create_app_name_battler.magic.BaseUseMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.party.IPlayer;

public class Thunder extends BaseUseMagic {

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    if (hasEnoughMp(attacker.getMp())) {
      System.out.printf("%sは%sを唱えた！\n雷が地面を這っていく！\n", attacker.getName(),
          MagicData.THUNDER.getName());
      attacker.downMp(this.magicData.getMpcost());

      damage =
          random.nextInt(magicData.getMaxDamage() - magicData.getMinDamage())
              + magicData.getMinDamage();// 乱数20～50

      return damage;

    } else {
      System.out.printf("%sは術を唱えようとしたが、MPが足りない！！\n", attacker.getName());
    }
    return NO_MP;
  }

  @Override
  public boolean hasEnoughMp(int mp) {
    return super.hasEnoughMp(mp);
  }

  @Override
  public void initMagic() {
    this.magicData = MagicData.THUNDER;
  }

}
