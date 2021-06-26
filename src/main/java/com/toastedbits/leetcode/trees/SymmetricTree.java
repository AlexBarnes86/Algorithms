package com.toastedbits.leetcode.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SymmetricTree {
    public class TreeNode {
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
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> leftHalf = new ArrayList<>();
            List<TreeNode> rightHalf = new ArrayList<>();

            for(int i = 0; i < size / 2; ++i) {
                TreeNode cur = queue.poll();
                leftHalf.add(cur);
                if(cur != null) {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }

            for(int i = 0; i < size / 2; ++i) {
                TreeNode cur = queue.poll();
                rightHalf.add(cur);
                if(cur != null) {
                    queue.offer(cur.right);
                    queue.offer(cur.left);
                }
            }

            for(int i = 0; i < size / 2; ++i) {
                if(leftHalf.get(i) == null && rightHalf.get(i) == null) {
                    continue;
                }

                if(leftHalf.get(i) == null || rightHalf.get(i) == null) {
                    return false;
                }

                if(leftHalf.get(i).val != rightHalf.get(i).val) {
                    return false;
                }
            }
        }

        return true;
    }
}
