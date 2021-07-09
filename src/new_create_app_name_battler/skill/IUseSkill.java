package new_create_app_name_battler.skill;

import new_create_app_name_battler.party.IPlayer;

public interface IUseSkill {
  void initSkill();

  int effect(IPlayer attacker, IPlayer defender);
}
