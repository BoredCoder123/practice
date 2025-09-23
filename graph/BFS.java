package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    /***
     * Link: <a href="https://cp-algorithms.com/graph/breadth-first-search.html">CP algorithm link</a>
     * <p>
     * Time complexity: O(V+E), Space complexity: O(V)
     * <p>
     * This algorithm can be used either with a start vertex or without the start vertex to explore the whole graph.
     * If there is no start vertex, do for loop for all the vertices from 0 to n-1 and run bfs on each of them if they are not marked as done.
     * This is similar to a level order traversal of trees where were traverse the tree in levels, but in graph we traverse all the connected vertices before move to the next depth
     * <p>
     * Multi-source bfs: Enqueue all valid starting points in the queue and start from there
     * <p>
     * Level-order bfs or multi-level bfs: Record the current size of queue, poll all the current elements and run bfs on them. generally by running a for loop from 1..queue.size(use recorded size because as you add new elements, the size will keep increasing)
     * <p>
     * Applications:
     * <ul>
     * <li>Shortest path from source to other vertices in an unweighted graph</li>
     * <li>Find connected components</li>
     * <li>Solution to a game with the least number of moves</li>
     * <li>01 BFS</li>
     * <li>Shortest cycle in directed unweighted graph. Do bfs at each node.</li>
     * <li>All edges/vertices between 2 pairs of vertices and record the distances. Start bfs from both ends</li>
     * <p>
     * Common BFS problems on LeetCode:
     * <ul>
     *   <li><a href="https://leetcode.com/problems/number-of-islands/">200. Number of Islands</a></li>
     *   <li><a href="https://leetcode.com/problems/shortest-path-in-binary-matrix/">1091. Shortest Path in Binary Matrix</a></li>
     *   <li><a href="https://leetcode.com/problems/word-ladder/">127. Word Ladder</a></li>
     *   <li><a href="https://leetcode.com/problems/clone-graph/">133. Clone Graph</a></li>
     *   <li><a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a></li>
     *   <li><a href="https://leetcode.com/problems/surrounded-regions/">130. Surrounded Regions</a></li>
     *   <li><a href="https://leetcode.com/problems/is-graph-bipartite/">785. Is Graph Bipartite?</a></li>
     *   <li><a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">102. Binary Tree Level Order Traversal</a></li>
     *   <li><a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">111. Minimum Depth of Binary Tree</a></li>
     *   <li><a href="https://leetcode.com/problems/rotting-oranges/">994. Rotting Oranges</a></li>
     *   <li><a href="https://leetcode.com/problems/01-matrix/">542. 01 Matrix</a> *(distance to nearest zero in grid, multi-source BFS)*</li>
     *   <li><a href="https://leetcode.com/problems/open-the-lock/">752. Open the Lock</a> *(state-graph BFS over combinations)*</li>
     *   <li><a href="https://leetcode.com/problems/perfect-squares/">279. Perfect Squares</a> *(shortest path in number state space)*</li>
     * </ul>
     * @param adjList Represents the graph as an adjacency list
     * @return List of vertices in the breadth first search
     */
    public static List<Integer> bfsFromStart(List<List<Integer>> adjList, int start, boolean[] visited) {
        List<Integer> traversal = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(visited[curr]) {
                continue;
            }
            traversal.add(curr);

            for(int i : adjList.get(curr)) {
                if(!visited[i]) {
                    queue.add(i);
                }
                visited[i] = true;
            }
        }

        return traversal;
    }

    public static List<Integer> fullBfs(List<List<Integer>> adjList) {
        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[adjList.size()];

        for(int i = 0 ; i < adjList.size(); i++) {
            if(!visited[i]) {
                traversal.addAll(bfsFromStart(adjList, i, visited));
            }
        }

        return traversal;
    }
}
