package com.toastedbits.leetcode.recursion;

import com.toastedbits.leetcode.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> left = new LinkedList<>();
        Queue<TreeNode> right = new LinkedList<>();
        left.offer(p);
        right.offer(q);
        while(!left.isEmpty() || !right.isEmpty()) {
            TreeNode l = left.poll();
            TreeNode r = right.poll();
            if(l == null && r == null) {
                continue;
            }
            if(l == null || r == null) {
                return false;
            }
            if(l.val != r.val) {
                return false;
            }
            left.offer(l.left);
            left.offer(l.right);
            right.offer(r.left);
            right.offer(r.right);
        }
        return true;
    }
}
