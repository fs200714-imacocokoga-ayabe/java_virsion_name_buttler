package new_create_app_name_battler.type;

public enum TypeData {

	BLOOD("血", 40, 0.5, "相手の血の力が発動してダメージを吸収された！","血:物理攻撃吸収"),
	SHIELD("盾", 70, 0.5, "相手の盾の力が発動して攻撃が弱められた！","盾:物理攻撃半減"),
	DEVIL("魔", 70, 0.5, "相手の魔の力が発動して魔法が弱められた！","魔:魔法攻撃半減"),
	DARK("闇", 100, 10, "相手の闇の力が発動して呪いをうけた！","闇:呪いを与える"),
	SHADOW("影", 40, 0.0, "相手の影の力が発動して攻撃をかわされた！","影:攻撃回避"),
	HOLY("聖", 40, 0.0, "相手の聖の力が発動して魔法が無効にされた！","聖:魔法攻撃無効")
	;

	private String name;
	private int invocationRate;// 発動率
	private double collectionValue;// 属性の補正値
	private String message; // 属性のメッセージ
	private String description; // 属性の説明

	private TypeData(String name, int invocationRate,  double collectionValue, String message, String description){

		this.name = name;
		this.invocationRate = invocationRate;
		this.collectionValue = collectionValue;
		this.message = message;
		this.description = description;
	}

	public String getTypeName(){

		return this.name;
	}

	public int getInvocationRate(){
		return this.invocationRate;
	}

	public double getCollectionValue(){
		return this.collectionValue;
	}

	public String getMessage(){
		return this.message;
	}

	public String getDescription(){
		return this.description;
	}

}

