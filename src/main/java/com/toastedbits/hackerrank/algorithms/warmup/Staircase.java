package com.toastedbits.hackerrank.algorithms.warmup;

import java.util.Scanner;

public class Staircase {

    // Complete the staircase function below.
    static void staircase(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; ++i) {
            for(int j = 0; j < n; ++j) {
                sb.append(j < n - i ? " " : "#");
            }
            if(i != n) {
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        staircase(n);

        scanner.close();
    }
}

