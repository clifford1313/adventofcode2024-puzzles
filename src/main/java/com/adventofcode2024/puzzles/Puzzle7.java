package com.adventofcode2024.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.adventofcode2024.IPuzzle;

public class Puzzle7 implements IPuzzle {

	public void execute() {
		executeStep1();
		executeStep2();
	}

	private void executeStep1() {
		List<Program> programs = readProgram();

		for (Program prg : programs) {
			if (prg.parent == null) {
				System.out.println("Bottom program : " + prg.name);
				break;
			}
		}
	}

	private void executeStep2() {
		List<Program> programs = readProgram();
		Program bottom = null;

		for (Program prg : programs) {
			if (prg.parent == null) {
				bottom = prg;
				break;
			}
		}

		Program cur = bottom;
		// displayAll("", cur);

		findUnbalanced(cur);

	}

	void findUnbalanced(Program cur) {
		Map<Integer, List<Program>> values = new HashMap<Integer, List<Program>>();
		System.out.println(cur.displayNames() + " = " + cur.displayValues());
		if (cur.getChilds() != null) {
			for (Program child : cur.getChilds()) {
				int value = child.getValue();
				if (!values.containsKey(value)) {
					values.put(value, new ArrayList<Program>());
				}
				values.get(value).add(child);
			}
			if (values.size() == 1) {
				return;
			} else {
				Iterator<Integer> it = values.keySet().iterator();
				int goodVal = 0, badVal = 0;
				while (it.hasNext()) {
					int val = it.next();
					if (values.get(val).size() != 1) {
						goodVal = val;
					} else {
						badVal = val;
					}
				}
				System.out.println("Good Val = " + goodVal + " - Bad Val = " + badVal);
				for (List<Program> list : values.values()) {
					if (list.size() == 1) {
						findUnbalanced(list.get(0));
					}
				}

			}
		}
	}

	void displayAll(String prefix, Program cur) {
		System.out.println(prefix + cur.displayNames() + " = " + cur.displayValues());
		if (cur.getChilds() != null) {
			for (Program prg : cur.getChilds()) {
				displayAll(prefix + "  ", prg);
			}
		}
	}

	List<Program> readProgram() {
		List<Program> programs = new ArrayList<Program>();
		File file = getResourceFile("puzzle7_1.txt");
		try {
			// pass 1 - programs
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				programs.add(new Program(line));
			}

			// pass 2 - links
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(" ");
				if (data.length > 2) {
					Program curPrg = findProgram(programs, data[0]);
					for (int i = 3; i < data.length; i++) {
						Program child = findProgram(programs, data[i].replace(',', ' ').trim());
						curPrg.addChild(child);
						child.setParent(curPrg);
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programs;
	}

	Program findProgram(List<Program> programs, String name) {
		for (Program prg : programs) {
			if (prg.name.equals(name)) {
				return prg;
			}
		}
		return null;
	}

	private class Program {
		private String name;
		private List<Program> childs = null;
		private Program parent;
		private int value;

		public Program(String line) {
			String[] data = line.split(" ");
			name = data[0];
			value = Integer.parseInt(data[1].replace('(', ' ').replace(')', ' ').trim());
		}

		public void addChild(Program program) {
			if (childs == null) {
				childs = new ArrayList<Program>();
			}
			childs.add(program);
		}

		public List<Program> getChilds() {
			return childs;
		}

		public void setParent(Program parent) {
			this.parent = parent;
		}

		public int getValue() {
			int ret = value;
			if (childs != null) {
				for (Program child : childs) {
					ret += child.getValue();
				}
			}
			return ret;
		}

		public String displayNames() {
			String display = name;
			if (childs != null) {
				display += " + (";
				for (Program child : childs) {
					display += child.displayNames() + " + ";
				}
				display += " )";
			}
			return display;
		}

		public String displayValues() {
			String display = "" + value;
			if (childs != null) {
				display += " + (";
				for (Program child : childs) {
					display += child.displayValues() + " + ";
				}
				display += " )";
			}
			return display;
		}

		@Override
		public String toString() {
			return name + " (" + getValue() + " )";
		}
	}
}