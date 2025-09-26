package graph;

import java.util.*;

/***
 * Link: <a href="https://cp-algorithms.com/graph/dijkstra.html">CP algorithm link</a><br>
 * Finds the shortest path from a single source to all other vertices in a weighted graph with non-negative edge weights.
 * Works for both directed and undirected graphs.
 * <br>
 * Time complexity: O((V+E) * log V) using a min-priority queue (Binary Heap).<br>
 * Space complexity: O(V).
 * <br><br>
 * Applications:
 * <ul>
 *     <li>Find the shortest path from a source to all vertices in a weighted graph</li>
 *     <li>Routing in networks (minimum latency/cost paths)</li>
 *     <li>GPS navigation systems (shortest road distance/time)</li>
 *     <li>Finding paths in game maps (AI pathfinding with uniform costs)</li>
 *     <li>As a subroutine in advanced algorithms (e.g., Johnson’s algorithm for all-pairs shortest paths)</li>
 *     <li>Resource allocation problems with weighted costs</li>
 * </ul>
 * LeetCode problems:
 /**
 * <ul>
 *     <li><a href="https://leetcode.com/problems/network-delay-time/">743. Network Delay Time</a> — classic single-source shortest path on directed weighted graph</li>
 *     <li><a href="https://leetcode.com/problems/path-with-minimum-effort/">1631. Path With Minimum Effort</a> — grid variant, min-max edge weights</li>
 *     <li><a href="https://leetcode.com/problems/shortest-path-in-binary-matrix/">1091. Shortest Path in Binary Matrix</a> — shortest path in a 2D grid with 8 directions</li>
 *     <li><a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">787. Cheapest Flights Within K Stops</a> — Dijkstra with stop constraint</li>
 *     <li><a href="https://leetcode.com/problems/swim-in-rising-water/">778. Swim in Rising Water</a> — min-max path in grid, elevation constraints</li>
 *     <li><a href="https://leetcode.com/problems/second-minimum-time-to-reach-destination/">2045. Second Minimum Time to Reach Destination</a> — shortest path with extra time constraints</li>
 *     <li><a href="https://leetcode.com/problems/shortest-path-to-get-all-keys/">864. Shortest Path to Get All Keys</a> — BFS + Dijkstra hybrid on grid with state masks</li>
 *     <li><a href="https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/">2577. Minimum Time to Visit a Cell in a Grid</a> — shortest path with dynamic constraints</li>
 *     <li><a href="https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/">1368. Minimum Cost to Make at Least One Valid Path in a Grid</a> — weighted grid path problem</li>
 *     <li><a href="https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts/">1928. Minimum Cost to Reach City With Discounts</a> — Dijkstra with edge discounts</li>
 * </ul>
 */

public class Dijkstra {
    private class Pair {
        public int idx, dist;
        public Pair(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
     /*** @param graph adjacency matrix (graph[u][v] = weight, or -1 if no edge)
     * @param start starting vertex
     * @return shortest distances from start to all vertices (-1 if unreachable)
     */
    public int[] findMinPath(int[][] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.dist));
        dist[start] = 0;
        pq.add(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.idx;

            if (visited[u]) continue;

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != -1 && !visited[v]) {
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.add(new Pair(v, newDist));
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
        return dist;
    }

}
