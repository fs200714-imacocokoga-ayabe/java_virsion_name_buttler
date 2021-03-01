package new_create_app_name_battler.strategy;

import java.util.List;

import new_create_app_name_battler.party.IPlayer;

public class StrategyUseSkill extends BaseStrategy {// スキル攻撃

	/**
	 *player1,party1,party2を受け取りskillAttackを実行する
	 * @param player1 :自身
	 * @param party1 :パーティ1
	 * @param party2 :パーティ2
	 */
	@Override
	public int attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2) {

		this.player1 = player1;

		if (player1.isMark()) {// player1がtrueの場合

			party.addAll(party2);// partyにparty2を入れる

		} else {// player1がfalseの場合

			party.addAll(party1);// partyにparty1を入れる
		}

		player2 = party.get(random.nextInt(party.size()));

		id = player2.getIdNumber();

		player1.skillAttack(player2);

		party.clear();

		return id;
	}
}
