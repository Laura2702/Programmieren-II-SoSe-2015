public class Node {
	Node links;
	Node rechts;
	Node parent;
	Integer inhalt;
	int depth = 0;
	int height = 0;

	public int height() {
		int z = 0;
		if (hatlinkesKind() && hatrechtesKind()) {
			z = Math.max(links.height(), rechts.height());
		} else if (hatlinkesKind()) {
			z = links.height();
		} else if (hatrechtesKind()) {
			z = rechts.height();
		}
		return z + 1;

	}

	public void insert(Integer x, int depth) {
		if (isEmpty()) {
			inhalt = x;
			return;
		}
		if (x < inhalt) {
			// links
			if (!hatlinkesKind()) {
				links = new Node();
				if (!parent.balanciert()){
					//baum rotieren
				}
				links.depth = depth;
			}
			links.parent = this;
			links.insert(x, ++depth);
			links.height = links.height();
		} else {
			// Fall rechts
			if (!hatrechtesKind()) {
				rechts = new Node();
				if (!parent.balanciert()){
					//baum rotieren
				}
				rechts.depth = depth;
			}
			rechts.parent = this;
			rechts.insert(x, ++depth);
			rechts.height = rechts.height();

		}
		this.height = height();
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
				if (x < inhalt && !hatlinkesKind()) {
					drin = false;
				} else {
					links.contains(x);
				}
			} else if (x > inhalt) {
				if (x > inhalt && !hatrechtesKind()) {
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
			if (!hatlinkesKind() && !hatrechtesKind()) {
				if (this == parent) {
					inhalt = null;
					return;
				}
				if (parent.hatlinkesKind()) {
					if (parent.links == this) {
						parent.links = null;
						// updateToRoot(parent);
						parent.height = parent.height();
						return;
					}
				} else if (parent.hatrechtesKind()) {
					if (parent.rechts == this) {
						parent.rechts = null;
						// updateToRoot(parent);
						parent.height = parent.height();
						return;
					}
				}
			}
			// linkes Kind
			if (hatlinkesKind() && !hatrechtesKind()) {
				if (this == parent) { // im wurzelknoten
					inhalt = links.inhalt;
					if (links.hatrechtesKind()) {
						rechts = links.rechts;
						links.rechts.parent = this;
					}
					if (links.hatlinkesKind()) {
						links.links.parent = this;
					}
					links.depth--;// maybe useless
					links = links.links;
					this.height = height();
					links.height = links.height();
					return;
				}
				if (parent.hatlinkesKind()) { // ich bin das linke kind.
					if (parent.links == this) {
						links.parent = parent;
						parent.links = links;
						links.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						return;
					}
				} else if (parent.hatrechtesKind()) {
					if (parent.rechts == this) {
						links.parent = parent;
						parent.rechts = links;
						links.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						return;
					}
				}
			}
			// rechtes Kind
			if (!hatlinkesKind() && hatrechtesKind()) {
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
					rechts.depth--;
					this.height = height();
					rechts.height = rechts.height();
					return;
				}
				if (parent.hatlinkesKind()) {
					if (parent.links == this) {
						rechts.parent = parent;
						parent.links = rechts;
						rechts.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						return;
					}
				} else if (parent.hatrechtesKind()) {
					if (parent.rechts == this) {
						rechts.parent = parent;
						parent.rechts = rechts;
						rechts.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						return;
					}
				}
			}
			// 2 Kinder
			if (hatlinkesKind() && hatrechtesKind()) {
				if (!rechts.hatlinkesKind()) {
					if (!rechts.hatrechtesKind()) {
						inhalt = rechts.inhalt;
						rechts = null;
						return;
					} else {
						inhalt = rechts.inhalt;
						rechts.rechts.parent = this;
						rechts = rechts.rechts;
						parent.height = height();
						rechts.height = rechts.height();
						return;
					}
				} else {
					Node lmc = leftMostChild(rechts);
					if (!lmc.hatrechtesKind()) {
						inhalt = lmc.inhalt;
						lmc.parent.links = null;
						// updateToRoot(lmc.parent);
						lmc.parent.height = lmc.parent.height();
						return;
					} else {
						inhalt = lmc.inhalt;
						lmc.rechts.parent = lmc.parent;
						lmc.parent.links = lmc.rechts;
						// updateToRoot(lmc.parent);
						lmc.parent.height = lmc.parent.height();
						return;
					}
				}
			}
		} else if (x < inhalt) {
			if (x < inhalt && !hatlinkesKind()) {
				return;
			} else {
				links.delete(x);
				height = height();
			}
		} else if (x > inhalt) {
			if (x > inhalt && !hatrechtesKind()) {
				return;
			} else {
				rechts.delete(x);
				height = height();
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
		while (tmp.hatlinkesKind()) {
			tmp = tmp.links;
		}
		return tmp;
	}

	public void updateToRoot(Node n) {
		Node temp = n;
		temp.height = height();
		while (temp.parent != temp) {
			temp = temp.parent;
			temp.height = height();
		}
	}

	public boolean balanciert() {
		if (links == null && rechts == null) {// keine kinder
			return true;
		}
		if (links != null && rechts == null) {// linkes kind
			if (links.height() > 1) {
				return false;
			} else {
				return true;
			}
		}
		if (links == null && rechts != null) {// rechtes kind
			if (rechts.height() > 1) {
				return false;
			} else {
				return true;
			}
		}
		if (Math.abs(links.height() - rechts.height()) > 1) {// beide kinder
			return false;
		} else {
			return true;
		}

	}
	public void rotation(){
		if (!parent.balanciert()){
			Node temp;
			temp = parent;
			while (temp.parent != temp) {
				temp = temp.parent;
				temp.height = height();
			}
		}
	}

}
