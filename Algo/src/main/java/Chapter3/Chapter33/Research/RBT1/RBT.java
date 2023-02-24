package Chapter3.Chapter33.Research.RBT1;

import java.util.LinkedList;
import java.util.Queue;

public class RBT {
    public Node root;

    public Node search(int data) {
        if (this.root == null)
            return null;

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

        return null;
    }

    public void preorder(Node node) {
        if (node == null) return;

        System.out.print(node.data + " ( " + node.color + " ) ");
        preorder(node.left);
        preorder(node.right);
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    public void rightRotation(Node node) {
        if (node == null)
            return;

        Node leftNode = node.left;
        Node leftRightNode = node.left.right;

        if (node == this.root) {
            this.root = leftNode;
        }

        leftNode.parent = node.parent;
        if (node.parent != null && node.parent.left != null && node.parent.left.data == node.data) {
            node.parent.left = leftNode;
        } else if (node.parent != null && node.parent.right != null && node.parent.right.data == node.data) {
            node.parent.right = leftNode;
        }

        leftNode.right = node;
        node.parent = leftNode;

        node.left = leftRightNode;

        if (leftRightNode != null)
            leftRightNode.parent = node;
    }

    public void leftRotation(Node node) {
        if (node == null)
            return;

        Node rightNode = node.right;
        Node rightLeftNode = node.right != null ? node.right.left : null;

        if (node == this.root) {
            this.root = rightNode;
        }

        rightNode.parent = node.parent;

        if (node.parent != null && node.parent.left.data == node.data) {
            node.parent.left = rightNode;
        } else if (node.parent != null && node.parent.right.data == node.data) {
            node.parent.right = rightNode;
        }

        rightNode.left = node;
        node.parent = rightNode;

        node.right = rightLeftNode;

        if (rightLeftNode != null) {
            rightLeftNode.parent = node;
        }
    }

    public void insert(int data) {
        // Firstly use the normal BST insert algorithm
        if (this.root == null) {
            this.root = new Node(data, 'B');
            return;
        }

        Node previous = null;
        Node current = this.root;

        while (current != null) {
            previous = current;

            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else {
                throw new IllegalArgumentException("There is a node in the RBT that already contains the given data.");
            }
        }

        Node newNode = new Node(data, 'R');
        newNode.parent = previous;

        if (data < previous.data) {
            previous.left = newNode;
        } else {
            previous.right = newNode;
        }

        // Fix the RBT through recoloring and rotations.
        this.insertionFix(newNode);
    }

    private void insertionFix(Node newNode) {
        Node grandparentNode = this.getNodeGrandparent(newNode);

        if (newNode == this.root) {
            // Case 0 (A): The NewNode is the root
            newNode.color = 'B';
        } else if (newNode.parent != null && newNode.parent.color == 'R') {
            // Case 0 (B) : Recoloring
            this.fix_insertion_case_0(newNode);
            this.insertionFix(newNode);
        } else if (grandparentNode != null && newNode.parent != null && newNode.parent.left == newNode && grandparentNode.left == newNode.parent) {
            this.fix_insertion_case_1(newNode, grandparentNode);
            this.insertionFix(newNode);
        } else if (grandparentNode != null && newNode.parent != null && newNode.parent.right == newNode && grandparentNode.right == newNode.parent) {
            this.fix_insertion_case_2(newNode, grandparentNode);
            this.insertionFix(newNode);
        } else if (grandparentNode != null && newNode.parent != null && newNode.parent.right == newNode && grandparentNode.left == newNode.parent) {
            this.fix_insertion_case_3(newNode, grandparentNode);
            this.insertionFix(newNode);
        } else if (grandparentNode != null && newNode.parent != null && newNode.parent.left == newNode && grandparentNode.right == newNode.parent) {
            this.fix_insertion_case_4(newNode, grandparentNode);
            this.insertionFix(newNode);
        }
    }

    private void fix_insertion_case_4(Node newNode, Node grandparentNode) {
        // CASE 3: RIGHT-LEFT
        System.out.println("FIXING CASE 4 FOR NODE WITH DATA " + newNode.data);

        this.rightRotation(newNode.parent);
        this.leftRotation(grandparentNode);

        if (newNode.left != null) {
            newNode.left.color = 'R';
        }

        if (newNode.right != null) {
            newNode.right.color = 'R';
        }
    }

    private void fix_insertion_case_3(Node newNode, Node grandparentNode) {
        // CASE 3: LEFT-RIGHT
        System.out.println("FIXING CASE 3 FOR NODE WITH DATA " + newNode.data);

        this.leftRotation(newNode.parent);
        this.rightRotation(grandparentNode);

        if (newNode.left != null) {
            newNode.left.color = 'R';
        }

        if (newNode.right != null) {
            newNode.right.color = 'R';
        }
    }

    private void fix_insertion_case_2(Node newNode, Node grandparentNode) {
        // CASE 2: RIGHT-RIGHT
        System.out.println("FIXING CASE 2 FOR NODE WITH DATA " + newNode.data);

        this.leftRotation(grandparentNode);

        newNode.color = 'R';
        newNode.parent.color = 'B';
        newNode.parent.left.color = 'R';

        this.insertionFix(grandparentNode);
    }

    private void fix_insertion_case_1(Node newNode, Node grandparentNode) {
        // Case 1: LEFT-LEFT
        System.out.println("FIXING CASE 1 FOR NODE WITH DATA " + newNode.data);

        this.rightRotation(grandparentNode);

        newNode.color = 'R';
        newNode.parent.color = 'B';
        newNode.parent.right.color = 'R';

        this.insertionFix(grandparentNode);
    }

    private void fix_insertion_case_0(Node newNode) {
        System.out.println("FIXING CASE 0 FOR NODE WITH DATA " + newNode.data);

        if (newNode == this.root) {
            newNode.color = 'B';
        } else {
            // Change the grandparent's color to RED and the parent's and uncle's color to be black
            newNode.parent.parent.color = 'R';
            newNode.parent.color = 'B';

            Node nodeUncle = this.getNodeUncle(newNode);
            if (nodeUncle != null) {
                // If it is null it's already considered 'BLACK'
                nodeUncle.color = 'B';
            }

            // Fix the grandparent
            this.insertionFix(newNode.parent.parent);
        }
    }

    private Node getNodeGrandparent(Node node) {
        if (node == null)
            return null;

        if (node.parent == null)
            return null;
        else
            return node.parent.parent;
    }

    private Node getNodeUncle(Node node) {
        if (node == null)
            return null;

        if (node.parent.parent.left != null && node.parent.parent.left.data == node.parent.data) {
            return node.parent.parent.right;
        } else if (node.parent.parent.right != null && node.parent.parent.right.data == node.parent.data) {
            return node.parent.parent.left;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        RBT tree = new RBT();

        // Case 0 -> recoloring
        int[] values_case_0_0 = {10};
        int[] values_case_0_1 = {10, 6, 13, 2};
        int[] values_case_0_2 = {10, 7, 15, 9};

        // Case 1 -> LEFT-LEFT
        int[] values_case_1_0 = {10, 6, 4};

        // Case 2 -> RIGHT-RIGHT
        int[] values_case_2_0 = {10, 12, 15};

        // Case 3 -> LEFT-RIGHT
        int[] values_case_3_0 = {10, 6, 9};

        // Case 4 -> RIGHT-LEFT
        int[] values_case_4_0 = {10, 15, 12};

        // Arbitrary case
        int[] values_arbitrary = {25, 10, 20, 5};

        for (int i : values_arbitrary) {
            tree.insert(i);
        }

        tree.preorder(tree.root);
        System.out.println("");
    }
}
