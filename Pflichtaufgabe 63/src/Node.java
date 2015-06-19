public class Node {
	Node links;
	Node rechts;
	Node parent;
	Integer inhalt;

	public void insert(Integer x) {
		if (isEmpty()) {
			inhalt = x;
			return;
		}
		if (x < inhalt) {
			// links
			if (links == null)
				links = new Node();
			links.parent = this;
			links.insert(x);
		} else {
			// Fall rechts
			if (rechts == null)
				rechts = new Node();
			rechts.parent = this;
			rechts.insert(x);
		}
	}

	public boolean isEmpty() {
		return inhalt == null;
	}

	public boolean contains(Integer x) {
		boolean drin = false;
		if (isEmpty()) {
			drin = false;
		} else {
			if (x == inhalt) {
				drin = true;
			} else if (x < inhalt) {
				if (x < inhalt && links == null) {
					drin = false;
				} else {
					links.contains(x);
				}
			} else if (x > inhalt) {
				if (x > inhalt && rechts == null) {
					drin = false;
				} else {
					rechts.contains(x);
				}
			}
		}
		return drin;
	}

	public void delete(Integer x) {
		if (isEmpty()) {
			return;
		}
		if (x == inhalt) {
			// keine Kinder
			if (links == null && rechts == null) {
				if (this == parent) {
					inhalt = null;
					return;
				}
				if (parent.links != null) {
					if (parent.links == this) {
						parent.links = null;
						return;
					}
				} else if (parent.rechts != null) {
					if (parent.rechts == this) {
						parent.rechts = null;
						return;
					}
				}
			}
			// linkes Kind
			if (hatlinkesKind() && rechts == null) {
				if (this == parent) {
					inhalt = links.inhalt;
					if (links.hatrechtesKind()) {
						rechts = links.rechts;
						links.rechts.parent = this;
					}
					if (links.hatlinkesKind()) {
						links.links.parent = this;
					}
					links = links.links;
					return;
				}
				if (parent.links != null) {
					if (parent.links == this) {
						links.parent = parent;
						parent.links = links;
						return;
					}
				} else if (parent.rechts != null) {
					if (parent.rechts == this) {
						links.parent = parent;
						parent.rechts = links;
						return;
					}
				}
			}
			// rechtes Kind
			if (links == null && hatrechtesKind()) {
				if (this == parent) {
					inhalt = rechts.inhalt;
					if (rechts.hatlinkesKind()) {
						links = rechts.links;
						rechts.links.parent = this;
					}
					if (rechts.hatrechtesKind()) {
						rechts.rechts.parent = this;
					}
					rechts = rechts.rechts;
					return;
				}
				if (parent.links != null) {
					if (parent.links == this) {
						rechts.parent = parent;
						parent.links = rechts;
						return;
					}
				} else if (parent.rechts != null) {
					if (parent.rechts == this) {
						rechts.parent = parent;
						parent.rechts = rechts;
						return;
					}
				}
			}
			// 2 Kinder
			if (hatlinkesKind() && hatrechtesKind()) {
				
			}

		} else if (x < inhalt) {
			if (x < inhalt && links == null) {
				return;
			} else {
				links.delete(x);
			}
		} else if (x > inhalt) {
			if (x > inhalt && rechts == null) {
				return;
			} else {
				rechts.delete(x);
			}
		}
	}

	public String toString() {
		String b = "";
		b += inhalt + "(";
		if (hatlinkesKind()) {
			b += links.toString();
		}
		b += ",";
		if (hatrechtesKind()) {
			b += rechts.toString();
		}
		b += ")";
		System.out.println(b);
		return b;
	}

	public boolean hatlinkesKind() {
		return links != null;
	}

	public boolean hatrechtesKind() {
		return rechts != null;
	}
}
