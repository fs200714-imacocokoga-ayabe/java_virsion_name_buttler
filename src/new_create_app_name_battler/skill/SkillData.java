package new_create_app_name_battler.skill;

public enum SkillData {

  ASSAULT("突撃", 30), SWALLOW("燕返し", 30), ;

  private String name;
  private int invocationRate;// 発動率

  private SkillData(String name, int i) {

    this.name = name;
    this.invocationRate = i;
  }

  public String getName() {

    return this.name;
  }

  public int getInvocationRate() {

    return invocationRate;
  }

}
