package com.adventofcode2017.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.adventofcode2017.IPuzzle;

public class Puzzle4 implements IPuzzle {
	@Override
	public void execute() {
		executeStep1();
		executeStep2();
	}

	private void executeStep1() {
		int nbValid = 0;
		try {
			File file = getResourceFile("puzzle4_1.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String curLine = "";
			
			while ((curLine = br.readLine()) != null) {
				Set<String> words = new HashSet<String>();
				String[] lineWords = curLine.split(" ");
				boolean valid = true;
				for (String curWord : lineWords) {
					if (words.contains(curWord)) {
						valid = false;
						break;
					} 
					words.add(curWord);
				}
				if(valid) {
					nbValid++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Number of valid entries : " + nbValid);
	}

	private void executeStep2() {
		int nbValid = 0;
		try {
			File file = getResourceFile("puzzle4_2.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String curLine = "";
			
			while ((curLine = br.readLine()) != null) {
				Set<String> words = new HashSet<String>();
				String[] lineWords = curLine.split(" ");
				boolean valid = true;
				for (String curWord : lineWords) {
					curWord = sortString(curWord);
					if (words.contains(curWord)) {
						valid = false;
						break;
					} 
					words.add(curWord);
				}
				if(valid) {
					nbValid++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Number of valid entries : " + nbValid);
	}
	
	String sortString(String string) {
		char[] chars = string.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}