package new_create_app_name_battler.party;

public interface IPlayer {

  void damage(int damage);

  void recovery(int healValue);

  void conditionCheck();

  boolean isLive();

  boolean isPoison();

  boolean isParalysis();

  String getName();

  String getJob();

  String getJobName();

  int getMaxHp();

  int getMaxMp();

  int getHp();

  int getMp();

  int getStr();

  int getDef();

  int getAgi();

  int getLuck();

  void setMark(boolean b);

  void setIdNumber(int i);

  void setMaxHp(int hp);

  void setMaxMp(int mp);

  void printStatus();

  int getIdNumber();

  boolean isMark();

  void setPoison(boolean poison);

  void setParalysis(boolean paralysis);

  void setType(int a);

  int getType();

}
