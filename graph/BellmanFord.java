package graph;

import java.util.*;

/**
 * Link: <a href="https://cp-algorithms.com/graph/bellman_ford.html">CP algorithm link</a><br>
 * Bellman–Ford Algorithm — Single Source Shortest Path (SSSP) algorithm for weighted directed graphs.
 * <br><br>
 * Time complexity: O(V * E), Space complexity: O(V)
 * <br>
 * Works for:
 * <ul>
 *   <li>Directed and undirected graphs (undirected can be modeled as bidirectional edges)</li>
 *   <li>Graphs containing negative weight edges</li>
 *   <li>Detecting negative weight cycles</li>
 * </ul>
 * <br>
 * Limitations:
 * <ul>
 *   <li>Slower than Dijkstra (O(VE) vs O(E log V))</li>
 *   <li>Cannot handle negative cycles (distance keeps decreasing infinitely)</li>
 * </ul>
 * <br>
 * Applications:
 * <ul>
 *   <li>Detect arbitrage opportunities in currency exchange graphs</li>
 *   <li>Routing protocols (e.g., RIP in networking)</li>
 *   <li>Finding negative cycles in constraint graphs</li>
 * </ul>
 * <br>
 * LeetCode problems (free tier):
 * <ul>
 *   <li><a href="https://leetcode.com/problems/network-delay-time/">743. Network Delay Time</a> — Can be solved using Dijkstra or Bellman–Ford.</li>
 *   <li><a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">787. Cheapest Flights Within K Stops</a> — Modified Bellman–Ford limited to K edges.</li>
 * </ul>
 */
public class BellmanFord {

    /**
     * Edge representation for weighted graphs.
     */
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Bellman–Ford algorithm to compute shortest path from a single source.
     *
     * @param n     number of vertices
     * @param edges list of edges in the graph
     * @param src   source vertex
     * @return array of shortest distances; if a negative cycle is detected, returns null
     */
    public static int[] bellmanFord(int n, List<Edge> edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 1: Relax all edges (V-1) times
        for (int i = 1; i < n; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                if (dist[e.from] != Integer.MAX_VALUE && dist[e.from] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.weight;
                    updated = true;
                }
            }
            // Early exit if no edge was updated in this iteration
            if (!updated) break;
        }

        // Step 2: Detect negative weight cycles
        for (Edge e : edges) {
            if (dist[e.from] != Integer.MAX_VALUE && dist[e.from] + e.weight < dist[e.to]) {
                // Negative cycle detected
                return null;
            }
        }

        return dist;
    }
}
