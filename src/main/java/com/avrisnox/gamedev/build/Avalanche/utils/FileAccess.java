package com.avrisnox.gamedev.build.Avalanche.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileAccess {
	public static String readFromFile(String file) {
		StringBuilder lines = new StringBuilder();

		try(Stream<String> contents = Files.lines(Path.of(file))) {
			contents.forEach((line) -> lines.append(line).append('\n'));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines.toString();
	}
}
