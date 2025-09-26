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
 *     <li><a href="https://leetcode.com/problems/number-of-islands/">200. Number of Islands</a></li>
 *     <li><a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">104. Maximum Depth of Binary Tree</a></li>
 *     <li><a href="https://leetcode.com/problems/path-sum/">112. Path Sum</a></li>
 *     <li><a href="https://leetcode.com/problems/path-sum-ii/">113. Path Sum II</a></li>
 *     <li><a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/">236. Lowest Common Ancestor of a Binary Tree</a></li>
 *     <li><a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. Construct Binary Tree from Preorder and Inorder Traversal</a></li>
 *     <li><a href="https://leetcode.com/problems/combination-sum/">39. Combination Sum</a></li>
 *     <li><a href="https://leetcode.com/problems/combination-sum-ii/">40. Combination Sum II</a></li>
 *     <li><a href="https://leetcode.com/problems/permutations/">46. Permutations</a></li>
 *     <li><a href="https://leetcode.com/problems/permutations-ii/">47. Permutations II</a></li>
 *     <li><a href="https://leetcode.com/problems/subsets/">78. Subsets</a></li>
 *     <li><a href="https://leetcode.com/problems/subsets-ii/">90. Subsets II</a></li>
 *     <li><a href="https://leetcode.com/problems/word-search/">79. Word Search</a></li>
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
