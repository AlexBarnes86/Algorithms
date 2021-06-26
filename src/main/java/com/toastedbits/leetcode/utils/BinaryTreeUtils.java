package com.toastedbits.leetcode.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeUtils {
    public static TreeNode buildTree(List<Integer> values) {
        List<TreeNode> list = new ArrayList<>();
        for(int i = 0; i < values.size(); ++i) {
            list.add(values.get(i) == null ? null : new TreeNode(values.get(i)));
        }
        for(int i = 0; i <= list.size() / 2; ++i) {
            if(list.get(i) != null) {
                if(2*i+1 < list.size()) {
                    list.get(i).left = list.get(2 * i + 1);
                }
                if(2*i+2 < list.size()) {
                    list.get(i).right = list.get(2 * i + 2);
                }
            }
        }
        return list.get(0);
    }

    public static List<List<Integer>> levelOrderTraverse(TreeNode root) {
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
}
