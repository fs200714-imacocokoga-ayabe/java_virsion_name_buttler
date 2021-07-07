package new_create_app_name_battler.magic;

import java.util.ArrayList;
import java.util.List;

public enum MagicData {

  FIRE("ファイア", 10, 10, 30, 0, 0, 0), THUNDER("サンダー", 20, 20, 50, 0, 0, 0), HEAL("ヒール", 20, 0, 0,
      50, 0, 0), PARALYSIS("パライズ", 10, 0, 0, 0, 20, 0), POISON("ポイズン", 10, 0, 20, 0, 0, 0), FIREROLL(
      "火遁の術", 10, 10, 30, 0, 0, 0), ;

  private String name;// 魔法の名前
  private int mpCost;// 消費MP
  private int minDamage;// 最小ダメージ
  private int maxDamage;// 最大ダメージ
  private int recoveryValue;// 回復量
  private int continuousRate;// 継続率
  private int invocationRate;// 発動率

  private MagicData(String name, int i, int j, int k, int l, int m, int n) {

    this.name = name;
    this.mpCost = i;
    this.minDamage = j;
    this.maxDamage = k;
    this.recoveryValue = l;
    this.continuousRate = m;
    this.invocationRate = n;
  }

  List<MagicData> magicalList = new ArrayList<MagicData>();

  public void magicList() {

    for (MagicData m : MagicData.values()) {
      magicalList.add(m);
    }
  }

  public List<MagicData> getMagicalList() {

    return magicalList;
  }

  /**
   * 魔法の名前を取得する
   *
   * @return name :魔法の名前
   */
  public String getName() {

    return this.name;
  }

  /**
   * 消費MPを取得する
   *
   * @return mpcost :消費MP
   */
  public int getMpcost() {

    return mpCost;
  }

  /**
   * 最小ダメージを取得する
   *
   * @return mindamage :最小ダメージ
   */
  public int getMinDamage() {

    return minDamage;
  }

  /**
   * 最大ダメージを取得する
   *
   * @return maxdamage :最大ダメージ
   */
  public int getMaxDamage() {

    return maxDamage;
  }

  /**
   * 回復量を取得する
   *
   * @return recoveryvalue :回復量
   */
  public int getRecoveryValue() {

    return recoveryValue;
  }

  /**
   * 継続率を取得する
   *
   * @return continuousrate :継続率
   */
  public int getContinuousRate() {

    return continuousRate;
  }

  public int getInvocationRate() {

    return invocationRate;
  }
}
