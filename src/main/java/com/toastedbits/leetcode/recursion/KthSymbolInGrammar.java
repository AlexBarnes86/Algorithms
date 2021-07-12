package com.toastedbits.leetcode.recursion;

public class KthSymbolInGrammar {
    public boolean helper(int n, int k) {
        if(n == 0) return false;

        if(k > (int)Math.pow(2, n-1)) {
            return !helper(n-1, k - (int)Math.pow(2,n-1));
        }
        else {
            return helper(n-1, k);
        }
    }

    public int kthGrammar(int n, int k) {
        return helper(n-1, k) ? 1 : 0;
    }

    public static void main(String[] args) {
        KthSymbolInGrammar solver = new KthSymbolInGrammar();
        for(int n = 1; n <= 8;  ++n) {
            for(int k = 1; k <= (int)Math.pow(2, n-1); ++k) {
//                System.out.println("--");
                System.out.print(solver.kthGrammar(n,k));
            }
            System.out.println();
        }
    }
}
