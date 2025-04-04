package com.adventofcode2024.puzzles;

import java.util.HashMap;

import com.adventofcode2024.IPuzzle;

public class Puzzle3 implements IPuzzle {
	private final int INPUT = 312051;
//	private final int INPUT = 37;
	@Override
	public void execute() {
		executeStep1();
		executeStep2();
	}
	
	private void executeStep2() {
		int sq[][] = new int[100][100];
		int x = 50;
		int y = 50;
		int cursqsz = 3;
		
		sq[x][y] = 1;

		try {
			for (;;) {
				x += 1;
				sq[x][y] = addAll(sq, x, y);
				for (int i = 1; i < cursqsz - 1; i++) {
					y += 1;
					sq[x][y] = addAll(sq, x, y);
				}
				for (int i = 1; i < cursqsz; i++) {
					x -= 1;
					sq[x][y] = addAll(sq, x, y);
				}
				for (int i = 1; i < cursqsz; i++) {
					y -= 1;
					sq[x][y] = addAll(sq, x, y);
				}
				for (int i = 1; i < cursqsz; i++) {
					x += 1;
					sq[x][y] = addAll(sq, x, y);
				}
				cursqsz += 2;
//				printSq(sq);
			}
		} catch(Exception e) {
			System.out.println("Result = " + e.getMessage());
		}
		
	}
	
	private void printSq(int[][] sq) {
		System.out.println();
		for(int y = 0; y < 100; y++) {
			for(int x = 0; x < 100; x++) {
				System.out.print(String.format("%5d", sq[x][y]));
			}
			System.out.println();
		}
	}
	
	private int addAll(int[][] sq, int x, int y) throws Exception {
		int value = 0;
		value += sq[x-1][y+1];
		value += sq[x][y+1];
		value += sq[x+1][y+1];
		value += sq[x-1][y];
		value += sq[x+1][y];
		value += sq[x-1][y-1];
		value += sq[x][y-1];
		value += sq[x+1][y-1];
		if(value >= INPUT) {
			throw new Exception(new Integer(value).toString());
		}
		return value;
	}
	
	private void executeStep1() {
		//determine which square is the good one
		int squarenb = 1;
		int squaresize = 0;
		for(int i = 2;;i+=2) {
			if(i * i > INPUT) {
				break;
			}
			squarenb++;
			squaresize = i;
		}
		
		System.out.println("Square nb = " + squarenb + " - Square size = " + squaresize);
		
		//determine position of INPUT in the square
		int sc1 = squaresize * squaresize;
		int sc2 = 0;
		int inputpos = 0;
		for(int i = 0; i < 4; i++) {
			sc2 = sc1 + squaresize;
			if(sc1 < INPUT && sc2 > INPUT) {
				int mid = (sc1 + sc2) / 2;
				inputpos = Math.abs(INPUT - mid);
				break;
			}
			sc1 = sc2;
		}
		
		System.out.println("Result = " + (inputpos + squarenb));
	}
}
