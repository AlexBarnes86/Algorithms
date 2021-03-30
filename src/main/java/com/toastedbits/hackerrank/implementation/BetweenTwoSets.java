package com.toastedbits.hackerrank.implementation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BetweenTwoSets {
    static class Result {

        private static boolean allDivisible(int val, List<Integer> factors) {
            for(int factor : factors) {
                if(val % factor != 0) {
                    return false;
                }
            }
            return true;
        }

        private static boolean factorOfAll(int divisor, List<Integer> numbers) {
            for(int number : numbers) {
                if(number % divisor != 0) {
                    return false;
                }
            }
            return true;
        }

        public static int getTotalX(List<Integer> a, List<Integer> b) {
            int ct = 0;
            for(int i = 1; i <= 100; i++) {
                if(allDivisible(i, a) && factorOfAll(i, b)) {
                    ct++;
                }
            }
            return ct;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
