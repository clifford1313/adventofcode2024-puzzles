package com.adventofcode2024.puzzles;

import java.awt.DisplayMode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.plaf.BorderUIResource;

import com.adventofcode2024.IPuzzle;

public class Puzzle6 implements IPuzzle {

    static final char UP = '^';
    static final char DOWN = 'v';
    static final char RIGHT = '>';
    static final char LEFT = '<';

    @Override
    public void execute() {
        executeStep1();
        executeStep2();
    }

    private void executeStep1() {
        List<String> lines = getInputs("puzzle6_1.txt");
        int startX = 0, startY = 0;
        char startDirection = '^';
        char[][] map = new char[lines.get(0).length()][lines.size()];
        int total = 0;
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                map[x][y] = lines.get(y).charAt(x);
                if (map[x][y] == UP || map[x][y] == DOWN || map[x][y] == LEFT || map[x][y] == RIGHT) {
                    startX = x;
                    startY = y;
                    startDirection = map[x][y];
                }
            }
        }
        //displayAll(map);
		char[][] result = walkAndMark(map, startX, startY, startDirection);
		//displayAll(result);

		for(int y = 0; y < result.length; y++) {
			for(int x = 0; x < result[0].length; x++) {
				total += (result[x][y] == 'X' ? 1 : 0);
			}
		}

        System.out.println("Response 1 = " + total + "\n");
    }

    private void executeStep2() {
    }

    char[][] walkAndMark(char[][] map, int startX, int startY, char direction) {
        int curX = startX;
        int curY = startY;
        char curDirection = direction;
        while (curX >= 0 && curX < map[0].length - 1 && curY >= 0 && curY < map.length - 1) {
			//displayAll(map);
			map[curX][curY] = 'X';
            switch (curDirection) {
                case UP:
                    if (map[curX][curY - 1] == '#') {
                        curDirection = RIGHT;
                    } else {
                        map[curX][curY - 1] = curDirection;
						curY--;
                    }
                    break;
                case DOWN:
                    if (map[curX][curY + 1] == '#') {
                        curDirection = LEFT;
                    } else {
                        map[curX][curY + 1] = curDirection;
						curY++;
                    }
                    break;
                case LEFT:
				if (map[curX - 1][curY] == '#') {
					curDirection = UP;
				} else {
					map[curX - 1][curY] = curDirection;
					curX--;
				}
				break;
                case RIGHT:
				if (map[curX + 1][curY] == '#') {
					curDirection = DOWN;
				} else {
					map[curX + 1][curY] = curDirection;
					curX++;
				}
				break;
            }
        }
		map[curDirection == LEFT ? curX + 1 : curDirection == RIGHT ? curX - 1 : curX][curDirection == UP ? curY + 1 : curDirection == DOWN ? curY - 1 : curY] = 'X';
		return map;
	}

    void displayAll(char[][] items) {
        for (int y = 0; y < items.length; y++) {
            for (int x = 0; x < items[y].length; x++) {
                System.out.print(items[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    ArrayList<String> getInputs(String filename
    ) {
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
