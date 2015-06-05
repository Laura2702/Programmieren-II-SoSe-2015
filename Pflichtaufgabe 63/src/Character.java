/**
 * The type Character.
 *
 * @author Max Mustermann 1234567 Gruppe 42z
 * @author Erika Musterfrau 1234567 Gruppe 42z
 */
public class Character {
    /**
     * The constant ATTACK_NORMAL.
     */
    public static final int ATTACK_NORMAL = 0;
    /**
     * The constant ATTACK_SPECIAL.
     */
    public static final int ATTACK_SPECIAL = 1;
    /**
     * The Max hp.
     */
    private int maxHp;
    /**
     * The Hp.
     */
    private int hp;
    /**
     * The Atk.
     */
    private int atk;
    /**
     * The Hit chance.
     */
    private double hitChance;
    /**
     * The Inventar.
     */
    private List inventar;
    /**
     * The Gold.
     */
    private int gold;

    /**
     * Instantiates a new Character.
     *
     * @param maxHp     the max hp
     * @param atk       the atk
     * @param hitChance the hit chance
     * @param gold      the gold
     */
    public Character(int maxHp, int atk, double hitChance, int gold) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.atk = atk;
        this.hitChance = hitChance;
        inventar = new Inventar();
        this.gold = gold;
    }

    /**
     * Gets hit chance.
     *
     * @return the hit chance
     */
    public double getHitChance() {
        return hitChance;
    }

    /**
     * Sets hit chance.
     *
     * @param hitChance the hit chance
     */
    public void setHitChance(double hitChance) {
        if (hitChance >= 0 && hitChance <= 1) {
            this.hitChance = hitChance;
        }
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
        if (hp > maxHp) {
            this.hp = maxHp;
        } else if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    /**
     * Gets max hp.
     *
     * @return the max hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Gets atk.
     *
     * @return the atk
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Sets atk.
     *
     * @param atk the atk
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     *
     * @return the int
     */
    public int takeDamage(int damage) {
        return takeDamage(damage, ATTACK_NORMAL);
    }

    /**
     * Take damage.
     *
     * @param damage     the damage
     * @param attackType the attack type
     *
     * @return the damage
     */
    public int takeDamage(int damage, int attackType) {
        setHp(getHp() - damage);
        return damage;
    }

    /**
     * Is defeated.
     *
     * @return true, wenn man besiegt ist
     */
    public boolean isDefeated() {
        return getHp() == 0;
    }

    /**
     * Attack int.
     *
     * @param c the enemy
     *
     * @return -1, fuer Verfehlt, sonst den angerichteten Schaden
     */
    public int attack(Character c) {
        if (Math.random() <= hitChance) {
            int damage = (int) (atk * (Math.random() + 1.0));
            return c.takeDamage(damage);
        } else {
            return -1;
        }
    }

    /**
     * Loot void.
     *
     * @param corpse the corpse
     */
    public void loot(Character corpse) {
        gold += corpse.gold;
        corpse.gold = 0;

        while (!corpse.inventar.isEmpty()) {
            inventar.insert(corpse.inventar.firstItem());
            corpse.inventar.delete();
        }
    }

    /**
     * Gets inventar.
     *
     * @return the inventar
     */
    public List getInventar() {
        return inventar;
    }

    /**
     * Gets gold.
     *
     * @return the gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Fill inventar.
     */
    public void fillInventory() {
        int k = (int) (10 * Math.random());
        for (int i = 0; i < k; i++) {
            inventar.insert(new Item());
        }
    }
}
