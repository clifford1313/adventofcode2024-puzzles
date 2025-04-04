package com.adventofcode2024;

import java.io.File;

public interface IPuzzle {
	default void execute() {
		System.out.println("I am " + this.getClass().getName());
	}
	
	default File getResourceFile(String resourceName) {
		ClassLoader cl = getClass().getClassLoader();
		File file = new File(cl.getResource(resourceName).getFile());
		return file;
	}
}
