package com.toastedbits.hackerrank.interviewkit.arrays;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//Solved
public class MinimumSwaps2 {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int swaps = 0;
        for(int i = 0; i < arr.length; ++i) {
//            System.out.format("i: %d, arr[%d]: %d, ", i, i, arr[i]);
            while(arr[i] != i+1) {
                swaps++;
                int swapIdx = arr[i]-1;
//                System.out.format("Swap %d with %d", arr[i], arr[swapIdx]);
                int temp = arr[i];
                arr[i] = arr[swapIdx];
                arr[swapIdx] = temp;
//                System.out.print(Arrays.toString(arr));
            }
//            System.out.println();
        }
//        System.out.println("---");
        return swaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}