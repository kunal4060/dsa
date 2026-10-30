 import java.util.Scanner;

 // Q2 - Array-based deque (circular array) for card values.
 class CardDeque {
     private int[] arr;
     private int front; // index of the front card
     private int rear; // index of the rear card
     private int size;
     private int capacity;

     CardDeque(int capacity) {
         this.capacity = capacity;
         arr = new int[capacity];
         front = 0;
         rear = -1;
         size = 0;
     }

     void insertFront(int x) {
         if (size == 0) {
             front = 0;
             rear = 0;
         } else {
             front = (front - 1 + capacity) % capacity;
         }
         arr[front] = x;
         size++;
     }

     void insertRear(int x) {
         if (size == 0) {
             front = 0;
             rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        arr[rear] = x;
        size++;
    }

    void removeFront() {
        front = (front + 1) % capacity;
        size--;
    }

    void removeRear() {
        rear = (rear - 1 + capacity) % capacity;
        size--;
    }

    int getFront() {
        return arr[front];
    }

    int getRear() {
        return arr[rear];
    }

    void processOperations(int m, Scanner scanner) {
        for (int i = 0; i < m; i++) {
            int op = scanner.nextInt();
            if (op == 1) {
                insertFront(scanner.nextInt());
            } else if (op == 2) {
                insertRear(scanner.nextInt());
            } else if (op == 3) {
                removeFront();
            } else { // op == 4
                removeRear();
            }
        }
        int product = getFront() * getRear();
        int count5 = 0;
        for (int i = 0; i < size; i++) {
            int val = arr[(front + i) % capacity];
            if (val % 5 == 0) {
                count5++;
            }
        }
        System.out.println("Product of front and rear values: " + product);
        System.out.println("Count of remaining values divisible by 5: " + count5);
    }
}

public class DequeOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        CardDeque deque = new CardDeque(n);
        deque.processOperations(m, scanner);
        scanner.close();
        }
  }
