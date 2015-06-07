/** 
 *  Character ist eine Klasse.
 *  @author Laura Pichlmeier 4573524 Gruppe 3b
 *  @author Sophie Duehn 4577449 Gruppe 3b
 *  @author Sophie Unverzagt 4568856 Gruppe 3b
 **/
public class Character {
    /** maxHP */
    protected int maxHP;
    /** hp */
    protected int hp;
    /** atk */
    protected int atk;
    /** hitChance */
    protected double hitChance;
    /** gold*/
    protected int gold;
    /** inventar*/
    protected Inventar<Item> inventar;
    
    /**
     *  Character-Constructor:
     *  @param maxHP maxHP
     *  @param hp hp
     *  @param atk atk
     *  @param hitChance hitChance
     **/
    public Character(int maxHP, int hp, int atk, double hitChance,Inventar<Item> inventar) {
        this.maxHP = maxHP;
        this.hp = hp;
        this.atk = atk;
        this.hitChance = hitChance;
        this.inventar = inventar;
    }
    
    /**
     *  @return maxHP
     **/
    public int getMaxHP() {
        return this.maxHP;
    }
    
    /**
     *  @return hp
     **/
    public int getHp() {
        return this.hp;
    }
    
    /**
     *  @return atk
     **/
    public int getAtk() {
        return this.atk;
    }
    
    /**
     *  @return hitChance
     **/
    public double getHitChance() {
        return this.hitChance;
    }
    
    /**
     *  @return true, wenn player besiegt ist
     **/
    public boolean isDefeated() {
        if (this.hp == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *  @param damage Schaden, der erlitten wird
     *  @return damage, der erlitten wird
     **/
    public int takeDamage(int damage) {
        if (this.hp - damage < 0) {
            this.hp = 0;
        } else {
            this.hp = this.hp - damage;
        }
        return damage;
    }
    
    /**
     *  Ermittelt, ob der Angriff des Characters trifft und wie viel 
     *  Schaden dieser beim Gegner verursacht.
     *  @param character den anzugreifenden character
     *  @return damage Schaden am Gegner
     */
    public int attack(Character character) {
        if (this.hitChance > Math.random()) {
            int damage = (int) (this.atk * (Math.random() + 1));
            if (character.takeDamage(damage) != -1) {
                return damage;
            } else {
                return -2;
            }
        } else {
            return -1;
        }
    }
    
    /**
     *  atk wird um bestimmten wert erhoeht
     *  @param wert , um den die atk erhoeht werden
     **/
    public void atkerhoehen(int wert) {
        this.atk += wert;
    }
    
    /**
     *  hp werden vollgeheilt
     **/
    public void hperhoehen() {
        this.hp = maxHP;
    }
}
