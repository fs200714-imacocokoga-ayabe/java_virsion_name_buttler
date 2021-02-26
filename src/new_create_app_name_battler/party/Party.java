package new_create_app_name_battler.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {

	Random random = new Random();

	public ArrayList<IPlayer> members;// プレイヤーの入れ物

	public List<IPlayer> party1 = new ArrayList<IPlayer>();// パーティ1の入れ物

	public List<IPlayer> party2 = new ArrayList<IPlayer>();// パーティ2の入れ物

	/**
	 * コンストラクタ
	 */
	Party() {

		members = new ArrayList<IPlayer>();
	}

	/**
	 * パーティーメンバーをArrayList で取得する
	 */
	ArrayList<IPlayer> getMembers() {

		return members;
	}

	/**
	 * パーティーにプレイヤーを追加する
	 * @param player: 追加するプレイヤー
	 */
	public void appendPlayer(IPlayer player) {

		if (player.isMark()) {

			party1.add(player);// playerがtrueならparty1に入れる

		} else {

			party2.add(player);// playerがfalseならparty2に入れる
		}
	}

	/**
	 * パーティーからプレイヤーを離脱させる
	 * @param player : 離脱させるプレイヤー
	 */
	public void removePlayer(IPlayer player) {

		if (player.isMark()) {

			party1.remove(player);// playerがtrueならparty1から削除する

		} else {

			party2.remove(player);// playerがfalseならparty2から削除する
		}
	}

	/**
	 * membersにplayerをセットする
	 * @param player : プレイヤー
	 */
	public void setMembers(IPlayer player) {

		members.add(player);
	}

	/**
	 *  membersにplayerを削除する
	 * @param player : プレイヤー
	 */
	public void removeMembers(IPlayer player) {

		members.remove(player);
	}

	/**
	 * パーティ1を返す
	 * @return party1 : パーティ１
	 */
	public List<IPlayer> getParty1() {

		return party1;
	}

	/**
	 * パーティ2を返す
	 * @return party2 : パーティ2
	 */
	public List<IPlayer> getParty2() {

		return party2;
	}

	/**
	 * パーティから1人返す処理
	 * @param id : プレイヤーのID
	 * @return IDのプレイヤー
	 */
	public IPlayer selectMember(int id) {

		for (int i = 0; i < members.size(); i++) {

			if (members.get(i).getIdNumber() == id) {

				return members.get(i);

			}
		}

		return null;

	}
}
