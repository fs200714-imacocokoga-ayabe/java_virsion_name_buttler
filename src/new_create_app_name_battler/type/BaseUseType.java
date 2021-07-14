package new_create_app_name_battler.type;

import java.util.Random;
import new_create_app_name_battler.party.BasePlayer;

public class BaseUseType implements IUseType {

  Random random = new Random();
  protected TypeData typeData;
  protected int damage = 0;
  
  public BaseUseType(){
    this.initType();
  }

  @Override
  public int typeProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {
    return 0;
  }

  @Override
  public void initType() {}

  @Override
  public TypeData getType(){
    return typeData;
  }
}
