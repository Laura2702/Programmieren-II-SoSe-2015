/**
 *  Die Klasse Skill ist eine Klasse fuer Faehigkeiten.
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *          Sophie Duehn 4577449 Gruppe 3b
 *  @version 1.0
 */
public class Skill {
    /** Name des Skills */
    private String name;
    /** AP Kosten */
    private int apCost;
    
    /**
     *  Skill-Constructor:
     *  @param name Name des Skills
     *  @param apCost AP Kosten des Skills
     */
    public Skill(String name, int apCost) {
        this.name = name;
        this.apCost = apCost;
    }
    
    /**
     *  @return Name des Skills
     */
    public String getName() {
        return this.name;
    }
    
    /**
     *  @return AP Kosten des Skills
     */
    public int getApCost() {
        return this.apCost;
    }
    
    /**
     *  @param player Caster des Skills
     *  @param monster Ziel des Skills
     *  @return true keine richtige Funktion
     */
    public boolean useSkill(Player player, Monster monster) {
        return true;
    }
}
