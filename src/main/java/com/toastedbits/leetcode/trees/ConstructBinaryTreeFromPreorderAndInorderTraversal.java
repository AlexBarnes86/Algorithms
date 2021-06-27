package com.toastedbits.leetcode.trees;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode dfs(int[] preorder, int[] inorder, int startin, int endin, int startpre, int endpre) {
        if(startin >= endin) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[startpre]);

        int idx = startin;
        for(; idx < endin; ++idx) {
            if (inorder[idx] == root.val) {
                break;
            }
        }

        int leftsize = idx - startin;
        int rightsize = endin - idx;

        root.left = dfs(preorder, inorder, startin, idx, startpre+1, startpre+leftsize+1);
        root.right = dfs(preorder, inorder, idx+1, endin, startpre+1+leftsize,  endpre-rightsize);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, inorder.length, 0, preorder.length);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal solver = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        TreeNode root = solver.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(BinaryTreeUtils.levelOrderTraverse(root));

        root = solver.buildTree(new int[]{-1}, new int[]{-1});
        System.out.println(BinaryTreeUtils.levelOrderTraverse(root));
    }
}
