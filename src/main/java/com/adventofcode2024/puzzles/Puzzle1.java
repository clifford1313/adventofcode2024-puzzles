package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adventofcode2024.IPuzzle;

public class Puzzle1 implements IPuzzle {

	@Override
	public void execute() {
		execute1();
		execute2();
	}

	public void execute1() {
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();

		List<String> lines = getInputs("puzzle1_1.txt");
		for(String l : lines) {
			String[] landr = l.split("   ");
			//System.out.println(landr[0] + "-" + landr[1]);
			left.add(Integer.parseInt(landr[0]));
			right.add(Integer.parseInt(landr[1]));
		}
		Collections.sort(left);
		Collections.sort(right);

		int total = 0;
		for(int i = 0; i < left.size(); i++) {
			total += Math.abs(left.get(i) - right.get(i));
		}
		System.out.println("Response part1 = " + total);
		return;
	}

	public void execute2() {
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();

		List<String> lines = getInputs("puzzle1_1.txt");
		for(String l : lines) {
			String[] landr = l.split("   ");
			//System.out.println(landr[0] + "-" + landr[1]);
			left.add(Integer.parseInt(landr[0]));
			right.add(Integer.parseInt(landr[1]));
		}

		int total = 0;
		for(int i = 0; i < left.size(); i++) {
			int nbOccurence = 0;
			int value = left.get(i);
			for(int j = 0; j < right.size(); j++) {
				if(right.get(j) == value) {
					nbOccurence++;
				}
			}
			total += value * nbOccurence;
		}
		System.out.println("Response part2 = " + total);
		return;
	}

	ArrayList<String> getInputs(String filename) {
		ArrayList<String> lines = null;
		try {
			File file = getResourceFile(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));
			lines = new ArrayList<String>(br.lines().toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
