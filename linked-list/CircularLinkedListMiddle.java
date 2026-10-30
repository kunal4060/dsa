  // Lab 4 — Q2: Middle element of a circular linked list
  // Standalone file: includes the platform scaffolding (Node, findMiddleElement)
  // plus a Main that reproduces the footer behaviour — reads N and the values,
  // builds the circular list (last node points back to head), and prints the
  // middle element's data. For even N this is the SECOND middle element.
  // No banned classes used.

  import java.util.Scanner;

  class Node {
      int data;
      Node next;

      Node(int data) {
          this.data = data;
          this.next = null;
      }
  }

  public class CircularLinkedListMiddle {

      // Partly provided by the platform: count the nodes by walking until we
      // are back at head, then advance count/2 steps from head.
      // count/2 gives the second middle when N is even (e.g. N=4 -> step 2).
      static Node findMiddleElement(Node head) {
          int count = 0;
          Node temp = head;
          do {
              count++;
              temp = temp.next;
          } while (temp != head);

           Node mid = head;
           for (int i = 0; i < count / 2; i++) {
                mid = mid.next;
            }
            return mid;
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            Node head = null;
            Node tail = null;
            for (int i = 0; i < n; i++) {
                int value = sc.nextInt();
                Node newNode = new Node(value);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
            }
            // Close the circle: last node points back to head.
            if (tail != null) {
                tail.next = head;
            }
            sc.close();

            Node middle = findMiddleElement(head);
            System.out.println(middle.data);
        }
  }
