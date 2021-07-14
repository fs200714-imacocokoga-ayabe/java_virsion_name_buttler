package new_create_app_name_battler.party;

public interface INinja {

  default void ninjaAttackMessage(IPlayer attacker) {
    System.out.printf("%sの攻撃！\n手裏剣を投げつけた！\n", attacker.getName());
  }
}
