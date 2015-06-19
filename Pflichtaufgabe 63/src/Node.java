public class Node {
	Node links;
	Node rechts;
	Integer inhalt;
	Node parent;

	public String toString() {
		String s = "";

		if (inhalt != null) {
			s += inhalt;
		}
		s += "(";
		if (links != null) {
			s += links.toString();
		}
		s += ",";
		if (rechts != null) {
			s += rechts.toString();
		}
		s += ")";
		return s;

	}

	public boolean isEmpty() {
		if (inhalt != null) {
			return false;
		}
		return true;
	}

	public void insert(Integer i) {
		if (isEmpty()) {
			inhalt = i;
			return;
		}
		if (i <= inhalt) {
			if (links == null) {
				links = new Node();
				links.parent = this;
			}
			links.insert(i);
		} else {
			if (rechts == null) {
				rechts = new Node();
				rechts.parent = this;
			}
			rechts.insert(i);
		}
	}

	public boolean contains(Integer i) {
		if (isEmpty()) {
			return false;
		}
		if (i == inhalt) {
			return true;
		}

		if (i <= inhalt) {
			if (links != null) {
				if (links.contains(i)) {
					return true;
				}
			}
		} else {
			if (rechts != null) {
				if (rechts.contains(i)) {
					return true;
				}
			}
		}
		return false;
	}

	public void delete(Integer i) {
		if (isEmpty()) {
			return;
		}
		if (inhalt == i) {
			if (links == null && rechts == null) {
				if (parent.links == this) {
					parent.links = null;
				}
				if (parent.rechts == this) {
					parent.rechts = null;
				}

			}
			if (links != null && rechts == null) {
				// nur linkes kind
			}
			if (links == null && rechts != null) {
				// nur rechtes kind
			}
			if (links != null && rechts != null) {
				// beide kinder
			}
		}

		if (i <= inhalt) {
			if (links != null) {
				links.delete(i);
			}
		} else {
			if (rechts != null) {
				rechts.delete(i);
			}
		}

	}
}
