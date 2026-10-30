  import java.util.Scanner;

  public class LinearProbingRehash {

      // Insert one key into the given table using linear probing.
      // index = key % size, then +1, +2, ... (mod size) until an empty slot (-1).
      static void insert(int[] table, int size, int key) {
          int index = key % size;
          while (table[index] != -1) {
              index = (index + 1) % size;
          }
          table[index] = key;
      }

      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          int n = sc.nextInt();
          int[] keys = new int[n];
          for (int i = 0; i < n; i++) {
              keys[i] = sc.nextInt();
          }
          sc.close();

           int size = 5;                           // initial table size
           int[] table = new int[size];
           for (int i = 0; i < size; i++) {
               table[i] = -1;                      // -1 means empty (keys are >= 0)
           }

           int[] inserted = new int[n];            // keys in original insertion order
           int count = 0;

           for (int i = 0; i < n; i++) {
               insert(table, size, keys[i]);
               inserted[count] = keys[i];
               count++;

               // Load factor checked AFTER every insertion, as a double.
               double loadFactor = (double) count / size;
               if (loadFactor > 0.7) {
                   int newSize = 2 * size + 1; // rehash: grow the table
                   int[] newTable = new int[newSize];
                   for (int j = 0; j < newSize; j++) {
                       newTable[j] = -1;
                   }
                       // Re-insert ALL keys so far in their ORIGINAL insertion order,
                       // using linear probing on the NEW size (hashes change with size).
                       for (int j = 0; j < count; j++) {
                           insert(newTable, newSize, inserted[j]);
                       }
                       table = newTable;
                       size = newSize;
                 }
             }

             System.out.println("Table Size: " + size);
             for (int i = 0; i < size; i++) {
                 System.out.println("[" + i + "]: " + table[i]);
             }
         }
  }
