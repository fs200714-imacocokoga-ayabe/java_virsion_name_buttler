package new_create_app_name_battler.party;

import new_create_app_name_battler.magic.IMagicalUsable;
import new_create_app_name_battler.magic.Magic;

public class JobWizard extends BasePlayer implements IMagicalUsable {

	/**
	 * コンストラクタ
	 * @param name : プレイヤー名
	 */
	public JobWizard(String name) {

		super(name);
	}

	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void makeCharacter() {

		this.job = "魔法使い";
		this.hp = getNumber(0, 100) + 50;// 50-150
		this.mp = getNumber(1, 50) + 30;// 30-80
		this.str = getNumber(2, 49) + 1;// 1-50
		this.def = getNumber(3, 49) + 1;// 1-50
		this.luck = getNumber(4, 99) + 1;// 1-100
		this.agi = getNumber(5, 40) + 20;// 20-60
	}

	@Override
	public void normalAttack(IPlayer defender) {

		System.out.printf("%sの攻撃！\n%sは杖を投げつけた！\n", getName(), getName());
		damage = calcDamage(defender);// 与えるダメージを求める
		damageProcess(defender, damage);
	}

	@Override
	public void skillAttack(IPlayer defender) {

		int r = random.nextInt(100) + 1;

		if (r > 75) {// 25％で発動

			System.out.printf("%sは魔法陣を描いて%sを召還した\n%sの攻撃！\n", getName(),
					Magic.FIREELEMENTAL.getName(),
					Magic.FIREELEMENTAL.getName());

			super.damageProcess(defender, Magic.FIREELEMENTAL.getMinDamage());// ダメージ処理

		} else {// 75%で不発

			System.out.printf("%sの攻撃だがスキルは発動しなかった！\n", getName());
		}
	}

	@Override
	public void magicAttack(IPlayer defender) {

		if (hasEnoughMp()) {

			damage = effect(defender);
			super.damageProcess(defender, damage);

		} else {

			System.out.println("MPが足りない！");
			normalAttack(defender);
		}
	}

	@Override
	public void eatGrass() {
		super.eatGrass();
	}

	@Override
	public int effect(IPlayer defender) {

		if (getMp() >= 20) {// MPが20以上の場合

			int r = random.nextInt(2) + 1;// 乱数1～2

			if (r == 1) {// 1の場合サンダーを使用

				damage = useThunder();

			} else if (r == 2) {// 2の場合ファイアを使用

				damage = useFire();
			}

		} else if (getMp() < 20 && getMp() >= 10) {// MPが10以上20未満の場合ファイアを使用する

			damage = useFire();// ファイアを使用
		}

		return damage;
	}

	private int useThunder() {

		System.out.printf("%sは%sを唱えた！\n雷が地面を這っていく！\n", getName(),
				Magic.THUNDER.getName());

		damage = random.nextInt(Magic.THUNDER.getMaxDamage()
				- Magic.THUNDER.getMinDamage())
				+ Magic.THUNDER.getMinDamage();// 乱数20～50

		this.mp = this.getMp() - Magic.THUNDER.getMpcost();// MPを消費

		return damage;
	}

	private int useFire() {
		System.out.printf("%sは%sを唱えた！\n炎が渦を巻いた！\n", getName(),
				Magic.FIRE.getName());

		damage = random.nextInt(Magic.FIRE.getMaxDamage()
				- Magic.FIRE.getMinDamage())
				+ Magic.FIRE.getMinDamage();// 乱数10～30

		this.mp = this.getMp() - Magic.FIRE.getMpcost();// MPを消費

		return damage;
	}

	@Override
	public boolean hasEnoughMp() {

		if (this.getMp() >= 10) {

			return true;

		} else {

			return false;
		}
	}
}
