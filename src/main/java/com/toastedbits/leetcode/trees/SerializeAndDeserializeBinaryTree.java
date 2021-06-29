package com.toastedbits.leetcode.trees;

import com.toastedbits.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    //Uses BFS (level by level, '#' for nulls) to produce, comma separated
    public String serialize(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode h = q.poll();
            if (h == null) {
                list.add("#");
            } else {
                list.add("" + h.val);
                q.offer(h.left);
                q.offer(h.right);
            }
        }

        return String.join(",", list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        if (arr[0].equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;

        while (!q.isEmpty()) {
            TreeNode h = q.poll();
            if (h != null) {
                TreeNode left = null;
                if (!arr[i].equals("#")) {
                    left = new TreeNode(Integer.parseInt(arr[i]));
                }
                h.left = left;
                q.offer(left);
                i++;

                TreeNode right = null;
                if (!arr[i].equals("#")) {
                    right = new TreeNode(Integer.parseInt(arr[i]));
                }
                h.right = right;
                q.offer(right);
                i++;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree solver = new SerializeAndDeserializeBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(solver.serialize(root));

        System.out.println(solver.serialize(solver.deserialize(solver.serialize(solver.deserialize("1,2,3,#,#,4,5,#,#,#,#")))));
    }
}
