package com.toastedbits.hackerrank.interviewkit.hashmaps;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RansomNote {

    // Complete the checkMagazine function below.
    static boolean checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magMap = new HashMap<>();

        for(String word : magazine) {
            word = word.trim();
            magMap.compute(word, (k, v) -> (v == null) ? 1 : v+1);
        }

        for(String word : note) {
            word = word.trim();
            if(!magMap.containsKey(word)) {
                return false;
            }
            else {
                int ct = magMap.get(word) - 1;
                if(ct < 0) {
                    return false;
                }
                magMap.put(word, ct);
            }
        }

        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        System.out.println(checkMagazine(magazine, note) ? "Yes" : "No");

        scanner.close();
    }
}


