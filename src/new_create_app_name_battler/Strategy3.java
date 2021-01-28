package new_create_app_name_battler;

import java.util.List;

public class Strategy3 extends BaseStrategy {// スキル攻撃

	/**
	 *player1,party1,party2を受け取りdata(味方ID,敵ID,作戦番号)を返す
	 * @param player1 :自身
	 * @param party1 :パーティ1
	 * @param party2 :パーティ2
	 * @return  :味方ID,敵ID,作戦番号4
	 */
	@Override
	public int[] attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2) {

		this.player1 = player1;

		if (player1.isMark()) {// player1がtrueの場合

			party.addAll(party2);// partyにparty2を入れる

		} else {// player1がfalseの場合

			party.addAll(party1);// partyにparty1を入れる
		}

		int a = random.nextInt(party.size());

		player2 = party.get(a);
		data[0] = player2.getIdNumber();// ランダムで出た相手ID
		data[1] = 3;// 作戦番号3を入れる
		party.clear();

		return data;
	}
}
