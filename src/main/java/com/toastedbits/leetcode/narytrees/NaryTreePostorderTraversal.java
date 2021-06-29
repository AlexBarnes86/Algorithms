package com.toastedbits.leetcode.narytrees;

import java.util.ArrayList;
import java.util.List;

public class NaryTreePostorderTraversal {
    private void postorder(Node node, List<Integer> list) {
        if(node.children != null) {
            for (Node child : node.children) {
                if (child != null) {
                    postorder(child, list);
                }
            }
        }
        list.add(node.val);
    }

    public List<Integer> postorder(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        postorder(root, list);
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
        System.out.println(postorder(n));
    }

    public static void main(String[] args) {
        NaryTreePostorderTraversal solver = new NaryTreePostorderTraversal();
        solver.test1();
    }
}
