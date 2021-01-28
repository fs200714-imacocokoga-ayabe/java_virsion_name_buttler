package new_create_app_name_battler;

public class Fighter extends BasePlayer {

	/**
	 * コンストラクタ
	 * @param name : プレイヤー名
	 */
	public Fighter(String name) {
		super(name);
	}

	@Override
	protected void makeCharacter() {

		this.job = "戦士";
		this.hp = getNumber(0, 200) + 100;// 100-300
		this.mp = getNumber(1, 0);// 0
		this.str = getNumber(2, 70) + 30;// 30-100
		this.def = getNumber(3, 70) + 30;// 30-100
		this.luck = getNumber(4, 99) + 1;// 1-100
		this.agi = getNumber(5, 49) + 1;// 1-50
	}

	@Override
	public void attack(IPlayer defender, int strategyNumber) {
		super.attack(defender, strategyNumber);
	}

	@Override
	public void normalAttack(IPlayer defender) {

		System.out.printf("%sの攻撃！\n%sは剣で斬りつけた！\n", getName(), getName());
		damage = calcDamage(defender); // 与えるダメージを求める
		damageProcess(defender, damage);
	}

	public void skillAttack(IPlayer defender) {

		int r = random.nextInt(100) + 1;

		if (r > 75) {// 乱数値が75より大きいなら

			System.out.printf("%sの捨て身の突撃！\n", getName());
			damage = calcDamage(defender); // 与えるダメージを求める
			damage = damage * 2;// ダメージ2倍
			damageProcess(defender, damage);

		} else {

			System.out.printf("%sの捨て身の突撃はかわされた！\n", getName());
		}
	}

	@Override
	public void eatGrass() {
		super.eatGrass();
	}
}
