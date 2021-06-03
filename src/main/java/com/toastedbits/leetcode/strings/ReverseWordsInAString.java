package com.toastedbits.leetcode.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if(Character.isWhitespace(ch)) {
                if(temp.length() > 0) {
                    result.add(temp.toString());
                    temp = new StringBuilder();
                }
            }
            else {
                temp.append(ch);
            }
        }
        if(temp.length() > 0) {
            result.add(temp.toString());
        }

        Collections.reverse(result);
        return String.join(" ", result);
    }

    private void solve(String s) {
        System.out.println(reverseWords(s));
    }

    public static void main(String[] args) {
        ReverseWordsInAString solver = new ReverseWordsInAString();
        solver.solve("the sky is blue");
        solver.solve("  hello world  ");
        solver.solve("a good   example");
        solver.solve("  Bob    Loves  Alice   ");
        solver.solve("Alice does not even like bob");
    }
}
