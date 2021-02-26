package new_create_app_name_battler.strategy;

import java.util.ArrayList;
import java.util.List;

import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.party.IPlayer;

public class StrategyUseHealingMagic extends BaseStrategy {// 回復優先の作戦

	List<IPlayer> _party1 = new ArrayList<IPlayer>();
	List<IPlayer> _party2 = new ArrayList<IPlayer>();

	/**
	 * player,party1,party2を受け取りhealingMagicまたはnormalAttackを実行する
	 *
	 * @param player1
	 *            :自身
	 * @param party1
	 *            :パーティ1
	 * @param party2
	 *            :パーティ2
	 */
	@Override
	public void attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2) {

		if (player1.isMark()) {// player1がtrueの場合
			_party1.addAll(party1);// _party1にparty1を入れる
			_party2.addAll(party2);// _party2にparty2を入れる

		} else {// player1がfalseの場合
			_party1.addAll(party2);// party1にparty2を入れる
			_party2.addAll(party1);// party2にparty1を入れる
		}

		if (_party2.size() > 0) {
			int a = random.nextInt(_party2.size());
			player2 = _party2.get(a);
		}

		if (player1 instanceof IRecoveryMagic) {
			player = player1;

			for (int i = 0; i < _party1.size(); i++) {// HPの低い味方を選ぶ

				if ((_party1.get(i).getMaxHp() - _party1.get(i).getHp()) > player
						.getMaxHp() - player.getHp()) {

					player = _party1.get(i);
				}
			}

			if (player1.getMp() >= 20) {// 自身のMPが20以上の場合

				player1.healingMagic(player);
				player1.fall(player2);

			} else {// 自身のMPが20未満の場合

				player1.normalAttack(player2);
				player1.fall(player2);
			}

		} else {// player1が僧侶ではない場合

			player1.normalAttack(player2);
			player1.fall(player2);
		}

		_party1.clear();// _party1をクリア
		_party2.clear();// _party2をクリア

	}
}
