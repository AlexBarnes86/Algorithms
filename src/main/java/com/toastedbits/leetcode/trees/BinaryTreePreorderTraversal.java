package com.toastedbits.leetcode.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {
    public static class TreeNode {
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

        @Override
        public String toString() {
            return "{val: " + val + "}";
        }
    }

    private List<Integer> preorderTraversal(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if(root.left != null) {
            preorderTraversal(root.left, list);
        }
        if(root.right != null) {
            preorderTraversal(root.right, list);
        }

        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        return preorderTraversal(root, new ArrayList<>());
    }
}
