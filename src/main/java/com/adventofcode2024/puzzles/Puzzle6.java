package com.adventofcode2024.puzzles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.adventofcode2024.IPuzzle;

public class Puzzle6 implements IPuzzle {
//	int[] MEMORYBANK = {0,2,7,0};
	int[] MEMORYBANK = {10,3,15,10,5,15,5,15,9,2,5,8,5,2,3,6};
	
	public void execute() {
		executeStep1();
		executeStep2();
	}

	private void executeStep1() {
		int[] memorybanks = Arrays.copyOf(MEMORYBANK, MEMORYBANK.length);
		Set<String> encountered = new HashSet<String>();
		int counter = 0;
		for(;;) {
			int bigIdx = 0;
			encountered.add(Arrays.toString(memorybanks));
			for(int i = 0; i < memorybanks.length; i++) {
				if(memorybanks[i] > memorybanks[bigIdx]) {
					bigIdx = i;
				}
			}
			int toDistr = memorybanks[bigIdx];
			memorybanks[bigIdx] = 0;
			
			int curIdx = bigIdx + 1;
			for(int i = 0; i < toDistr; i++) {
				if(curIdx > memorybanks.length - 1) curIdx = 0;
				memorybanks[curIdx++]++;
			}
			counter++;
			if(encountered.contains(Arrays.toString(memorybanks)))
				break;
		}
		System.out.println("Counter = " + counter);
	}

	private void executeStep2() {
		int[] memorybanks = Arrays.copyOf(MEMORYBANK, MEMORYBANK.length);
		Set<String> encountered = new LinkedHashSet<String>();
		int counter = 0;
		int loop = 0;
		for(;;) {
			int bigIdx = 0;
			encountered.add(Arrays.toString(memorybanks));
			for(int i = 0; i < memorybanks.length; i++) {
				if(memorybanks[i] > memorybanks[bigIdx]) {
					bigIdx = i;
				}
			}
			int toDistr = memorybanks[bigIdx];
			memorybanks[bigIdx] = 0;
			
			int curIdx = bigIdx + 1;
			for(int i = 0; i < toDistr; i++) {
				if(curIdx > memorybanks.length - 1) curIdx = 0;
				memorybanks[curIdx++]++;
			}
			counter++;
			if(encountered.contains(Arrays.toString(memorybanks))) {
				for(String s : encountered) {
					if(loop > 0) loop++;
					if(s.equals(Arrays.toString(memorybanks))) {
						loop++;
					}
				}
				break;				
			}
		}
		System.out.println("Counter = " + counter + " / Loop = " + loop);
	}
}