import java.util.Scanner;

// Q1 - Array-based queue (front/rear pointers) for ride groups.
class RideQueue {
    private int[] arr;
    private int front; // index of the front group
    private int rear; // index of the last group (-1 when empty)

    // capacity is the size passed by the driver (m in the given scaffolding).
    // Either n or m bounds the queue: enqueues never exceed m, and n is the
    // max number of enqueue operations, so an array of this size is safe.
    RideQueue(int capacity) {
        arr = new int[capacity];
        front = 0;
        rear = -1;
    }

    void enqueue(int x) {
        arr[++rear] = x;
    }

    void dequeue() {
        front++;
    }

    void processOperations(int m, Scanner scanner) {
        for (int i = 0; i < m; i++) {
            int op = scanner.nextInt();
            if (op == 1) {
                enqueue(scanner.nextInt());
            } else { // op == 2 : front group boards the ride
                dequeue();
            }
        }
        int sum = 0;
        int ones = 0;
        for (int i = front; i <= rear; i++) {
            sum += arr[i];
            if (arr[i] == 1) {
                ones++;
            }
        }
        System.out.println("Sum of remaining group sizes: " + sum);
        System.out.println("Count of groups with size 1: " + ones);
    }
}

public class ArrayQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        RideQueue queue = new RideQueue(m);
             queue.processOperations(m, scanner);
             scanner.close();
         }
  }
