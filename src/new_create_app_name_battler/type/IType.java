package new_create_app_name_battler.type;

import java.util.Random;
import new_create_app_name_battler.party.BasePlayer;


public interface IType {

  Random random = new Random();

  int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage);

}
