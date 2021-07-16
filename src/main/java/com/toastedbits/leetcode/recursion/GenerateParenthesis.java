package com.toastedbits.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    List<String> list = new ArrayList<>();
    public void dfs(int n, int depth, int open, String str) {
        if (depth == n && open == 0) {
            list.add(str);
            return;
        }

        if(depth < n)
            dfs(n, depth + 1, open + 1, str + "(");
        if(open > 0)
            dfs(n, depth, open-1,str + ")");
    }

    public List<String> generateParenthesis(int n) {
        list = new ArrayList<>();
        dfs(n, 0, 0,"");
        return list;
    }

    public static void main(String[] args) {
        GenerateParenthesis solver = new GenerateParenthesis();
        System.out.println(solver.generateParenthesis(8));
    }
}
