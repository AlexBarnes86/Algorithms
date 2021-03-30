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

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> ctMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> results = new ArrayList<>();

        for(List<Integer> query : queries) {
            int val = query.get(1);
            switch(query.get(0)) {
                case 1:
                    ctMap.compute(val, (k, v) -> v == null ? 1 : v + 1);
                    freqMap.compute(ctMap.get(val), (k, v) -> v == null ? 1 : v + 1);
                    freqMap.compute(ctMap.get(val)-1, (k, v) -> v == null ? 0 : Math.max(0, v - 1));
                    break;
                case 2:
                    if(ctMap.containsKey(val) && ctMap.get(val) > 0) {
                        freqMap.compute(ctMap.get(val), (k, v) -> v == null ? 0 : Math.max(0, v - 1));
                        freqMap.compute(ctMap.get(val)-1, (k, v) -> v == null ? 1 : Math.max(1, v + 1));
                        ctMap.put(val, Math.max(0, ctMap.get(val)-1));
                    }
                    break;
                case 3:
                    results.add(freqMap.containsKey(val) && freqMap.get(val) > 0 ? 1 : 0);
                    break;
            }
        }

        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
                + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
