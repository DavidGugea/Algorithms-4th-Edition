package Chapter3.Chapter31;

public class SequentialSearchST<Key, Value> {
    private Node<Key, Value> first;

    public Value get(Key key) {
        for (Node<Key, Value> x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;

        return null;
    }

    public void put(Key key, Value val) {
        for (Node<Key, Value> x = first; x != null; x = x.next)
            if(key.equals(x.key)) {
                x.val = val;
                return;
            }

        first = new Node(key, val, first);
    }
}
