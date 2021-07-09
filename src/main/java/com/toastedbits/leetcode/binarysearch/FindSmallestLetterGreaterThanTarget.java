package com.toastedbits.leetcode.binarysearch;

public class FindSmallestLetterGreaterThanTarget {
    private int binarySearch(char[] letters, char target) {
        if(letters[0] > target || letters[letters.length-1] <= target) {
            return 0;
        }

        int left = 1, right = letters.length;
        while(left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(letters[mid-1] <= target && (letters[mid] > target || mid == letters.length-1)) {
                return mid;
            }
            else if(letters[mid-1] <= target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return left;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int idx = binarySearch(letters, target);
        return idx == letters.length ? letters[0] : letters[idx];
    }

    public static void main(String[] args) {
        FindSmallestLetterGreaterThanTarget solver = new FindSmallestLetterGreaterThanTarget();
        System.out.println(solver.nextGreatestLetter(new char[]{'c','f','j'}, 'a') + " == c");
        System.out.println(solver.nextGreatestLetter(new char[]{'c','f','j'}, 'c') + " == f");
        System.out.println(solver.nextGreatestLetter(new char[]{'c','f','j'}, 'd') + " == f");
        System.out.println(solver.nextGreatestLetter(new char[]{'c','f','j'}, 'g') + " == j");
        System.out.println(solver.nextGreatestLetter(new char[]{'c','f','j'}, 'j') + " == c");
        System.out.println(solver.nextGreatestLetter(new char[]{'c','f','j'}, 'k') + " == c");

        System.out.println(solver.nextGreatestLetter(new char[]{'c','d','d','d','d','d','d','d','d','d'}, 'c') + " == d");
        System.out.println(solver.nextGreatestLetter(new char[]{'e','e','e','e','e','e','n','n','n','n'}, 'e') + " == n");
    }
}
