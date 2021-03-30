package com.toastedbits.clrs.chapter2;

public class Problem2_1_3 {
	public static int indexOf(int[] A, int key) {
		for(int i = 0; i < A.length; ++i)
			if(A[i] == key)
				return i;
		return -1;
	}
	
	public static void main(String[] args) {
		int[] list = {5,3,6,1,2,4};
		System.out.println(indexOf(list, 6));
		System.out.println(indexOf(list, 42));
	}
}

/*
 * Loop Invariant:
 * 	The subarray A[0..i-1] does not contain key
 * 
 * Initialization:
 * 	i = 0, the subarray is empty and cannot therefore contain they key
 * 
 * Maintenance:
 * 	during each iteration of the loop we check for A[i] == key
 * 	if so we return i (an index in the array)
 * 
 * Termination:
 * 	if we reach the end of the array, i == A.length and the subarray is equal
 * 	to the entire array which does not contain the key
 * 	we therefore return -1 as a sentinel (false negative index)
 */