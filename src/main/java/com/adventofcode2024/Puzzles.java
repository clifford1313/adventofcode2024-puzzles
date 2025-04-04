package com.adventofcode2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.adventofcode2024.puzzles.*;

/**
 * Hello world!
 *
 */
public class Puzzles {
	static ArrayList<IPuzzle> puzzles = new ArrayList<IPuzzle>();

	public static void main(String[] args) {
		String sPuzzle = null;
		int iPuzzle = 0;
		IPuzzle puzzleToExe = null;
		
		puzzles.add(new Puzzle1());
		puzzles.add(new Puzzle2());
		puzzles.add(new Puzzle3());
		puzzles.add(new Puzzle4());
		puzzles.add(new Puzzle5());
		puzzles.add(new Puzzle6());
		puzzles.add(new Puzzle7());
		
		System.out.println("Puzzle ? ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			sPuzzle = reader.readLine();
		} catch (IOException e) {}
		
		try {
			iPuzzle = Integer.parseInt(sPuzzle);
			if(iPuzzle < 1) throw new NumberFormatException();
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid entry\n");
			System.exit(1);
		}
		
		try {
			puzzleToExe = puzzles.get(iPuzzle - 1);
		}catch(Exception e) {}
		
		if(puzzleToExe == null) {
			System.out.println("Puzzle does not exist\n");
			System.exit(2);
		}
		
		puzzleToExe.execute();
	}
}
