package com.toastedbits.uva.programmingchallenges.chapter8;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problem10128 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	private static int total = 0;
	
	enum Direction {
		LEFT, RIGHT;
	}
	
	private static int countPeople(List<Integer> a, Direction d) {
		int inc = -1;
		int start = a.size()-1;
		int end = -1;
		
		if(d == Direction.LEFT) {
			inc = 1;
			start = 0;
			end = a.size();
		}
		
		int max = -1;
		
		int ct = 0;
		for(int i = start; i != end; i += inc) {
			if(a.get(i) > max) {
				ct++;
				max = a.get(i);
			}
		}
		
		return ct;
	}
	
	public static List<Integer> range(int a, int b) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = a; i < b; ++i) {
			list.add(i);
		}
		return list;
	}
	
	private static List<Integer> candidates(List<Integer> a, int N, int P, int R) {
		List<Integer> cList;
		
		if(a.size() < P -2 || a.size() > N-R+1) {
			cList = range(0, N-2);
		}
		else {
			cList = range(0, N);
		}
		
		cList.removeAll(a);
		return cList;
	}
	
	private static void solve(List<Integer> a, int N, int P, int R) {
		int left = countPeople(a, Direction.LEFT);
		int right = countPeople(a, Direction.RIGHT);
		
		if(left > P || (a.size() == N && right > R)) {
			return;
		}
		else if(a.size() == N && left == P && right == R) {
			total++;
		}
		else {
			List<Integer> candidateList = candidates(a, N, P, R);
			for(Integer c : candidateList) {
				List<Integer> successor = new ArrayList<Integer>(a);
				successor.add(c);
				solve(successor, N, P, R);
			}
		}
	}
	
	static class Tuple {
		public Tuple(int N, int P, int R) {
			this.N = N;
			this.P = P;
			this.R = R;
		}
		public int N, P, R;
		
		@Override
		public boolean equals(Object o) {
			Tuple t = (Tuple)o;
			return N == t.N && P == t.P && R == t.R;
		}
		
		@Override
		public int hashCode() {
			return N * P * R;
		}
	}
	static Map<Tuple, Integer> memo = new HashMap<Tuple, Integer>();
	
	public static long solve(int N, int P, int R) {
		int x = P, y = R;
		if(R < P) {
			x = R;
			y = P;
		}
		
		Tuple key = new Tuple(N, x, y);
		if(!memo.containsKey(key)) {
			total = 0;
			solve(new ArrayList<Integer>(), N, x, y);
			memo.put(key, total);
		}
		
		return memo.get(key);
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		for(int N = 1; N < 13; ++N) {
			for(int P = 1; P < N; ++P) {
				for(int R = 1; R < N; ++R) {
					System.out.println("(" + N + ", " + P + ", " + R + ") = " + solve(N, P, R));
				}
			}
		}
		
//		debug(solve(10, 4, 4));
//		debug(solve(10, 4, 4));
//		debug(solve(10, 5, 3));
//		debug(solve(10, 3, 5));
		
//		long startTime = System.currentTimeMillis();
//		debug(solve(10,4,4));
//		debug(System.currentTimeMillis() - startTime);
		
//		int T = sc.nextInt();
//		for(int t = 0; t < T; ++t) {
//			pw.println(solve(sc.nextInt(), sc.nextInt(), sc.nextInt()));
//		}
		
		pw.flush();
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
				String fileName = "ProgrammingChallenges/input/chapter8/Problem10128.in";
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