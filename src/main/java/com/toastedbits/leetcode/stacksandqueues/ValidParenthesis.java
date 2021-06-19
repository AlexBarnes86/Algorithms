package com.toastedbits.leetcode.stacksandqueues;

import java.util.HashMap;
import java.util.Stack;

public class ValidParenthesis {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for(Character ch : s.toCharArray()) {
            if(ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            else {
                if(stack.isEmpty() || !map.get(ch).equals(stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParenthesis solver = new ValidParenthesis();
        System.out.println(solver.isValid("()"));
        System.out.println(solver.isValid("()[]{}"));
        System.out.println(solver.isValid("(]"));
        System.out.println(solver.isValid("([)]"));
        System.out.println(solver.isValid("{[]}"));

        System.out.println(solver.isValid(""));
        System.out.println(solver.isValid("["));
        System.out.println(solver.isValid("]"));
    }
}
