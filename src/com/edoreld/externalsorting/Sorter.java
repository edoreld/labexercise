package com.edoreld.externalsorting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Sorter
{

	public String sort(String string) {
		char[] arr = string.replaceAll(" ", "").toCharArray();
		Arrays.sort(arr);
		String sorted = new String(arr);
		return sorted;
	}

	public static void compute(int numInts, int numPagesBuffer, String inputFile)
			throws IOException, FileNotFoundException {

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile), numInts * numPagesBuffer)) {
			PrintWriter writer = new PrintWriter("/Users/edoreld/Desktop/tmp.txt", "UTF-8");

			Sorter sorter = new Sorter();
			String currentLine;
			int counter = 0;
			String aux = "";

			while ((currentLine = reader.readLine()) != null) {
				writer.write(sorter.sort(currentLine));
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String[] myArgs = new String[2];
		myArgs[0] = "/Users/edoreld/Desktop/a.txt";
		myArgs[1] = "/Users/edoreld/Desktop/b.txt";

		Sorter.compute(2, 3, myArgs[0]);
	}
}
