public class Baum<T extends Comparable<T>> implements List<T> {
    Node<T> root;
    
    /**
     * Baum-Test
     * @param args
     */
    public static void main(String[] args) {
        Baum b = new Baum();
        // b.insert(100);
        // b.insert(20);
        // b.insert(15);
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
    /**
     * Baum-Constructor
     */
    public Baum() {
        root = new Node<T>();
        root.parent = root;

    }

    public List<T> insert(T x) {
        root.insert(x, 1);
        return this;
    }

    public boolean isEmpty() {
        return root.isEmpty();
    }

    public boolean contains(T x) {
        return root.contains(x);
    }

    public List<T> delete(T x) {
        root.delete(x);
        return this;
    }

    @Override
    public int length() {
        // TODO Auto-generated method stub
        return root.size();
    }

    @Override
    public boolean isInList(T x) {
        // TODO Auto-generated method stub
        return contains(x);
    }

    @Override
    public T firstItem() throws IllegalStateException {
        // TODO Auto-generated method stub
        if (!isEmpty()) {
            return root.leftMostChild(root).inhalt;
        }
        throw new IllegalStateException();

    }

    @Override
    public T getItem(int i) throws IndexOutOfBoundsException {
        return root.getItem(i);
        // TODO Auto-generated method stub

    }

    @Override
    public List<T> append(T x) {
        throw new UnsupportedOperationException();
        // TODO Auto-generated method stub

    }

    @Override
    public List<T> delete() {
        if (!isEmpty()) {
            delete(firstItem());
        }
        // TODO Auto-generated method stub
        return this;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        int length = length();
        StringBuilder string = new StringBuilder();
        if (this.isEmpty()) {
            string.append("Inventar ist leer.");
            return string.toString();
        }
        for (int i = 0; i < length; i++) {
            T item = getItem(i);
            if (item instanceof Item) {
                Item neuesItem = (Item) item;
                string.append("\n");
                string.append(i);
                string.append(" - ");
                string.append(((Item) getItem(i)).getName());
                string.append(" - ");
                string.append((int) (((Item) getItem(i)).getValue() * 1.3));
                string.append("G - ");
                string.append(((Item) getItem(i)).getValue());
                string.append("G - ");
                string.append(((Item) getItem(i)).getWeight());
                string.append("g");
            }
            if (item instanceof Quest) {
                Quest neueQuest = (Quest) item;
                string.append("\n");
                string.append(i);
                string.append(" - ");
                string.append(((Quest) getItem(i)).getName());
                string.append(" - ");
                string.append(((Quest) getItem(i)).getPrequest());
                string.append(" - ");
                string.append(((Quest) getItem(i)).getItem());
                string.append(" - ");
                string.append(((Quest) getItem(i)).getQuantity());
            }
        }
        return string.toString();
    }
}
