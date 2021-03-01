package new_create_app_name_battler.magic;

public enum Skill {

	ASSAULT("突撃", 30),
	SWALLOW("燕返し",30),
	;

	private String name;
	private int invocationRate;// 発動率

	private Skill(String name, int i) {

		this.name = name;
		this.invocationRate = i;
	}

	public String getName() {

		return this.name;
	}

	public int getInvocationRate() {

		return invocationRate;
	}

}