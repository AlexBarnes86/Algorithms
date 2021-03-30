package com.toastedbits.hackerrank.algorithms.warmup;

import java.util.Scanner;

public class PlusMinus {

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        float pos = 0, neg = 0, zero = 0;
        for(int val : arr) {
            if(val > 0) {
                pos++;
            }
            else if(val == 0) {
                zero++;
            }
            else {
                neg++;
            }
        }

        System.out.format("%6f%n", pos / arr.length);
        System.out.format("%6f%n", neg / arr.length);
        System.out.format("%6f%n", zero / arr.length);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();
    }
}

