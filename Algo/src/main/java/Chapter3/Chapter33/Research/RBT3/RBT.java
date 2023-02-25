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
            this.fix_insertion_case_1(newNode);
        } else if (newNode.parent != null && uncle != null && newNode.parent.color == 'R' && uncle.color == 'R') {
            // Case 2 -> Parent & Uncle nodes are red
            this.fix_insertion_case_2(newNode, uncle, grandparent);
        } else if (newNode.parent != null && newNode.parent.parent != null && newNode.parent.color == 'R' && (uncle == null || uncle.color == 'B')) {
            // 4 cases
            if (grandparent.right == newNode.parent && newNode.parent.left == newNode) {
                // CASE 3 (A) - RL CASE
                this.fix_insertion_case_3_a(newNode, grandparent);
            } else if (grandparent.left == newNode.parent && newNode.parent.right == newNode) {
                // CASE 3 (B) - LR CASE
                this.fix_insertion_case_3_b(newNode, grandparent);
            } else if (grandparent.right == newNode.parent && newNode.parent.right == newNode) {
                // CASE 4 (A) -- RR CASE
                this.fix_insertion_case_4_a(newNode, grandparent);
            } else if (grandparent.left == newNode.parent && newNode.parent.left == newNode) {
                // CASE 4 (B) -- LL CASE
                this.fix_insertion_case_4_b(newNode, grandparent);
            }
        }
    }

    private void fix_insertion_case_4_b(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 4 (B-LL) FOR NODE WITH DATA " + newNode.data);

        rightRotation(grandparent);

        newNode.parent.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_insertion_case_4_a(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 4 (A-RR) FOR NODE WITH DATA " + newNode.data);

        leftRotation(grandparent);

        newNode.parent.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_insertion_case_3_b(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 3 (B-LR) FOR NODE WITH DATA " + newNode.data);

        leftRotation(newNode.parent);
        rightRotation(grandparent);

        newNode.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_insertion_case_3_a(Node newNode, Node grandparent) {
        System.out.println("FIXING CASE 3 (A-RL) FOR NODE WITH DATA " + newNode.data);

        rightRotation(newNode.parent);
        leftRotation(grandparent);

        newNode.color = 'B';
        grandparent.color = 'R';
    }

    private void fix_insertion_case_2(Node newNode, Node uncle, Node grandparent) {
        System.out.println("FIXING CASE 2 FOR NODE WITH DATA " + newNode.data);

        uncle.color = 'B';
        newNode.parent.color = 'B';
        grandparent.color = 'R';

        // Recursively fix coloring on the grandparent node.
        this.fixInsert(grandparent);
    }

    private void fix_insertion_case_1(Node newNode) {
        System.out.println("FIXING CASE 1 FOR NODE WITH DATA " + newNode.data);

        newNode.color = 'B';
    }

    private Node getSibling(Node node) {
        if (node.parent == null) {
            return null;
        }

        if (node.parent.left == node) {
            return node.parent.right;
        } else {
            return node.parent.left;
        }
    }

    private Node getOuterNephew(Node node) {
        if (node.parent == null) {
            return null;
        }

        Node sibling = this.getSibling(node);

        if (sibling == null) {
            return null;
        }

        if (node.parent.right == sibling) {
            return sibling.right;
        } else {
            return sibling.left;
        }
    }

    private Node getBlackOrNullNephew(Node node) {
        Node siblingNode = this.getSibling(node);

        if (siblingNode == null) {
            return null;
        }

        if (siblingNode.left == null) {
            return null;
        } else if (siblingNode.left.color == 'B') {
            return siblingNode.left;
        }

        if (siblingNode.right == null) {
            return null;
        } else if (siblingNode.right.color == 'B') {
            return siblingNode.right;
        }

        throw new IllegalStateException("Both outer nephews are red.");
    }

    public Node getBiggestNodeFromSubtree(Node subtreeRootNode) {
        if (subtreeRootNode == null) {
            return null;
        }

        while (subtreeRootNode.right != null) {
            subtreeRootNode = subtreeRootNode.right;
        }

        return subtreeRootNode;
    }

    public void recolorNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.color == 'R') {
            node.color = 'B';
        } else {
            node.color = 'R';
        }
    }

    public void deleteNode(int data) {
        if (this.root.data == data) {
            this.fix_deletion_case_0();
            return;
        }

        // FIND THE NODE THAT NEEDS TO BE DELETED.
        Node deleteNode = this.root;

        while (deleteNode != null && deleteNode.data != data) {
            if (data < deleteNode.data) {
                deleteNode = deleteNode.left;
            } else {
                deleteNode = deleteNode.right;
            }
        }

        if (deleteNode == null) {
            throw new IllegalArgumentException("No node with the given data could be found in the RBT.");
        }

        this.fixDeletion(deleteNode);
    }

    public boolean nodeHasOneRedChildAndOuterNephewIsBlackOrNull(Node node) {
        if (node.right != null) {
            if (node.right.color == 'B' && node.left != null && node.left.color == 'R') {
                return true;
            }
            if (node.right.color == 'R' && node.left == null) {
                return true;
            }
            if (node.right.color == 'R' && node.left.color == 'B') {
                return true;
            }
        } else {
            // node.right is null
            if (node.left == null) {
                return false;
            }

            if (node.left.color == 'R') {
                return true;
            }
        }

        return false;
    }

    public boolean nodeHasTwoBlackChildrenOrTwoNullChildren(Node node) {
        if (node.left != null && node.left.color == 'R') {
            return false;
        }

        if (node.right != null && node.right.color == 'R') {
            return false;
        }

        return true;
    }

    private void fixDeletion(Node deleteNode) {
        // Case 0 is called directly from the deletion method.
        // This method fixed cases 1 and upwards...
        Node siblingNode = this.getSibling(deleteNode);
        Node parentNode = deleteNode.parent;

        if (deleteNode.color == 'R') {
            // Case 1: Deleted node is red.
            this.fix_deletion_case_1(deleteNode);
        } else if (siblingNode != null && siblingNode.color == 'R') {
            this.fix_deletion_case_2(deleteNode, siblingNode);
        } else if (siblingNode != null && siblingNode.color == 'B' && this.nodeHasTwoBlackChildrenOrTwoNullChildren(siblingNode) && parentNode.color == 'R') {
            this.fix_deletion_case_3(deleteNode, siblingNode, parentNode);
        } else if (siblingNode != null && siblingNode.color == 'B' && this.nodeHasTwoBlackChildrenOrTwoNullChildren(siblingNode) && parentNode.color == 'B') {
            this.fix_deletion_case_4(deleteNode, siblingNode, parentNode);
        } else if (siblingNode != null && siblingNode.color == 'B' && this.nodeHasOneRedChildAndOuterNephewIsBlackOrNull(siblingNode)) {
            Node nephewNode = this.getBlackOrNullNephew(deleteNode);
            this.fix_deletion_case_5(deleteNode, siblingNode, parentNode, nephewNode);
        } else if (siblingNode != null && siblingNode.color == 'B' && siblingNode.left != null && siblingNode.left.color == 'R' && siblingNode.right != null && siblingNode.right.color == 'R') {
            Node nephewNode = this.getOuterNephew(deleteNode);
            this.fix_deletion_case_6(deleteNode, siblingNode, parentNode, nephewNode);
        }
    }

    private void fix_deletion_case_6(Node deleteNode, Node siblingNode, Node parentNode, Node nephewNode) {
        this.recolorNode(parentNode);
        this.recolorNode(siblingNode);
        this.recolorNode(parentNode);

        this.leftRotation(parentNode);
    }

    private void fix_deletion_case_5(Node deleteNode, Node siblingNode, Node parentNode, Node nephewNode) {

        if (parentNode.right == deleteNode) {
            parentNode.right = null;
        } else {
            parentNode.left = null;
        }

        this.recolorNode(siblingNode);
        this.recolorNode(parentNode);

        this.rightRotation(parentNode);

        this.recolorNode(parentNode);
        this.recolorNode(siblingNode);
        this.recolorNode(nephewNode);

        this.leftRotation(parentNode);
    }

    private void fix_deletion_case_4(Node deleteNode, Node siblingNode, Node parentNode) {
        // CASE 4 -> Sibling is black and has two black children, parent is black.

        System.out.println("FIXING DELETION CASE 4 FOR NODE WITH DATA " + deleteNode.data);

        if (parentNode.left == deleteNode) {
            parentNode.left = null;
        } else {
            parentNode.right = null;
        }

        this.recolorNode(siblingNode);
    }

    private void fix_deletion_case_3(Node deleteNode, Node siblingNode, Node parentNode) {
        // CASE 3 -> Sibling is black and has two black children, parent is red.

        System.out.println("FIXING DELETION CASE 3 FOR NODE WITH DATA " + deleteNode.data);

        if (parentNode.left == deleteNode) {
            parentNode.left = null;
        } else {
            parentNode.right = null;
        }

        this.recolorNode(siblingNode);
        this.recolorNode(parentNode);
    }

    private void fix_deletion_case_2(Node deleteNode, Node siblingNode) {
        // CASE 2 -> Sibling node is red.

        System.out.println("FIXING DELETION CASE 2: THE SIBLING NODE IS RED FOR NODE WITH DATA " + deleteNode.data);

        Node parentNode = deleteNode.parent;

        this.recolorNode(parentNode);
        this.recolorNode(siblingNode);

        if (parentNode.left == deleteNode) {
            // CASE 2 A - DELETE LEFT CHILD
            parentNode.left = null;
            this.leftRotation(parentNode);
        } else {
            // CASE 2 B - DELETE RIGHT CHILD
            parentNode.right = null;
            this.rightRotation(parentNode);
        }

        this.recolorNode(parentNode);
        this.recolorNode(parentNode.left); // CASE 2 A
        this.recolorNode(parentNode.right); // CASE 2 B
    }

    private void fix_deletion_case_0() {
        // CASE 0 : Delete the root node.

        System.out.println("FIXING DELETION CASE 0 : DELETE THE ROOT NODE");

        Node replaceDataNode;

        if (this.root.right != null) {
            replaceDataNode = this.getBiggestNodeFromSubtree(this.root.right);
        } else {
            replaceDataNode = this.getBiggestNodeFromSubtree(this.root.left);
        }

        if (replaceDataNode != null) {
            this.deleteNode(replaceDataNode.data);
            this.root.data = replaceDataNode.data;
        } else {
            this.root = null;
        }
    }

    private void fix_deletion_case_1(Node deleteNode) {
        // CASE 1 -> The deleted node is red.

        System.out.println("FIXING DELETION CASE 1: THE DELETED NODE IS RED FOR NODE WITH DATA " + deleteNode.data);

        if (deleteNode.left == null && deleteNode.right == null) {
            System.out.println("CASE 1 NO CHILDREN");

            // No children case
            if (deleteNode.parent.left == deleteNode) {
                deleteNode.parent.left = null;
            } else {
                deleteNode.parent.right = null;
            }
        } else if ((deleteNode.left == null && deleteNode.right != null) || (deleteNode.left != null && deleteNode.right == null)) {
            System.out.println("CASE 1 ONE CHILD");

            // One child case
            Node child = deleteNode.left != null ? deleteNode.left : deleteNode.right;

            child.parent = deleteNode.parent;

            if (deleteNode.parent.left == deleteNode) {
                deleteNode.parent.left = child;
            } else {
                deleteNode.parent.right = child;
            }
        } else {
            System.out.println("CASE 1 TWO CHILDREN");

            // Two children case
            Node replaceDataNode = this.getBiggestNodeFromSubtree(deleteNode.right);

            deleteNode.data = replaceDataNode.data;
            this.deleteNode(replaceDataNode.data);
        }
    }

    public static void main(String[] args) {
        RBT rbt = new RBT();

        // CASE 1 VALUES
        int[] values_case_1_0 = {7};

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

        // CASE 0 && 1 VALUES
        int[] values_deletion_case_1_0 = {7};
        int[] values_deletion_case_1_1 = {7, 5};
        int[] values_deletion_case_1_2 = {7, 10};
        int[] values_deletion_case_1_3 = {7, 5, 10};

        // CASE 2 VALUES
        int[] values_deletion_case_2_0 = {17, 9, 19, 18, 75}; // CASE 2 A
        int[] values_deletion_case_2_1 = {17, 9, 19, 5, 15}; // CASE 2 B

        // CASE 3 VALUES
        int[] values_deletion_case_3_0 = {17, 9, 3, 12, 19, 18, 75}; // CASE 2 A

        // ARBITRARY VALUES
        int[] arbitrary_values_0 = {17, 9, 19, 18};

        for (int i : values_deletion_case_3_0) {
            rbt.insert(i);
        }

        rbt.preorder(rbt.root);
        System.out.println(" ");

        System.out.println("--------------- DELETION ---------------");
        // rbt.deleteNode(19);

        rbt.preorder(rbt.root);
        System.out.println(" ");
    }
}
