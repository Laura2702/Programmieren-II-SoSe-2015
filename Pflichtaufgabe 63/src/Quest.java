import java.io.Serializable;

/**
 * Die Klasse Quest erzeugt eine Quest
 * 
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 * @version 1.1
 */
public class Quest implements Comparable<Quest>,Serializable {
    /** Name */
    String name;
    /** Vorquest */
    String prequest;
    /** Anzahl */
    int quantity;
    /** Item */
    String item;
    /** Erledigt */
    boolean erledigt;
    /** angenommen */
    boolean angenommen;

    /**
     * Quest-Constructor
     * @param name
     * @param prequest
     * @param item
     * @param quantity
     */
    public Quest(String name, String prequest, String item, int quantity) {
        this.name = name;
        this.prequest = prequest;
        this.item = item;
        this.quantity = quantity;
        this.erledigt = false;
        this.angenommen = false;

    }

    /**
     * Gets Quantity
     * 
     * @return quantity.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets Name
     * 
     * @return name.
     */

    public String getName() {
        return name;
    }

    /**
     * Gets Prequest
     * 
     * @return prequest.
     */

    public String getPrequest() {
        return prequest;
    }

    /**
     * Gets Item
     * 
     * @return item.
     */

    public String getItem() {
        return item;
    }

    /**
     * Gets Erledigt
     * 
     * @return erledigt.
     */
    public boolean getErledigt() {
        return erledigt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (angenommen ? 1231 : 1237);
        result = prime * result + (erledigt ? 1231 : 1237);
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((prequest == null) ? 0 : prequest.hashCode());
        result = prime * result + quantity;
        return result;
    }

    /**
     * Vergleicht zwei Objekte
     * 
     * @param obj
     *            zu vergleichendes Objekt(Quest)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quest other = (Quest) obj;
        if (angenommen != other.angenommen)
            return false;
        if (erledigt != other.erledigt)
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (prequest == null) {
            if (other.prequest != null)
                return false;
        } else if (!prequest.equals(other.prequest))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    /**
     * Gets Angenommen
     * 
     * @return angenommen
     */
    public boolean getAngenommen() {
        return angenommen;
    }

    /**
     * Sets Erledigt
     * @param erledigt
     */
    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;

    }

    /**
     * Sets Angenommen
     * @param angenommen
     */
    public void setAngenommen(boolean angenommen) {
        this.angenommen = angenommen;

    }

    /**
     * Erzeugt String 
     * @return ein string.
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(name);
        string.append(" - ");
        string.append(prequest);
        string.append(" - ");
        string.append(item);
        string.append(" - ");
        string.append(quantity);
        return string.toString();
    }

    /**
     * Leere compareTo Methode
     * @param arg0
     * @return 0.
     */
    @Override
    public int compareTo(Quest arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
}
