package com.toastedbits.leetcode.recursion;

import com.toastedbits.leetcode.utils.BinaryTreeUtils;
import com.toastedbits.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=mAyZoUcNdxs
public class UniqueBinarySearchTreesTwo {
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> roots = new ArrayList<>();

        if(left > right) {
            roots.add(null);
        } else {
            for(int i = left; i <= right; ++i) {
                List<TreeNode> leftSubtrees = generateTrees(left, i-1);
                List<TreeNode> rightSubtrees = generateTrees(i+1, right);

                //When you consider 'all possible combinations' - think cross multiply
                //cross multiply can be performed simply with nested for loops to pair up the operands
                //This can easily be extended to cross multiplies involving more than 2 parameters, just add more nested loops
                for(TreeNode leftSubtree: leftSubtrees) {
                    for(TreeNode rightSubtree: rightSubtrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftSubtree;
                        root.right = rightSubtree;
                        roots.add(root);
                    }
                }
            }
        }

        return roots;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesTwo solver = new UniqueBinarySearchTreesTwo();
        for(TreeNode node : solver.generateTrees(3)) {
            System.out.println(BinaryTreeUtils.levelOrderTraverse(node));
        }
    }
}
