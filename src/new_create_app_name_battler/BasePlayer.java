package new_create_app_name_battler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class BasePlayer implements IPlayer {

	Random random = new Random();

	protected String name;
	protected String job;
	protected int maxHp;// 最大HP
	protected int maxMp;// 最大MP
	protected int hp;// HP
	protected int mp;// MP
	protected int str;// 攻撃力
	protected int def;// 防御力
	protected int luck;// 運
	protected int agi;// すばやさ
	protected boolean poison;// 状態異常：毒
	protected boolean paralysis;// 状態異常：麻痺
	protected int damage;// damage値
	protected boolean mark;// 敵味方識別
	protected int idNumber;// ID値の入れ物
	protected int strategyData;
	protected int healValue;
	private static final int HERB_RECOVERY_VALUE = 30;

	public BasePlayer() {
	}

	public BasePlayer(String name) {

		this.name = name;
		makeCharacter();
	}

	protected void makeCharacter() {
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getJob() {
		return this.job;
	}

	@Override
	public String getJobName() {
		return this.job + this.name;
	}

	@Override
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	@Override
	public boolean isMark() {
		return mark;
	}

	@Override
	public void setIdNumber(int id) {
		this.idNumber = id;
	}

	@Override
	public int getIdNumber() {
		return idNumber;
	}

	@Override
	public void setMaxHp(int hp) {
		this.maxHp = hp;
	}

	@Override
	public int getMaxHp() {
		return this.maxHp;
	}

	@Override
	public void setMaxMp(int mp) {
		this.maxMp = mp;
	}

	@Override
	public int getMaxMp() {
		return this.maxMp;
	}

	@Override
	public int getHp() {
		return this.hp;
	}

	@Override
	public int getMp() {
		return this.mp;
	}

	@Override
	public int getStr() {
		return this.str;
	}

	@Override
	public int getDef() {
		return def;
	}

	@Override
	public int getAgi() {
		return this.agi;
	}

	@Override
	public int getLuck() {
		return luck;
	}

	@Override
	public boolean isPoison() {
		return poison;
	}

	@Override
	public void setPoison(boolean poison) {
		this.poison = poison;
	}

	@Override
	public boolean isParalysis() {
		return paralysis;
	}

	@Override
	public void setParalysis(boolean paralysis) {
		this.paralysis = paralysis;
	}

	/**
	 * 名前(name)からハッシュ値を生成し、指定された位置の数値を取り出す
	 * @param index : 何番目の数値を取り出すか
	 * @param max : 最大値(内部的に0～255の値を生成するが、0～maxまでの値に補正)
	 * @return 数値(0～max) ※maxも含む
	 */
	protected int getNumber(int index, int max) {
		try {
			// 名前からハッシュ値を生成する
			byte[] result = MessageDigest.getInstance("SHA-1").digest(
					this.name.getBytes());
			String digest = String.format("%040x", new BigInteger(1, result));

			// ハッシュ値から指定された位置の文字列を取り出す（２文字分）
			String hex = digest.substring(index * 2, index * 2 + 2);

			// 取り出した文字列（16進数）を数値に変換する
			int val = Integer.parseInt(hex, 16);
			return val * max / 255;
		} catch (Exception e) {
			// エラー
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void attack(IPlayer defender, int strategyNumber) {

		if (!isParalysis()) {// 麻痺していない

			switch (strategyNumber) {

			case 1:// 全員通常攻撃

				normalAttack(defender);
				break;

			case 2:// 魔法を使用 = 「魔法使い」、「僧侶」、「忍者」 以外は通常攻撃

				if (this instanceof IMagicalUsable) {

					magicAttack(defender);

				} else {

					normalAttack(defender);
				}
				break;

			case 3:// スキル攻撃 = 全員スキル攻撃

				skillAttack(defender);
				break;

			case 4:// 回復優先 = 「僧侶」以外は通常攻撃

				if (this instanceof IRecoveryMagic) {

					healingMagic(defender);

				} else {

					normalAttack(defender);
				}
				break;

			case 5:// 草を食べる = 「全員」

				eatGrass();
				break;
			}

		} else {

			System.out.printf("%sは麻痺で動けない！！\n", getName());
		}

		fall(defender);// 倒れた判定
	}

	@Override
	public void normalAttack(IPlayer defender) {
	}

	public void skillAttack(IPlayer defender) {
	}

	@Override
	public void magicAttack(IPlayer defender) {
	}

	@Override
	public void healingMagic(IPlayer defender) {
	}

	@Override
	public void eatGrass() {

		System.out.printf("%sは革袋の中にあった草を食べた！\n", getName());

		int r = random.nextInt(2) + 1;

		switch (r) {

		case 0:

			System.out
					.printf("%sは体力が%d回復した！\n", getName(), HERB_RECOVERY_VALUE);
			recoveryProcess(this, HERB_RECOVERY_VALUE);
			break;

		case 1:

			if (isPoison()) {// 毒状態の場合

				System.out.printf("%sは毒が消えた！\n", getName());
				setPoison(false);

			} else {

				System.out
						.printf("%sは体力が%d回復した！\n", getName(), HERB_RECOVERY_VALUE);
				recoveryProcess(this, HERB_RECOVERY_VALUE);

			}
			break;

		case 2:

			System.out.printf("%sは何も起こらなかった！\n", getName());
			break;
		}
	}

	@Override
	public int calcDamage(IPlayer defender) {

		int power = random.nextInt(this.getStr()) + 1;
		int luk = random.nextInt(100) + 1;

		if (luk <= this.getLuck()) {// 乱数の値がlukの値の中なら
			System.out.println("会心の一撃!");
			damage = this.getStr();

		} else {
			damage = power - defender.getDef();

			if (damage < 0) {
				System.out.printf("%sの攻撃はミス！\n", getName());
				damage = 0;
			}
		}

		return damage;
	}

	@Override
	public void damageProcess(IPlayer defender, int damage) {

		System.out.printf("%sに%dのダメージ！\n", defender.getName(), damage);
		defender.damage(damage);// 求めたダメージを対象プレイヤーに与える
	}

	@Override
	public void damage(int damage) {// ダメージ値分、HPを減少させる

		this.hp = Math.max(this.getHp() - damage, 0);
	}

	@Override
	public int recoveryProcess(IPlayer defender, int healValue) {

		healValue = Math.min(defender.getMaxHp(), defender.getHp() + healValue);
		System.out.printf("%sはHPが%d回復した！\n", defender.getName(), healValue - defender.getHp());
		defender.recovery(healValue - defender.getHp());
		return healValue - defender.getHp();
	}

	@Override
	public void recovery(int healValue) {

		this.hp = this.getHp() + healValue;
	}

	@Override
	public boolean isLive() {

		return this.hp > 0;
	}

	@Override
	public void printStatus() {

		String poison;
		String paralysis;

		if (this.isPoison()) {// 毒状態の場合
			poison = "[毒]";
		} else {// 毒になっていない場合
			poison = "";
		}

		if (this.isParalysis()) {// 麻痺状態の場合
			paralysis = "[麻痺]";
		} else {// 麻痺になっていない場合
			paralysis = "";
		}

		System.out
				.printf("%s(HP=%3d/%d : MP=%3d : STR=%3d : DEF=%3d : LUCK=%3d : AGI=%3d)%S%S\n",
						this.getJobName(), this.getHp(), getMaxHp(),
						this.getMp(), this.getStr(), this.getDef(),
						this.getLuck(), this.getAgi(), paralysis, poison);
	}

	@Override
	public void fall(IPlayer defender) {

		if (defender.getHp() <= 0) {

			System.out.printf("%sは力尽きた...\n", defender.getName());
			abnormalState();// 状態異常チェック

		} else {

			abnormalState();// 状態異常チェック

			if (getHp() <= 0) {// playerの倒れた判定
				System.out.printf("%sは力尽きた...\n", getName());
			}
		}
	}

	/**
	 * 状態異常のチェック
	 */
	private void abnormalState() {

		if (isPoison()) {// true:毒状態 false:無毒状態

			damage(Magic.POISON.getMaxDamage());// 毒のダメージ計算
			System.out.printf("%sは毒のダメージを%d受けた！\n", getName(),
					Magic.POISON.getMaxDamage());
		}

		if (isParalysis()) {// true:麻痺状態 false:麻痺していない

			if (random.nextInt(100) + 1 > Magic.PARALYSIS.getContinuousRate()) {// 麻痺の確立より乱数が上なら麻痺の解除
				setParalysis(false);// 麻痺解除
				System.out.printf("%sは麻痺が解けた！\n", getName());
			}
		}
	}
}
