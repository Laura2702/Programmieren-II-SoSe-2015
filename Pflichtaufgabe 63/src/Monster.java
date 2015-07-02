/**
 * Die Klasse Monster ist eine Unterklasse von Character
 *
 * @author Laura Pichlmeier 4753524 Gruppe 3b
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 * @version 1.1
 */
public class Monster extends Character {
    /** gold */
    protected int gold;
    public String typ;
	

    /**
     * Constructor setzt Werte fuer maxHP, hp, atk und hitChance
     **/
    public Monster() {
        super(70, 70, 2, 0.6, new Inventar<Item>());
        inventar.insert(new Item());
        inventar.insert(new Item());
        inventar.insert(new Item());
        inventar.insert(new Item());
        this.gold = 0;
        this.typ="monster";
    }

    /**
     * @return Gibt aktuelle HP- und ATK-Anzahl aus.
     */
    public String toString() {
        return "Monster: HP: " + this.hp + " - ATK: " + this.atk;
    }

    /**
     * 
     * @return inventar
     */
    public Inventar<Item> getInventar() {
        return this.inventar;
    }

    /**
     * 
     * @return gold
     */
    public int getGold() {
        return gold;
    }

	public int deathclock(Player player) {
		player.hp = 0;
        return player.hp;
		
	}

}
