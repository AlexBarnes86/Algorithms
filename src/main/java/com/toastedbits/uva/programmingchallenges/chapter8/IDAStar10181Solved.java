package com.toastedbits.uva.programmingchallenges.chapter8;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

class IDAStar10181Solved {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = true;
	public static final Scanner sc = getScanner();
	
	static final int INFINITE = Integer.MAX_VALUE;
	static final int WIDTH = 4; // ROW_SIZE is a matrix of 4 x 4
	static final int WIDTH_SQUARED = (WIDTH*WIDTH);
	static final int MAX_NUMBER = WIDTH_SQUARED - 1;
	static final int MAX_DEPTH = 45;
	
	static int[] board = new int[WIDTH_SQUARED];
	static int limit, nLimit;
	static int[] dr = new int[] { 0,-1, 0, 1}; // E,N,W,S
	static int[] dc = new int[] { 1, 0,-1, 0}; // R,U,L,D
	static TreeMap<Integer, Integer> predicate = new TreeMap<Integer, Integer>();
	static TreeMap<Long, Integer> visited = new TreeMap<Long, Integer>();
	static char[] answer = new char[] {'R', 'U', 'L', 'D'};
	
	static int manhattanHeuristic() { // heuristic: sum of Manhattan distances (compute all)
		int ans = 0;
		
		for (int i = 0; i < WIDTH_SQUARED; i++) {
			int targetRow = board[i] / WIDTH, targetCol = board[i] % WIDTH;
			if (board[i] != MAX_NUMBER) {
				ans += Math.abs(i / WIDTH - targetRow) + Math.abs(i % WIDTH - targetCol); // Manhattan distance
			}
		}
		return ans;
	}
	
	static int h2(int r1, int c1, int r2, int c2) { // heuristic: sum of manhattan distances (compute delta)
		int tgt_i = board[r2 * WIDTH + c2] / WIDTH, tgt_j = board[r2 * WIDTH + c2] % WIDTH;
		return -(Math.abs(r2 - tgt_i) + Math.abs(c2 - tgt_j)) + (Math.abs(r1 - tgt_i) + Math.abs(c1 - tgt_j));
	}
	
	static boolean isSolution() {
		for (int i = 0; i < WIDTH_SQUARED; i++) {
			if (board[i] != MAX_NUMBER && board[i] != i) {
				return false;
			}
		}
		
		return true;
	}
	
	static boolean valid(int r, int c) {
		return 0 <= r && r < WIDTH && 0 <= c && c < WIDTH;
	}
	
	static void swap(int i, int j, int new_i, int new_j) {
		int temp = board[i * WIDTH + j];
		board[i * WIDTH + j] = board[new_i * WIDTH + new_j];
		board[new_i * WIDTH + new_j] = temp;
	}
	
	static boolean DFS(int g, int h) {
		if (g + h > limit) {
			nLimit = Math.min(nLimit, g + h);
			return false;
		}
		
		if (isSolution()) {
			return true;
		}
		
		long state = 0;
		for (int i = 0; i < WIDTH_SQUARED; i++) { // transform 16 numbers into 64 bits, exactly into ULL
			state <<= WIDTH; // move left 4 bits
			state += board[i]; // add this digit (max 15 or 1111)
		}
		
		if (visited.containsKey(state) && visited.get(state) <= g) { // not pure backtracking... this is to prevent cycling
			return false; // not good
		}
		visited.put(state, g); // mark this as visited
		
		int idx, d, nRow, nCol;
		for (idx = 0; idx < WIDTH_SQUARED; idx++) {
			if (board[idx] == MAX_NUMBER) {
				break;
			}
		}
		
		int r = idx / WIDTH;
		int c = idx % WIDTH;
		
		for (d = 0; d < WIDTH; d++) {
			nRow = r + dr[d];
			nCol = c + dc[d];
			if (valid(nRow, nCol)) {
				int dh = h2(r, c, nRow, nCol);
				swap(r, c, nRow, nCol); // swap first
				predicate.put(g + 1, d);
				if (DFS(g + 1, h + dh)) { // if ok, no need to restore, just go ahead
					return true;
				}
				swap(r, c, nRow, nCol); // restore
			}
		}
		
		return false;
	}
	
	static int IDA_Star() {
		limit = manhattanHeuristic();
		while (true) {
			nLimit = INFINITE; // next limit
			predicate.clear();
			visited.clear();
			if (DFS(0, manhattanHeuristic())) {
				return limit;
			}
			if (nLimit == INFINITE) {
				return -1;
			}
			limit = nLimit; // nlim > lim
			if (limit > MAX_DEPTH) { // pruning condition in the problem
				return -1;
			}
		}
	}
	
	static void output(int d) {
		if (d == 0) {
			return;
		}
		output(d - 1);
		System.out.printf("%c", answer[predicate.get(d)]);
	}
	
	public static String boardToString(int[] p) {
		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < WIDTH; ++r) {
			for(int c = 0; c < WIDTH; ++c) {
				sb.append(p[r * WIDTH + c] + " ");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append("\n");
		}
		sb.delete(sb.length()-1, sb.length());
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		int N = sc.nextInt();
		for(int n = 0; n < N; ++n) {
			int r, c, blank = 0, sum = 0, ans = 0;
			for (r = 0; r < WIDTH; r++) {
				for (c = 0; c < WIDTH; c++) {
					board[r * WIDTH + c] = sc.nextInt();
					if (board[r * WIDTH + c] == 0) {
						board[r * WIDTH + c] = MAX_NUMBER; // intentionally change to MAX_NUMBER
						blank = r * WIDTH + c; // and remember the index
					}
					else {
						board[r * WIDTH + c]--; // use 0-based indexing
					}
				}
			}
			
			for (r = 0; r < WIDTH_SQUARED; r++) {
				for (c = 0; c < r; c++) {
					if (board[r] != MAX_NUMBER && board[c] != MAX_NUMBER && board[c] > board[r]) {
						sum++;
					}
				}
			}
			
			sum += blank / WIDTH;
			
			if (sum % 2 != 0 && ((ans = IDA_Star()) != -1)) {
				output(ans);
				System.out.printf("\n");
			}
			else {
				System.out.printf("This puzzle is not solvable.\n");
			}
		}
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter8/Problem10181.in";
				Scanner sc = new Scanner(new File(fileName));
				System.out.println("Reading from: " + fileName);
				return sc;
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
}