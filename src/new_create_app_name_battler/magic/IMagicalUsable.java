package new_create_app_name_battler.magic;

import new_create_app_name_battler.party.BasePlayer;

public interface IMagicalUsable {

	int effect(BasePlayer defender);

	boolean hasEnoughMp();
}
