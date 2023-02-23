package Chapter3.Chapter33.Research;

public class Node {
    public int data;

    public char color; // 'R' or 'B'

    public Node left;
    public Node right;
    public Node parent;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, char color) {
        this.data = data;
        this.color = color;
    }
}
