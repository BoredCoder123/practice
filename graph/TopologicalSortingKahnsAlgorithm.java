package graph;

import java.util.*;

/***
 * Topological Sort using Kahn's Algorithm (BFS-based)
 *
 * Topological Sort orders the vertices of a Directed Acyclic Graph (DAG)
 * such that for every directed edge u -> v, vertex u comes before v in the ordering.
 *
 * Uses:
 * <ul>
 *     <li>Scheduling tasks with dependencies (courses, jobs, projects)</li>
 *     <li>Course prerequisite problems</li>
 *     <li>Build systems / compilation order</li>
 *     <li>Detecting cycles in a directed graph (if topological sort is impossible)</li>
 *     <li>Dependency resolution in package managers</li>
 * </ul>
 *
 * Algorithm:
 * <ul>
 *     <li>Compute in-degree of all vertices</li>
 *     <li>Initialize a queue with all vertices having in-degree 0</li>
 *     <li>Process nodes from the queue, reduce in-degree of their neighbors</li>
 *     <li>If in-degree of a neighbor becomes 0, add it to the queue</li>
 *     <li>If all nodes are processed, we get a valid topological order</li>
 *     <li>If not all nodes are processed, the graph has a cycle</li>
 * </ul>
 *
 * Complexity:
 * <ul>
 *     <li>Time: O(V + E), where V = number of vertices, E = number of edges</li>
 *     <li>Space: O(V) for in-degree array and queue</li>
 * </ul>
 *
 * Free-tier LeetCode problems (cover ~90% of FAANG DAG/topological patterns):
 * <ol>
 *     <li><a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a> — Detect cycle in a DAG</li>
 *     <li><a href="https://leetcode.com/problems/course-schedule-ii/">210. Course Schedule II</a> — Return topological order</li>
 *     <li><a href="https://leetcode.com/problems/task-scheduler/">621. Task Scheduler</a> — Scheduling tasks with dependencies</li>
 *     <li><a href="https://leetcode.com/problems/minimum-height-trees/">310. Minimum Height Trees</a> — Leaf-removal topological pattern</li>
 *     <li><a href="https://leetcode.com/problems/course-schedule-iv/">1462. Course Schedule IV</a> — Reachability / transitive closure in DAG</li>
 *     <li><a href="https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/">1203. Sort Items by Groups Respecting Dependencies</a> — Multi-layer DAG topological sort</li>
 *     <li><a href="https://leetcode.com/problems/shortest-path-visiting-all-nodes/">847. Shortest Path Visiting All Nodes</a> — BFS variant on DAG-like constraints</li>
 *     <li><a href="https://leetcode.com/problems/rotting-oranges/">994. Rotting Oranges</a> — Multi-source BFS (dependency propagation)</li>
 *     <li><a href="https://leetcode.com/problems/number-of-provinces/">547. Number of Provinces</a> — Connected components (indirect topological reasoning)</li>
 *     <li><a href="https://leetcode.com/problems/redundant-connection/">684. Redundant Connection</a> — Detect cycles in an undirected graph (union-find, relates to DAG reasoning)</li>
 *     <li><a href="https://leetcode.com/problems/accounts-merge/">721. Accounts Merge</a> — Union-find + grouping, topological-like dependencies</li>
 *     <li><a href="https://leetcode.com/problems/parallel-courses-ii/">2050. Parallel Courses II</a> — Batch scheduling DAG variant</li>
 *     <li><a href="https://leetcode.com/problems/critical-connections-in-a-network/">1192. Critical Connections in a Network</a> — Bridges in graphs (dependency analysis)</li>
 *     <li><a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/">329. Longest Increasing Path in a Matrix</a> — DFS + memoization, can be solved topologically</li>
 * </ol>
 *
 * These problems together cover:
 * - Cycle detection
 * - Finding topological order
 * - Scheduling tasks / courses / jobs
 * - Dependency resolution in multiple layers
 * - Variants like unique ordering, batch constraints, and longest path in DAG
 */
public class TopologicalSortingKahnsAlgorithm {

    /***
     * Returns a topological ordering of nodes in a DAG
     * @param graph adjacency list: graph.get(u) contains nodes u points to
     * @param n number of vertices (0 to n-1)
     * @return list of vertices in topological order; empty list if graph contains a cycle
     */
    public static List<Integer> findPath(List<List<Integer>> graph, int n) {
        List<Integer> ans = new ArrayList<>();
        int[] inDegree = new int[n];

        // Step 1: compute in-degree of each vertex
        for (int u = 0; u < n; u++) {
            for (int v : graph.get(u)) {
                inDegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        // Step 2: add all vertices with in-degree 0
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        // Step 3: process the queue
        while (!queue.isEmpty()) {
            int u = queue.poll();
            ans.add(u);

            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // Step 4: detect cycle
        if (ans.size() != n) return new ArrayList<>(); // empty list indicates cycle

        return ans;
    }
}
