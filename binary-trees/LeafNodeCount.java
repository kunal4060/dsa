import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    // Build a binary tree from values in level order
    // (top to bottom, left to right) using a queue.
    Node buildTree(int[] values, int n) {
        if (n == 0) {
            return null;
        }
        Node root = new Node(values[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < n) {
            Node current = queue.poll();
            // Left child
            if (i < n) {
                current.left = new Node(values[i]);
                queue.add(current.left);
                i++;
            }
            // Right child
            if (i < n) {
                current.right = new Node(values[i]);
                queue.add(current.right);
                i++;
            }
        }
        return root;
    }

    // Count nodes that have no children (leaf nodes).
    int countLeafNodes(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return countLeafNodes(root.left) + countLeafNodes(root.right);
    }
}

public class LeafNodeCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
             int n = sc.nextInt();
             int[] values = new int[n];
             for (int i = 0; i < n; i++) {
                 values[i] = sc.nextInt();
             }
             sc.close();
             Solution sol = new Solution();
             Node root = sol.buildTree(values, n);
             System.out.println(sol.countLeafNodes(root));
        }
  }
