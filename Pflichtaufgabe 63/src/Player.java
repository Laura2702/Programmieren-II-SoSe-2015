/**
 *  Die Klasse Player ist eine Klasse von Objekten mit den Eigenschaften
 *  hp, maxHP, atk, healingPower, hitChance, remainingItemUses, ap, maxAP,
 *  apRegen und einer Anzahl an Skills (skills).
 *
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *          Sophie Duehn 4577449 Gruppe 3b
 *  @version 1.1 
 */
public class Player extends Character {
    /** healingPower */
    private int healingPower;
    /** remainingItemUses */
    private int remainingItemUses;
    /** ap */
    private int ap;
    /** maxAP */
    private int maxAP;
    /** apRegen */
    private int apRegen;
    /** skills */
    private Skill[] skills;
    
    private int gold;
    
        
    /**
     *  Player-Constructor:
     **/
    public Player() {
        super(200, 200, 40, 0.7,new Inventar<Item>());
        this.healingPower = 100;
        this.remainingItemUses = 3;
        this.maxAP = 250;
        this.ap = 250;
        this.apRegen = 10;
        this.skills = new Skill[3];
        this.skills[0] = new Feuerball();
        this.skills[1] = new Wasserkanone();
        this.skills[2] = new Stampfer();
        
        this.gold = 0;
    }

    /**
     *  Berechnet die AP, die nach dem Benutzen eines Skills noch vorhanden sind.
     *  @param value Wert von AP, die abgezogen werden
     */
    public void useAP(int value) {
        if (this.ap - value < 0) {
            this.ap = 0;
        } else {
            this.ap = this.ap - value;
        }
    }
    

    
    /**
     *  @return Anzahl der verbleibenden Heiltraenke
     */
    public int getRemainingItemUses() {
        return this.remainingItemUses;
    }
    
    /**
     *  Heilt den Player, um den Wert 100, wenn genuegend Traenke vorhanden sind.
     *  @return true, wenn Player erfolgreich geheilt wurde
     */
    public boolean heal() {
        if ((this.remainingItemUses > 0) && (this.hp < this.maxHP)) {
            this.hp = this.hp + this.healingPower;
            
            if (this.hp > this.maxHP) {
                this.hp = this.maxHP;
            }
            this.remainingItemUses = this.remainingItemUses - 1;
            return true;
        } else {
            return false;
        }
    }
 
    /**
     *  @return Gibt aktuelle HP-, ATK-, Item-, AP-Anzahl aus.
     */
    public String toString() {
        return "Player: HP: " + this.hp + " - ATK: " + this.atk + "\n        Items: " 
            + this.remainingItemUses + " AP: " + this.ap;
    }
    
    /**
     *  regeneriert AP
     *  @return regenerierte AP
     */
    public int regenerateAp() {
        if (this.ap + this.apRegen <= this.maxAP) {
            this.ap = this.ap + this.apRegen;
            return this.apRegen;
        } else {
            int result = this.maxAP - this.ap;
            this.ap = this.maxAP;
            return result;
        }
    }
    
    /**
     *  @param index Index des Skills
     *  @return Skill
     */
    public Skill getSkill(int index) {
        return this.skills[index];
    }
    
    /**
     *  @return AP
     */
    public int getAp() {
        return this.ap;
    }
    
    /**
     *  AP werden auf maxAP gesetzt
     **/
    public void aperhoehen() {
        this.ap = maxAP;
    }
    public Inventar getInventar() {
    	return this.inventar;
    }
    public int getGold() {
    	return this.gold;
    }
}
