package new_create_app_name_battler.strategy;

import java.util.List;

import new_create_app_name_battler.party.IPlayer;

public class StrategyNormalAttack extends BaseStrategy {// 通常攻撃

	/**
	 *player,party1,party2を受け取りnormalAttackを実行
	 * @param player1 :自身
	 * @param party1 :パーティ1
	 * @param party2 :パーティ2
	 */
	@Override
	public void attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2) {

		this.player1 = player1;

		if (player1.isMark()) {// player1がtrueの場合

			party.addAll(party2);// partyにparty2を入れる

		} else {// player1がfalseの場合

			party.addAll(party1);// partyにparty1を入れる
		}

		int a = random.nextInt(party.size());

		player2 = party.get(a);

		player1.normalAttack(player2);
		player1.fall(player2);

		party.clear();

	}
}
