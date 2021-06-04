package com.toastedbits.leetcode.strings;

public class ReverseWordsInAStringThree {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if(Character.isWhitespace(ch)) {
                if(temp.length() > 0) {
                    result.append(temp.reverse());
                    temp = new StringBuilder();
                }
                result.append(ch);
            }
            else {
                temp.append(ch);
            }
        }
        if(temp.length() > 0) {
            result.append(temp.reverse());
        }

        return result.toString();
    }

    private void solve(String s) {
        System.out.println("[" + reverseWords(s) + "]");
    }

    public static void main(String[] args) {
        ReverseWordsInAStringThree solver = new ReverseWordsInAStringThree();
        solver.solve("Let's take   LeetCode contest  ");
        solver.solve("God Ding");

        solver.solve("the sky is blue");
        solver.solve("  hello world  ");
        solver.solve("a good   example");
        solver.solve("  Bob    Loves  Alice   ");
        solver.solve("Alice does not even like bob");
    }
}
