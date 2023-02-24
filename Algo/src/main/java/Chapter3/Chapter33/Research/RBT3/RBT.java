package Chapter3.Chapter33.Research.RBT3;

import java.util.LinkedList;
import java.util.Queue;

public class RBT {
    public Node root;

    public void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ( " + node.color + " ) ");
        preorder(node.left);
        preorder(node.right);
    }

    public void levelOrder() {
        Node current = this.root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(current);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.data + " ( " + node.color + " ) ");

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

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

        throw new IllegalArgumentException("No nodes were found with the given data.");
    }

    private void rightRotation(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild.parent;

        this.replaceNodeParents(parent, node, leftChild);
    }

    private void leftRotation(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        this.replaceNodeParents(parent, node, rightChild);
    }

    private void replaceNodeParents(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            this.root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else {
            parent.right = newChild;
        }

        newChild.parent = parent;
    }

    public void insert(int data) {
        Node newNode = new Node(data, 'R');

        if (this.root == null) {
            this.root = newNode;
        } else {
            Node previous = null;
            Node current = this.root;

            while (current != null) {
                previous = current;

                if (data < current.data) {
                    current = current.left;
                } else if (data > current.data) {
                    current = current.right;
                } else {
                    throw new IllegalArgumentException("The given data is already present in the BST");
                }
            }

            newNode.parent = previous;

            if (data < previous.data) {
                previous.left = newNode;
            } else {
                previous.right = newNode;
            }
        }

        this.fixInsert(newNode);
    }

    private Node getNodeUncle(Node node) {
        if (node.parent == null) {
            return null;
        }

        if (node.parent.parent == null) {
            return null;
        }

        if (node.parent.parent.right == node.parent) {
            return node.parent.parent.left;
        } else {
            return node.parent.parent.right;
        }
    }

    private void fixInsert(Node newNode) {
        Node grandparent = newNode.parent == null ? null : newNode.parent.parent;
        Node uncle = getNodeUncle(newNode);

        if (this.root == newNode) {
            // Case 1 -> New node is root
            this.fix_case_1(newNode);
        } else if (newNode.parent != null && uncle != null && newNode.parent.color == 'R' && uncle.color == 'R') {
            // Case 2 -> Parent & Uncle nodes are red
            this.fix_case_2(newNode, uncle, grandparent);
        } else if (newNode.parent != null && newNode.parent.parent != null && newNode.parent.color == 'R' && (uncle == null || uncle.color == 'B')) {
            // 4 cases
            if (grandparent.right == newNode.parent && newNode.parent.left == newNode) {
                // CASE 3 (A) - RL CASE
                this.fix_case_3_a(newNode, grandparent);
            } else if (grandparent.left == newNode.parent && newNode.parent.right == newNode) {
                // CASE 3 (B) - LR CASE
                this.fix_case_3_b(newNode, grandparent);
            } else if (grandparent.right == newNode.parent && newNode.parent.right == newNode) {
                // CASE 4 (A) -- RR CASE
                this.fix_case_4_a(newNode, grandparent);
            } else if (grandparent.left == newNode.parent && newNode.parent.left == newNode) {
                // CASE 4 (B) -- LL CASE
                this.fix_case_4_b(newNode, grandparent);
            }
        }
    }

    private void fix_case_4_b(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 4 (B-LL) FOR NODE WITH DATA " + newNode.data);

        rightRotation(grandparent);

        newNode.parent.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_case_4_a(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 4 (A-RR) FOR NODE WITH DATA " + newNode.data);

        leftRotation(grandparent);

        newNode.parent.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_case_3_b(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 3 (B-LR) FOR NODE WITH DATA " + newNode.data);

        leftRotation(newNode.parent);
        rightRotation(grandparent);

        newNode.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_case_3_a(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 3 (A-RL) FOR NODE WITH DATA " + newNode.data);

        rightRotation(newNode.parent);
        leftRotation(grandparent);

        newNode.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_case_2(Node newNode, Node uncle, Node grandparent) {
        System.out.println("FIXING CASE 2 FOR NODE WITH DATA " + newNode.data);

        uncle.color = 'B';
        newNode.parent.color = 'B';
        grandparent.color = 'R';

        // Recursively fix coloring on the grandparent node.
        this.fixInsert(grandparent);
    }

    private void fix_case_1(Node newNode) {
        System.out.println("FIXING CASE 1 FOR NODE WITH DATA " + newNode.data);

        newNode.color = 'B';
    }

    public static void main(String[] args) {
        RBT rbt = new RBT();

        // CASE 1 VALUES
        int[] values_case_0_0 = {7};

        // CASE 2 VALUES
        int[] values_case_2_0 = {17, 9, 19, 20};

        // CASE 3 A VALUES
        int[] values_case_3_a_0 = {17, 9, 19, 75, 24};

        // CASE 3 B VALUES
        int[] values_case_3_b_0 = {17, 9, 19, 5, 6};

        // CASE 4 A VALUES
        int[] values_case_4_a_0 = {17, 9, 19, 75, 81};

        // CASE 4 B VALUES
        int[] values_case_4_b_0 = {17, 9, 19, 5, 1};

        // ARBITRARY VALUES
        int[] arbitrary_values_0 = {17, 9, 19, 18};

        for (int i : arbitrary_values_0) {
            rbt.insert(i);
        }

        rbt.preorder(rbt.root);
        System.out.println(" ");
    }
}
