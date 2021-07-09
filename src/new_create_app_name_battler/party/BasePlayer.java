package new_create_app_name_battler.party;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java_virsion_name_battler.herb.IEat;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.magic.IUseMagic;
import new_create_app_name_battler.magic.MagicData;
import new_create_app_name_battler.skill.IUseSkill;
import new_create_app_name_battler.type.TypeBlood;
import new_create_app_name_battler.type.TypeContext;
import new_create_app_name_battler.type.TypeDark;
import new_create_app_name_battler.type.TypeData;
import new_create_app_name_battler.type.TypeDevil;
import new_create_app_name_battler.type.TypeHoly;
import new_create_app_name_battler.type.TypeShadow;
import new_create_app_name_battler.type.TypeShield;

public class BasePlayer implements IPlayer, IEat {

  Random random = new Random();

  protected List<IUseMagic> magics;
  protected IUseMagic magic;
  protected List<IUseSkill> skills;
  protected IUseSkill skill;
  protected JobData jobData;
  protected String name;
  protected String job;
  protected int maxHp;// 最大HP
  protected int maxMp;// 最大MP
  private int hp;// HP
  protected int mp;// MP
  protected int str;// 攻撃力
  protected int def;// 防御力
  protected int luck;// 運
  protected int agi;// すばやさ
  protected boolean poison;// 状態異常：毒
  protected boolean paralysis;// 状態異常：麻痺
  protected int damage;// damage値
  protected boolean mark;// 敵味方識別
  protected int idNumber;// ID値の入れ物
  protected int strategyData;
  protected int healValue;
  // private final int HERB_RECOVERY_VALUE = 30;
  protected int type;
  protected String attackType;

  private TypeContext typeContext;

  protected BasePlayer attacker;
  protected BasePlayer defender;

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

  public void makeCharacter() {
    this.job = jobData.getJob();
    this.hp = getNumber(0, jobData.getHp()) + jobData.getMinHp();
    this.mp = getNumber(1, jobData.getMp()) + jobData.getMinMp();
    this.str = getNumber(2, jobData.getStr()) + jobData.getMinStr();
    this.def = getNumber(3, jobData.getDef()) + jobData.getMinDef();
    this.agi = getNumber(5, jobData.getAgi()) + jobData.getMinAgi();
    this.luck = getNumber(4, jobData.getLuck()) + jobData.getMinLuck();
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

  /**
   * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
   *
   * @param index : 何番目の数値を取り出すか
   * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
   * @return 数値(0～max) ※maxも含む
   */
  protected int getNumber(int index, int max) {
    try {
      // 名前からハッシュ値を生成する
      byte[] result = MessageDigest.getInstance("SHA-1").digest(this.name.getBytes());
      String digest = String.format("%040x", new BigInteger(1, result));

      // ハッシュ値から指定された位置の文字列を取り出す（２文字分）
      String hex = digest.substring(index * 2, index * 2 + 2);

      // 取り出した文字列（16進数）を数値に変換する
      int val = Integer.parseInt(hex, 16);
      return val * max / 255;
    } catch (Exception e) {
      // エラー
      e.printStackTrace();
    }
    return 0;
  }

  public void normalAttack(BasePlayer defender) {}

  public void skillAttack(BasePlayer defender) {}

  public void magicAttack(BasePlayer defender) {}

  public void healingMagic(BasePlayer defender) {}

  public void eat() {

    selectEat(this);

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

  public void damageProcess(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    damage = selectType(attackType, attacker, defender, damage);// 属性処理
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
        this.getTypeName(this.type), this.getJobName(), this.getHp(), getMaxHp(), this.getMp(),
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

  private int selectType(String attackType, BasePlayer attacker, BasePlayer defender, int damage) {

    switch (defender.getType()) {

      case 1:
        typeContext = new TypeContext(new TypeBlood());
        break;
      case 2:
        typeContext = new TypeContext(new TypeShield());
        break;
      case 3:
        typeContext = new TypeContext(new TypeDevil());
        break;
      case 4:
        typeContext = new TypeContext(new TypeDark());
        break;
      case 5:
        typeContext = new TypeContext(new TypeShadow());
        break;
      case 6:
        typeContext = new TypeContext(new TypeHoly());
        break;
    }

    int d = typeContext.typeProcess(attackType, attacker, defender, damage);
    return d;
  }

  private String getTypeName(int t) {

    String typeName = "";

    switch (t) {

      case 0:
        typeName = TypeData.BLOOD.getTypeName();
        break;
      case 1:
        typeName = TypeData.SHIELD.getTypeName();
        break;
      case 2:
        typeName = TypeData.DEVIL.getTypeName();
        break;
      case 3:
        typeName = TypeData.DARK.getTypeName();
        break;
      case 4:
        typeName = TypeData.SHADOW.getTypeName();
        break;
      case 5:
        typeName = TypeData.HOLY.getTypeName();
        break;

    }
    return typeName;
  }

  @Override
  public void setType(int i) {
    this.type = i;

  }

  @Override
  public int getType() {

    return type;
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
    return null;
  }

}
