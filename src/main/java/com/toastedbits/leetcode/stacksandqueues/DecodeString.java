package com.toastedbits.leetcode.stacksandqueues;

import java.util.Stack;

public class DecodeString {
    class Token {
        int repeats;
        StringBuilder sb;
        public Token(int repeats) {
            this.repeats = repeats;
            this.sb = new StringBuilder();
        }
        public void append(char ch) {
            this.sb.append(ch);
        }
        public void append(StringBuilder sb) {
            this.sb.append(sb);
        }
    }

    public String decodeString(String s) {
        Stack<Token> tokens = new Stack<>();
        Token curToken = new Token(1);
        tokens.push(curToken);

        StringBuilder repeatStr = new StringBuilder();

        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) {
                repeatStr.append(ch);
            }
            else if(ch == '[') {
                curToken = new Token(Integer.parseInt(repeatStr.toString()));
                tokens.push(curToken);
                repeatStr = new StringBuilder();
            }
            else if(ch == ']') {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < curToken.repeats; ++j) {
                    sb.append(curToken.sb);
                }
                tokens.pop();
                curToken = tokens.peek();
                curToken.append(sb);
            }
            else {
                curToken.append(ch);
            }
        }

        return curToken.sb.toString();
    }

    public static void main(String[] args) {
        DecodeString solver = new DecodeString();
        System.out.println(solver.decodeString("3[a]2[bc]"));
        System.out.println("aaabcbc");
        System.out.println(solver.decodeString("3[a2[c]]"));
        System.out.println("accaccacc");
        System.out.println(solver.decodeString("2[abc]3[cd]ef"));
        System.out.println("abcabccdcdcdef");
        System.out.println(solver.decodeString("abc3[cd]xyz"));
        System.out.println("abccdcdcdxyz");
        System.out.println(solver.decodeString("10[a]"));
        System.out.println("aaaaaaaaaa");
        System.out.println(solver.decodeString("1[a]"));
        System.out.println("a");
        System.out.println(solver.decodeString(""));
        System.out.println("");
        System.out.println(solver.decodeString("a"));
        System.out.println("a");
    }
}
