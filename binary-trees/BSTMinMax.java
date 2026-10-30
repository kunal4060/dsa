  import java.util.Scanner;

  class Node {
      char data;
    Node left, right;

    Node(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    // Insert a character into the BST.
    // Smaller value -> left subtree, larger (or equal) value -> right subtree.
    Node insert(Node root, char data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    // Minimum value = leftmost node of the BST.
    char findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    // Maximum value = rightmost node of the BST.
    char findMax(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }
}

public class BSTMinMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Solution sol = new Solution();
        Node root = null;
        for (int i = 0; i < n; i++) {
            char ch = sc.next().charAt(0);
            root = sol.insert(root, ch);
        }
        sc.close();
        System.out.println("Minimum value: " + sol.findMin(root));
        System.out.println("Maximum value: " + sol.findMax(root));
    }
}
