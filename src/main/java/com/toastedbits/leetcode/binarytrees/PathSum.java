package com.toastedbits.leetcode.binarytrees;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;
import java.util.Arrays;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }

        if(root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public void test1() {
        TreeNode node = BinaryTreeUtils.buildTree(Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,null,1));
        System.out.println(hasPathSum(node, 22));
    }

    public void test2() {
        TreeNode node = BinaryTreeUtils.buildTree(Arrays.asList(1,2,3));
        System.out.println(hasPathSum(node, 5));
    }

    public void test3() {
        TreeNode node = BinaryTreeUtils.buildTree(Arrays.asList(1,2));
        System.out.println(hasPathSum(node, 0));
    }

    public void test4() {
        System.out.println(hasPathSum(null, 0));
    }

    public void test5() {
        System.out.println(hasPathSum(null, 1));
    }

    public void test6() {
        TreeNode node = BinaryTreeUtils.buildTree(Arrays.asList(1,2));
        System.out.println(hasPathSum(node, 1));
    }

    public static void main(String[] args) {
        PathSum solver = new PathSum();
        solver.test1();
        solver.test2();
        solver.test3();
        solver.test4();
        solver.test5();
        solver.test6();
    }
}
