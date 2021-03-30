package com.toastedbits.clrs.chapter2;

//solution to 2.3-5
public class BinarySearch {
	private static int indexOf(int[] A, int key, int min, int max) {
		int mid = min+(max-min)/2;
		if(min > max)
			return -1;
		else if(A[mid] == key)
			return mid;
		else if(A[mid] > key)
			return indexOf(A, key, min, mid);
		else
			return indexOf(A, key, mid+1, max);
	}
	
	public static int indexOf(int[] A, int key) {
		return indexOf(A, key, 0, A.length-1);
	}
	
	public static void main(String[] args) {
		int[] ary = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
		System.out.println(indexOf(ary, 2));
		System.out.println(indexOf(ary, 42));
		System.out.println(indexOf(ary, 30));
	}
}
