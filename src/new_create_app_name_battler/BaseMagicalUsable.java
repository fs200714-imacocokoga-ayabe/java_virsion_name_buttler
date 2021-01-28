package new_create_app_name_battler;

public class BaseMagicalUsable implements IMagicalUsable{

	@Override
	public int effect(IPlayer defender) {
		return 0;
	}


	@Override
	public boolean hasEnoughMp() {

		return false;
	}

}
