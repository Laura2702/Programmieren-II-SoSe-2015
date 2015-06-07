public class Quest implements Comparable<Quest>{
	String name;
	String prequest;
	int quantity;
	String item;
	boolean erledigt;
	boolean angenommen;

	public Quest(String name, String prequest, String item, int quantity) {
		this.name = name;
		this.prequest = prequest;
		this.item = item;
		this.quantity = quantity;
		this.erledigt = false;
		this.angenommen = false;
		
	}

	public int getQuantity() {
		return this.quantity;
	}
	public String getName() {
		return name;
	}
	public String getPrequest() {
		return prequest;
	}
	public String getItem() {
		return item;
	}
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

	public boolean getAngenommen() {
		return angenommen;
	}
	public void setErledigt(boolean erledigt) {
		this.erledigt = erledigt;
		
	}
	public void setAngenommen(boolean angenommen) {
		this.angenommen = angenommen;
		
	}
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


	@Override
	public int compareTo(Quest arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
