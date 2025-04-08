package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.adventofcode2024.IPuzzle;

public class Puzzle3 implements IPuzzle {

    @Override
    public void execute() {
        executeStep1();
        executeStep2();
    }

    private void executeStep1() {
        List<String> lines = getInputs("puzzle3_1.txt");
        List<String> allMatches = new ArrayList<>();
        for (String line : lines) {
            Matcher m = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)").matcher(line);
            while (m.find()) {
                allMatches.add(m.group());
            }
        }
        //displayAll(allMatches);
        int total = executeInstructions(allMatches);
        System.out.println("Response 1 = " + total + "\n");
    }

	private void executeStep2() {
        List<String> lines = getInputs("puzzle3_1.txt");
        List<String> allMatches = new ArrayList<>();
        for (String line : lines) {
            Matcher m = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)|don't\\(\\)|do\\(\\)").matcher(line);
            while (m.find()) {
                allMatches.add(m.group());
            }
        }

		allMatches = keepOnlyDoInstructions(allMatches);
        //displayAll(allMatches);
        int total = executeInstructions(allMatches);
        System.out.println("Response 2 = " + total + "\n");
	}

	List<String> keepOnlyDoInstructions(List<String> instructions) {
		List<String> finalList = new ArrayList<>();
		boolean keep = true;
		for(String instruction : instructions) {
			switch(instruction) {
				case "do()":
					keep = true;
					break;
				case "don't()":
					keep = false;
					break;
				default:
					if(keep) {
						finalList.add(instruction);
					}
			}
		}
		return finalList;
	}

	int executeInstruction(String op, String p1, String p2) {
		switch(op) {
			case "mul":
				return Integer.parseInt(p1) * Integer.parseInt(p2);
		}
		return 0;
	}

    int executeInstructions(List<String> instructions) {
        int result = 0;
        for (String instruction : instructions) {
            String sOp = instruction.substring(0, instruction.indexOf("("));
            String sP1 = instruction.substring(instruction.indexOf("(") + 1, instruction.indexOf(","));
            String sP2 = instruction.substring(instruction.indexOf(",") + 1, instruction.indexOf(")"));

			int temp = executeInstruction(sOp, sP1, sP2);
			result += temp;

			//System.out.println("[" + sOp + "][" + sP1 + "][" + sP2 + "] = " + temp);
		}

		return result ;
    }

    void displayAll(List<String> items) {
        String text = items.stream().map(Object::toString).collect(Collectors.joining(", "));
        System.out.println("Items : " + text + "\n");
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
