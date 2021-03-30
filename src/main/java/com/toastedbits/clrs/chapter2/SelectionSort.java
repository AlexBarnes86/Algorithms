package com.toastedbits.clrs.chapter2;

public class SelectionSort implements Sorter {
	public int[] sort(int[] list) {
		for(int i = 0; i < list.length-1; ++i) {
			int min = Integer.MAX_VALUE;
			int min_idx = i;
			for(int j = i; j < list.length; ++j) {
				if(list[j] < min) {
					min = list[j];
					min_idx = j;
				}
			}
			int temp = list[min_idx];
			list[min_idx] = list[i];
			list[i] = temp;
		}
		return list;
	}
	
	public String toString() {
		return "Selection Sort";
	}
}
