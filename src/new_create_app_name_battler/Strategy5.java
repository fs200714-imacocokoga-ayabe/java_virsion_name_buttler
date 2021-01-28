package new_create_app_name_battler;

import java.util.List;

public class Strategy5 extends BaseStrategy {// 草を食べる

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
		data[1] = 5;// 作戦番号5を入れる

		party.clear();

		return data;// playerIDとランダムで出た相手IDと作戦番号を返す

	}

}
