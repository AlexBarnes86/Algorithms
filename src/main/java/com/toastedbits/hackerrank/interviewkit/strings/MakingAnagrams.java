package com.toastedbits.hackerrank.interviewkit.strings;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

//Solved
public class MakingAnagrams {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        Map<Character, Integer> aMap = new HashMap<>();
        Map<Character, Integer> bMap = new HashMap<>();
        Set<Character> keys = new HashSet<>();

        for(char aCh : a.toCharArray()) {
            aMap.compute(aCh, (k, v) -> (v == null) ? 1 : v+1);
            keys.add(aCh);
        }
        for(char bCh : b.toCharArray()) {
            bMap.compute(bCh, (k, v) -> (v == null) ? 1 : v+1);
            keys.add(bCh);
        }
        int total = 0;
        for(char ch : keys) {
            total += Math.abs(aMap.getOrDefault(ch, 0) - bMap.getOrDefault(ch, 0));
        }
        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
