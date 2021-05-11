package new_create_app_name_battler.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {

	Random random = new Random();

	public ArrayList<BasePlayer> members;// プレイヤーの入れ物
	public List<BasePlayer> party1 = new ArrayList<BasePlayer>();// パーティ1の入れ物
	public List<BasePlayer> party2 = new ArrayList<BasePlayer>();// パーティ2の入れ物

	/**
	 * コンストラクタ
	 */
	public Party() {
		members = new ArrayList<BasePlayer>();
	}

	/**
	 * パーティーメンバーをArrayList で取得する
	 */
	public ArrayList<BasePlayer> getMembers() {
		return members;
	}

	/**
	 * パーティーにプレイヤーを追加する
	 * @param player: 追加するプレイヤー
	 */
	public void appendPlayer(BasePlayer player) {

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
	public void removePlayer(BasePlayer player) {

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
	public void setMembers(BasePlayer player) {
		members.add(player);
	}

	/**
	 *  membersにplayerを削除する
	 * @param player : プレイヤー
	 */
	public void removeMembers(BasePlayer player) {
		members.remove(player);
	}

	/**
	 * パーティ1を返す
	 * @return party1 : パーティ１
	 */
	public List<BasePlayer> getParty1() {
		return party1;
	}

	/**
	 * パーティ2を返す
	 * @return party2 : パーティ2
	 */
	public List<BasePlayer> getParty2() {
		return party2;
	}

	/**
	 * パーティから1人返す処理
	 * @param id : プレイヤーのID
	 * @return IDのプレイヤー
	 */
	public BasePlayer selectMember(int id) {

		for (int i = 0; i < members.size(); i++) {

			if (members.get(i).getIdNumber() == id) {

				return members.get(i);
			}
		}
		return null;
	}
}
