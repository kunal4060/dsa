  import java.util.Scanner;

  /**
   * Lab 6 - Q2: BST preorder traversal of characters.
   *
   * Input : line 1 - numNodes
   *         line 2 - numNodes space-separated uppercase letters
   * Output: preorder traversal on one line, space-separated
   *         (node, then left subtree, then right subtree).
   */
  public class BSTPreorder {

       static class Node {
           char key;
           Node left, right;

           Node(char key) {
               this.key = key;
            }
        }

        // Plain BST insertion: smaller keys go left, larger keys go right
        static Node insert(Node node, char key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }
            // duplicate - not expected (letters are distinct in the tests)
            return node;
        }

        // Preorder traversal: node first, then left subtree, then right subtree
        static void preorder(Node root, StringBuilder out) {
            if (root == null) {
                return;
            }
            if (out.length() > 0) {
                out.append(' ');
            }
            out.append(root.key);
            preorder(root.left, out);
            preorder(root.right, out);
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            Node root = null;
            for (int i = 0; i < n; i++) {
                root = insert(root, sc.next().charAt(0));
            }
            sc.close();

            StringBuilder out = new StringBuilder();
            preorder(root, out);
            System.out.println(out);
        }
  }
