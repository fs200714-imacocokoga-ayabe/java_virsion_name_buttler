package new_create_app_name_battler.magic.magictype;

import new_create_app_name_battler.magic.BaseUseMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.party.IPlayer;

public class Poison extends BaseUseMagic {

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    if (hasEnoughMp(attacker.getMp())) {
      System.out.printf("%sは%sを唱えた！\n瘴気が相手を包んだ！\n", attacker.getName(), magicData.getName());
      defender.setPoison(true);// 相手に毒をセット
      System.out.printf("%sは毒状態になった！\n", defender.getName());
      attacker.downMp(this.magicData.getMpcost());

      return 1;

    } else {
      System.out.printf("%sは術を唱えようとしたが、MPが足りない！！\n", attacker.getName());
    }
    return 0;
  }

  @Override
  public boolean hasEnoughMp(int mp) {
    return super.hasEnoughMp(mp);
  }

  @Override
  public void initMagic() {
    this.magicData = MagicData.POISON;

  }

}
