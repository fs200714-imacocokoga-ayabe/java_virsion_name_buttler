package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.BasePlayer;

public class BaseMagicalUsable implements IMagicalUsable {

  @Override
  public int effect(BasePlayer defender) {
    return 0;
  }

  @Override
  public boolean hasEnoughMp() {
    return false;
  }

}
