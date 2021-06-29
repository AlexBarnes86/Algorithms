package com.toastedbits.leetcode.binarytrees;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        return dfs(inorder, postorder, 0, len-1, 0, len-1);
    }

    TreeNode dfs(int[] inorder, int[] postorder, int instart, int inend, int poststart, int postend){
        if(instart > inend) return null;

        int rootval = postorder[postend];
        TreeNode root = new TreeNode(rootval);

        int rootindex = instart;
        for(; rootindex<= inend; rootindex++){
            if(inorder[rootindex] == rootval) break;
        }
        int leftsize = rootindex - instart;
        int rightsize = inend - rootindex;

        root.left = dfs(inorder, postorder, instart, rootindex-1, poststart, poststart+ leftsize-1);
        root.right = dfs(inorder, postorder, rootindex+1, inend, postend- rightsize, postend-1);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal solver = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
        System.out.println(BinaryTreeUtils.levelOrderTraverse(solver.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3})));
        System.out.println(BinaryTreeUtils.levelOrderTraverse(solver.buildTree(new int[]{-1}, new int[]{-1})));
    }
}
