package graph;

import java.util.*;

/**
 * Link: <a href="https://cp-algorithms.com/graph/mst_prim.html">CP algorithm link</a><br>
 * Prim’s Algorithm — Minimum Spanning Tree (MST) construction for weighted undirected graphs.
 * <br>
 * Time complexity: O(E log V) using PriorityQueue (min-heap), Space complexity: O(V)
 * <br><br>
 * Works for:
 * <ul>
 *     <li>Connected, weighted, undirected graphs</li>
 *     <li>Can be modified to handle disconnected graphs (find MST for each component)</li>
 * </ul>
 * <br>
 * Applications:
 * <ul>
 *     <li>Network design (minimum cost wiring / road construction)</li>
 *     <li>Clustering (e.g., in ML or data grouping)</li>
 *     <li>Approximating NP-hard problems like Traveling Salesman</li>
 *     <li>Reducing redundant edges while maintaining connectivity</li>
 * </ul>
 * <br>
 * LeetCode problems (free tier):
 * <ul>
 *   <li><a href="https://leetcode.com/problems/min-cost-to-connect-all-points/">1584. Min Cost to Connect All Points</a> — Can be solved cleanly with Prim’s using Manhattan distances on a complete graph.</li>
 *   <li><a href="https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/">1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree</a> — Build MST and analyze edges; pairs well with Prim/Kruskal understanding.</li>
 *   <li><a href="https://leetcode.com/problems/redundant-connection/">684. Redundant Connection</a> — Union-Find cycle detection; inverse perspective of MST building</li>
 *   <li><a href="https://leetcode.com/problems/redundant-connection-ii/">685. Redundant Connection II</a> — Directed variant; handle two-parent node vs cycle with Union-Find logic.</li>
 *   <li><a href="https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/">1101. The Earliest Moment When Everyone Become Friends</a> — Sort by time and union until fully connected</li>
 *   <li><a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/">947. Most Stones Removed with Same Row or Column</a> — Union rows and columns; remove all but one per component</li>
 *   <li><a href="https://leetcode.com/problems/accounts-merge/">721. Accounts Merge</a> — Merge accounts via shared emails using Union-Find.</li>
 * </ul>
 */
public class PrimMST {

    /**
     * Represents an edge in the graph.
     */
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Prim's algorithm using a min-heap (priority queue).
     * @param graph adjacency list where graph[u] = list of (v, weight)
     * @param start starting vertex (any vertex in connected component)
     * @return total weight of MST
     */
    public static int prim(List<List<Edge>> graph, int start) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(start, 0));

        int totalCost = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < n) {
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            totalCost += cur.weight;
            edgesUsed++;

            for (Edge next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        // If graph is disconnected, edgesUsed < n (no MST possible)
        return edgesUsed == n ? totalCost : Integer.MAX_VALUE;
    }

    /**
     * Builds MST for all connected components in a disconnected graph.
     */
    public static int primForDisconnectedGraph(List<List<Edge>> graph) {
        int total = 0;
        int n = graph.size();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                total += primFromNode(graph, i, visited);
            }
        }
        return total;
    }

    private static int primFromNode(List<List<Edge>> graph, int start, boolean[] visited) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(start, 0));
        int totalCost = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            totalCost += cur.weight;

            for (Edge next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
        return totalCost;
    }
}
