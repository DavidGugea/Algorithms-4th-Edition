package Chapter3.Chapter31;

public class Node<Key, Value> {
    Key key;
    Value val;
    Node next;

    public Node(Key key, Value val, Node next) {
        this.key = key;
        this.val = val;
        this.next = next;
    }
}
