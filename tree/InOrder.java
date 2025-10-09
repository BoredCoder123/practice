package tree;

import java.util.*;

/***
 * Inorder Traversal of a Binary Tree (Recursive and Iterative)
 *
 * <p>
 * Inorder traversal visits the nodes of a binary tree in the order:
 * <b>Left → Node → Right</b>.
 * </p>
 *
 * <h3>Key Properties:</h3>
 * <ul>
 *     <li>In a Binary Search Tree (BST), inorder traversal gives nodes in <b>sorted order</b>.</li>
 *     <li>Used for converting trees to arrays/lists in sorted sequence.</li>
 *     <li>Helps verify BST property: for all nodes, left < root < right.</li>
 * </ul>
 *
 * <h3>Algorithm (Recursive):</h3>
 * <ol>
 *     <li>Recursively traverse the left subtree.</li>
 *     <li>Visit the current node.</li>
 *     <li>Recursively traverse the right subtree.</li>
 * </ol>
 *
 * <h3>Algorithm (Iterative - Using Stack):</h3>
 * <ol>
 *     <li>Initialize an empty stack.</li>
 *     <li>Push all left nodes into the stack until reaching null.</li>
 *     <li>Pop a node from the stack, visit it, and move to its right child.</li>
 *     <li>Repeat until both stack is empty and node is null.</li>
 * </ol>
 *
 * <h3>Complexity:</h3>
 * <ul>
 *     <li>Time: O(N) — each node is visited once.</li>
 *     <li>Space: O(H) — height of the tree (worst case O(N) for skewed tree).</li>
 * </ul>
 *
 * <h3>LeetCode / Practice Problems:</h3>
 * <ul>
 *     <li><a href="https://leetcode.com/problems/binary-tree-inorder-traversal/">94. Binary Tree Inorder Traversal</a></li>
 *     <li><a href="https://leetcode.com/problems/validate-binary-search-tree/">98. Validate Binary Search Tree</a></li>
 *     <li><a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/">108. Convert Sorted Array to BST</a></li>
 *     <li><a href="https://leetcode.com/problems/recover-binary-search-tree/">99. Recover Binary Search Tree</a></li>
 *     <li><a href="https://leetcode.com/problems/binary-search-tree-iterator/">173. BST Iterator</a></li>
 * </ul>
 *
 * <p>
 * These problems together cover:
 * <ul>
 *     <li>Recursive traversal</li>
 *     <li>Iterative traversal using stack</li>
 *     <li>Inorder properties in BST validation</li>
 *     <li>Conversion between trees and sorted sequences</li>
 * </ul>
 * </p>
 */
public class InOrder {

    /***
     * Performs recursive inorder traversal of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return list of node values in inorder sequence
     */
    public List<Integer> recursiveInOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        recUtil(root, ans);
        return ans;
    }

    /***
     * Helper method for recursive traversal.
     *
     * @param root current node being processed
     * @param ans list to accumulate traversal order
     */
    private void recUtil(TreeNode root, List<Integer> ans) {
        if (root == null) return;

        recUtil(root.left, ans);
        ans.add(root.val);
        recUtil(root.right, ans);
    }

    /***
     * Performs iterative inorder traversal using a stack.
     *
     * <p>Simulates recursion using an explicit stack.
     * Push left children until null, then process the top
     * node and move to its right child.</p>
     *
     * @param root the root node of the binary tree
     * @return list of node values in inorder sequence
     */
    public List<Integer> iterativeInOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;

        while (true) {
            if (node != null) {
                st.push(node);
                node = node.left;
            } else {
                if (st.isEmpty())
                    break;
                node = st.pop();
                ans.add(node.val);
                node = node.right;
            }
        }

        return ans;
    }
}