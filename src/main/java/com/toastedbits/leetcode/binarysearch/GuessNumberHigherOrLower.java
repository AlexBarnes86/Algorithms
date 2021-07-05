package com.toastedbits.leetcode.binarysearch;

public class GuessNumberHigherOrLower {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0)
                return mid;
            else if (res < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    static int actual;
    int guess(int num) {
        if(num < actual) {
            return -1;
        }
        else if(num > actual) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLower solver = new GuessNumberHigherOrLower();

        actual = 6; System.out.println(solver.guessNumber(10));

        for(int i = 1; i <= 1000; ++i) {
            actual = i;
            System.out.print(i + ": " + solver.guessNumber(Integer.MAX_VALUE) + " ");
            System.out.print(solver.guessNumber(i) + " ");
            System.out.print(solver.guessNumber(i+100) + " ");
            System.out.println(solver.guessNumber(i+101));
        }
    }
}
