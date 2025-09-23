package graph;

import java.util.*;

/***
 * Link: <a href="https://cp-algorithms.com/graph/depth-first-search.html">CP algorithm link</a><br>
 * Go as deep as possible first and then backtrack if required. Can be done either by an explicit stack or recursion stack
 * <br>
 * Time complexity: O(V+E), Space complexity: O(V)
 * <br><br>
 * Applications:
 * <ul>
 *     <li>Find path from source to to all vertices</li>
 *     <li>Lexicographical first path in graph from source</li>
 *     <li>Check if one vertex is ancestor of another</li>
 *     <li>LCA of two vertices</li>
 *     <li>Topological sorting</li>
 *     <li>Check if graph is acyclic and find cycles in a graph</li>
 *     <li>Find strongly connected components in a graph</li>
 *     <li>Bridges in undirected graph</li>
 * </ul>
 * LeetCode problems:
 * <ul>
 *     <li><a href="https://leetcode.com/problems/number-of-islands/">200. Number of Islands</a> — Count the number of connected components in a 2D grid.</li>
 *     <li><a href="https://leetcode.com/problems/surrounded-regions/">130. Surrounded Regions</a> — Capture regions surrounded by 'X' using DFS to mark boundaries.</li>
 *     <li><a href="https://leetcode.com/problems/word-search/">79. Word Search</a> — Backtracking DFS to find a word in a 2D board.</li>
 *     <li><a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/">329. Longest Increasing Path in a Matrix</a> — DFS with memoization to find the longest increasing path.</li>
 *     <li><a href="https://leetcode.com/problems/clone-graph/">133. Clone Graph</a> — DFS to clone an undirected graph.</li>
 *     <li><a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a> — Detect cycles in a directed graph using DFS.</li>
 *     <li><a href="https://leetcode.com/problems/course-schedule-ii/">210. Course Schedule II</a> — Topological sort using DFS to find a valid course order.</li>
 *     <li><a href="https://leetcode.com/problems/pacific-atlantic-water-flow/">417. Pacific Atlantic Water Flow</a> — DFS to find cells that can flow to both oceans.</li>
 *     <li><a href="https://leetcode.com/problems/max-area-of-island/">695. Max Area of Island</a> — DFS to find the largest connected component in a grid.</li>
 *     <li><a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/">17. Letter Combinations of a Phone Number</a> — Backtracking DFS to generate all letter combinations.</li>
 *     <li><a href="https://leetcode.com/problems/generate-parentheses/">22. Generate Parentheses</a> — Backtracking DFS to generate valid parentheses combinations.</li>
 *     <li><a href="https://leetcode.com/problems/permutations/">46. Permutations</a> — Backtracking DFS to generate all permutations of a list.</li>
 * </ul>
 */
public class DFS {
    public static void dfsFromStart(List<List<Integer>> graph, int start, List<Integer> traversal, Set<Integer> visited) {
        visited.add(start);
        traversal.add(start);

        for (Integer neighbor : graph.get(start)) {
            if (!visited.contains(neighbor)) {
                dfsFromStart(graph, neighbor, traversal, visited);
            }
        }
    }

    public static List<Integer> fullDfs(List<List<Integer>> graph) {
        List<Integer> traversal = new ArrayList<>();
        Set<Integer> visited = new HashSet<>(); //If only integers, can use int[] instead of Set<Integer> as int[] would be faster and easier to maintain. Set would work better for generic things like nodes etc.

        for (int i = 0; i < graph.size(); i++) {
            if (!visited.contains(i)) {
                dfsFromStart(graph, i, traversal, visited);
            }
        }

        return traversal;
    }

    public static List<Integer> iterativeDfs(List<List<Integer>> graph) {
        List<Integer> traversal = new ArrayList<>();
        Set<Integer> visited = new HashSet<>(); //If only integers, can use int[] instead of Set<Integer> as int[] would be faster and easier to maintain. Set would work better for generic things like nodes etc.
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < graph.size(); i++) {
            if(!visited.contains(i)) {
                visited.add(i);
                st.push(i);

                while(!st.isEmpty()) {
                    int x = st.pop();
                    traversal.add(x);
                    for(int y : graph.get(x)) {
                        if(!visited.contains(y)) {
                            st.push(y);
                            visited.add(y);
                        }
                    }
                }
            }
        }

        return traversal;
    }
}
