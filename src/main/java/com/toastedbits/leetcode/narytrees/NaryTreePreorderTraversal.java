package com.toastedbits.leetcode.narytrees;

import java.util.ArrayList;
import java.util.List;

public class NaryTreePreorderTraversal {
    private void preorder(Node node, List<Integer> list) {
        list.add(node.val);
        if(node.children != null) {
            for (Node child : node.children) {
                if (child != null) {
                    preorder(child, list);
                }
            }
        }
    }

    public List<Integer> preorder(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        preorder(root, list);
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
        System.out.println(preorder(n));
    }

    public static void main(String[] args) {
        NaryTreePreorderTraversal solver = new NaryTreePreorderTraversal();
        solver.test1();
    }
}
