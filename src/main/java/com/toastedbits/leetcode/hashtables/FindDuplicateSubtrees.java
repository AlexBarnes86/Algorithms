package com.toastedbits.leetcode.hashtables;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int countNodes(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return countNodes(node.left) + 1 + countNodes(node.right);
    }

    private String getKeys(TreeNode node, StringBuilder sb, String strIfNull, Map<String, List<TreeNode>> map) {
        if(node == null) {
            return strIfNull;
        }

        sb.append(getKeys(node.left, new StringBuilder(), "L", map));
        sb.append(",");
        sb.append(node.val);
        sb.append(",");
        sb.append(getKeys(node.right, new StringBuilder(), "R", map));

        map.compute(sb.toString(), (k, v) -> {
            if(v == null) {
                v = new ArrayList<>();
            }
            v.add(node);
            return v;
        });

        return sb.toString();
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode node) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        getKeys(node, new StringBuilder(), "R", map);
        List<TreeNode> duplicates = new ArrayList<>();
        for(Map.Entry<String, List<TreeNode>> entry : map.entrySet()) {
            if(entry.getValue().size() > 1) {
                //System.out.println(entry.getKey());
                duplicates.add(entry.getValue().get(0));
            }
        }

        return duplicates;
    }

    private void test1() {
        System.out.println("== Test1 ==");
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(3);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;

        n3.left = n5;

        findDuplicateSubtrees(n1);
    }

    private void test2() {
        System.out.println("== Test2 ==");
        TreeNode n1 = new TreeNode(0);
        TreeNode n2 = new TreeNode(0);
        TreeNode n3 = new TreeNode(0);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(0);
        TreeNode n6 = new TreeNode(0);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;

        n3.right = n5;

        n5.right = n6;

        findDuplicateSubtrees(n1);
    }

    public static void main(String[] args) {
        FindDuplicateSubtrees solver = new FindDuplicateSubtrees();
        solver.test1();
        solver.test2();
    }
}
