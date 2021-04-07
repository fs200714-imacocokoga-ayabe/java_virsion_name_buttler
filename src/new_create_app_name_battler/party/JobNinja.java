package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IMagicalUsable;
import new_create_app_name_battler.magic.Magic;
import new_create_app_name_battler.magic.Skill;

public class JobNinja extends BasePlayer implements IMagicalUsable, INinja {

	/**
	 * コンストラクタ
	 * @param name: プレイヤー名
	 */
	public JobNinja(String name) {
		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {

		this.job = "忍者";
		this.hp = getNumber(0, 100) + 70;
		this.mp = getNumber(1, 20) + 10;
		this.str = getNumber(2, 50) + 20;
		this.def = getNumber(3, 49) + 1;
		this.luck = getNumber(4, 99) + 1;
		this.agi = getNumber(5, 40) + 40;
	}

	@Override
	public void normalAttack(IPlayer defender) {

		System.out.printf("%sの攻撃！\n手裏剣を投げつけた！\n", getName());
		damage = calcDamage(defender); // 与えるダメージを求める
		damageProcess(defender, damage);// ダメージ処理
		knockedDownCheck(defender);
	}

	@Override
	public void skillAttack(IPlayer defender) {

		if (random.nextInt(100) + 1 <= Skill.SWALLOW.getInvocationRate()) {// 25%で発動

			System.out.printf("%sは目にも止まらぬ速さで攻撃した！\n", getName());

			for (int i = 1; i <= 2; i++) {

				System.out.printf("%d回目の攻撃\n", i);
				damage = calcDamage(defender);// 攻撃処理

				super.damageProcess(defender, damage);// ダメージ処理

				if (defender.getHp() <= 0) {// 倒れた判定

					break;
				}
			}

		} else {// 75%で不発

			System.out.printf("%sは転んだ！\n", getName());
		}
		knockedDownCheck(defender);
	}

	@Override
	public void magicAttack(IPlayer defender) {

		if (hasEnoughMp()) {

			damage = effect(defender);
			super.damageProcess(defender, damage);
			knockedDownCheck(defender);

		} else {

			System.out.printf("%sは術を唱えようとしたが、MPが足りない！！\n", getName());
			normalAttack(defender);
		}
	}

	@Override
	public void eatGrass() {
		super.eatGrass();
		knockedDownCheck(this);
	}

	@Override
	public int effect(IPlayer defender) {

		damage = random.nextInt(Magic.FIREROLL.getMaxDamage()
				- Magic.FIREROLL.getMinDamage())
				+ Magic.FIREROLL.getMinDamage();// 乱数10～30

		this.mp = this.getMp() - Magic.FIREROLL.getMpcost();// MP消費

		System.out.printf("%sは%sを唱えた！\n火の球が飛んでいく！\n", getName(),
				Magic.FIREROLL.getName());

		return damage;
	}

	@Override
	public boolean hasEnoughMp() {

		if (10 <= this.getMp()) {

			return true;

		} else {

			return false;

		}
	}
}
