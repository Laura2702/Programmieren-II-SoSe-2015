/**
 *  Die Klasse SCP049 ist eine Unterklasse von Monster
 *  mit abgeaenderten Werten fuer hp, etc.
 *  @author Laura Pichlmeier 4573524 Gruppe 3b
 *  @author Sophie Duehn 4577449 Gruppe 3b
 **/
public class SCP049 extends Monster {
    /**
     *  Aendert Statuswerte von SCP049
     **/
    public SCP049() {
        this.hp = hp + 40;
        this.maxHP = maxHP + 40;
        this.atk = atk - 20;
        this.hitChance = hitChance + 0.1;
     
    }
    
    /**
     *  Verursacht Schaden am Spieler
     *  @param player Spieler
     *  @return damage Schaden
     **/
    public int attack(Player player) {
        if (this.hitChance > Math.random()) {
            int damage = (int) (this.atk * (Math.random() + 1));
            player.takeDamage(damage);
            return damage; 
        } else {
            return -1;
        }
    }
    
    /**
     *  Setzt HP des Spieler auf Null
     *  @param player Spieler
     *  @return hp hp
     **/
    public int deathclock(Player player) {
        player.hp = 0;
        return player.hp;
    }
}
