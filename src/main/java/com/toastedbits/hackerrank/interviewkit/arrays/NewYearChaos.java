package com.toastedbits.hackerrank.interviewkit.arrays;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//Solved
public class NewYearChaos {

    // Function to count the number of inversions
    // during the merge process
    private static int mergeAndCount(int[] arr, int l, int m, int r)
    {
        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            arr[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l, int r)
    {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

    // Complete the minimumBribes function below.
    static Optional<Integer> minimumBribes(int[] q) {
        int totalBribes = 0;
        for(int i = q.length-1; i >= 0; --i) {
            int bribes = q[i] - (i+1);
            if(bribes > 2) {
                return Optional.empty();
            }
            totalBribes += Math.max(0, bribes);
        }

        return Optional.of(mergeSortAndCount(q, 0, q.length-1));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            Optional<Integer> result = minimumBribes(q);
            if(result.isPresent()) {
                System.out.println(result.get());
            }
            else {
                System.out.println("Too chaotic");
            }
        }

        scanner.close();
    }
}
