package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adventofcode2024.IPuzzle;

public class Puzzle2 implements IPuzzle {

	@Override
	public void execute() {
		execute1();
		execute2();
	}

	public void execute1() {
		List<String> lines = getInputs("puzzle2_1.txt");
		int safe = 0;
		for(String l : lines) {
			String[] sElements = l.split(" ");
			List<Integer> iElements = new ArrayList<>();
			for(String sElement : sElements) {
				iElements.add(Integer.parseInt(sElement));
			}
			if(!isListAscendingOrDescending(iElements) || !areElementsDistantForMoreThanOneAndLessThanThree(iElements)) {
				continue;
			}			
			safe++;
		}
		System.out.println("Response part1 = " + safe);
		return;
	}

	public void execute2() {
		List<String> lines = getInputs("puzzle2_1.txt");
		int safe = 0;
		for(String l : lines) {
			String[] sElements = l.split(" ");
			List<Integer> iElements = new ArrayList<>();
			for(String sElement : sElements) {
				iElements.add(Integer.parseInt(sElement));
			}
			if(isListSafe(iElements)) {
				safe++;
			} else {
				//Let's try by removing 1 element at a time

				for(int i = 0; i < iElements.size(); i++) {
					List<Integer> tempList = new ArrayList<>(iElements);
					tempList.remove(i);
					if(isListSafe(tempList)) {
						safe++;
						break;
					}
				}
			}
		}
		System.out.println("Response part1 = " + safe);
		return;
	}

	boolean isListSafe(List<Integer> list) {
		return isListAscendingOrDescending(list) && areElementsDistantForMoreThanOneAndLessThanThree(list);
	}

	boolean areElementsDistantForMoreThanOneAndLessThanThree(List<Integer> list) {
		int previous = list.get(0);
		for(int i = 1; i < list.size(); i++) {
			int current = list.get(i);
			if(Math.abs(current - previous) < 1 || Math.abs(current - previous) > 3) {
				return false;
			}
			previous = current;
		}
	return true;
	}

	boolean isListAscendingOrDescending(List<Integer> list) {
		List<Integer> iElementsAsc = new ArrayList<>(list);
		List<Integer> iElementsDesc = new ArrayList<>(list);

		Collections.sort(iElementsAsc);
		Collections.sort(iElementsDesc, Collections.reverseOrder());
		return !(!list.equals(iElementsAsc) && !list.equals(iElementsDesc));
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
