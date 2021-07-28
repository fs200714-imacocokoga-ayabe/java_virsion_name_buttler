package new_create_app_name_battler.party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import new_create_app_name_battler.herb.IEat;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.magic.IUseMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.skill.IUseSkill;
import new_create_app_name_battler.type.BaseUseType;
import new_create_app_name_battler.type.IOwnType;
import new_create_app_name_battler.type.TypeBlood;
import new_create_app_name_battler.type.TypeDark;
import new_create_app_name_battler.type.TypeData;
import new_create_app_name_battler.type.TypeDevil;
import new_create_app_name_battler.type.TypeHoly;
import new_create_app_name_battler.type.TypeShadow;
import new_create_app_name_battler.type.TypeShield;

public class BasePlayer implements IPlayer, IEat, IOwnType {

  Random random = new Random();
  List<IUseMagic> magics;
  IUseMagic magic;
  List<IUseSkill> skills;
  IUseSkill skill;
  JobData jobData;
  TypeData typeData;
  BaseUseType type;
  int typeNumber;
  String name;
  String job;
  int maxHp;// 最大HP
  int maxMp;// 最大MP
  int hp;// HP
  int mp;// MP
  int str;// 攻撃力
  int def;// 防御力
  int luck;// 運
  int agi;// すばやさ
  boolean poison;// 状態異常：毒
  boolean paralysis;// 状態異常：麻痺
  int damage;// damage値
  boolean mark;// 敵味方識別
  int idNumber;// ID値の入れ物
  int strategyData;
  int healValue;
  boolean isPhysicalAttack;
  String attackType;
  BasePlayer attacker;
  BasePlayer defender;

  public BasePlayer() {}

  public BasePlayer(String name) {
    magics = new ArrayList<>();
    skills = new ArrayList<>();
    this.name = name;
    initJob();
    makeCharacter();
  }

  @Override
  public void initJob() {}

  @Override
  public void initTypes(int typeNumber) {

    switch (typeNumber) {

      case 0:
        this.type = new TypeBlood();
        break;
      case 1:
        this.type = new TypeShield();
        break;
      case 2:
        this.type = new TypeDevil();
        break;
      case 3:
        this.type = new TypeDark();
        break;
      case 4:
        this.type = new TypeShadow();
        break;
      case 5:
        this.type = new TypeHoly();
        break;
    }
  }

  public void makeCharacter() {
    this.job = jobData.getJob();
    this.hp = jobData.getNumber(this.name, 0, jobData.getHp()) + jobData.getMinHp();
    this.mp = jobData.getNumber(this.name, 1, jobData.getMp()) + jobData.getMinMp();
    this.str = jobData.getNumber(this.name, 2, jobData.getStr()) + jobData.getMinStr();
    this.def = jobData.getNumber(this.name, 3, jobData.getDef()) + jobData.getMinDef();
    this.agi = jobData.getNumber(this.name, 4, jobData.getAgi()) + jobData.getMinAgi();
    this.luck = jobData.getNumber(this.name,5, jobData.getLuck()) + jobData.getMinLuck();
  }

  public TypeData getTypeName() {
    return this.type.getType();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getJob() {
    return this.job;
  }

  @Override
  public String getJobName() {
    return this.job + this.name;
  }

  @Override
  public void setMark(boolean mark) {
    this.mark = mark;
  }

  @Override
  public boolean isMark() {
    return mark;
  }

  @Override
  public void setIdNumber(int id) {
    this.idNumber = id;
  }

  @Override
  public int getIdNumber() {
    return idNumber;
  }

  @Override
  public void setMaxHp(int hp) {
    this.maxHp = hp;
  }

  @Override
  public int getMaxHp() {
    return this.maxHp;
  }

  @Override
  public void setMaxMp(int mp) {
    this.maxMp = mp;
  }

  @Override
  public int getMaxMp() {
    return this.maxMp;
  }

  @Override
  public int getHp() {
    return this.hp;
  }

  @Override
  public int getMp() {
    return this.mp;
  }

  @Override
  public int getStr() {
    return this.str;
  }

  @Override
  public int getDef() {
    return def;
  }

  @Override
  public int getAgi() {
    return this.agi;
  }

  @Override
  public int getLuck() {
    return luck;
  }

  @Override
  public boolean isPoison() {
    return poison;
  }

  @Override
  public void setPoison(boolean poison) {
    this.poison = poison;
  }

  @Override
  public boolean isParalysis() {
    return paralysis;
  }

  @Override
  public void setParalysis(boolean paralysis) {
    this.paralysis = paralysis;
  }

  public void normalAttack(BasePlayer defender) {}

  public void skillAttack(BasePlayer defender) {}

  public void magicAttack(BasePlayer defender) {}

  public void healingMagic(BasePlayer defender) {}

  public void eat() {
    selectEat(this);
   // conditionCheck();
    }

  public int calcDamage(BasePlayer defender) {

    int power = random.nextInt(this.getStr()) + 1;
    int luk = random.nextInt(100) + 1;

    if (luk <= this.getLuck()) {// 乱数の値がlukの値の中なら
      System.out.println("会心の一撃!");
      damage = this.getStr();

    } else {
      damage = power - defender.getDef();

      if (damage < 0) {
        System.out.printf("%sの攻撃はミス！\n", getName());
        damage = 0;
      }
    }
    return damage;
  }

  public void damageProcess(boolean isPhysicalAttack, BasePlayer attacker, BasePlayer defender, int damage) {
    damage = type.typeProcess(isPhysicalAttack, attacker, defender, damage);
    System.out.printf("%sに%dのダメージ！\n", defender.getName(), damage);
    defender.damage(damage);// 求めたダメージを対象プレイヤーに与える
  }

  @Override
  public void damage(int damage) {// ダメージ値分、HPを減少させる
    this.setHp(Math.max(this.getHp() - damage, 0));
  }


  public int recoveryProcess(BasePlayer defender, int healValue) {
    return healValue - defender.getHp();
  }

  @Override
  public void recovery(int healValue) {
    this.setHp(this.getHp() + healValue);
  }

  @Override
  public boolean isLive() {
    return 0 < this.getHp();
  }

  @Override
  public void printStatus() {

    String poison;
    String paralysis;

    if (this.isPoison()) {// 毒状態の場合
      poison = "[毒]";
    } else {// 毒になっていない場合
      poison = "";
    }

    if (this.isParalysis()) {// 麻痺状態の場合
      paralysis = "[麻痺]";
    } else {// 麻痺になっていない場合
      paralysis = "";
    }

    System.out.printf("[%s]%s(HP=%3d/%d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)%S%S\n",
        type.getType().getTypeName(), this.getJobName(), this.getHp(), getMaxHp(), this.getMp(),
        this.getStr(), this.getDef(), this.getLuck(), this.getAgi(), paralysis, poison);
  }

  public void knockedDownCheck(BasePlayer defender) {

    if (defender.getHp() <= 0) {
      System.out.printf("%sは力尽きた...\n", defender.getName());
    }
    conditionCheck();// 状態異常チェック
  }

  /**
   * 状態異常のチェック
   */
  public void conditionCheck() {

    if (isParalysis()) {// true:麻痺状態 false:麻痺していない

      if (MagicData.PARALYSIS.getContinuousRate() < random.nextInt(100) + 1) {// 麻痺の確立より乱数が上なら麻痺の解除
        setParalysis(false);// 麻痺解除
        System.out.printf("%sは麻痺が解けた！\n", getName());
      }
    }

    if (isPoison()) {// true:毒状態 false:無毒状態

      damage(MagicData.POISON.getMaxDamage());// 毒のダメージ計算
      System.out.printf("%sは毒のダメージを%d受けた！\n", getName(), MagicData.POISON.getMaxDamage());
    }

    if (this.getHp() <= 0) {// playerの倒れた判定
      System.out.printf("%sは力尽きた...\n", getName());
    }
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  @Override
  public void downMp(int mpCost) {
    this.mp = this.mp - mpCost;// MPを消費

  }

  public IUseMagic choiceMagic() {

    Collections.shuffle(magics);

    for (IUseMagic magic : magics) {

      if (!(magic instanceof IRecoveryMagic)) {
        return magic;
      }
    }
    return magic;
  }
}
