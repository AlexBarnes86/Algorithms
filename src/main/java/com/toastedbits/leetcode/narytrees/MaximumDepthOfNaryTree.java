package com.toastedbits.leetcode.narytrees;

import java.util.ArrayList;

public class MaximumDepthOfNaryTree {
    private int maxDepth(Node root, int depth) {
        if(root == null || root.children == null) {
            return depth;
        }

        int max = depth;
        for(Node child : root.children) {
            int childDepth = maxDepth(child, depth + 1);
            if(max < childDepth) {
                max = childDepth;
            }
        }

        return max;
    }

    public int maxDepth(Node root) {
        if(root == null) {
            return 0;
        }

        return maxDepth(root, 1);
    }

    private void test1() {
        Node n = new Node(1, new ArrayList<>());

        Node n3 = new Node(3, new ArrayList<>());
        n3.children.add(new Node(5));
        n3.children.add(new Node(6));

        n.children.add(n3);
        n.children.add(new Node(2));
        n.children.add(new Node(4));

        System.out.println(maxDepth(n));
    }

    private void myTest1() {
        System.out.println(maxDepth(null));
    }

    public static void main(String[] args) {
        MaximumDepthOfNaryTree solver = new MaximumDepthOfNaryTree();
        solver.test1();
        solver.myTest1();
    }
}
