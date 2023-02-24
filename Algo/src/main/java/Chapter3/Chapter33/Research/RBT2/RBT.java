package Chapter3.Chapter33.Research.RBT2;

public class RBT {
    public Node root;

    public Node search(int data) {
        if (this.root == null) {
            return null;
        }

        Node current = this.root;

        while (current != null) {
            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                return current;
            }
        }

        throw new IllegalArgumentException("No nodes could be found with the given data in the RBT.");
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = parent.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Ineffable state when trying to reach the parent of the oldChild Node.");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    public void insert(int data) {
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;

            if (data < node.data) {
                node = node.left;
            } else if (data > node.data) {
                node = node.right;
            } else {
                throw new IllegalArgumentException("The given data is already in the RBT.");
            }
        }

        Node newNode = new Node(data, "RED");
        newNode.parent = parent;

        if (parent == null) {
            root = newNode;
        } else if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
}
