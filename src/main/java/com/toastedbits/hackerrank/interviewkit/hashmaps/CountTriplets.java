package com.toastedbits.hackerrank.interviewkit.hashmaps;

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

public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        if(arr == null || arr.size() <= 2) {
            return 0;
        }

        Map<Long, Long> ctLeft = new HashMap<>();
        Map<Long, Long> ctRight = new HashMap<>();

        for (int i = 1; i < arr.size(); ++i) {
            ctRight.compute(arr.get(i), (k, v) -> v == null ? 1 : v + 1);
        }

        long ct = 0;
        for (int secondIndex = 0; secondIndex < arr.size() - 1; secondIndex++) {
            long second = arr.get(secondIndex);
            if(second % r == 0) {
                long first = second / r;
                long third = second * r;

                long combos = ctLeft.getOrDefault(first, 0L) * ctRight.getOrDefault(third, 0L);
                ct += combos;
            }

            ctLeft.compute(second, (k, v) -> v == null ? 1 : v + 1);
            ctRight.compute(arr.get(secondIndex+1), (k, v) -> v - 1);
        }

        return ct;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
