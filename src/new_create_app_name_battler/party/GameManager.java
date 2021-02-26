package new_create_app_name_battler.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import new_create_app_name_battler.strategy.Context;
import new_create_app_name_battler.strategy.StrategyNormalAttack;
import new_create_app_name_battler.strategy.StrategyUseHealingMagic;
import new_create_app_name_battler.strategy.StrategyUseHerb;
import new_create_app_name_battler.strategy.StrategyUseMagic;
import new_create_app_name_battler.strategy.StrategyUseSkill;

public class GameManager {

	Random random = new Random();
	Context context;
	Scanner s = new Scanner(System.in);
	Party party = new Party();// Partyクラスの呼び出し用
	List<IPlayer> jobList = new ArrayList<IPlayer>();// 職業を格納

	IPlayer player;
	IPlayer player1;
	IPlayer player2;

	int allyStrategyNumber;// 作戦の選択に使用
	int enemyStrategyNumber;// 作戦の選択に使用
	int strategyNumber;// 個別の作戦の選択に使用
	int[] strategyData = new int[2];// 攻撃プレイヤーIDと守備プレイヤーIDと作戦番号を格納

	private IPlayer[] speedData = new IPlayer[6];
	List<IPlayer> attackList = new ArrayList<IPlayer>();// 行動するプレイヤーを格納

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
		System.out.println("");
		System.out.println("=== バトル開始 ===");
		int turnNumber = 1;// ターンの初期値

		while (turnNumber <= 20) {// 最大でも20ターンまで

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
			for (int i = 0; i < party.getMembers().size(); i++) {// 人数分繰り返し

				player = party.getMembers().get(i);
				attackList.add(player);// attackにplayerを格納
			}

			for (int i = 0; i < attackList.size(); i++) {// attackに格納したplayerが全員行動する

				player1 = attackList.get(i);// 攻撃リストから呼び出し

				if (player1.isLive()) {

					if (player1.isParalysis()) {

						System.out.printf("%sは麻痺で動けない！！\n", player1.getName());

						break;

					} else {

						if (player1.isMark()) {// player1が味方の場合

							selectStrategyNumber(allyStrategyNumber);

						} else {

							enemyStrategyNumber = random.nextInt(5) + 1;// 作戦ランダム
							selectStrategyNumber(enemyStrategyNumber);
						}

						System.out.println("");

						if (player1.getHp() <= 0) {// プレイヤー１の敗北判定

							party.removePlayer(player1);// プレイヤー１がHP0の場合パーティから削除する
							party.removeMembers(player1);// 死亡したプレイヤーを削除する

						}

						if (player2.getHp() <= 0) {// 相手プレイヤーがHP0の場合

							party.removePlayer(player2);// 相手プレイヤーをパーティから削除する
							party.removeMembers(player2);// 死亡したプレイヤーを削除する
						}

						if (party.getParty1().size() <= 0
								|| party.getParty2().size() <= 0) {

							break;
						}
					}

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
	}

	private void selectStrategyNumber(int number) {

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
		}

		context.attackStrategy(player1, party.getParty1(), party.getParty2());

	}

	private void attackOrder() throws InterruptedException {

		System.out.println("");
		System.out.println("---攻撃の順番---");// 攻撃順

		for (int i = 0; i < party.getMembers().size(); i++) {// パーティ1,パーティ2に振り分ける処理

			player = party.getMembers().get(i);// membersからプレイヤーを呼び出す
			player.printStatus();// ステータスの表示

		}
	}

	private void printParty() throws InterruptedException {

		System.out.println("");

		if (party.getParty1().size() > 0) {// パーティ1が1人以上の場合

			System.out.println("パーティ1");

			for (int i = 0; i < party.getParty1().size(); i++) {

				player = party.getParty1().get(i);
				player.printStatus();// パーティ1のプレイヤーのステータスを表示する
			}
		}

		if (party.getParty1().size() > 0) {// パーティ2が1人以上の場合

			System.out.println("パーティ2");

			for (int i = 0; i < party.getParty2().size(); i++) {

				player = party.getParty2().get(i);
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

				player1 = speedData[j];
				player2 = speedData[j + 1];

				if (player1.getAgi() < player2.getAgi()) {

					IPlayer box = speedData[j];
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
			System.out.printf("1-プレイヤー作成 2-作成済みプレイヤー");
			try

			{

				menu = s.nextInt();
				s.nextLine();

				if (menu == 1 || menu == 2) {

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

			this.fixePlayer();// 固定playerを使う

		}
	}

	private void fixePlayer() throws InterruptedException {

		jobList.add(new JobFighter("味方アシュラム"));
		jobList.add(new JobWizard("味方スレイン"));
		jobList.add(new JobPriest("味方ゼロス"));
		jobList.add(new JobNinja("敵ウッド"));
		jobList.add(new JobWizard("敵ガンダルフ"));
		jobList.add(new JobPriest("敵バグナード"));

		for (int i = 1; i <= 6; i++) {

			if (i <= 3) {// 最初の3人

				player = jobList.get(i - 1);// 選択した職業をplayerに入れ
				player.setMark(true);// プレイヤーにtrue(識別)をセットする

			} else {// 次の3人

				player = jobList.get(i - 1);// 選択した職業をplayerに入れ
				player.setMark(false);// プレイヤーにfalse(識別)をセットする

			}

			player.setIdNumber(i);
			player.setMaxHp(player.getHp());
			player.printStatus();
			speedData[i - 1] = player;// 速さ順ソートで使用する

		}
	}

	private void makePlayer() {

		int job;

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

			} else if (4 <= i && i <= 6) {

				System.out.printf("対戦相手%dの名前を入力してください：", i - 3);

			}

			String name = s.nextLine();

			while (true)

			{

				System.out.printf("%sの職業を選択して下さい(1-戦士 2-魔法使い 3-僧侶 4-忍者)", name);
				try

				{

					job = s.nextInt();
					s.nextLine();

					if (job >= 1 && job <= 4)

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

			player.setIdNumber(i);// IDをセットする
			player.setMaxHp(player.getHp());// MaxHPをセットする
			player.printStatus();
			System.out.println("");
			speedData[i - 1] = player;

		}
	}

	/**
	 * 選択した職業をjobListに格納する
	 *
	 * @param job
	 *            :選択した職業の数字
	 * @param name
	 *            :入力した名前
	 */
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
