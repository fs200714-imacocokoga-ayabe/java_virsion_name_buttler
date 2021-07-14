package new_create_app_name_battler.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import new_create_app_name_battler.party.BasePlayer;

public class BaseStrategy implements IStrategy {

  Random random = new Random();
  BasePlayer player;
  BasePlayer player1;
  BasePlayer player2;
  List<BasePlayer> party = new ArrayList<BasePlayer>();
  List<BasePlayer> party1 = new ArrayList<BasePlayer>();
  List<BasePlayer> party2 = new ArrayList<BasePlayer>();
  String strategyname;// 作戦の名前
  int id;// 味方IDと敵IDと作戦番号を格納

  @Override
  public int attackStrategy(BasePlayer player1, List<BasePlayer> party1, List<BasePlayer> party2) {
    return id;
  }
}
