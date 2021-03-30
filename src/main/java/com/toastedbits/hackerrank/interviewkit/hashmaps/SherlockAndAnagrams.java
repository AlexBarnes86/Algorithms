package com.toastedbits.hackerrank.interviewkit.hashmaps;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndAnagrams {

    private static Map<Character, Integer> toCharacterMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.compute(ch, (k, v) -> v == null ? 1 : v+1);
        }
        return map;
    }

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int total = 0;
        for(int len = 1; len < s.length(); ++len) {
            for(int i = 0; i < s.length() - len; ++i) {
                String anagram = s.substring(i, i+len);
                Map<Character, Integer> anagramMap = toCharacterMap(anagram);
                for(int j = i+1; j < s.length() - len + 1; ++j) {
                    String queryStr = s.substring(j, j+len);
                    Map<Character, Integer> queryMap = toCharacterMap(queryStr);
                    if(anagramMap.equals(queryMap)) {
                        log("Match at len: " + len + ", i: " + i + ", j: " + j + ", anagram: " + anagram + ", queryStr: " + queryStr);
                        total++;
                    }
                }
            }
        }
        return total;
    }

    private static void log(String str) {
        System.out.println("log: " + str);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
