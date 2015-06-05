/**
 * The type Item.
 *
 * @author Max Mustermann 1234567 Gruppe 42z
 * @author Erika Musterfrau 1234567 Gruppe 42z
 */
public class Item implements Comparable {

    /**
     * The constant names.
     */
    private static String[] names = new String[]{"Trollohren", "Ogerfuesse", "Stirnlappenbasiliskengehirn"};
    /**
     * The Name.
     */
    private String name;
    /**
     * The Value.
     */
    private int value;
    /**
     * The Weight.
     */
    private int weight;

    /**
     * Instantiates a new Item.
     *
     * @param name   the name
     * @param value  the value
     * @param weight the weight
     */
    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    /**
     * Instantiates a new Item.
     */
    public Item() {
        this(names[(int) (names.length * Math.random())], (int) (100 * Math.random()), (int) (10 * Math.random()));
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (value != item.value) {
            return false;
        }
        if (weight != item.weight) {
            return false;
        }
        return !(name != null ? !name.equals(item.name) : item.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + value;
        result = 31 * result + weight;
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return String.format("%s - %d Gold - %d kg", name, value, weight);
    }

    /**
     * Compare to.
     *
     * @param o the o
     *
     * @return the int
     */
    @Override
    public int compareTo(Object o) {
        if (o != null && o instanceof Item) {
            Item item = (Item) o;
            int v = name.compareTo(item.name);
            if (v == 0) {
                if (Integer.compare(item.value, value) == 0) {
                    return Integer.compare(item.weight, weight);
                }
                return Integer.compare(item.value, value);
            }
            return v;
        }
        return 0;
    }
}
