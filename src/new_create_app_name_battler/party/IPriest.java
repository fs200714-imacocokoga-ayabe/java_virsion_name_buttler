package new_create_app_name_battler.party;

public interface IPriest {

  default void priestAttackMessage(IPlayer attacker) {
    System.out.printf("%sの攻撃！\n錫杖で突いた！\n", attacker.getName());
  }
}
