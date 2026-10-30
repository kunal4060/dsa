  import java.util.Scanner;

  public class PrimsMST {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);

           int V = sc.nextInt();
           int[][] matrix = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int INF = 1000000000;

        int[] key = new int[V];      // cheapest edge connecting v to the MST
        int[] parent = new int[V];   // parent[v] = vertex that connects v to the MST
        boolean[] inMST = new boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = INF;
            parent[i] = -1;
            inMST[i] = false;
        }
        key[0] = 0;
        parent[0] = -1; // root of the MST has no parent

        // O(V^2) Prim's: no heap allowed, so pick the vertex
        // outside the MST with the smallest key[] by scanning.
        for (int count = 0; count < V; count++) {
            int u = -1;
            int min = INF;
            for (int i = 0; i < V; i++) {
                if (!inMST[i] && key[i] < min) {
                    min = key[i];
                    u = i;
                }
            }
            inMST[u] = true;

            // Update keys of u's neighbours using the matrix (0 = no edge).
            for (int v = 0; v < V; v++) {
                if (matrix[u][v] != 0 && !inMST[v] && matrix[u][v] < key[v]) {
                    key[v] = matrix[u][v];
                    parent[v] = u;
                }
            }
        }

        System.out.println("Optimal Connections");
        System.out.println();

        int total = 0;
        // Print edges in parent-array order: v = 1..V-1, edge parent[v] - v.
        for (int v = 1; v < V; v++) {
            System.out.println("Site " + parent[v] + " connected to Site " + v
                    + " : " + matrix[parent[v]][v]);
            total += matrix[parent[v]][v];
        }

        System.out.println();
        System.out.println("Total Installation Cost = " + total);

        sc.close();
    }
}
