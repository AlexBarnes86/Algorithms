package com.toastedbits.hackerrank.bitmanipulation;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

//Solved for 50 points
public class AorB {
    private static boolean[][] hexValues = new boolean[][] {
        new boolean[]{false, false, false, false}, //0
        new boolean[]{false, false, false, true}, //1
        new boolean[]{false, false, true, false}, //2
        new boolean[]{false, false, true, true}, //3
        new boolean[]{false, true, false, false}, //4
        new boolean[]{false, true, false, true}, //5
        new boolean[]{false, true, true, false}, //6
        new boolean[]{false, true, true, true}, //7
        new boolean[]{true, false, false, false}, //8
        new boolean[]{true, false, false, true}, //9
        new boolean[]{true, false, true, false}, //10
        new boolean[]{true, false, true, true}, //11
        new boolean[]{true, true, false, false}, //12
        new boolean[]{true, true, false, true}, //13
        new boolean[]{true, true, true, false}, //14
        new boolean[]{true, true, true, true} //15
    };

    private static boolean[] getBitArrayFromHexCharacter(char ch) {
        if(ch >= '0' && ch <= '9') {
            return hexValues[ch - '0'];
        }
        else if(ch >= 'A' && ch <= 'F') {
            return hexValues[ch - 'A' + 10];
        }
        else {
            throw new IllegalArgumentException("Invalid hex character: [" + ch + "]");
        }
    }

    private static boolean[] toBitArray(String str, int length) {
        boolean[] ary = new boolean[length];
        int bitIdx = length - 4;
        for(int i = str.length() - 1; i >= 0; i--) {
            char hexCh = str.charAt(i);
            boolean[] bits = getBitArrayFromHexCharacter(hexCh);
            for(int j = 0; j < 4; ++j) {
                ary[bitIdx + j] = bits[j];
            }
            bitIdx -= 4;
        }

        return ary;
    }

    private static String booleanArrayToStr(boolean[] ary) {
        StringBuilder sb = new StringBuilder(ary.length);
        int ct = 1;
        for(int i = 0; i < ary.length; ++i) {
            sb.append(ary[i] ? '1' : '0');
            if(ct++ % 4 == 0) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    private static void log(String str) {
        //System.out.println("log: " + str);
    }

    private static class Result {
        boolean[] A;
        boolean[] B;
        int flips;

        public Result(final boolean[] A, final boolean[] B, int flips) {
            this.A = A;
            this.B = B;
            this.flips = flips;
        }

        @Override
        public String toString() {
            return "{A: " + booleanArrayToStr(A) + ", B: " + booleanArrayToStr(B) + ", flips: " + flips + "}";
        }
    }

    private static Optional<Result> aOrB(int k, String a, String b, String c) {
        int maxStrLen = Math.max(Math.max(a.length(), b.length()), c.length());
        int maxBitAryLen = maxStrLen * 4;
        log("Max Bit Ary Len: " + maxBitAryLen);


        boolean[] bitAryA = toBitArray(a, maxBitAryLen);
        boolean[] bitAryB = toBitArray(b, maxBitAryLen);
        boolean[] bitAryC = toBitArray(c, maxBitAryLen);

        log("A: " + booleanArrayToStr(bitAryA));
        log("B: " + booleanArrayToStr(bitAryB));
        log("C: " + booleanArrayToStr(bitAryC));

        StringBuilder sb = new StringBuilder(maxBitAryLen);
        int total = 0;

        //First pass, flip bits that no matter what must be flipped
        //this is all the bits for which Ci = 0 or Ci = 1 and Ai = 0
        for(int i = 0; i < maxBitAryLen; ++i) {
            boolean Ai = bitAryA[i];
            boolean Bi = bitAryB[i];
            boolean Ci = bitAryC[i];

            if(!Ci) {
                if(!Ai && !Bi) {
                    continue;
                }
                else if(!Ai && Bi) {
                    total += 1;
                    bitAryB[i] = false;
                }
                else if(Ai && !Bi) {
                    total += 1;
                    bitAryA[i] = false;
                }
                else {
                    total += 2;
                    bitAryA[i] = false;
                    bitAryB[i] = false;
                }
            }

            if(Ci && !Ai && !Bi) {
                bitAryB[i] = true;
                total += 1;
            }

            if(total > k) {
                return Optional.empty();
            }
        }

        //Second pass, flip bits that we can to reduce A to as small as possible
        for(int i = 0; i < maxBitAryLen; ++i) {
            boolean Ai = bitAryA[i];
            boolean Bi = bitAryB[i];
            boolean Ci = bitAryC[i];

            if(Ci && Ai && !Bi && total <= k - 2) {
                bitAryA[i] = false;
                bitAryB[i] = true;
                total += 2;
            }
            else if(Ci && Ai && Bi && total < k) {
                bitAryA[i] = false;
                total += 1;
            }
        }

        log("ResultA: " + booleanArrayToStr(bitAryA) + ": " + bitArrayToHexStr(bitAryA));
        log("ResultB: " + booleanArrayToStr(bitAryB) + ": " + bitArrayToHexStr(bitAryB));

        return Optional.of(new Result(bitAryA, bitAryB, total));
    }

    private static char bitsToHex(boolean[] bits) {
        if(bits.length != 4) {
            throw new IllegalArgumentException("must be 4 bits");
        }
        int total = 0;
        for(int i = bits.length - 1; i >= 0; --i) {
            if(bits[i]) {
                total += 1 << (3-i);
            }
        }
        if(total >= 0 && total <= 9) {
            return (char)('0' + total);
        }
        else {
            return (char)('A' + total - 10);
        }
    }

    public static StringBuilder removeZeros(StringBuilder str)
    {
        // Count leading zeros
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0')
            i++;

        // The  StringBuffer replace function removes
        // i characters from given index (0 here)
        str.replace(0, i, "");

        if(str.length() == 0) {
            str.append("0");
            return str;
        }
        return str;
    }

    private static String bitArrayToHexStr(boolean[] bitAry) {
        StringBuilder sb = new StringBuilder();
        boolean[] hexBits = new boolean[4];
        for(int i = 0; i < bitAry.length; i += 4) {
            System.arraycopy(bitAry, i, hexBits, 0, 4);
            sb.append(bitsToHex(hexBits));
        }
        return removeZeros(sb).toString();
    }

    @Test
    public void testSample() {
        log("toBitArray: " + booleanArrayToStr(new boolean[]{false, false, false, true}));

        for(char ch = '0'; ch <= '9'; ++ch) {
            boolean[] bitAry = getBitArrayFromHexCharacter(ch);
            log(ch + ": " + Arrays.toString(bitAry) + ", toBitArray: " + booleanArrayToStr(bitAry) + ", bitsToHex: " + bitsToHex(bitAry));
        }
        for(char ch = 'A'; ch <= 'F'; ++ch) {
            boolean[] bitAry = getBitArrayFromHexCharacter(ch);
            log(ch + ": " + Arrays.toString(bitAry) + ", toBitArray: " + booleanArrayToStr(bitAry) + ", bitsToHex: " + bitsToHex(bitAry));
        }

        Optional<Result> result = aOrB(12, "A42", "B", "CD332F");
        result.ifPresent(res -> {
            log("" + res);
        });

        log("Translate: " + bitArrayToHexStr(toBitArray("CD332F", 32)));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int k = Integer.parseInt(scanner.nextLine().trim());

            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String c = scanner.nextLine();

            Optional<Result> opt = aOrB(k, a, b, c);
            if(!opt.isPresent()) {
                System.out.println("-1");
            }
            else {
                Result res = opt.get();
                System.out.println(bitArrayToHexStr(res.A));
                System.out.println(bitArrayToHexStr(res.B));
            }
        }
    }
}