package com.toastedbits.leetcode.recursion;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ValidateBinarySearchTree {
    private boolean isBST = true;

    public boolean isValidBST(TreeNode root) {

        dfs2(root, Long.MIN_VALUE);
        return isBST;
    }

    public long  dfs2(TreeNode root, long prev) {

        if (root == null)
            return prev;
        prev = dfs2(root.left, prev);

        //  in order
        if (prev >= root.val)
            isBST = false;
        prev = root.val;
        //

        prev=dfs2(root.right, prev);
        return prev;
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree solver = new ValidateBinarySearchTree();
        System.out.println(solver.isValidBST(BinaryTreeUtils.buildTree(Arrays.asList(5,2,6,1,3,null,7)))); //true
        System.out.println(solver.isValidBST(BinaryTreeUtils.buildTree(Arrays.asList(5,2,6,1,3,5,7)))); //false
        System.out.println(solver.isValidBST(BinaryTreeUtils.buildTree(Arrays.asList(5,4,6,null,null,3,7)))); //false
        System.out.println(solver.isValidBST(BinaryTreeUtils.buildTree(Arrays.asList(2,1,3)))); //true
        System.out.println(solver.isValidBST(BinaryTreeUtils.buildTree(Arrays.asList(2,2,2)))); //false
        System.out.println(solver.isValidBST(BinaryTreeUtils.buildTree(Arrays.asList(1,1)))); //false
    }
}
