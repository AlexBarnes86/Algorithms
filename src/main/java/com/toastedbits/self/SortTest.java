package com.toastedbits.self;

//import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

//import static org.junit.jupiter.api.Assert.assertArrayEquals;

//Looking at no external resources, implement mergesort for integers
public class SortTest {
    private static void sort(int[] ary) {
        sort(ary, 0, ary.length / 2, ary.length-1);
    }

    private static void sort(int[] ary, int left, int mid, int right) {
//        System.out.format(
//            "%s, %d, %d, %d%n",
//            Arrays.toString(ary),
//            left, mid, right
//        );
        if(right <= left || left >= ary.length) {
            return;
        }

        int leftStart = left;
        int leftEnd = mid;
        int leftMid = leftStart + ((leftEnd - leftStart) / 2);

        int rightStart = mid + 1;
        int rightEnd = right;
        int rightMid = rightStart + ((rightEnd - rightStart) / 2);

        sort(ary, leftStart, leftMid, leftEnd);
        sort(ary, rightStart, rightMid, rightEnd);

        merge(ary, leftStart, leftEnd, rightStart, rightEnd);
    }

    private static void merge(int[] ary, int leftStart, int leftEnd, int rightStart, int rightEnd)  {
//        System.out.format("merge: %s, %d, %d, %d, %d%n",
//            Arrays.toString(ary),
//            leftStart, leftEnd,
//            rightStart, rightEnd
//        );
        int leftIdx = leftStart;
        int rightIdx = rightStart;
        int mergedIdx = 0;

        int[] merged = new int[leftEnd - leftStart + rightEnd - rightStart+2];

        while(leftIdx <= leftEnd && rightIdx <= rightEnd) {
            if(ary[leftIdx] < ary[rightIdx]) {
                merged[mergedIdx++] = ary[leftIdx++];
            }
            else {
                merged[mergedIdx++] = ary[rightIdx++];
            }
        }

        while(leftIdx <= leftEnd) {
            merged[mergedIdx++] = ary[leftIdx++];
        }

        while(rightIdx <= rightEnd) {
            merged[mergedIdx++] = ary[rightIdx++];
        }

//        System.out.format("merged: %s%n", Arrays.toString(merged));

        for(int i = 0; i < merged.length; ++i) {
            ary[i+leftStart] = merged[i];
        }
    }

    private static int[] merge(int[] leftAry, int [] rightAry)  {
        int[] merged = new int[leftAry.length + rightAry.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int mergedIdx = 0;

        while(leftIdx < leftAry.length && rightIdx < rightAry.length) {
            if(leftAry[leftIdx] < rightAry[rightIdx]) {
                merged[mergedIdx++] = leftAry[leftIdx++];
            }
            else {
                merged[mergedIdx++] = rightAry[rightIdx++];
            }
        }

        while(leftIdx < leftAry.length) {
            merged[mergedIdx++] = leftAry[leftIdx++];
        }

        while(rightIdx < rightAry.length) {
            merged[mergedIdx++] = rightAry[rightIdx++];
        }

        return merged;
    }

    private static final Random random = new Random(System.currentTimeMillis());

    private static int[] randomIntArray(int size, int min, int max) {
        int[] ary = new int[size];
        for(int i = 0; i < size; ++i) {
            ary[i] = random.nextInt(max - min) + min;
        }
        return ary;
    }

//    @Test
    public void testMergeOld() {
        int[] left = new int[0];
        int[] right = new int[0];

        for(int testCt = 1; testCt <= 100; ++testCt) {
            int [] merged = merge(left, right);
            System.out.format(
                "%d: merge(%s, %s) = %s%n", testCt++,
                Arrays.toString(left),
                Arrays.toString(right),
                Arrays.toString(merged)
            );

            int[] expected = IntStream.concat(Arrays.stream(left), Arrays.stream(right)).toArray();
            Arrays.sort(expected);

//            assertArrayEquals(expected, merged);

            left = randomIntArray(random.nextInt(10), 0, 5);
            right = randomIntArray(random.nextInt(10), 0, 5);

            Arrays.sort(left);
            Arrays.sort(right);
        }
    }

//    @Test
    public void testSort() {
        for(int testCt = 1; testCt <= 10000; ++testCt) {
            int[] orig = randomIntArray( random.nextInt(50), -100, 100);
            int[] sorted = Arrays.copyOf(orig, orig.length);
            sort(sorted);

            System.out.format(
                "%d: sort(%s) = %s%n", testCt++,
                Arrays.toString(orig),
                Arrays.toString(sorted)
            );

            int[] expected = Arrays.copyOf(orig, orig.length);
            Arrays.sort(expected);

//            assertArrayEquals(expected, sorted);
        }
    }
}
