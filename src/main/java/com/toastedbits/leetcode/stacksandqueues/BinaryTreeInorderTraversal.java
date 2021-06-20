package com.toastedbits.leetcode.stacksandqueues;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {
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

    private List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
        if(root.left != null) {
            inorderTraversal(root.left, list);
        }
        list.add(root.val);
        if(root.right != null) {
            inorderTraversal(root.right, list);
        }

        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        return inorderTraversal(root, new ArrayList<>());
    }

    public void test1() {
        TreeNode n1 = new TreeNode(1);
        n1.right = new TreeNode(2);
        n1.right.left = new TreeNode(3);
        System.out.println(inorderTraversal(n1));
    }

    public void test2() {
        System.out.println(inorderTraversal(null));
    }

    public void test3() {
        TreeNode n1 = new TreeNode(1);
        System.out.println(inorderTraversal(n1));
    }

    public static void main(String[] args) {
        BinaryTreeInorderTraversal solver = new BinaryTreeInorderTraversal();
        solver.test1();
        solver.test2();
        solver.test3();
    }
}
