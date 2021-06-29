package com.toastedbits.leetcode.narytrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; ++i) {
                Node cur = queue.poll();
                level.add(cur.val);
                if(cur.children != null) {
                    for(Node child : cur.children) {
                        if(child != null) {
                            queue.offer(child);
                        }
                    }
                }
            }
            list.add(level);
        }

        return list;
    }

    private void test1() {
        Node n = new Node(1, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        n3.children.add(new Node(5));
        n3.children.add(new Node(6));
        n.children.add(n3);
        n.children.add(new Node(2));
        n.children.add(new Node(4));
        System.out.println(levelOrder(n));
    }

    public static void main(String[] args) {
        NaryTreeLevelOrderTraversal solver = new NaryTreeLevelOrderTraversal();
        solver.test1();
    }
}
