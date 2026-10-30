import java.util.Scanner;

public class DirectedCycleDetection {

   static class Graph {
       int V;
       int[][] adj;          // adjacency matrix: adj[u][v] = 1 means edge u -> v
       int[] colour;         // 0 = white (unvisited), 1 = grey (in current DFS stack), 2 = black
       int[] parent;         // parent[v] = vertex from which v was first reached
       int[] cycle;          // vertices of the found cycle, in order
       int cycleSize;
       boolean found;

       Graph(int V) {
           this.V = V;
           adj = new int[V][V];
           colour = new int[V];
           parent = new int[V];
           cycle = new int[V + 1];
           cycleSize = 0;
           found = false;
           for (int i = 0; i < V; i++) {
               parent[i] = -1;
           }
       }

       void addEdge(int u, int v) {
           adj[u][v] = 1;
       }

       void dfs(int u) {
           colour[u] = 1; // grey: u is now in the current DFS path
           for (int v = 0; v < V; v++) {
               if (found) return;
               if (adj[u][v] == 0) continue;
               if (colour[v] == 0) {          // white: tree edge
                   parent[v] = u;
                   dfs(v);
               } else if (colour[v] == 1) {   // grey: back edge -> cycle!
                   buildCycle(u, v);
                   found = true;
                   return;
               }
               // black neighbour: cross/forward edge, ignore
           }
           colour[u] = 2; // black: u is finished
       }

       // Walk parents from u back to v, then reverse so the cycle reads v -> ... -> u.
       void buildCycle(int u, int v) {
           int[] temp = new int[V + 1];
           int n = 0;
           int cur = u;
           temp[n++] = cur;
           while (cur != v) {
                cur = parent[cur];
                temp[n++] = cur;
             }
             // temp is u, ..., v ; reverse it to get v, ..., u
             cycleSize = 0;
             for (int i = n - 1; i >= 0; i--) {
                 cycle[cycleSize++] = temp[i];
             }
         }

         boolean hasCycle() {
             for (int i = 0; i < V; i++) {
                 if (colour[i] == 0) {
                     dfs(i);
                     if (found) return true;
                 }
             }
             return false;
         }

         void printCycle() {
             StringBuilder sb = new StringBuilder();
             sb.append("Cycle: ");
             for (int i = 0; i < cycleSize; i++) {
                 if (i > 0) sb.append(" -> ");
                 sb.append(cycle[i]);
             }
             sb.append(" -> ").append(cycle[0]);
             System.out.println(sb.toString());
         }
     }

     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int V = sc.nextInt();
         int E = sc.nextInt();
         Graph g = new Graph(V);
         for (int i = 0; i < E; i++) {
             int u = sc.nextInt();
             int v = sc.nextInt();
             g.addEdge(u, v);
         }
         sc.close();

         if (g.hasCycle()) {
             System.out.println("true");
             g.printCycle();
         } else {
             System.out.println("No cycle found");
         }
     }
 }
