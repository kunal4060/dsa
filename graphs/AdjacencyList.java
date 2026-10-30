import java.util.Scanner;

public class AdjacencyList {

   static class Graph {
       int V;
       boolean[][] adj; // adj[u][v] = true means an undirected edge u-v exists

        Graph(int V) {
            this.V = V;
            adj = new boolean[V][V];
        }

        void buildFromInput(Scanner sc, int E) {
            for (int i = 0; i < E; i++) {
                int src = sc.nextInt();
                int dest = sc.nextInt();
                adj[src][dest] = true;
                adj[dest][src] = true;
            }
        }

        void removeEdge(int src, int dest) {
            adj[src][dest] = false;
            adj[dest][src] = false;
        }

        void displayGraph() {
            for (int i = 0; i < V; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("Adjacent vertices of vertex ").append(i).append(":");
                for (int j = 0; j < V; j++) {
                    if (adj[i][j]) {
                        sb.append(" ").append(j);
                    }
                }
                System.out.println(sb.toString());
            }
        }
   }

   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int V = sc.nextInt();
       int E = sc.nextInt();
       Graph g = new Graph(V);
       g.buildFromInput(sc, E);
       int src = sc.nextInt();
       int dest = sc.nextInt();
       sc.close();
       g.removeEdge(src, dest);
            g.displayGraph();
       }
 }
