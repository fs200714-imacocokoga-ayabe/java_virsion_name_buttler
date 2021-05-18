package new_create_app_name_battler.type;

import new_create_app_name_battler.party.BasePlayer;

public class TypeContext {

  IType itype;
  String attackType;
  BasePlayer defender;
  int damage;

  public TypeContext(IType itype) {
    this.itype = itype;
  }

  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {
    return this.itype.typeProcess(attackType, attacker, defender, damage);
  }

}
