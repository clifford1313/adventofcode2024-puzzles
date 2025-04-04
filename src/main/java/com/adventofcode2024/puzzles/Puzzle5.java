package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.adventofcode2024.IPuzzle;

public class Puzzle5 implements IPuzzle {
	@Override
	public void execute() {
		executeStep1();
		executeStep2();
	}

	private void executeStep1() {
		int[] instructions = readProgram();
		int pc = 0;
		int steps = 0;

		for(;;) {
			int jump = instructions[pc];
			instructions[pc]++;
			pc += jump;
			steps++;
			if((pc < 0) || (pc >= instructions.length))
				break;
		}
		System.out.println("Number of steps : " + steps);
	}

	private void executeStep2() {
		int[] instructions = readProgram();
		int pc = 0;
		int steps = 0;

		for(;;) {
			int jump = instructions[pc];
			if(jump >= 3) {
				instructions[pc]--;
			} else {
				instructions[pc]++;
			}
			pc += jump;
			steps++;
			if((pc < 0) || (pc >= instructions.length))
				break;
		}
		System.out.println("Number of steps : " + steps);
	}

	int[] readProgram() {
		int[] instructions = null;
		File file = getResourceFile("puzzle5_1.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			instructions = br.lines().mapToInt(Integer::parseInt).toArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instructions;

	}

}