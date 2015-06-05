/**
 *  Die Klasse Monster ist eine Unterklasse von Character
 *
 *  @author Laura Pichlmeier 4753524 Gruppe 3b
 *  @author Sophie Duehn 4577449 Gruppe 3b
 *  @version 1.1 
 */
public class Monster extends Character {
	
	
	
	protected int gold;
    /**
     *  Constructor setzt Werte fuer maxHP, hp, atk und hitChance
     **/
    public Monster() {
        super(70, 70, 2, 0.6,new Inventar<Item>());
        inventar.append(new Item());
        inventar.append(new Item());
        inventar.append(new Item());
        inventar.append(new Item());
        this.gold = 0;
    }
    
    /**
     *  @return Gibt aktuelle HP- und ATK-Anzahl aus.
     */
    public String toString() {
        return "Monster: HP: " + this.hp + " - ATK: " + this.atk;
    }

	

	public Inventar<Item> getInventar() {
		// TODO Auto-generated method stub
		return this.inventar;
	}
	public int getGold() {
		return gold;
	}
	
}
