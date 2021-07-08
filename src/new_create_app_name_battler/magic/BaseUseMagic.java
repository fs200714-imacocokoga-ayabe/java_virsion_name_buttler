package new_create_app_name_battler.magic;

import java.util.Random;
import new_create_app_name_battler.party.IPlayer;

public class BaseUseMagic implements IUseMagic {

  protected Random random = new Random();
  protected MagicData magicData;
  protected int damage;

  public BaseUseMagic() {
    this.initMagic();
  }

  @Override
  public void initMagic() {}

  @Override
  public int effect(IPlayer attacker, IPlayer defender) {
    return 0;
  }

  @Override
  public boolean hasEnoughMp(int mp) {
    return this.magicData.getMpcost() <= mp;
  }
}
