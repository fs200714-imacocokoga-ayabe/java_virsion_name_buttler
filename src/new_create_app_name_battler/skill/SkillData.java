package new_create_app_name_battler.skill;

public enum SkillData {

  ASSAULT("突撃", 30, 0, 0, 0), SWALLOW("燕返し", 30, 0, 0, 0),
  FIREELEMENTAL("炎の精霊", 30, 0, 60, 0),OPTICALELEMENTAL("光の精霊", 40, 0, 0, 70), ;

  private String name;// スキルの名前
  private int invocationRate;// 発動率
  private int minDamage;// 最小ダメージ
  private int maxDamage;// 最大ダメージ
  private int recoveryValue;// 回復量

  private SkillData(String name, int i, int j, int k, int l) {

    this.name = name;
    this.invocationRate = i;
    this.minDamage = j;
    this.maxDamage = k;
    this.recoveryValue = l;
  }

  public String getName() {
    return this.name;
  }

  public int getInvocationRate() {
    return invocationRate;
  }

  public int getMinDamage() {
    return minDamage;
  }

  public int getMaxDamage() {
    return maxDamage;
  }

  public int getRecoveryValue() {
    return recoveryValue;
  }
}



