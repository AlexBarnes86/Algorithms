package com.toastedbits.leetcode.trees;

import com.toastedbits.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            result.add(level);
            for(int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if(cur.left != null) {
                    queue.offer(cur.left);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        return result;
    }

    public void test1() {
        TreeNode n1 = new TreeNode(3);
        n1.left = new TreeNode(9);
        n1.right = new TreeNode(20);
        n1.right.left = new TreeNode(15);
        n1.right.right = new TreeNode(7);
        System.out.println(levelOrder(n1));
    }

    public void test2() {
        System.out.println(levelOrder(null));
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solver = new BinaryTreeLevelOrderTraversal();
        solver.test1();
        solver.test2();
    }
}
