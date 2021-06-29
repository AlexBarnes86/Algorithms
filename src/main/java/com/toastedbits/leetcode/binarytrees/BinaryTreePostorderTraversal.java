package com.toastedbits.leetcode.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal {
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

    private List<Integer> postorderTraversal(TreeNode root, List<Integer> list) {
        if(root.left != null) {
            postorderTraversal(root.left, list);
        }
        if(root.right != null) {
            postorderTraversal(root.right, list);
        }
        list.add(root.val);

        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        return postorderTraversal(root, new ArrayList<>());
    }
}
