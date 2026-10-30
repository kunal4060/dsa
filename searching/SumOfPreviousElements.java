  import java.util.Scanner;

  public class SumOfPreviousElements {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          int n = sc.nextInt();
          int[] a = new int[n];
          for (int i = 0; i < n; i++) {
                  a[i] = sc.nextInt();
             }

             int sum = 0; // sum of all elements before the current one
             for (int i = 0; i < n; i++) {
                 if (a[i] == sum) {
                     System.out.println(a[i]);
                     return; // first match only - stop scanning
                 }
                 sum = sum + a[i];
             }
             System.out.println("Element not found");
         }
  }
