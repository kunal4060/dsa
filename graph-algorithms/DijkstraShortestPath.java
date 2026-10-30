  import java.util.Scanner;

  public class DijkstraShortestPath {
      public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);

            int N = sc.nextInt();
            int M = sc.nextInt();

            int INF = 1000000000;

            // Adjacency matrix: graph[i][j] = weight of edge i-j, INF if no edge.
            int[][] graph = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = (i == j) ? 0 : INF;
                }
            }

            for (int k = 0; k < M; k++) {
                int r1 = sc.nextInt();
                int r2 = sc.nextInt();
                int w = sc.nextInt();
                // Undirected edge; keep the smaller weight if repeated.
                if (w < graph[r1][r2]) {
                    graph[r1][r2] = w;
                    graph[r2][r1] = w;
                }
            }

            int s = sc.nextInt();
            int d = sc.nextInt(); // destination hub - read it, but NOT used in the output

            int[] dist = new int[N];
            boolean[] visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                dist[i] = INF;
                visited[i] = false;
            }
            dist[s] = 0;

            // O(V^2) Dijkstra: no heap allowed, so pick the next
            // unvisited vertex with the smallest distance by scanning dist[].
            for (int count = 0; count < N; count++) {
                int u = -1;
                int min = INF;
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && dist[i] < min) {
                           min = dist[i];
                           u = i;
                     }
                 }
                 if (u == -1) {
                     break; // remaining vertices are unreachable
                 }
                 visited[u] = true;

                 // Relax every neighbour of u.
                 for (int v = 0; v < N; v++) {
                     if (!visited[v] && graph[u][v] != INF
                             && dist[u] + graph[u][v] < dist[v]) {
                         dist[v] = dist[u] + graph[u][v];
                     }
                 }
             }

             for (int i = 0; i < N; i++) {
                 System.out.println(i + " " + dist[i]);
             }

             sc.close();
         }
  }
