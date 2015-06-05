/**
 *  Der dritte Skill. Klasse Stampfer ist eine Unterklasse von Skill.
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *          Sophie Duehn 4577449 Gruppe 3b
 *  @version 1.1
 */
public class Stampfer extends Skill {
    /**
     *  Constructor erstellt einen Stampfer mit dem Namen
     *  Stampfer mit den Kosten 150AP.
     */
    public Stampfer() {
        super("Stampfer", 150);
    }
    
    /**
     *  Anwendung: Fuegt dem Monster 100 Schaden zu und zieht dem Player 150AP ab.
     *  @param player Caster des Skills
     *  @param monster Ziel des Skills
     *  @return true, wenn der Skill ausgefuehrt wird
     */
    public boolean useSkill(Player player, Monster monster) {
        if (player.getAp() >= this.getApCost()) {
            if (monster.takeDamage(100) != -1) { 
                player.useAP(this.getApCost());
                return true;
            }
        }
        return false;    
    }
}        
