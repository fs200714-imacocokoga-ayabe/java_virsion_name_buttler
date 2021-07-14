package new_create_app_name_battler.party;

public interface IFighter {

  default void fighterAttackMessage(IPlayer attacker){
    System.out.printf("%sの攻撃！\n%sは剣で斬りつけた！\n", attacker.getName(), attacker.getName());
  }
}
