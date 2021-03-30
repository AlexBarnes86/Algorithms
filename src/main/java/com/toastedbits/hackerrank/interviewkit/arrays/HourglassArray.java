package com.toastedbits.hackerrank.interviewkit.arrays;
//https://www.hackerrank.com/challenges/2d-array/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//Solved
public class HourglassArray {
    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int max = Integer.MIN_VALUE;
        for(int r = 0; r < 4; ++r) {
            for(int c = 0; c < 4; ++c) {
                int sum = hourglassSum(arr, r, c);
                if(sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    static int hourglassSum(int[][] arr, int r, int c) {
        return arr[r][c] + arr[r][c+1] + arr[r][c+2] +
            arr[r+1][c+1] +
            arr[r+2][c] + arr[r+2][c+1] + arr[r+2][c+2];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
