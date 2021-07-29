package new_create_app_name_battler.magic.magictype;

import new_create_app_name_battler.magic.BaseUseMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.party.IPlayer;

public class Paralysis extends BaseUseMagic {

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {

    if (hasEnoughMp(attacker.getMp())) {
      System.out.printf("%sは%sを唱えた！\n蒼い霧が相手を包んだ！\n", attacker.getName(),
          MagicData.PARALYSIS.getName());

      if (random.nextInt(100) + 1 <= MagicData.PARALYSIS.getContinuousRate()) {// 乱数がPARALYSISの値以下の場合麻痺状態になる
        defender.setParalysis(true);// 相手に麻痺をセット
        System.out.printf("%sは麻痺を受けた！\n", defender.getName());

      } else {// 麻痺を状態にならなかった場合
        System.out.printf("%sは麻痺を受けなかった！\n", defender.getName());
      }
      attacker.downMp(this.magicData.getMpcost());

      return USE_STATE_MAGIC;

    } else {
      System.out.printf("%sは術を唱えようとしたが、MPが足りない！！\n", attacker.getName());
    }
    return NO_MP;
  }

  @Override
  public void initMagic() {
    this.magicData = MagicData.PARALYSIS;
  }

  @Override
  public boolean hasEnoughMp(int mp) {
    return super.hasEnoughMp(mp);
  }


}
