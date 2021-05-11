package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IMagicalUsable;
import new_create_app_name_battler.magic.IRecoveryMagic;
import new_create_app_name_battler.magic.Magic;

public class JobPriest extends BasePlayer implements IRecoveryMagic, IMagicalUsable, IPriest {

	boolean isHeal;

	/**
	 * コンストラクタ
	 * @param name: プレイヤー名
	 */
	public JobPriest(String name) {
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {

		this.job = "僧侶";
		this.setHp(getNumber(0, 120) + 80);// 80-200
		this.mp = getNumber(1, 30) + 20;// 20-50
		this.str = getNumber(2, 40) + 10;// 10-50
		this.def = getNumber(3, 60) + 10;// 10-70
		this.luck = getNumber(4, 99) + 1;// 1-100
		this.agi = getNumber(5, 40) + 20;// 20-60
	}

	@Override
	public void normalAttack(BasePlayer defender) {

	  attackType = "A";
		System.out.printf("%sの攻撃！\n錫杖で突いた！\n", getName());
		damage = calcDamage(defender);// 与えるダメージを求める
		damageProcess(attackType, this, defender, damage);// ダメージ処理
		knockedDownCheck(defender);
	}

	@Override
	public void skillAttack(BasePlayer defender) {

	  attackType = "M";
	  
		if (random.nextInt(100) + 1 <= Magic.OPTICALELEMENTAL.getInvocationRate()) {

			System.out.printf("%s祈りを捧げて%sを召還した\n%sの祝福を受けた！\n", getName(),
					Magic.OPTICALELEMENTAL.getName(), Magic.OPTICALELEMENTAL.getName());

			recoveryProcess(this, Magic.OPTICALELEMENTAL.getRecoveryValue());

		} else {

			System.out.printf("%sは祈りを捧げたが何も起こらなかった！\n", getName());
		}
		knockedDownCheck(this);
	}

	@Override
	public void magicAttack(BasePlayer defender) {

		if (hasEnoughMp()) {

		  attackType = "M";
			damage = effect(defender);
			knockedDownCheck(defender);

		} else {

			System.out.printf("%sは魔法を唱えようとしたが、MPが足りない！！\n", getName());
			normalAttack(defender);
		}
	}

	@Override
	public void healingMagic(BasePlayer defender) {

		this.isHeal = true;

		if (hasEnoughMp()) {// MPが20以上の場合ヒールを使用

			this.mp = this.getMp() - Magic.HEAL.getMpcost();// MP消費

			System.out.printf(
					"%sはヒールを唱えた！\n光が%sを包んだ\n",
					getName(),
					defender.getName(),
					defender.getName());

			recoveryProcess(defender, Magic.HEAL
					.getRecoveryValue());
			knockedDownCheck(this);

		} else {// MPが20未満の場合

			System.out.printf("%sはヒールを唱えようとしたが、MPが足りない！！\n", getName());
			System.out.printf("%sの攻撃！\n錫杖を振りかざした！\n", getName());
			damage = calcDamage(defender);// 与えるダメージを求める
			super.damageProcess(attackType, this, defender, damage);// ダメージ処理
			knockedDownCheck(defender);
		}

		this.isHeal = false;

	}

	@Override
	public void eatGrass() {
		super.eatGrass();
		knockedDownCheck(this);
	}

	public int effect(BasePlayer defender) {

		if (!defender.isPoison() && !defender.isParalysis()) {// 相手が毒,麻痺状態にかかっていない場合

			if (random.nextInt(2) == 0) {// 乱数1の場合パライズを使用

				useParalysis(defender);

			} else {// 乱数2の場合ポイズンを使用

				usePoison(defender);
			}

		} else if (defender.isPoison() || defender.isParalysis()) {// 相手が毒または麻痺にかかっている場合

			if (defender.isPoison()) {// 相手が毒にかかっている場合パライズを使う

				useParalysis(defender);

			} else if (defender.isParalysis()) {// 相手が麻痺にかかっている場合パライズを使う

				usePoison(defender);
			}
		}

		return damage;
	}

	private void usePoison(IPlayer defender) {

		this.mp = this.getMp() - Magic.POISON.getMpcost();// MP消費

		System.out.printf("%sは%sを唱えた！\n瘴気が相手を包んだ！\n", getName(), Magic.POISON.getName());
		defender.setPoison(true);// 相手に毒をセット
		System.out.printf("%sは毒状態になった！\n", defender.getName());

	}

	private void useParalysis(IPlayer defender) {

		this.mp = this.getMp() - Magic.PARALYSIS.getMpcost();// MP消費

		System.out.printf("%sは%sを唱えた！\n蒼い霧が相手を包んだ！\n", getName(), Magic.PARALYSIS
				.getName());

		if (random.nextInt(100) + 1 <= Magic.PARALYSIS.getContinuousRate()) {// 乱数がPARALYSISの値以下の場合麻痺状態になる

			defender.setParalysis(true);// 相手に麻痺をセット
			System.out.printf("%sは麻痺を受けた！\n", defender.getName());

		} else {// 麻痺を状態にならなかった場合

			System.out.printf("%sは麻痺を受けなかった！\n", defender.getName());
		}
	}

	@Override
	public boolean hasEnoughMp() {

		if (10 <= this.getMp() && !isHeal) {

			return true;

		} else if (20 <= this.getMp() && isHeal) {

			return true;

		} else {

			return false;
		}
	}

}


