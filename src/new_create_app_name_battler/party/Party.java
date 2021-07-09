package new_create_app_name_battler.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {

  Random random = new Random();

  public ArrayList<BasePlayer> members;// プレイヤーの入れ物
  public List<BasePlayer> party1 = new ArrayList<BasePlayer>();// パーティ1の入れ物
  public List<BasePlayer> party2 = new ArrayList<BasePlayer>();// パーティ2の入れ物

  public Party() {
    members = new ArrayList<BasePlayer>();
  }

  public ArrayList<BasePlayer> getMembers() {
    return members;
  }

  public void appendPlayer(BasePlayer player) {

    if (player.isMark()) {
      party1.add(player);// playerがtrueならparty1に入れる

    } else {
      party2.add(player);// playerがfalseならparty2に入れる
    }
  }

  public void removePlayer(BasePlayer player) {

    if (player.isMark()) {
      party1.remove(player);// playerがtrueならparty1から削除する

    } else {
      party2.remove(player);// playerがfalseならparty2から削除する
    }
  }

  public void setMembers(BasePlayer player) {
    members.add(player);
  }

  public void removeMembers(BasePlayer player) {
    members.remove(player);
  }

  public List<BasePlayer> getParty1() {
    return party1;
  }

  public List<BasePlayer> getParty2() {
    return party2;
  }

  public BasePlayer selectMember(int id) {

    for (int i = 0; i < members.size(); i++) {

      if (members.get(i).getIdNumber() == id) {

        return members.get(i);
      }
    }
    return null;
  }
}
