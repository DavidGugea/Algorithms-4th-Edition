package Chapter3.Chapter33.Research.RBT2;

public class Node {
    public int data;

    public Node left;
    public Node right;
    public Node parent;

    public String color;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, String color) {
        this.data = data;
        this.color = color;
    }
}
