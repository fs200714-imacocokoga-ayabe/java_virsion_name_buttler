package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public interface IUseType {

  void initType();
  int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage);
  public TypeData getType();
}
