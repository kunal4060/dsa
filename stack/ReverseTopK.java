  import java.util.ArrayList;
  import java.util.List;
  import java.util.Scanner;
  import java.util.Stack;
  public class ReverseTopK {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);

           int n = sc.nextInt();
           Stack<Integer> st = new Stack<>();
           for (int i = 0; i < n; i++) {
               st.push(sc.nextInt()); // first value pushed is at the bottom
           }
           int k = sc.nextInt();
           sc.close();

           // Pop the top K elements into a list, in pop order.
           // For stack [10,20,30,40,50] (50 on top), K = 3:
           // list becomes [50, 40, 30].
           List<Integer> topK = new ArrayList<>();
           for (int i = 0; i < k; i++) {
               topK.add(st.pop());
           }

           // Push them back in that SAME order. The last pushed
           // (30) lands on top, so the top-K segment read top-to-bottom
           // is now reversed: [30, 40, 50].
           for (int x : topK) {
               st.push(x);
           }

           // Print the final stack top-to-bottom.
           StringBuilder out = new StringBuilder();
           while (!st.isEmpty()) {
               out.append(st.pop());
               if (!st.isEmpty()) {
                   out.append(' ');
               }
           }
           System.out.println(out);
      }
  }
