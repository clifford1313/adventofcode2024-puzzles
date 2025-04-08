package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adventofcode2024.IPuzzle;
import com.sun.jdi.IntegerType;

public class Puzzle5 implements IPuzzle {

    @Override
    public void execute() {
        executeStep1();
        executeStep2();
    }

    private void executeStep1() {
        Map<Integer, List<Integer>> rules = new HashMap<>();
		List<List<Integer>> prints = new ArrayList<>();
        List<String> inputs = getInputs("puzzle5_1.txt");
        boolean inputFlag = false;
        for (String rule : inputs) {
            if (rule.isEmpty()) {
				inputFlag = true;
                break;
            }
            if (!inputFlag) {
                String[] ruleParts = rule.split("|");
                int ruleKey = Integer.parseInt(ruleParts[0]);
                int ruleValue = Integer.parseInt(ruleParts[1]);

                List<Integer> values = rules.get(ruleKey);
                if (values == null) {
                    List<Integer> temp = new ArrayList<>();
                    rules.put(ruleKey, temp);
                    values = temp;
                }
                values.add(ruleValue);
            } else {
				List<Integer> currentPageList = new ArrayList<>();
				for(String page : rule.split(",")) {
					currentPageList.add(Integer.parseInt(page));
				}
				prints.add(currentPageList);
			}
        }

		// Ici on a donc la liste *rules* qui contient les règles d'impression
		// et dans *prints* la liste des impressions à effectuer
		

    }

    private void executeStep2() {
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
