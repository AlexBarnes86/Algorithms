package com.toastedbits.clrs.chapter2;

public class InsertionSort implements Sorter{
	public int[] sort(int[] list) {
		for(int j = 1; j < list.length; ++j) {
			int key = list[j];
			int i = j-1;
			while(i >= 0 && list[i] > key) {
				list[i+1] = list[i];
				i--;
			}
			list[i+1] = key;
		}
		return list;
	}
	
	public String toString() {
		return "Insertion Sort";
	}
}
