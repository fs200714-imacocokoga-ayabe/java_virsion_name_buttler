package new_create_app_name_battler.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import new_create_app_name_battler.party.BasePlayer;
import new_create_app_name_battler.party.JobFighter;
import new_create_app_name_battler.party.JobNinja;
import new_create_app_name_battler.party.JobPriest;
import new_create_app_name_battler.party.JobWizard;
import new_create_app_name_battler.party.Party;
import new_create_app_name_battler.strategy.Context;
import new_create_app_name_battler.strategy.StrategyEnemyPattern;
import new_create_app_name_battler.strategy.StrategyNormalAttack;
import new_create_app_name_battler.strategy.StrategyUseHealingMagic;
import new_create_app_name_battler.strategy.StrategyUseHerb;
import new_create_app_name_battler.strategy.StrategyUseMagic;
import new_create_app_name_battler.strategy.StrategyUseSkill;
import new_create_app_name_battler.type.TypeData;

public class GameManager {

  Context context;
  Random random = new Random();
  Scanner s = new Scanner(System.in);
  Party party = new Party();// Partyクラスの呼び出し用
  List<BasePlayer> jobList = new ArrayList<BasePlayer>();// 職業を格納

  BasePlayer player;
  BasePlayer player1;
  BasePlayer player2;

  int allyStrategyNumber;// 作戦の選択に使用
  final int ENEMY_STRATEGY_NUMBER = 6;// 作戦の選択に使用
  private BasePlayer[] speedData = new BasePlayer[6];
  List<BasePlayer> attackList = new ArrayList<BasePlayer>();// 行動するプレイヤーを格納

  final int MAKE_CHARACTER = 1;
  final int CREATED_CHARACTER = 2;
  final int SETTING = 3;
  final int DISPLAY_SPEED_FAST = 0;
  final int DISPLAY_SPEED_NORMAL = 750;
  final int DISPLAY_SPEED_SLOW = 1000;
  final int DISPLAY_SPEED_VERY_SLOW = 2000;
  final int MAX_TURN_NUMBER = 20;

  // 初期表示速度
  int speedChanePlace1 = DISPLAY_SPEED_SLOW;// 開始表示速度
  int speedChanePlace2 = DISPLAY_SPEED_NORMAL;// ステータス表示速度
  int speedChanePlace3 = DISPLAY_SPEED_SLOW;// 戦闘中メッセージ速度

  public void start() throws InterruptedException {

    opening();// 最初のメニュー
    speedReordering();// 速さ順にソート
    divideParty();// パーティを分ける
    printParty();// パーティの表示
    attackOrder();// 攻撃順の表示
    battle();
  }

  private void battle() throws InterruptedException {
    // バトル開始の表示
    Thread.sleep(speedChanePlace1);
    System.out.println("");
    System.out.println("=== バトル開始 ===");
    int turnNumber = 1;// ターンの初期値

    while (turnNumber <= MAX_TURN_NUMBER) {

      System.out.println("--------------------------------");
      System.out.printf("- ターン%d -\n", turnNumber);
      System.out.println("-----作戦を選んでください-----");

      while (true)

      {
        // 作戦メニューの表示
        System.out.println("1-通常攻撃 2-魔法 3-スキル 4-回復魔法 5-薬草");

        try {
          allyStrategyNumber = s.nextInt();
          s.nextLine();

          if (1 <= allyStrategyNumber && allyStrategyNumber <= 5) {
            break;
          } else

            new Exception();
        }

        catch (Exception e)

        {
          s.nextLine();
          System.out.printf("1～5の数字を入力して下さい。\n");
        }
      }

      // ■プレイヤーの行動ターン
      // 生きているplayerをattackListに格納
      for (int i = 0; i < party.getMembers().size(); i++) {
        player = party.getMembers().get(i);
        attackList.add(player);// attackにplayerを格納
      }

      // attackに格納したplayerが全員行動する
      for (int i = 0; i < attackList.size(); i++) {

        player = attackList.get(i);// 攻撃リストから呼び出し

        if (player.isLive()) {

          if (player.isParalysis()) {// 麻痺している場合

            System.out.printf("%sは麻痺で動けない！！\n", player.getName());
            player.knockedDownCheck(player);

          } else {// 麻痺していない場合

            if (player.isMark()) {// player1が味方の場合

              player2 = party.selectMember(selectStrategyType(allyStrategyNumber));

            } else {// player1が敵の場合

              player2 = party.selectMember(selectStrategyType(ENEMY_STRATEGY_NUMBER));
            }
          }

          Thread.sleep(speedChanePlace3);

          if (player.getHp() <= 0) {// プレイヤー１の敗北判定
            party.removePlayer(player);// プレイヤー１がHP0の場合パーティから削除する
            party.removeMembers(player);// 死亡したプレイヤーを削除する
          }

          if (player2.getHp() <= 0) {// 相手プレイヤーがHP0の場合
            party.removePlayer(player2);// 相手プレイヤーをパーティから削除する
            party.removeMembers(player2);// 死亡したプレイヤーを削除する
          }
        }

        System.out.println("");

        if (party.getParty1().size() <= 0 || party.getParty2().size() <= 0) {
          break;
        }
      }

      printParty();

      // Party1またはParty2が全滅していた場合処理を抜ける
      if (party.getParty1().size() <= 0 || party.getParty2().size() <= 0) {

        break;
      }

      attackList.clear();
      turnNumber += 1;
    }

    // 勝ち負けの表示(残っているパーティの勝ち)
    System.out.println("");

    if (party.getParty1().size() <= 0) {// パーティ1が0の場合

      System.out.println("パーティ2の勝利！！");

    } else if (party.getParty2().size() <= 0) {// パーティ2が0の場合

      System.out.println("パーティ１の勝利！！");

    } else {

      System.out.println("引き分け");// お互いパーティが全滅していなければ引き分け
    }
    s.close();
  }

  private int selectStrategyType(int number) {

    switch (number) {
      case 1:
        context = new Context(new StrategyNormalAttack());
        break;
      case 2:
        context = new Context(new StrategyUseMagic());
        break;
      case 3:
        context = new Context(new StrategyUseSkill());
        break;
      case 4:
        context = new Context(new StrategyUseHealingMagic());
        break;
      case 5:
        context = new Context(new StrategyUseHerb());
        break;
      case 6:
        context = new Context(new StrategyEnemyPattern());
        break;
    }
    return context.attackStrategy(player, party.getParty1(), party.getParty2());
  }


  private void attackOrder() throws InterruptedException {

    System.out.println("");
    Thread.sleep(speedChanePlace1);
    System.out.println("---攻撃の順番---");// 攻撃順

    for (int i = 0; i < party.getMembers().size(); i++) {// パーティ1,パーティ2に振り分ける処理
      player = party.getMembers().get(i);// membersからプレイヤーを呼び出す
      Thread.sleep(speedChanePlace2);
      player.printStatus();// ステータスの表示
    }
  }

  private void printParty() throws InterruptedException {

    System.out.println("");

    if (0 < party.getParty1().size()) {// パーティ1が1人以上の場合

      System.out.println("パーティ1");

      for (int i = 0; i < party.getParty1().size(); i++) {

        player = party.getParty1().get(i);
        Thread.sleep(speedChanePlace2);
        player.printStatus();// パーティ1のプレイヤーのステータスを表示する
      }
    }

    if (0 < party.getParty2().size()) {// パーティ2が1人以上の場合

      System.out.println("パーティ2");

      for (int i = 0; i < party.getParty2().size(); i++) {
        player = party.getParty2().get(i);
        Thread.sleep(speedChanePlace2);
        player.printStatus();// パーティ2のプレイヤーのステータスを表示す
      }
    }
  }

  private void divideParty() {

    for (int i = 0; i < party.getMembers().size(); i++) {

      player = party.getMembers().get(i);// membersからプレイヤーを呼び出す

      if (player.isMark()) {

        party.appendPlayer(player);// trueならパーティ1に加える

      } else {

        party.appendPlayer(player);// falseならパーティ2に加える
      }
    }
  }

  private void speedReordering() {

    for (int i = 0; i < speedData.length - 1; i++) {// 速さ順の並び替え処理

      for (int j = 0; j < speedData.length - i - 1; j++) {
        BasePlayer player1 = speedData[j];
        BasePlayer player2 = speedData[j + 1];

        if (player1.getAgi() < player2.getAgi()) {
          BasePlayer box = speedData[j];
          speedData[j] = speedData[j + 1];
          speedData[j + 1] = box;
        }
      }
    }

    for (int i = 0; i < speedData.length; i++) {// membersに速さ順に格納
      player = speedData[i];
      party.setMembers(player);// membersにplayerを加える
    }
  }

  private void opening() throws InterruptedException {

    int menu;

    while (true)

    {
      System.out.printf("1-プレイヤー作成 2-作成済みプレイヤー 3-設定");
      System.out.println("");
      try

      {
        menu = s.nextInt();
        s.nextLine();

        if (menu == SETTING) {// 設定を選択した場合

          Setting();

        }

        if (menu == MAKE_CHARACTER || menu == CREATED_CHARACTER) {
          break;
        }

        else

          new Exception();
      }

      catch (Exception e)

      {
        s.nextLine();
        System.out.printf("input 1 or 2\n");
      }
    }

    if (menu == 1) {

      this.makePlayer();// プレイヤー1～6のキャラクターを作成

    } else {

      this.createdPlayer();// 固定playerを使う
    }
  }

  private void Setting() {

    int ss;

    while (true) {

      System.out.printf("%s\n1-%s 2-%s 3-%s\n", "戦闘中メッセージ", "普通", "早い", "遅い");

      try {
        ss = s.nextInt();
        s.nextLine();

        if (1 <= ss && ss <= 3) {

          break;

        } else

          new Exception();

      } catch (Exception e) {

        s.nextLine();
        System.out.printf("input 1 - 3\n");
      }

    }

    switch (ss) {

      case 1:
        speedChanePlace1 = DISPLAY_SPEED_SLOW;
        speedChanePlace2 = DISPLAY_SPEED_NORMAL;
        speedChanePlace3 = DISPLAY_SPEED_SLOW;
        break;

      case 2:
        speedChanePlace1 = DISPLAY_SPEED_FAST;
        speedChanePlace2 = DISPLAY_SPEED_FAST;
        speedChanePlace3 = DISPLAY_SPEED_FAST;
        break;

      case 3:
        speedChanePlace1 = DISPLAY_SPEED_SLOW;
        speedChanePlace2 = DISPLAY_SPEED_NORMAL;
        speedChanePlace3 = DISPLAY_SPEED_VERY_SLOW;
        break;

    }
  }

  private void createdPlayer() throws InterruptedException {

    jobList.add(new JobFighter("味方アシュラム"));
    jobList.add(new JobWizard("味方スレイン"));
    jobList.add(new JobPriest("味方ゼロス"));
    jobList.add(new JobNinja("敵ウッド"));
    jobList.add(new JobWizard("敵ガンダルフ"));
    jobList.add(new JobPriest("敵バグナード"));

    for (int i = 1; i <= 6; i++) {

      if (i <= 3) {// 最初の3人

        player = jobList.get(i - 1);// 選択した職業をplayerに入れ
        player.initTypes(i - 1);
        player.setMark(true);// プレイヤーにtrue(識別)をセットする

      } else {// 次の3人
        player = jobList.get(i - 1);// 選択した職業をplayerに入れ
        player.initTypes(i - 1);
        player.setMark(false);// プレイヤーにfalse(識別)をセットする
      }

      player.setIdNumber(i);
      player.setMaxHp(player.getHp());
      Thread.sleep(speedChanePlace2);
      player.printStatus();
      speedData[i - 1] = player;// 速さ順ソートで使用する
    }
  }

  private void makePlayer() {

    int job;
    int type;
    String name = "";

    jobList.add(new JobFighter(""));// プレイヤー作成のメニュに表示するため初期設定jobList0に格納
    jobList.add(new JobWizard(""));// 初期設定jobList1に格納
    jobList.add(new JobPriest(""));// 初期設定jobList2に格納
    jobList.add(new JobNinja(""));// 初期設定jobList3に格納

    for (int i = 1; i <= 6; i++) {// playerの作成

      if (i == 1) {
        System.out.println("操作するプレイヤーを3人作成してください\n");

      } else if (i == 4) {

        System.out.println("対戦相手を3人作成してください");

      }

      if (1 <= i && i <= 3) {

        System.out.printf("プレイヤー%dの名前を入力してください：", i);

        name = checkInputLength();

      } else if (4 <= i && i <= 6) {

        System.out.printf("対戦相手%dの名前を入力してください：", i - 3);

        name = checkInputLength();

      }

      while (true)

      {

        System.out.printf("%sの職業を選択して下さい(1-戦士 2-魔法使い 3-僧侶 4-忍者)", name);
        try

        {

          job = s.nextInt();
          s.nextLine();

          if (1 <= job && job <= 4)

            break;

          else

            new Exception();
        }

        catch (Exception e)

        {

          s.nextLine();
          System.out.println("適切な値を入力してください");
        }
      }

      while (true)

      {

        System.out.printf("%sの属性を1つ選択して下さい(1-%s 2-%s 3-%s 4-%s 5-%s 6-%s 7-(説明))", name,
            TypeData.BLOOD.getTypeName(), TypeData.SHIELD.getTypeName(),
            TypeData.DEVIL.getTypeName(), TypeData.DARK.getTypeName(),
            TypeData.SHADOW.getTypeName(), TypeData.HOLY.getTypeName());

        try

        {

          type = s.nextInt();
          s.nextLine();

          if (type == 7) {
            typeMemo();// 属性の説明
          }

          if (1 <= type && type <= 6)

            break;

          else

            new Exception();
        }

        catch (Exception e)

        {
          s.nextLine();
          System.out.println("適切な値を入力してください");
        }
      }

      if (i <= 3) {// 最初の3人

        JobSelect(job, name);
        player = jobList.get(i + 3);
        player.setMark(true);// 操作するプレイヤーにtrue(識別)をセットする

      } else {// 次の3人

        JobSelect(job, name);
        player = jobList.get(i + 3);
        player.setMark(false);// 対戦相手ににfalse(識別)をセットする
      }

      player.initTypes(type - 1);
      player.setIdNumber(i);// IDをセットする
      player.setMaxHp(player.getHp());// MaxHPをセットする
      player.printStatus();
      System.out.println("");
      speedData[i - 1] = player;
    }
  }

  private String checkInputLength() {

    String name;

    while (true) {

      name = s.nextLine();

      if (name.length() <= 10) {

        break;

      } else {

        System.out.printf("10文字以内でプレイヤーの名前を入力してください：");
      }
    }
    return name;
  }

  private void typeMemo() {

    System.out.println("");

    System.out.printf("%s %s %s %s %s %s\n\n", TypeData.BLOOD.getDescription(),
        TypeData.SHIELD.getDescription(), TypeData.DEVIL.getDescription(),
        TypeData.DARK.getDescription(), TypeData.SHADOW.getDescription(),
        TypeData.HOLY.getDescription());
  }

  private void JobSelect(int job, String name) {

    switch (job) {

      case 1:
        jobList.add(new JobFighter(name));
        break;

      case 2:
        jobList.add(new JobWizard(name));
        break;

      case 3:
        jobList.add(new JobPriest(name));
        break;

      case 4:
        jobList.add(new JobNinja(name));
        break;
    }
  }
}
