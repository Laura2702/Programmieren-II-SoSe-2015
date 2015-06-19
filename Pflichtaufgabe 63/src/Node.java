public class Node {
	Node links;
	Node rechts;
	Node parent;
	Integer inhalt;
	int height = 0;

	public void insert(Integer x, int height) {
		if (isEmpty()) {
			inhalt = x;
			return;
		}
		if (x < inhalt) {
			// links
			if (links == null) {
				links = new Node();
				links.height = height;
			}
			links.parent = this;
			links.insert(x, ++height);
		} else {
			// Fall rechts
			if (rechts == null) {
				rechts = new Node();
				rechts.height = height;
			}
			rechts.parent = this;
			rechts.insert(x, ++height);
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
					links.height--;
					return;
				}
				if (parent.links != null) {
					if (parent.links == this) {
						links.parent = parent;
						parent.links = links;
						links.height--;
						return;
					}
				} else if (parent.rechts != null) {
					if (parent.rechts == this) {
						links.parent = parent;
						parent.rechts = links;
						links.height--;
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
					rechts.height--;
					return;
				}
				if (parent.links != null) {
					if (parent.links == this) {
						rechts.parent = parent;
						parent.links = rechts;
						rechts.height--;
						return;
					}
				} else if (parent.rechts != null) {
					if (parent.rechts == this) {
						rechts.parent = parent;
						parent.rechts = rechts;
						rechts.height--;
						return;
					}
				}
			}
			// 2 Kinder
			if (hatlinkesKind() && hatrechtesKind()) {
				if (rechts.links == null) {
					if (rechts.rechts == null) {
						inhalt = rechts.inhalt;
						rechts = null;
						rechts.height--;
						return;
					} else {
						inhalt = rechts.inhalt;
						rechts.rechts.parent = this;
						rechts = rechts.rechts;
						rechts.height--;
						return;
					}
				} else {
					Node lmc = leftMostChild(rechts);
					if (lmc.rechts == null) {
						inhalt = lmc.inhalt;
						lmc.parent.links = null;
						return;
					} else {
						inhalt = lmc.inhalt;
						lmc.rechts.parent = lmc.parent;
						lmc.parent.links = lmc.rechts;
						lmc.rechts.height--;
						return;
					}
				}
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
		boolean klammern = false;
		if (hatlinkesKind() || hatrechtesKind())
			klammern = true;

		b += inhalt + "[" + height + "] ";
		if (klammern)
			 b += "(";
		if (hatlinkesKind()) {
			b += links.toString();
		}
		if (klammern)
			b += ",";
		if (hatrechtesKind()) {
			b += rechts.toString();
		}
		if (klammern)
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

	public Node leftMostChild(Node start) {
		Node tmp = start;
		while (tmp.links != null) {
			tmp = tmp.links;
		}
		return tmp;
	}
	
/*	public boolean balanciert()	{
	}*/
	
}
