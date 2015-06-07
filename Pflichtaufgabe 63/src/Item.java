import java.util.Random;

public class Item implements Comparable<Item> {

	String name;
	double weight;
	double value;
	
	
	public Item() {
		Random rand = new Random();
		int i = rand.nextInt(Crawler.itempool.length()) + 1;
		this.name = ((Item)Crawler.itempool.getItem(i)).name;
		this.weight = ((Item)Crawler.itempool.getItem(i)).weight;
		this.value = ((Item)Crawler.itempool.getItem(i)).value;
	}
	public Item(String name) {
		this.name = name;
	}
	
	public Item(String name, double value, double weight) {
		this.name = name;
		this.value = value;
		this.weight = weight;

	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public double getValue() {
		return value;
	}

	public boolean equals(Object obj) {
		Item i = (Item) obj;
		if (i.name.equals(this.name)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
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
