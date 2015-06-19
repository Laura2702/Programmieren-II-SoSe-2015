/**
 * Listeninterface
 * 
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 *
 * @param <T>
 */
public interface List<T extends Comparable<T>> {

    /**
     * Ueberprueft ob die Liste leer ist
     *
     * @return true, Liste ist leer
     */
    boolean isEmpty();

    /**
     * Gibt die Laenge der Liste zur�ck
     *
     * @return die Laenge
     */
    int length();

    /**
     * Prueft ob ein Item in der Liste ist
     *
     * @param x
     *            das Item
     * @return true, x ist in der Liste enthalten
     */
    boolean isInList(T x);

    /**
     * Gibt das erste Item der Liste zurueck
     *
     * @return das erste Item
     * @throws IllegalStateException
     *             wenn die Liste leer ist
     */
    T firstItem() throws IllegalStateException;

    /**
     * Gibt das i-te Item der Liste zurueck
     *
     * @param i
     *            der Index
     * @return das i-te Item
     * @throws IndexOutOfBoundsException
     *             wenn i < 0 oder i >= length()
     */
    T getItem(int i) throws IndexOutOfBoundsException;

    /**
     * Fuegt ein Element sortiert in die Liste ein
     *
     * @param x
     *            das Item
     * @return die geanderte Liste
     */
    List<T> insert(T x);

    /**
     * Fuegt ein Element an das Ende der Liste ein
     *
     * @param x
     *            das Item
     * @return die geaenderte Liste
     */
   
    List<T> delete(T x);

    /**
     * Loescht das erste Element der Liste
     *
     * @return die geanderte Liste
     */
    List<T> delete();
}
