package Chapter2.Chapter24.Research;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    public Node root;

    public void preorderTraversal(Node root) {
        if (root == null)
            return;

        System.out.print(root.data + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public void levelOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            System.out.print(node.data + " ");

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public boolean isComplete(Node root) {
        Queue<Node> queue = new LinkedList<>();
        boolean end = false;
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (node == null) {
                end = true;
            } else {
                if (end) {
                    return false;
                }

                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return true;
    }

    public void insert(int data) {
        if (this.root == null) {
            this.root = new Node(data);
            return;
        }

        Node prev = null;
        Node current = this.root;

        while (current != null) {
            prev = current;

            if (data <= current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (data < prev.data) {
            prev.left = new Node(data);
        } else {
            prev.right = new Node(data);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] arr = {20, 17, 25, 10, 18, 23, 30};

        for (int i : arr) {
            bst.insert(i);
        }

        bst.levelOrderTraversal(bst.root);
        System.out.println("");

        System.out.println(bst.isComplete(bst.root));
    }
}
