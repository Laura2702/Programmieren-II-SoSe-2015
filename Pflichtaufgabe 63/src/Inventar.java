/**
 * The type Inventar.
 *
 * @author Max Mustermann 1234567 Gruppe 42z
 * @author Erika Musterfrau 1234567 Gruppe 42z
 */
public class Inventar <T> implements List {

    /**
     * The Item.
     */
    private Item item;

    /**
     * The Next.
     */
    private Inventar <T> next;

    /**
     * Ueberprueft ob die Liste leer ist
     *
     * @return true, Liste ist leer
     */
    public boolean isEmpty() {
        return next == null;
    }

    /**
     * Gibt das erste Item der Liste zurueck
     *
     * @return das erste Item
     *
     * @throws IllegalStateException wenn die Liste leer ist
     */
    public Item firstItem() {
        if (isEmpty()) {
            throw new IllegalStateException("emtpy");
        }
        return next.item;
    }

    /**
     * Gibt das i-te Item der Liste zurueck
     *
     * @param i der Index
     *
     * @return das i-te Item
     *
     * @throws IndexOutOfBoundsException wenn i < 0 oder  i >= length()
     */
    public Item getItem(int i) {
        if (i < 0 || i >= length()) {
            throw new IndexOutOfBoundsException(String.format("%d / %d", i, length()));
        }
        if (i <= 0) {
            return firstItem();
        }
        return next.getItem(--i);
    }

    /**
     * Gibt die Laenge der Liste zurueck
     *
     * @return die Laenge
     */
    public int length() {
        if (isEmpty()) {
            return 0;
        }
        return 1 + next.length();
    }

    /**
     * Fuegt ein Element sortiert in die Liste ein
     *
     * @param x das Item
     *
     * @return die geanderte Liste
     */
    public Inventar<T> insert(Item x) {
        if (isEmpty() || x.compareTo(firstItem()) <= 0) {
            Inventar<T> l = new Inventar<T>();
            l.item = x;
            l.next = next;
            next = l;
            return this;
        }
        return next.insert(x);
    }

    /**
     * Fuegt ein Element an das Ende der Liste ein
     *
     * @param x das Item
     *
     * @return die geanderte Liste
     */
    public Inventar<T> append(Item x) {
        if (isEmpty()) {
            return insert(x);
        }
        return next.append(x);
    }

    /**
     * Loescht das erste vorkommen des Items x
     *
     * @param x das Item
     *
     * @return die geanderte Liste
     */
    public Inventar<T> delete(Item x) {
        Inventar<T> l = find(x);
        if (l != null) {
            l.next = l.next.next;
        }
        return this;
    }

    /**
     * Loescht das erste Element der Liste
     *
     * @return die geanderte Liste
     */
    public Inventar<T> delete() {
        if (!isEmpty()) {
            next = next.next;
        }
        return this;
    }

    /**
     * Find inventar.
     *
     * @param x the x
     *
     * @return the inventar
     */
    private Inventar<T> find(Item x) {
        if (isEmpty()) {
            return null;
        } else if (firstItem().equals(x)) {
            return this;
        }
        return next.find(x);
    }

    /**
     * Prueft ob ein Item in der Liste ist
     *
     * @param x das Item
     *
     * @return true, x ist in der Liste enthalten
     */
    public boolean isInList(Item x) {
        return (find(x) != null);
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        int length = length();
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < length; i++) {
            string.append(i);
            string.append(" - ");
            string.append(getItem(i));
            string.append(" - Händlerpreis");
            string.append((int)(getItem(i).getValue())*1.3);
            string.append("\n");
            string.append("- Verkaufswert");
            string.append(getItem(i).getValue());
            string.append("\n");
        }
        return string.toString();
    }
}