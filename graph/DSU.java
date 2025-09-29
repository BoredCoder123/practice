package graph;

import java.util.*;

/***
 * Disjoint Set Union (DSU) / Union-Find Data Structure
 * <br>
 * Supports two main operations efficiently:
 * <ul>
 *     <li><b>find(x):</b> Finds the representative (root) of the set that x belongs to</li>
 *     <li><b>union(x, y):</b> Merges the sets containing x and y</li>
 * </ul>
 *
 * Uses:
 * <ul>
 *     <li>Connected components in a graph</li>
 *     <li>Kruskal's algorithm for Minimum Spanning Tree (MST)</li>
 *     <li>Detecting cycles in an undirected graph</li>
 *     <li>Dynamic connectivity queries</li>
 *     <li>Grouping problems (clustering, social networks, etc.)</li>
 * </ul>
 *
 * Optimizations:
 * <ul>
 *     <li><b>Path Compression:</b> Flattens the tree structure during find, making future queries faster</li>
 *     <li><b>Union by Size:</b> Always attach the smaller tree under the larger to keep tree shallow</li>
 * </ul>
 *
 * Complexity:
 * <ul>
 *     <li>Amortized O(α(N)) per operation, where α(N) is the inverse Ackermann function (≤ 4 for any reasonable N)</li>
 * </ul>
 *
 * LeetCode problems:
 * <ul>
 *     <li><a href="https://leetcode.com/problems/redundant-connection/">684. Redundant Connection</a> — detect cycle in undirected graph</li>
 *     <li><a href="https://leetcode.com/problems/number-of-provinces//">547. Number of Provinces</a></li>
 *     <li><a href="https://leetcode.com/problems/friend-circles/">547. Number of Provinces</a></li>
 *     <li><a href="https://leetcode.com/problems/accounts-merge/">721. Accounts Merge</a></li>
 *     <li><a href="https://leetcode.com/problems/satisfiability-of-equality-equations/">990. Satisfiability of Equality Equations</a></li>
 *     <li><a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/">947. Most Stones Removed with Same Row or Column</a></li>
 *     <li><a href="https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/">1722. Minimize Hamming Distance After Swap Operations</a></li>
 * </ul>
 */

public class DSU {
    private int[] parent;
    private int[] size;

    /***
     * Initializes DSU with n elements (0 to n-1)
     * @param n number of elements
     */
    public DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // each node is its own parent initially
            size[i] = 1;    // each set has size 1 initially
        }
    }

    /***
     * Finds the representative (root) of the set containing x
     * Uses path compression for efficiency
     */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    /***
     * Unites the sets containing x and y
     * Uses union by size (smaller tree attaches to bigger tree)
     * @return true if union was successful (x and y were in different sets), false if already in the same set
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false; // already connected

        // attach smaller set to larger set
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
        return true;
    }

    /***
     * Checks if x and y belong to the same set
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    /***
     * Returns the size of the set containing x
     */
    public int getSize(int x) {
        return size[find(x)];
    }
}