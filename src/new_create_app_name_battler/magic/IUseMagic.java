package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.IPlayer;

public interface IUseMagic {

  void initMagic();

  int effect(IPlayer attacker, IPlayer defender);

  boolean hasEnoughMp(int mp);
}
