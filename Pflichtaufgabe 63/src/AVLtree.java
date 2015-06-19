public class AVLtree {
	Node root;

	public static void main(String args[]) {
		System.out.println("hallo");
		AVLtree B = new AVLtree();
		B.insert(3);
	/*	B.insert(2);
		B.insert(1);
		B.insert(4);
	*/	B.insert(5);
	    B.delete(5);
		System.out.println(B.contains(8));
		System.out.println(B);
	}

	public String toString() {
		return root.toString();

	}

	public boolean isEmpty() {
		if (root.inhalt != null) {
			return false;
		}
		return true;
	}

	public void insert(Integer i) {
		root.insert(i);
	}

	public boolean contains(Integer i) {
		return root.contains(i);

	}

	public void delete(Integer i) {
		root.delete(i);
	}

	public AVLtree() {
		root = new Node();
	}
}
