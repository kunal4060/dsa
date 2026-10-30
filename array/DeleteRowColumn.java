  import java.util.Scanner;

  public class DeleteRowColumn {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          int r = sc.nextInt();
          int c = sc.nextInt();
          int[][] a = new int[r][c];
          for (int i = 0; i < r; i++) {
              for (int j = 0; j < c; j++) {
                  a[i][j] = sc.nextInt();
              }
          }
          int target = sc.nextInt();
          sc.close();

            // Find the (single) row and column containing the target value.
            int delRow = -1, delCol = -1;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (a[i][j] == target) {
                        delRow = i;
                        delCol = j;
                    }
                }
            }

            // Print every element except that row and that column.
            for (int i = 0; i < r; i++) {
                if (i == delRow) {
                    continue;
                }
               StringBuilder row = new StringBuilder();
               for (int j = 0; j < c; j++) {
                   if (j == delCol) {
                       continue;
                   }
                   row.append(a[i][j]).append(' ');
               }
               System.out.println(row.toString().trim());
          }
      }
  }
