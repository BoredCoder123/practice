package graph;

import java.util.*;

/***
 * Link: <a href="https://cp-algorithms.com/graph/mst_kruskal.html">CP algorithm link</a><br>
 * Kruskal’s Algorithm — Minimum Spanning Tree (MST) construction using edge sorting and Disjoint Set Union (Union-Find).
 * <br>
 * Time complexity: O(E log E) due to sorting, Space complexity: O(V)
 * <br><br>
 * Works for:
 * <ul>
 *     <li>Weighted, undirected graphs (connected or disconnected)</li>
 *     <li>Sparse graphs (since it works directly with edge lists)</li>
 * </ul>
 * <br>
 * Applications:
 * <ul>
 *     <li>Minimum cost network design (roads, cables, pipelines)</li>
 *     <li>Detecting cycles in undirected graphs</li>
 *     <li>Union-Find applications (dynamic connectivity, clustering)</li>
 *     <li>Reducing redundant edges while preserving connectivity</li>
 * </ul>
 * <br>
 * LeetCode problems (free tier):
 * <ul>
 *     <li><a href="https://leetcode.com/problems/connecting-cities-with-minimum-cost/">1135. Connecting Cities With Minimum Cost</a> — Classic Kruskal MST using Union-Find.</li>
 *     <li><a href="https://leetcode.com/problems/min-cost-to-connect-all-points/">1584. Min Cost to Connect All Points</a> — Can be solved with both Prim or Kruskal approaches.</li>
 *     <li><a href="https://leetcode.com/problems/min-cost-to-supply-water/">1168. Min Cost to Supply Water</a> — Virtual node + Kruskal variant.</li>
 *     <li><a href="https://leetcode.com/problems/redundant-connection/">684. Redundant Connection</a> — Cycle detection using Union-Find (inverse of MST building).</li>
 *     <li><a href="https://leetcode.com/problems/redundant-connection-ii/">685. Redundant Connection II</a> — Directed version of Union-Find-based cycle resolution.</li>
 *     <li><a href="https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/">1101. The Earliest Moment When Everyone Become Friends</a> — Union-Find time of connectivity problem.</li>
 *     <li><a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/">947. Most Stones Removed with Same Row or Column</a> — Disjoint Set connectivity problem (Union-Find heavy).</li>
 *     <li><a href="https://leetcode.com/problems/accounts-merge/">721. Accounts Merge</a> — Classic Union-Find merging problem.</li>
 * </ul>
 */
public class KruskalMST {

    /**
     * Represents an edge in the graph.
     */
    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    /**
     * Disjoint Set Union (Union-Find) data structure with path compression and union by rank.
     */
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;
            if (rank[px] < rank[py]) parent[px] = py;
            else if (rank[px] > rank[py]) parent[py] = px;
            else {
                parent[py] = px;
                rank[px]++;
            }
            return true;
        }
    }

    /**
     * Kruskal’s algorithm for Minimum Spanning Tree.
     * @param edges list of edges (u, v, weight)
     * @param n number of vertices
     * @return total cost of MST, or Integer.MAX_VALUE if MST not possible
     */
    public static int kruskal(List<Edge> edges, int n) {
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        DSU dsu = new DSU(n);
        int totalCost = 0, edgesUsed = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                totalCost += e.weight;
                edgesUsed++;
            }
        }

        // If graph is disconnected (not enough edges used), MST not possible
        return edgesUsed == n - 1 ? totalCost : Integer.MAX_VALUE;
    }
}
