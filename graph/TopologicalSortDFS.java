package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortDFS {
    /***
     * Returns a topological ordering of nodes in a DAG
     * @param graph adjacency list: graph.get(u) contains nodes u points to
     * @param n number of vertices (0 to n-1)
     * @return list of vertices in topological order; empty list if graph contains a cycle
     */
    public static List<Integer> findPathRecursive(List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited, Stack<Integer> st) {
        visited[node] = true;
        for(int nei : graph.get(node)) {
            if(!visited[nei])
                dfs(nei, graph, visited, st);
        }
        st.push(node);
    }

    public static List<Integer> topoSortIterative(int n, List<List<Integer>> adj) {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                iterativeDFS(i, adj, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void iterativeDFS(int start, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        Stack<Integer> callStack = new Stack<>();
        Stack<Integer> postOrder = new Stack<>();

        callStack.push(start);

        while (!callStack.isEmpty()) {
            int node = callStack.pop();
            if (visited[node]) continue;

            visited[node] = true;
            postOrder.push(node);

            for (int nei : adj.get(node)) {
                if (!visited[nei]) {
                    callStack.push(nei);
                }
            }
        }

        // reverse post-order to mimic recursion "push after children"
        while (!postOrder.isEmpty()) {
            stack.push(postOrder.pop());
        }
    }
}
