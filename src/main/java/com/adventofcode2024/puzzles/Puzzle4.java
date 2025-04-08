package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.adventofcode2024.IPuzzle;

public class Puzzle4 implements IPuzzle {

    @Override
    public void execute() {
        executeStep1();
        executeStep2();
    }

    private void executeStep1() {
        int total = 0;
        char[][] lines = getInputs("puzzle4_1.txt");
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length; j++) {
                total += findNbOfXMASFromThisPosition(lines, i, j);
            }
        }
        System.out.println("Response 1 = " + total + "\n");
    }

    private void executeStep2() {
        int total = 0;
        char[][] lines = getInputs("puzzle4_1.txt");
        for (int i = 1; i < lines.length - 1; i++) {
            for (int j = 1; j < lines[i].length - 1; j++) {
                total += findCrossXMASSFromThisPosition(lines, i, j);
            }
        }
        System.out.println("Response 2 = " + total + "\n");
    }

	int findCrossXMASSFromThisPosition(char[][] letters, int x, int y) {
		int found = 0;
		if(letters[x][y] != 'A') {
			return 0;
		}
		if(letters[x - 1][y - 1] == 'M' && letters[x + 1][y + 1] == 'S' && letters[x + 1][y - 1] == 'M' && letters[x - 1][y + 1] == 'S') {
			found++;
		}
		if(letters[x - 1][y - 1] == 'M' && letters[x + 1][y + 1] == 'S' && letters[x + 1][y - 1] == 'S' && letters[x - 1][y + 1] == 'M') {
			found++;
		}
		if(letters[x - 1][y - 1] == 'S' && letters[x + 1][y + 1] == 'M' && letters[x + 1][y - 1] == 'M' && letters[x - 1][y + 1] == 'S') {
			found++;
		}
		if(letters[x - 1][y - 1] == 'S' && letters[x + 1][y + 1] == 'M' && letters[x + 1][y - 1] == 'S' && letters[x - 1][y + 1] == 'M') {
			found++;
		}
		return found;
	}

    int findNbOfXMASFromThisPosition(char[][] letters, int x, int y) {
        int found = 0;
		int w = letters[0].length - 1;
		int h = letters.length - 1;
		if(letters[x][y] != 'X') {
			return 0;
		}
        //1 - N
        if (y >= 3) {
            String xmas = new String("" + letters[x][y] + letters[x][y - 1] + letters[x][y - 2] + letters[x][y - 3]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
        //2 - NE
        if (y >= 3 && x <= w - 3) {
            String xmas = new String("" + letters[x][y] + letters[x + 1][y - 1] + letters[x + 2][y - 2] + letters[x + 3][y - 3]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
        //3 - E
        if (x <= w - 3) {
            String xmas = new String("" + letters[x][y] + letters[x + 1][y] + letters[x + 2][y] + letters[x + 3][y]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
		//4 - SE
        if (y <= h - 3 && x <= w - 3) {
            String xmas = new String("" + letters[x][y] + letters[x + 1][y + 1] + letters[x + 2][y + 2] + letters[x + 3][y + 3]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
		//5 - S
        if (y <= h - 3) {
            String xmas = new String("" + letters[x][y] + letters[x][y + 1] + letters[x][y + 2] + letters[x][y + 3]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
		//6 - SW
        if (y <= h - 3 && x >= 3) {
            String xmas = new String("" + letters[x][y] + letters[x - 1][y + 1] + letters[x - 2][y + 2] + letters[x - 3][y + 3]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
		//7 - W
        if (x >= 3) {
            String xmas = new String("" + letters[x][y] + letters[x - 1][y] + letters[x - 2][y] + letters[x - 3][y]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }
		//8 - NW
        if (y >= 3 && x >= 3) {
            String xmas = new String("" + letters[x][y] + letters[x - 1][y - 1] + letters[x - 2][y - 2] + letters[x - 3][y - 3]);
            if (xmas.equals("XMAS")) {
                found++;
            }
        }

        return found;
    }

    void displayAll(char[][] items) {
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                System.out.print("" + items[i][j]);
            }
            System.out.println("");
        }
    }

    char[][] getInputs(String filename) {
        char[][] letters = null;
        try {
            File file = getResourceFile(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>(br.lines().toList());
            int length = lines.get(0).length();
            letters = new char[length][lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                char[] line = lines.get(i).toCharArray();
                for (int j = 0; j < line.length; j++) {
                    char c = line[j];
                    if (c == 'X' || c == 'M' || c == 'A' || c == 'S') {
                        letters[j][i] = c;
                    } else {
                        letters[j][i] = '.';
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //displayAll(letters);
        return letters;
    }
}
