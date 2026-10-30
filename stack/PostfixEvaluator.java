  import java.util.Scanner;
  import java.util.Stack;

  public class PostfixEvaluator {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          String expr = sc.nextLine().trim();
          sc.close();

          Stack<Integer> st = new Stack<>();

          for (int i = 0; i < expr.length(); i++) {
              char ch = expr.charAt(i);

              if (Character.isDigit(ch)) {
                  // Single-digit operand: push its value
                  st.push(ch - '0');
              } else {
                  // Operator: pop right operand first, then left operand
                  int b = st.pop();
                  int a = st.pop();
                  int res;
                  switch (ch) {
                      case '+':
                          res = a + b;
                          break;
                      case '-':
                          res = a - b;
                          break;
                      case '*':
                          res = a * b;
                          break;
                      case '/':
                          res = a / b; // integer division
                          break;
                      default:
                          throw new IllegalArgumentException("Unknown operator: " + ch);
                  }
                  st.push(res);
              }
          }

          System.out.println(st.pop());
      }
  }
