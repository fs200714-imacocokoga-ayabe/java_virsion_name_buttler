package new_create_app_name_battler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseStrategy implements IStrategy {

	Random random = new Random();
	protected IPlayer player;
	protected IPlayer player1;
	protected IPlayer player2;
	protected List<IPlayer> party = new ArrayList<IPlayer>();
	protected List<IPlayer> party1 = new ArrayList<IPlayer>();
	protected List<IPlayer> party2 = new ArrayList<IPlayer>();
	protected String strategyname;// 作戦の名前
	protected int data[] = new int[2];// 味方IDと敵IDと作戦番号を格納

	@Override
	public int[] attackStrategy(IPlayer player1, List<IPlayer> party1,
			List<IPlayer> party2) {
		return data;
	}
}
