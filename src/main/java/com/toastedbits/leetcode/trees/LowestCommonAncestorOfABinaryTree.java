package com.toastedbits.leetcode.trees;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LowestCommonAncestorOfABinaryTree {
    private class PathNode {
        PathNode parent;
        TreeNode orig;
        TreeNode left() {
            return orig.left;
        }
        TreeNode right() {
            return orig.right;
        }
        int val() {
            return orig.val;
        }
        PathNode(TreeNode orig) {
            this.orig = orig;
        }
        PathNode(TreeNode orig, PathNode parent) {
            this.orig = orig;
            this.parent = parent;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Set<Integer> visited = new HashSet<>();
        Stack<PathNode> pathStack = new Stack<>();
        pathStack.push(new PathNode(root));
        visited.add(root.val);
        while(!pathStack.isEmpty()) {
            PathNode cur = pathStack.pop();
            if(cur.val() == p.val) {
                while(cur != null) {
                    visited.add(cur.val());
                    cur = cur.parent;
                }
                break;
            }
            if(cur.left() != null) {
                pathStack.push(new PathNode(cur.left(), cur));
            }
            if(cur.right() != null) {
                pathStack.push(new PathNode(cur.right(), cur));
            }
        }
        pathStack.empty();

        pathStack.push(new PathNode(root));

        //System.out.println("Visited: "  + visited);

        while(!pathStack.isEmpty()) {
            PathNode cur = pathStack.pop();
            if(cur.val() == q.val) {
                while(cur != null) {
                    if(visited.contains(cur.val())) {
                        return cur.orig;
                    }
                    cur = cur.parent;
                }
                break;
            }
            if(cur.left() != null) {
                pathStack.push(new PathNode(cur.left(), cur));
            }
            if(cur.right() != null) {
                pathStack.push(new PathNode(cur.right(), cur));
            }
        }

        return null;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfABinaryTree solver = new LowestCommonAncestorOfABinaryTree();
        TreeNode root = BinaryTreeUtils.buildTree(Arrays.asList(3,5,1,6,2,0,8,null,null,7,4));
        System.out.println(solver.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1)));
    }
}
