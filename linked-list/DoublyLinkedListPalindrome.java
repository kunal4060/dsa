  // Lab 4 — Q1: Doubly linked list palindrome (mirrored pattern) check
  // Standalone file: includes the platform scaffolding (CharNode, TextDLL with
  // addChar and toStringDLL) plus the completed isMirrored() and a Main that
  // reproduces the footer behaviour. No banned classes used.

  import java.util.Scanner;

  class CharNode {
      char data;
      CharNode prev;
      CharNode next;

         CharNode(char data) {
             this.data = data;
             this.prev = null;
             this.next = null;
         }
  }

  class TextDLL {
      CharNode head;
      CharNode tail;

         TextDLL() {
             head = null;
             tail = null;
         }

         // Provided by the platform: append one character at the end.
    void addChar(char c) {
        CharNode newNode = new CharNode(c);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Provided by the platform: all stored characters as one string.
    String toStringDLL() {
        String result = "";
        CharNode temp = head;
        while (temp != null) {
            result = result + temp.data;
            temp = temp.next;
        }
        return result;
    }

    // Completed: two-pointer check — front walks forward, back walks backward.
    // They stop when they meet (odd length) or cross each other (even length).
    boolean isMirrored() {
        CharNode front = head;
        CharNode back = tail;
        while (front != back && front.prev != back) {
            if (front.data != back.data) {
                return false;
            }
            front = front.next;
            back = back.prev;
        }
        return true;
    }
}

public class DoublyLinkedListPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        sc.close();

       TextDLL dll = new TextDLL();
       int spaces = 0;
       for (int i = 0; i < sentence.length(); i++) {
           char c = sentence.charAt(i);
           if (c == ' ') {
               spaces++;
           } else {
               dll.addChar(Character.toLowerCase(c));
           }
       }

       System.out.println("Original sentence: " + sentence);
       System.out.println("Processed (no spaces): " + dll.toStringDLL());
       System.out.println("Spaces removed: " + spaces);
             if (dll.isMirrored()) {
                 System.out.println("Pattern Status: Clean mirrored sequence");
             } else {
                 System.out.println("Pattern Status: Mirroring inconsistency found");
             }
         }
  }
