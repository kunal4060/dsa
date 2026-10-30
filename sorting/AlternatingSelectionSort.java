import java.util.Scanner;

/**
 * Lab 7 - Q2: Alternating Direction Selection Sort + Parking Efficiency Score.
 *
 * Sorting rule: pass i = 0, 2, 4, ... (even) scans the unsorted part
 * left-to-right for the minimum; pass i = 1, 3, 5, ... (odd) scans it
 * right-to-left for the minimum. A "direction change" is counted ONLY
 * when a swap actually happens (minIndex != i) - NOT on every pass.
 *
 * PES = (sum of all spots x directionChanges) / (n + spots[0]),
 * using integer (floor) division.
 */
public class AlternatingSelectionSort {

   // Sorts the array in place (alternating direction selection sort),
   // then prints the sorted spots and the Parking Efficiency Score.
   static void alternatingDirectionSort(int[] spots) {
       int n = spots.length;
       int directionChanges = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            if (i % 2 == 0) {
                // Even pass: scan left to right for the minimum.
                for (int j = i + 1; j < n; j++) {
                    if (spots[j] < spots[minIndex]) {
                        minIndex = j;
                    }
                }
            } else {
                // Odd pass: scan right to left for the minimum.
                for (int j = n - 1; j > i; j--) {
                    if (spots[j] < spots[minIndex]) {
                        minIndex = j;
                    }
                }
            }
            // Swap only if the minimum is not already in place.
            // The counter increases ONLY on an actual swap.
            if (minIndex != i) {
                int temp = spots[i];
                spots[i] = spots[minIndex];
                      spots[minIndex] = temp;
                      directionChanges++;
                 }
             }

             // Line 1: sorted spots.
             System.out.print("Sorted Spots:");
             for (int s : spots) {
                 System.out.print(" " + s);
             }
             System.out.println();

             // Line 2: Parking Efficiency Score (integer division = floor).
             // long is used because sum x directionChanges can exceed int range.
             long sum = 0;
             for (int s : spots) {
                 sum += s;
             }
             long pes = (sum * directionChanges) / (n + spots[0]);
             System.out.println("Parking Efficiency Score: " + pes);
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] spots = new int[n];
            for (int i = 0; i < n; i++) {
                spots[i] = sc.nextInt();
            }
            sc.close();
            alternatingDirectionSort(spots);
        }
  }
