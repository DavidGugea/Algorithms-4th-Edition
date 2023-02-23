package Chapter3.Chapter32;

public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public int size() {
        return size(root);
    }

    public int size(Node<Key, Value> x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node<Key, Value> x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node<Key, Value> x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}
