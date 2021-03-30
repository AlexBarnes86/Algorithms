package com.toastedbits.clrs.chapter2;

public class MergeSort implements Sorter {
	public static void printList(int[] list) {
		System.out.print("[");
		for(int i = 0; i < list.length-1; ++i)
			System.out.print(list[i] + ", ");
		System.out.println(list[list.length-1]+"]");
	}
	
	private int[] problem_2_3_2_merge(int[] A, int p, int q, int r) {
		int n1 = q-p+1;
		int n2 = r-q;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for(int i = 0; i < n1; ++i)
			L[i] = A[p+i];
		for(int j = 0; j < n2; ++j)
			R[j] = A[q+j+1];
		int i = 0, j = 0, k = p;
		for(; k <= r; ++k) {
			if(i == n1 || j == n2)
				break;
			if(L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			}
			else {
				A[k] = R[j];
				j++;
			}
		}
		if(i == n1) {
			for(; j < n2; j++) {
				A[k] = R[j];
				k++;
			}
		}
		else {
			for(; i < n1; i++) {
				A[k] = L[i];
				k++;
			}
		}
		return A;
	}
	
	private int[] merge(int[] A, int p, int q, int r) {
		int n1 = q-p+1;
		int n2 = r-q;
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		for(int i = 0; i < n1; ++i)
			L[i] = A[p+i];
		L[n1] = Integer.MAX_VALUE;
		for(int j = 0; j < n2; ++j)
			R[j] = A[q+j+1];
		R[n2] = Integer.MAX_VALUE;
		int i = 0, j = 0;
		for(int k = p; k <= r; ++k) {
			if(L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			}
			else {
				A[k] = R[j];
				j++;
			}
		}
		return A;
	}
	
	private int[] sort(int[] A, int p, int r) {
		if(p < r) {
			int q = (p+r)/2;
			sort(A, p, q);
			sort(A, q+1, r);
			problem_2_3_2_merge(A, p, q, r);
		}
		return A;
	}
	
	public int[] sort(int [] A) {
		return sort(A, 0, A.length-1);
	}
	
	public String toString() {
		return "Merge Sort";
	}
//	
//	private static void testMerge(MergeSort m) {
//		int[] A = {1,4,5,8,2,3,6,7};
//		TestSorting.printList(A);
//		TestSorting.printList(m.merge(A, 0, 3, A.length-1));
//	}
//	
//	private static void testSort(MergeSort m) {
//		int[] A = {1,4,42,5,6,2,3,7,8,42};
//		TestSorting.printList(A);
//		TestSorting.printList(m.sort(A));
//	}
//	
//	public static void main(String[] args) {
//		MergeSort m = new MergeSort();
//		//testMerge(m);
//		testSort(m);
//	}
}
