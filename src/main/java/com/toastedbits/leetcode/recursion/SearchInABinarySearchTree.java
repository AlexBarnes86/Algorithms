package com.toastedbits.leetcode.recursion;

public class SearchInABinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || val == root.val) {
            return root;
        }
        else if(val > root.val) {
            return searchBST(root.right, val);
        }
        return searchBST(root.left, val);
    }
}
