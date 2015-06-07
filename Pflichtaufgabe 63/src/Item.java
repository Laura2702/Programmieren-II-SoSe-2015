import java.util.Random;

/**
 * Dies ist Pflichtaufgabe 63.
 * 
 * @author Sophie Duehn 4577449 Gruppe 3b
 * @author Laura Pichlmeier 4573524 Gruppe 3b
 * @author Sophie Unverzagt 4568856 Gruppe 3b
 * @version 1.1
 */

public class Item implements Comparable<Item> {
	/** name */
	String name;
	/** Gewicht */
	double weight;
	/** Wert */
	double value;
	
	/**
	 * Item-Constructor
	 */
	public Item() {
		Random rand = new Random();
		int i = rand.nextInt(Crawler.itempool.length()) + 1;
		this.name = ((Item)Crawler.itempool.getItem(i)).name;
		this.weight = ((Item)Crawler.itempool.getItem(i)).weight;
		this.value = ((Item)Crawler.itempool.getItem(i)).value;
	}
	/**
	 * Item-Constructor
	 * @param name
	 */
	public Item(String name) {
		this.name = name;
	}
	/**
	 * @param name
	 * @param value
	 * @param weight
	 */
	public Item(String name, double value, double weight) {
		this.name = name;
		this.value = value;
		this.weight = weight;

	}
	/**
	 * Gets Name
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets Weight
	 * @return weight.
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * Gets Value
	 * @return value.
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Vergleicht die Namen von Items
	 * @return true, wenn Namen gleich sind
	 * @return false, wenn nicht.
	 */
	public boolean equals(Object obj) {
		Item i = (Item) obj;
		if (i.name.equals(this.name)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	/**Vergleicht zwei Items anhand von name,value und weight.
	 * 
	 */
	public int compareTo(Item i) {
		if (this.getName().equals(i.getName())
				&& this.getValue() == i.getValue()
				&& this.getWeight() > i.getWeight()) {
			// Name gleich,Wert gleich,Gewicht größer
			return 1;
		} else if (this.getName().equals(i.getName())
				&& this.getValue() == i.getValue()
				&& this.getWeight() < i.getWeight()) {
			// Name gleich,Wert gleich,Gewicht kleiner
			return -1;
		} else if (this.getName().equals(i.getName())
				&& this.getValue() > i.getValue()) {
			// Name gleich,Wert größer
			return 1;
		} else if (this.getName().equals(i.getName())
				&& this.getValue() < i.getValue()) {
			// Name gleich,Wert kleiner
			return -1;
		} else {
			return this.getName().compareTo(i.getName());
			// Sonst wenn Name unterschiedlich nach Bustaben/Zahlen sortiert.

		}

	}
}
