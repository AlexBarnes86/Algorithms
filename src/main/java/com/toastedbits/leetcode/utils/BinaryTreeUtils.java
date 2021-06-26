package com.toastedbits.leetcode.utils;

import com.toastedbits.leetcode.trees.PathSum;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeUtils {
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
}
