/**
 *  Der zweite Skill. Klasse Wasserkanone ist eine Unterklasse von Skill.
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *          Sophie Duehn 4577449 Gruppe 3b
 *  @version 1.1
 */
public class Wasserkanone extends Skill {
    /**
     *  Constructor erstellt eine Wasserkanone mit dem Namen
     *  Wasserkanone mit den Kosten 100AP.
     */
    public Wasserkanone() {
        super("Wasserkanone", 100);
    }
    
    /**
     *  Anwendung: Fuegt dem Monster 75 Schaden zu und zieht dem Player 100AP ab.
     *  @param player Caster des Skills
     *  @param monster Ziel des Skills
     *  @return true, wenn der Skill ausgefuehrt wird
     */
    public boolean useSkill(Player player, Monster monster) {
        if (player.getAp() >= this.getApCost()) {
            if (monster.takeDamage(75) != -1) { 
                player.useAP(this.getApCost());
                return true;
            }
        }
        return false;
    }
}
