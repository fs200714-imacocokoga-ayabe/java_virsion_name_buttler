package new_create_app_name_battler.party;

import java.util.ArrayList;
import java.util.List;

public enum JobData {

  FIGHTER(1, "戦士", 200, 100, 0, 0, 70, 30, 70, 30, 99, 1, 49, 1),
  WIZARD(2, "魔法使い", 100, 50, 50, 30, 49, 1, 49, 1, 99, 1, 40, 20),
  PRIEST(3, "僧侶", 120, 80, 30, 20, 40, 10, 60, 10, 99, 1, 40,20),
  NINJA(4, "忍者", 100, 70, 20, 10, 50, 20, 49, 1, 99, 1, 40, 40), ;

  private int id;
  private String job;
  private int hp;
  private int minHp;
  private int mp;
  private int minMp;
  private int str;
  private int minStr;
  private int def;
  private int minDef;
  private int agi;
  private int minAgi;
  private int luck;
  private int minLuck;

  private JobData(int id, String job, int hp, int minHp, int mp, int minMp, int str, int minStr,
      int def, int minDef, int agi, int minAgi, int luck, int minLuck) {

    this.id = id;
    this.job = job;
    this.hp = hp;
    this.minHp = minHp;
    this.mp = mp;
    this.minMp = minMp;
    this.str = str;
    this.minStr = minStr;
    this.def = def;
    this.minDef = minDef;
    this.agi = agi;
    this.minAgi = minAgi;
    this.luck = luck;
    this.minLuck = minLuck;
  }

  List<JobData> jobList = new ArrayList<JobData>();

  public void jobList(){

    for(JobData j : JobData.values()){
      jobList.add(j);
    }
  }

  public List<JobData> getJobList(){
    return jobList;
  }

  public int getId() {
    return id;
  }

  public String getJob() {
    return this.job;
  }

  public int getHp() {
    return hp;
  }

  public int getMinHp() {
    return minHp;
  }

  public int getMp() {
    return mp;
  }

  public int getMinMp() {
    return minMp;
  }

  public int getStr() {
    return str;
  }

  public int getMinStr() {
    return minStr;
  }

  public int getDef() {
    return def;
  }

  public int getMinDef() {
    return minDef;
  }

  public int getAgi() {
    return agi;
  }

  public int getMinAgi() {
    return minAgi;
  }

  public int getLuck() {
    return luck;
  }

  public int getMinLuck() {
    return minLuck;
  }
}

