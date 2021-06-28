package com.toastedbits.leetcode.trees;

import com.toastedbits.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    public TreeNode deserialize(String data) {
        String content = data.substring(1,data.length()-1);
        if(content.isEmpty()) {
            return null;
        }

        String[] tokens = content.split(",");
        List<Integer> values = new ArrayList<>();
        for(String token : tokens) {
            if("null".equals(token)) {
                values.add(null);
            }
            else {
                values.add(Integer.parseInt(token));
            }
        }

        return buildTree(values);
    }

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

    public String serialize(TreeNode root) {
        if(root == null) {
            return "[]";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<String> result = new ArrayList<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<String> level = new ArrayList<>();
            boolean hasNodes = false;
            for(int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                level.add(cur != null  ? cur.val + "" : null);
                queue.offer(cur != null && cur.left != null ? cur.left : null);
                queue.offer(cur != null && cur.right != null ? cur.right : null);
                hasNodes = hasNodes || cur != null;
            }
            if(hasNodes) {
                result.addAll(level);
            }
            else {
                break;
            }
        }

        if(result.size() > 0) {
            String cur = result.get(result.size() - 1);
            while (cur == null) {
                result.remove(result.size() - 1);
                cur = result.get(result.size() - 1);
            }
        }


        StringBuilder resultStr = new StringBuilder("[");
        resultStr.append(String.join(",", result));
        resultStr.append("]");
        return resultStr.toString();
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree solver = new SerializeAndDeserializeBinaryTree();
        System.out.println(solver.serialize(solver.deserialize(solver.serialize(solver.deserialize("[1,2,3,null,null,4,5]")))));
        System.out.println(solver.serialize(solver.deserialize(solver.serialize(solver.deserialize("[]")))));
        System.out.println(solver.serialize(solver.deserialize(solver.serialize(solver.deserialize("[1]")))));
        System.out.println(solver.serialize(solver.deserialize(solver.serialize(solver.deserialize("[1,2]")))));
    }
}
