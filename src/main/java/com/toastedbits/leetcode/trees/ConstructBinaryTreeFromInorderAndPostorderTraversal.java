package com.toastedbits.leetcode.trees;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(List<Integer> inorder, List<Integer> postorder) {
        if(postorder.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder.remove(postorder.size()-1));
        int idx = inorder.indexOf(root.val);
        int leftSize = idx;
        int rightSize = inorder.size() - (idx+1);
        if(leftSize > 0) {
            List<Integer> leftInorder = new ArrayList<>(inorder.subList(0, idx));
            List<Integer> leftPostorder = new ArrayList<>(postorder.subList(0, idx));
            root.left = buildTree(leftInorder, leftPostorder);
        }
        if(rightSize > 0) {
            List<Integer> rightInorder = new ArrayList<>(inorder.subList(idx+1, inorder.size()));
            List<Integer> rightPostorder = new ArrayList<>(postorder.subList(idx, postorder.size()));
            root.right = buildTree(rightInorder, rightPostorder);
        }
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        List<Integer> inorderList = Arrays.stream(inorder).boxed().collect(Collectors.toList());
        List<Integer> postorderList = Arrays.stream(postorder).boxed().collect(Collectors.toList());
        return buildTree(inorderList, postorderList);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal solver = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
        System.out.println(BinaryTreeUtils.levelOrderTraverse(solver.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3})));
        System.out.println(BinaryTreeUtils.levelOrderTraverse(solver.buildTree(new int[]{-1}, new int[]{-1})));
    }
}
