package com.toastedbits.clrs.chapter2;

public class Problem2_1_4 {
	public static void printArray(int[] ary) {
		System.out.print("[");
		for(int i = 0; i < ary.length-1; ++i)
			System.out.print(ary[i] + ", ");
		System.out.println(ary[ary.length-1]+"]");
	}
	
	public static void main(String[] args) {
		int A[] = {0, 1, 1, 0, 1};
		int B[] = {1, 1, 0, 1, 1};
		int C[] = new int[A.length+1];
		
		for(int i = 0; i < C.length; ++i) //Zero memory
			C[i] = 0;
		
		int carry = 0;
		int sum = 0;
		for(int i = A.length-1; i >= 0; --i) {
			if(carry == 1) {
				if(A[i] == 1 && B[i] == 1) {
					carry = 1;
					sum = 1;
				}
				else if(A[i] == 1 || B[i] == 1) {
					carry = 1;
					sum = 0;
				}
				else {
					carry = 0;
					sum = 1;
				}
			}
			else {
				if(A[i] == 1 && B[i] == 1) {
					carry = 1;
					sum = 0;
				}
				else if(A[i] == 1 || B[i] == 1) {
					carry = 0;
					sum = 1;
				}
				else {
					carry = 0;
					sum = 0;
				}
			}
			C[i+1] = sum;
		}
		if(carry == 1) {
			C[0] = 1;
		}
		
		System.out.print("   ");printArray(A);
		System.out.print("   ");printArray(B);
		System.out.println("--------------------");
		printArray(C);
	}
}
