public class Baum extends Node<xy>  {
	Node<xy> root;

	public static void main(String[] args) {
		Baum b = new Baum();
		//b.insert(100);
		//b.insert(20);
		//b.insert(15);
		b.insert(5);
		b.insert(15);
		b.insert(10);
		b.insert(6);
		b.insert(7);
		b.insert(4);
		
		b.toString();
		System.out.println(b.root.balanciert());
		System.out.println(b.root.links.balanciert());
		System.out.println(b.root.rechts.height());
		System.out.println(b.root.rechts.links.height());
		System.out.println(b.root.rechts.links.links.height());
		b.delete(5);
		b.toString();
	}
	
	public Baum() {
		root = new Node<xy>();
		root.parent = root;
		
	}

	public String toString() {
		return root.toString();
	}

	public void insert(Integer x) {
		root.insert(x, 1);
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
