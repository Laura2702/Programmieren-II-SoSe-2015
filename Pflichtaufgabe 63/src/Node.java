public class Node<xy extends Comparable<xy>> {
	Node<xy> links;
	Node<xy> rechts;
	Node<xy> parent;
	xy inhalt;
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

	/**
	 * Gibt die Anzahl der Items im Baum zurueck
	 * 
	 * @return die Anzahl
	 */
	public int size() {
		if (links == null && rechts == null) {
			return 1;
		}
		if (links == null) {
			return 1 + rechts.size();
		}
		if (rechts == null) {
			return 1 + links.size();
		}
		return 1 + links.size() + rechts.size();
	}

	/**
	 * Gibt das Item an Position i zurueck
	 * 
	 * @param i
	 *            die Position
	 * @return das Item
	 */
	public xy getItem(int i) {
		int sizeLeft = 0;
		if (links != null) {
			sizeLeft = links.size();
		}

		if (i == sizeLeft || rechts == null) {
			return inhalt;
		}

		if (i < sizeLeft) {
			return links.getItem(i);
		} else {
			return rechts.getItem(i - sizeLeft - 1);
		}
	}

	public void insert(xy x, int depth) {
		if (isEmpty()) {
			inhalt = x;
			return;
		}
		if (x.compareTo(inhalt) < 0) {
			// links
			if (!hatlinkesKind()) {
				links = new Node<xy>();

				links.depth = depth;
			}
			links.parent = this;
			links.insert(x, ++depth);
			links.height = links.height();
		} else {
			// Fall rechts
			if (!hatrechtesKind()) {
				rechts = new Node<xy>();

				rechts.depth = depth;
			}
			rechts.parent = this;
			rechts.insert(x, ++depth);
			rechts.height = rechts.height();

		}
		this.height = height();
		rebalancieren();
	}

	public boolean isEmpty() {
		return inhalt == null;
	}

	public boolean contains(xy x) {
		boolean drin = false;
		if (isEmpty()) {
			drin = false;
		} else {
			if (x == inhalt) {
				drin = true;
			} else if (x.compareTo(inhalt) < 0) {
				if (x.compareTo(inhalt) < 0 && !hatlinkesKind()) {
					drin = false;
				} else {
					links.contains(x);
				}
			} else if (x.compareTo(inhalt) > 0) {
				if (x.compareTo(inhalt) > 0 && !hatrechtesKind()) {
					drin = false;
				} else {
					rechts.contains(x);
				}
			}
		}
		return drin;
	}

	public void delete(xy x) {
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
					rebalancieren();
					return;
				}
				if (parent.hatlinkesKind()) { // ich bin das linke kind.
					if (parent.links == this) {
						links.parent = parent;
						parent.links = links;
						links.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						rebalancieren();
						return;
					}
				} else if (parent.hatrechtesKind()) {
					if (parent.rechts == this) {
						links.parent = parent;
						parent.rechts = links;
						links.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						rebalancieren();
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
					rebalancieren();
					return;
				}
				if (parent.hatlinkesKind()) {
					if (parent.links == this) {
						rechts.parent = parent;
						parent.links = rechts;
						rechts.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						rebalancieren();
						return;
					}
				} else if (parent.hatrechtesKind()) {
					if (parent.rechts == this) {
						rechts.parent = parent;
						parent.rechts = rechts;
						rechts.depth--;
						// updateToRoot(parent);
						parent.height = parent.height();
						rebalancieren();
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
						rebalancieren();
						return;
					} else {
						inhalt = rechts.inhalt;
						rechts.rechts.parent = this;
						rechts = rechts.rechts;
						parent.height = height();
						rechts.height = rechts.height();
						rebalancieren();
						return;
					}
				} else {
					Node<xy> lmc = leftMostChild(rechts);
					if (!lmc.hatrechtesKind()) {
						inhalt = lmc.inhalt;
						lmc.parent.links = null;
						// updateToRoot(lmc.parent);
						lmc.parent.height = lmc.parent.height();
						rebalancieren();
						return;
					} else {
						inhalt = lmc.inhalt;
						lmc.rechts.parent = lmc.parent;
						lmc.parent.links = lmc.rechts;
						// updateToRoot(lmc.parent);
						lmc.parent.height = lmc.parent.height();
						rebalancieren();
						return;
					}
				}
			}
		} else if (x.compareTo(inhalt) < 0) {
			if (x.compareTo(inhalt) < 0 && !hatlinkesKind()) {
				rebalancieren();
				return;
			} else {
				links.delete(x);
				height = height();
			}
		} else if (x.compareTo(inhalt) > 0) {
			if (x.compareTo(inhalt) > 0 && !hatrechtesKind()) {
				rebalancieren();
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

	public Node<xy> leftMostChild(Node<xy> start) {
		Node<xy> tmp = start;
		while (tmp.hatlinkesKind()) {
			tmp = tmp.links;
		}
		return tmp;
	}

	public void updateToRoot(Node<xy> n) {
		Node<xy> temp = n;
		temp.height = height();
		while (temp.parent != temp) {
			temp = temp.parent;
			temp.height = height();
		}
	}

	public int balanciert() {
		if (links == null && rechts == null) {// keine kinder
			return 0;
		}
		if (links != null && rechts == null) {// linkes kind

			return links.height();
		}

		if (links == null && rechts != null) {// rechtes kind

			return 0 - rechts.height();
		}

		// beide kinder

		return links.height() - rechts.height();
	}

	public void rebalancieren() {
		if (balanciert() == 2) {
			if (links.balanciert() == 1) {
				rotateRight();
			} else {
				doubleRotateRight();
			}
		}
		if (balanciert() == -2) {
			if (rechts.balanciert() == -1) {
				rotateLeft();
			} else {
				doubleRotateLeft();
			}
		}
	}

	public void rotateLeft() {
		Node<xy> newLeft = new Node<xy>();
		newLeft.inhalt = inhalt;
		if (links != null) {
			newLeft.links = links;
			links.parent = newLeft;
		}
		if (rechts.links != null) {
			newLeft.rechts = rechts.links;
			rechts.links.parent = newLeft;
		}
		inhalt = rechts.inhalt;
		links = newLeft;
		links.parent = this;
		rechts = rechts.rechts;
		if (rechts != null) {
			rechts.parent = this;
		}
	}

	public void rotateRight() {
		Node<xy> newRight = new Node<xy>();
		newRight.inhalt = inhalt;
		if (rechts != null) {
			newRight.rechts = rechts;
			rechts.parent = newRight;
		}
		if (links.rechts != null) {
			newRight.links = links.rechts;
			links.rechts.parent = newRight;
		}
		inhalt = links.inhalt;
		rechts = newRight;
		rechts.parent = this;
		links = links.links;
		if (links != null) {
			links.parent = this;
		}
	}

	public void doubleRotateRight() {
		links.rotateLeft();
		rotateRight();
	}

	public void doubleRotateLeft() {
		rechts.rotateRight();
		rotateLeft();
	}

}
