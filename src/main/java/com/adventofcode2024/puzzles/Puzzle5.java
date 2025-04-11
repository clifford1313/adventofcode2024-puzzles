package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adventofcode2024.IPuzzle;

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
                continue;
            }
            if (!inputFlag) {
                String[] ruleParts = rule.split("\\|");
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
                for (String page : rule.split(",")) {
                    currentPageList.add(Integer.parseInt(page));
                }
                prints.add(currentPageList);
            }
        }

        // Ici on a donc la liste *rules* qui contient les règles d'impression
        // et dans *prints* la liste des impressions à effectuer
        List<List<Integer>> validPrints = new ArrayList<>();
        for (List<Integer> print : prints) {
            if (isValidPrint(print, rules)) {
                validPrints.add(print);
            }
        }
        //System.out.println(validPrints);
        int total = 0;
        for (List<Integer> curValid : validPrints) {
            total += curValid.get((curValid.size() - 1) / 2);
        }
        System.out.println("Response 1 : " + total);
    }

    private void executeStep2() {
        Map<Integer, List<Integer>> rules = new HashMap<>();
        List<List<Integer>> prints = new ArrayList<>();
        List<String> inputs = getInputs("puzzle5_1.txt");
        boolean inputFlag = false;
        for (String rule : inputs) {
            if (rule.isEmpty()) {
                inputFlag = true;
                continue;
            }
            if (!inputFlag) {
                String[] ruleParts = rule.split("\\|");
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
                for (String page : rule.split(",")) {
                    currentPageList.add(Integer.parseInt(page));
                }
                prints.add(currentPageList);
            }
        }

        // Ici on a donc la liste *rules* qui contient les règles d'impression
        // et dans *prints* la liste des impressions à effectuer
        List<List<Integer>> invalidPrints = new ArrayList<>();
        for (List<Integer> print : prints) {
            if (!isValidPrint(print, rules)) {
                invalidPrints.add(print);
            }
        }

        int total = 0;
        for (List<Integer> print : invalidPrints) {
            boolean valid = false;
            List<Integer> temp = new ArrayList<>(print);
            while (!valid) {
                temp = swapIncorrectRule(temp, rules);
                valid = isValidPrint(temp, rules);
            }
            total += temp.get((temp.size() - 1) / 2);
        }

        System.out.println(
                "Response 2 : " + total);
    }

    boolean isValidPrint(List<Integer> print, Map<Integer, List<Integer>> rules) {
        for (int curIdx = 0; curIdx < print.size(); curIdx++) {
            List<Integer> rule = rules.get(print.get(curIdx));
            if (rule != null) {
                for (int curRule : rule) {
                    if (print.contains(curRule)) {
                        if (print.indexOf(curRule) < curIdx) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    List<Integer> swapIncorrectRule(List<Integer> print, Map<Integer, List<Integer>> rules) {
        int idxtoSwap1 = 0;
        int idxtoSwap2 = 0;
        for (int curIdx = 0; curIdx < print.size(); curIdx++) {
            List<Integer> rule = rules.get(print.get(curIdx));
            if (rule != null) {
                for (int curRule : rule) {
                    if (print.contains(curRule)) {
                        int secondIdx = print.indexOf(curRule);
                        if (secondIdx < curIdx) {
                            //We need to swap
                            idxtoSwap1 = secondIdx;
                            idxtoSwap2 = curIdx;
                        }
                    }
                }
            }
        }
        int save = print.get(idxtoSwap1);
        print.set(idxtoSwap1, print.get(idxtoSwap2));
        print.set(idxtoSwap2, save);
        return print;
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
