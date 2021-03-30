package com.toastedbits.clrs.chapter2;

import java.util.Random;

public class TestSorting {
	public static void printList(int[] list) {
		System.out.print("[");
		for(int i = 0; i < list.length-1; ++i)
			System.out.print(list[i] + ", ");
		System.out.println(list[list.length-1]+"]");
	}
	
	public static int[] randomArray(int length, int max) {
		Random r = new Random(System.currentTimeMillis());
		int[] A = new int[length];
		for(int i = 0; i < length; ++i)
			A[i] = r.nextInt(max);
		return A;
	}
	
	public static void testSorter(Sorter s) {
		int[] list = randomArray(9, 100);//{5, 3, 1, 6, 4, 2, 42};
		System.out.println(s);
		printList(list);
		printList(s.sort(list));
		System.out.println();
	}
	
	public static void main(String[] args) {
		//testSorter(new InsertionSort());
		//testSorter(new SelectionSort());
		//testSorter(new Problem2_1_2());
		testSorter(new MergeSort());
	}
}
