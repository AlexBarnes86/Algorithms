package com.toastedbits.hackerrank.interviewkit.warmup;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//Solved
public class RepeatedString {
    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        long ctPerStr = count(s, s.length());
        long mult = n / s.length();
        return ctPerStr * mult + count(s, (int)(n - s.length()*mult));
    }

    static long count(String s, int max) {
        long ct = 0;
        for(int i = 0; i < s.length() && i < max; ++i) {
            if(s.charAt(i) == 'a') {
                ct++;
            }
        }
        return ct;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
