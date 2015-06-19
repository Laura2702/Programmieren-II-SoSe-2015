public class Baum {
	Node root;

	public static void main(String[] args) {
		Baum b = new Baum();
		//b.insert(100);
		//b.insert(20);
		//b.insert(15);
		b.insert(5);
		b.insert(7);
		b.insert(8);
		b.insert(6);
		
		b.toString();
		System.out.println(b.contains(50));
		b.delete(5);
		b.toString();
	}
	
	public Baum() {
		root = new Node();
		root.parent = root;
	}

	public String toString() {
		return root.toString();
	}

	public void insert(Integer x) {
		root.insert(x);
	}

	public boolean isEmpty() {
		return root.isEmpty();
	}

	public boolean contains(Integer x) {
		return root.contains(x);
	}

	public void delete(Integer x) {
		root.delete(x);
	}
}
