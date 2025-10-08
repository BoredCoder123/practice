package graph;

import java.util.*;

/**
 * Link: <a href="https://cp-algorithms.com/graph/all-pair-shortest-path-floyd-warshall.html">CP algorithm link</a><br>
 * Floyd–Warshall Algorithm — All-Pairs Shortest Path (APSP) for weighted directed graphs.
 * <br><br>
 * Time complexity: O(V³), Space complexity: O(V²)
 * <br>
 * Works for:
 * <ul>
 *   <li>Directed or undirected graphs</li>
 *   <li>Graphs with negative weights (but no negative cycles)</li>
 * </ul>
 * <br>
 * Key Idea:
 * <ul>
 *   <li>Dynamic Programming approach using intermediate vertices.</li>
 *   <li>dp[i][j] = shortest distance from i to j considering only first k vertices as intermediates.</li>
 * </ul>
 * <br>
 * Applications:
 * <ul>
 *   <li>Computing transitive closure (reachability) of a graph</li>
 *   <li>Shortest paths between all node pairs in dense graphs</li>
 *   <li>Detecting negative cycles (if dp[i][i] < 0 after algorithm)</li>
 * </ul>
 * <br>
 * LeetCode problems (free tier):
 * <ul>
 *   <li><a href="https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/">1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance</a></li>
 *   <li><a href="https://leetcode.com/problems/network-delay-time/">743. Network Delay Time</a> — Can also be extended to all-pairs shortest path using Floyd–Warshall.</li>
 * </ul>
 */
public class FloydWarshall {

    /**
     * Floyd–Warshall algorithm for all-pairs shortest paths.
     *
     * @param graph adjacency matrix where graph[i][j] = weight of edge (i→j), or INF if no edge
     * @return shortest distance matrix; if negative cycle exists, dp[i][i] < 0
     */
    public static int[][] floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];

        // Initialize distance matrix
        for (int i = 0; i < n; i++) {
            dist[i] = Arrays.copyOf(graph[i], n);
        }

        // Main triple loop — consider each node as an intermediate vertex
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE)
                        continue; // skip unreachable paths

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }
}
