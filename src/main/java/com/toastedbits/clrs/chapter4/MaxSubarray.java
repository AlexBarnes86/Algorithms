package com.toastedbits.clrs.chapter4;

public class MaxSubarray {
	public static class Tuple {
		public Tuple(int l, int r, int s) {
			left = l;
			right = r;
			sum = s;
		}
		public Tuple() {left = right = sum = 0;}
		public int left;
		public int right;
		public int sum;
	}
	
	public static Tuple findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
		int left_sum = Integer.MIN_VALUE;
		int max_left = high;
		int sum = 0;
		for(int i = mid; i >= low; --i) {
			sum += A[i];
			if(sum > left_sum) {
				left_sum = sum;
				max_left = i;
			}
		}
		int right_sum = Integer.MIN_VALUE;
		int max_right = low;
		sum = 0;
		for(int j = mid+1; j <= high; ++j) {
			sum += A[j];
			if(sum > right_sum) {
				right_sum = sum;
				max_right = j;
			}
		}
		return new Tuple(max_left, max_right, left_sum + right_sum);
	}
	
	public static Tuple findMaxSubarray(int[] A, int low, int high) {
		if(high == low)
			return new Tuple(low, high, A[low]);
		else {
			int mid = (int)Math.floor((low+high)/2);
			Tuple leftTuple = findMaxSubarray(A, low, mid);
			Tuple rightTuple = findMaxSubarray(A, mid+1, high);
			Tuple crossTuple = findMaxCrossingSubarray(A, low, mid, high);
			if(leftTuple.sum >= rightTuple.sum && leftTuple.sum >= crossTuple.sum)
				return leftTuple;
			else if(rightTuple.sum >= leftTuple.sum && rightTuple.sum >= crossTuple.sum)
				return rightTuple;
			else
				return crossTuple;
		}
	}
	
	public static void testFindMaxCrossingSubarray() {
		int A[] = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
		int B[] = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
		A = B;
		Tuple t = findMaxCrossingSubarray(A, 0, A.length/2, A.length-1);
		System.out.println("Left: " + t.left + " Right: " + t.right + " Sum: " + t.sum);
	}
	
	public static void testFindMaxSubarray() {
		int A[] = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
		int B[] = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
		int C[] = {4, -1, 2, 1, -2, 1, -3, -5, 4};
		int D[] = {-4, -1, -2, -1, -2, -1, -3, -5, -4};
		A = D;
		Tuple t = findMaxSubarray(A, 0, A.length-1);
		System.out.println("Left: " + t.left + " Right: " + t.right + " Sum: " + t.sum);
	}
	
	public static int kadanes(int[] A) {
		int max_so_far = 0, max_ending_here = 0;
		for(int i = 0; i < A.length; ++i) {
			int x = A[i];
			max_ending_here = Math.max(0, max_ending_here+x);
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		return max_so_far;
	}
	
	public static void testKadanes() {
		int C[] = {4, -1, 2, 1, -2, 1, -3, -5, 4};
		System.out.println("Max Subarray: " + kadanes(C));
	}
	
	public static void main(String [] args) {
		testFindMaxCrossingSubarray();
		testFindMaxSubarray();
		testKadanes();
	}
}
