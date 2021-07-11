package com.toastedbits.leetcode.recursion;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ValidateBinarySearchTree {
    private boolean isValidBST(TreeNode root, Stack<Integer> stack) {
        if(root == null) {
            return true;
        }

        boolean isLeftValid = root.left == null || isValidBST(root.left, stack);
        if(!stack.isEmpty() && root.val <= stack.peek()) {
            return false;
        }
//        if(!stack.isEmpty()) { //reduce space complexity, We only need the most recent previous value
//            stack.pop();
//        }
        stack.push(root.val);
        boolean isRightValid = root.right == null || isValidBST(root.right, stack);
        return isLeftValid && isRightValid;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new Stack<>());
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
