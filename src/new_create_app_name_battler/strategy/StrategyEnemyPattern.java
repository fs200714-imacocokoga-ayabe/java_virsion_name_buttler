package new_create_app_name_battler.strategy;

import java.util.ArrayList;
import java.util.List;

import new_create_app_name_battler.party.BasePlayer;
import new_create_app_name_battler.party.IFighter;
import new_create_app_name_battler.party.INinja;
import new_create_app_name_battler.party.IPriest;
import new_create_app_name_battler.party.IWizard;

public class StrategyEnemyPattern extends BaseStrategy{

	List<BasePlayer> playerParty = new ArrayList<BasePlayer>();
	List<BasePlayer> aiParty = new ArrayList<BasePlayer>();

	BasePlayer player2;

	@Override
	public int attackStrategy(BasePlayer player1, List<BasePlayer> party1,
			List<BasePlayer> party2) {

		this.player1 = player1;

		playerParty.addAll(party1);

		aiParty.addAll(party2);


		if(player1 instanceof IFighter){

			id = FighterAi();

		}else if(player1 instanceof IWizard){

			id = WizardAi();

		}else if(player1 instanceof IPriest){

			id = PriestAi();

		}else if(player1 instanceof INinja){

			id = NinjaAi();

		}

		return id;

	}

	private int FighterAi() {

		player2 = SelectLowerHP();// HPの低い相手を呼び出す

		if (player1.isPoison()) {// 毒状態の場合

			player1.eatGrass();

			// 攻撃力の1/2が相手の防御力より大きい場合
		} else if ((player1.getStr() / 2) > player2.getDef()) {

			player1.normalAttack(player2);

		} else {// 防御力の方が大きい場合

			player1.skillAttack(player2);

		}

		id = player2.getIdNumber();

		return id;// 自身のIDと相手playerID

	}


	private int WizardAi() {

		player2 = SelectLowerHP();// HPの低い相手を呼び出す

		if (player1.isPoison()) {// player1が毒状態の場合

			player1.eatGrass();
			// 攻撃力の1/2より相手の防御力の方が大きい場合
		} else if (player2.getDef() < (player1.getStr() / 2)) {

			player1.skillAttack(player2);

		} else{

			player1.magicAttack(player2);

		}

		id = player2.getIdNumber();

		return id;

	}

	private int PriestAi() {

		player = lifeImpotance();// HPの低い味方を入れる

		player2 = selectHighHP();// HPの高い相手を入れる

		if (player1.isPoison()) {// playerが毒状態の場合

			player1.eatGrass();

		} else if (player1.getHp() < (player1.getMaxHp() / 4)) {

			player1.eatGrass();

		} else if (player1.getMp() >= 10) {

			player1.magicAttack(player2);// 魔法を使う

		} else if ((player1.getStr() / 2) > player2.getDef()) {

			player2 = SelectLowerHP();// HPの低い相手を呼び出す

			player1.normalAttack(player2);

		} else {

			player1.skillAttack(player2);

		}

		id = player2.getIdNumber();

		return id;

	}

private int NinjaAi() {

	player2 = SelectLowerHP();// HPの低い相手を呼び出す

	if (player1.isPoison()) {// playerが毒状態の場合

		player1.eatGrass();

		// 攻撃力の1/2が相手の防御力より大きい場合
	} else if ((player1.getStr() / 2) > player2.getDef()) {

		player1.normalAttack(player2);

	} else if (10 <= player1.getMp()) {// MPが10以上ある場合

		player1.magicAttack(player2);// 魔法を使う

	} else {

		player1.skillAttack(player2);
	}

	id = player2.getIdNumber();

	return id;

}


/**
 * HPの低い相手を選んで返す
 * @return player2 : HPの低い相手
 */
private BasePlayer SelectLowerHP() {

	player2 = playerParty.get(0);// 敵パーティから1人player2に入れる

	for (int i = 1; i < playerParty.size(); i++) {

		if (playerParty.get(i).getHp() < player2.getHp()) {// player2よりHPが低い場合

			player2 = playerParty.get(i);// HPのひくい敵をplayer2に入れる

		}

	}

	playerParty.clear();

	return player2;

}
/**
 * HPの高い相手を選んで返す
 * @return player2 :HPの高い相手
 */
private BasePlayer selectHighHP() {

	player2 = playerParty.get(0);// 敵パーティから1人player2に入れる

	for (int i = 1; i < playerParty.size(); i++) {

		if (playerParty.get(i).getHp() > player2.getHp()) {// player2よりHPが大きい場合

			player2 = playerParty.get(i);// HPの大きい相手をplayer2に入れる

		}

	}

	playerParty.clear();

	return player2;

}

/**
 * HPの低い味方を返す
 * @return player:HPの低い味方
 */
private BasePlayer lifeImpotance() {

	for (int i = 0; i < aiParty.size(); i++) {

		if (aiParty.get(i).getHp() < player1.getHp()) {

			player = aiParty.get(i);

		}
	}

	aiParty.clear();

	return player;

}

}
