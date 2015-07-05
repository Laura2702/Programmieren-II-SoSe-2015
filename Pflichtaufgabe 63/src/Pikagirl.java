/**
 *  Die Klasse Pikagirl ist eine Unterklasse von Monster
 *  mit abgeaenderten Werten fuer hp, etc.
 *  @author Laura Pichlmeier 4573524 Gruppe 3b
 *  @author Sophie Duehn 4577449 Gruppe 3b
 **/
public class Pikagirl extends Monster {
    /**
     *  Aendert Statuswerte von Pikagirl
     **/
    public Pikagirl() {
        this.hp = hp - 10;
        this.maxHP = maxHP - 10;
        this.hitChance = hitChance + 0.1;
    
    }
    
    /**
     *  Berechnet Schaden, der erlitten wird
     *  @param damage Schaden
     *  @return damage Schaden
     **/
    public int takeDamage(int damage) {
        if (0.75 < Math.random()) {
            if (this.hp - damage < 0) {
                this.hp = 0;
            } else {
                this.hp = this.hp - damage;
            } 
            return damage;
        } else {
            return -1;
        }
    }
}
