/**
 *  Der erste Skill. Klasse Feuerball ist eine Unterklasse von Skill.
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *          Sophie Duehn 4577449 Gruppe 3b
 *          Sophie Unverzagt 4568856 Gruppe 3b
 *  @version 1.1
 */
public class Feuerball extends Skill {
    /**
     *  Constructor erstellt einen Feuerball mit dem Namen
     *  Feuerball mit den Kosten 50AP.
     */
    public Feuerball() {
        super("Feuerball", 50);
    }
    
    /**
     *  Anwendung: Fuegt dem Monster 50 Schaden zu und zieht dem Player 50AP ab.
     *  @param player Caster des Skills
     *  @param monster Ziel des Skills
     *  @return true, wenn der Skill ausgefuehrt wird
     */
    public boolean useSkill(Player player, Monster monster) {
        if (player.getAp() >= this.getApCost()) {
            if (monster.takeDamage(50) != -1) { 
                player.useAP(this.getApCost());
                return true;
            }
        }
        return false;
    }
}
