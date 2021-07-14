package new_create_app_name_battler.party;

public interface IWizard {

  default void wizardAttackMessage(IPlayer attacker) {
    System.out.printf("%sの攻撃！\n%sは杖を投げつけた！\n", attacker.getName(), attacker.getName());
  }
}
