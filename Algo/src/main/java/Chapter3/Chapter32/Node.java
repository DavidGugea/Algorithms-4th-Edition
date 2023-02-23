package Chapter3.Chapter32;

public class Node<Key extends Comparable<Key>, Value> {
    public Key key;
    public Value val;

    public Node<Key, Value> left;
    public Node<Key, Value> right;

    public int N; // # nodes in subtree rooted here

    public Node (Key key, Value val, int N) {
        this.key = key;
        this.val = val;
        this.N = N;
    }
}
