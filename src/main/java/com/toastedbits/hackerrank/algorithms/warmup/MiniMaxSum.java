package com.toastedbits.hackerrank.algorithms.warmup;

import java.util.Scanner;

public class MiniMaxSum {

    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE, sum = 0L;
        for(int i = 0 ; i < arr.length; ++i) {
            sum += arr[i];
            if(min > arr[i]) min = arr[i];
            if(max < arr[i]) max = arr[i];
        }

        System.out.println((sum - max) + " " + (sum - min));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);


        scanner.close();
    }
}

