  import java.util.Scanner;

  public class InterleaveHalves {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          int n = sc.nextInt();
          int[] a = new int[n];
          for (int i = 0; i < n; i++) {
              a[i] = sc.nextInt();
          }
          sc.close();

             // First half gets the extra element when n is odd.
             int firstSize = (n + 1) / 2;
             int secondSize = n - firstSize;

             StringBuilder out = new StringBuilder();
             for (int i = 0; i < secondSize; i++) {
                 out.append(a[i]).append(' ');
                 out.append(a[firstSize + i]).append(' ');
             }
             // Odd n: leftover last element of the first half goes at the end.
             if (firstSize > secondSize) {
                 out.append(a[firstSize - 1]).append(' ');
             }
             System.out.println(out.toString().trim());
         }
  }
