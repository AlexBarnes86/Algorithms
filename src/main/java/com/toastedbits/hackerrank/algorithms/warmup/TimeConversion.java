package com.toastedbits.hackerrank.algorithms.warmup;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeConversion {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        s = s.trim();
        DateFormat inputFormat = new SimpleDateFormat("hh:mm:ssaa");
        DateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date dt = inputFormat.parse(s);
            return outputFormat.format(dt);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
