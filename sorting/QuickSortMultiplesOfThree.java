import java.util.Scanner;

/**
 * Lab 7 - Q1: Quick Sort only the multiples of 3, others stay put.
 *
 * Idea: collect every element divisible by 3 into a separate array (mul3),
 * sort ONLY that array with Quick Sort, then put the sorted values back
 * into exactly the positions where the original elements were multiples
 * of 3. Every other element never moves.
 */
public class QuickSortMultiplesOfThree {

   // Recursive Quick Sort - middle element as pivot, two-pointer partition.
   static void quickSort(int[] arr, int low, int high) {
       if (low >= high) {
           return;
       }
       int pivot = arr[(low + high) / 2];
       int i = low, j = high;
       while (i <= j) {
           while (arr[i] < pivot) {
               i++;
           }
           while (arr[j] > pivot) {
               j--;
           }
           if (i <= j) {
               int temp = arr[i];
               arr[i] = arr[j];
               arr[j] = temp;
               i++;
               j--;
           }
       }
       // Recurse on the two halves left of / right of the partition.
       if (low < j) {
           quickSort(arr, low, j);
       }
       if (i < high) {
           quickSort(arr, i, high);
       }
   }

   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int[] arr = new int[n];

         // Read the array and count the multiples of 3.
         int count = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                if (arr[i] % 3 == 0) {
                    count++;
                }
            }

            // Collect the multiples of 3 into a separate array.
            int[] mul3 = new int[count];
            int k = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] % 3 == 0) {
                    mul3[k++] = arr[i];
                }
            }

            // Sort only the multiples of 3.
            if (count > 0) {
                quickSort(mul3, 0, count - 1);
            }

            // Put the sorted multiples back into their original positions.
            k = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] % 3 == 0) {
                    arr[i] = mul3[k++];
                }
            }

            // Print the modified array on one line, single-space separated.
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append(arr[i]);
            }
            System.out.println(sb);
            sc.close();
        }
  }
