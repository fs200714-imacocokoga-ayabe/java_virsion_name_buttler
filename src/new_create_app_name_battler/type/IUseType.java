package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public interface IUseType {

  void initType();
  int typeProcess(boolean isPhysicalAttack, BasePlayer attacker, BasePlayer defender, int damage);
  TypeData getType();
}
