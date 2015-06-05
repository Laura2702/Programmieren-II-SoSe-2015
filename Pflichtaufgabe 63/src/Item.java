
public class Item implements Comparable<Item> {

	String name;
	int weight;
	int value;

	public Item() {
		this.name = Integer.toString((int) (Math.random() * (500 - 0) + 0));
		this.weight = (int) (Math.random() * (50 - 1) + 1);
		this.value = (int) (Math.random() * (100 - 1) + 1);

	}

	public Item(String name, int value, int weight) {
		this.name = name;
		this.value = value;
		this.weight = weight;

	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public boolean equals(Object obj) {
		Item i = (Item) obj;
		if (i.name == this.name && i.value == this.value
				&& i.weight == this.weight) {
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
