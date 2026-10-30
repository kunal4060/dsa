  import java.util.Scanner;

  /**
   * Lab 6 - Q1: AVL tree deletion with rebalancing, then inorder traversal.
   *
   * Input : line 1 - N (number of book IDs)
   *         line 2 - N distinct integers (book IDs, inserted in this order)
   *         line 3 - the ID to delete
   * Output: inorder traversal of the remaining tree, space-separated,
   *         or "Not Found" if the ID is not in the tree.
   *
   * NOTE (honest point): inorder traversal of any BST is always the sorted
   * order, so the printed output is just the sorted remaining IDs. But the
   * question tests the AVL logic - so we still implement a real AVL tree:
   * insert/delete with heights, balance factors and all four rotations.
   */
  public class AVLDeletion {
static class Node {
    int key;
    int height;
    Node left, right;

    Node(int key) {
        this.key = key;
        this.height = 1; // height of a leaf node
    }
}

// Height of a node; 0 for a null child
static int height(Node n) {
    return (n == null) ? 0 : n.height;
}

// Balance factor = height(left) - height(right)
static int getBalance(Node n) {
    return (n == null) ? 0 : height(n.left) - height(n.right);
}

// Right rotation (LL case) - used when the left subtree is too tall
static Node rotateRight(Node y) {
    Node x = y.left;
    Node t2 = x.right;

    x.right = y;
    y.left = t2;

    // Update heights bottom-up: y first, then x
    y.height = 1 + Math.max(height(y.left), height(y.right));
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return x; // new root of this subtree
}

// Left rotation (RR case) - used when the right subtree is too tall
static Node rotateLeft(Node x) {
    Node y = x.right;
    Node t2 = y.left;

    y.left = x;
    x.right = t2;

    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y; // new root of this subtree
}

// Insert a key into the AVL tree, rebalancing on the way back up
static Node insert(Node node, int key) {
    if (node == null) {
        return new Node(key);
    }
    if (key < node.key) {
        node.left = insert(node.left, key);
    } else if (key > node.key) {
        node.right = insert(node.right, key);
    } else {
        return node; // duplicate key - not expected (IDs are distinct)
    }

    node.height = 1 + Math.max(height(node.left), height(node.right));
    int balance = getBalance(node);

    // LL case: new key went into the left subtree of the left child
    if (balance > 1 && key < node.left.key) {
        return rotateRight(node);
    }
    // RR case: new key went into the right subtree of the right child
    if (balance < -1 && key > node.right.key) {
        return rotateLeft(node);
    }
    // LR case: new key went into the right subtree of the left child
    if (balance > 1 && key > node.left.key) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
    // RL case: new key went into the left subtree of the right child
    if (balance < -1 && key < node.right.key) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }
    return node;
}

// Smallest key in a subtree (the leftmost node)
static Node minValueNode(Node node) {
    Node current = node;
    while (current.left != null) {
        current = current.left;
    }
    return current;
}

// Delete a key from the AVL tree, rebalancing at every affected ancestor
static Node delete(Node root, int key) {
    if (root == null) {
        return null;
    }
    if (key < root.key) {
        root.left = delete(root.left, key);
    } else if (key > root.key) {
        root.right = delete(root.right, key);
    } else {
        // Found the node to delete
        if (root.left == null || root.right == null) {
            // Case 1 (leaf) and Case 2 (one child): the remaining child
            // (or null) simply takes this node's place
            Node temp = (root.left != null) ? root.left : root.right;
            root = temp; // null if it was a leaf
        } else {
            // Case 3 (two children): copy the inorder successor
            // (smallest key in the right subtree), then delete the
            // successor from its original position
            Node successor = minValueNode(root.right);
            root.key = successor.key;
            root.right = delete(root.right, successor.key);
        }
    }

    if (root == null) {
        return null; // the tree had only one node, now empty
    }

    root.height = 1 + Math.max(height(root.left), height(root.right));
    int balance = getBalance(root);

    // LL case
    if (balance > 1 && getBalance(root.left) >= 0) {
        return rotateRight(root);
    }
    // LR case
    if (balance > 1 && getBalance(root.left) < 0) {
        root.left = rotateLeft(root.left);
        return rotateRight(root);
    }
    // RR case
    if (balance < -1 && getBalance(root.right) <= 0) {
        return rotateLeft(root);
    }
    // RL case
    if (balance < -1 && getBalance(root.right) > 0) {
        root.right = rotateRight(root.right);
        return rotateLeft(root);
    }
    return root;
}

// Is the key present in the tree?
static boolean contains(Node root, int key) {
    if (root == null) {
        return false;
    }
    if (key == root.key) {
        return true;
    }
    return key < root.key ? contains(root.left, key)
                          : contains(root.right, key);
}

// Inorder traversal: left, node, right - prints in sorted order
static void inorder(Node root, StringBuilder out) {
    if (root == null) {
        return;
    }
    inorder(root.left, out);
    if (out.length() > 0) {
        out.append(' ');
    }
    out.append(root.key);
    inorder(root.right, out);
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
             Node root = null;
             for (int i = 0; i < n; i++) {
                 root = insert(root, sc.nextInt());
             }

             int toDelete = sc.nextInt();
             sc.close();

             if (!contains(root, toDelete)) {
                 System.out.println("Not Found");
                 return;
             }
             root = delete(root, toDelete);

             StringBuilder out = new StringBuilder();
             inorder(root, out);
             System.out.println(out);
         }
  }
