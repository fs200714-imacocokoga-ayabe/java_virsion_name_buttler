package new_create_app_name_battler.party;

public interface IPlayer {

	void normalAttack(IPlayer defender);
	void skillAttack(IPlayer defender);
	void magicAttack(IPlayer defender);
	void healingMagic(IPlayer defender);
	void eatGrass();
	void damageProcess(IPlayer defender, int damage);
	int recoveryProcess(IPlayer defender, int healValue);
	void damage(int damage);
	void recovery(int healValue);
	int calcDamage(IPlayer defender);
	void fall(IPlayer defender);

	boolean isLive();
	boolean isPoison();
	boolean isParalysis();

	String getName();
	String getJob();
	String getJobName();
	int getMaxHp();
	int getMaxMp();
	int getHp();
	int getMp();
	int getStr();
	int getDef();
	int getAgi();
	int getLuck();
	void setMark(boolean b);
	void setIdNumber(int i);
	void setMaxHp(int hp);
	void setMaxMp(int mp);
	void printStatus();
	int getIdNumber();
	boolean isMark();
	void setPoison(boolean poison);
	void setParalysis(boolean paralysis);
}
