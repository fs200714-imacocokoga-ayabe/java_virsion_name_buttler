package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.IPlayer;

public interface IMagicalUsable {

	int effect(IPlayer defender);

	boolean hasEnoughMp();
}
