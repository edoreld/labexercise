// Incomplete - Not Working //

package com.edoreld.externalsorting;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;

public class Sorter
{

	public String sort(String string) {
		String[] numbers = string.split("\\D+");
		Arrays.sort(numbers, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Integer.valueOf(s1).compareTo(Integer.valueOf(s2));
			}
		});
		return Arrays.toString(numbers);
	}

	public int read(Reader reader, char[] chars) throws IOException {
		return reader.read(chars);
	}

	public static void compute(int numInts, int numPagesBuffer, String inputFile)
			throws IOException, FileNotFoundException {

		int buffer = numInts * numPagesBuffer;
		try (Reader reader = new InputStreamReader(new FileInputStream(inputFile), "UTF-8")) {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("tmp.txt"), "utf-8"))) {

				Sorter sorter = new Sorter();
				String currentLine;
				int counter = 1;
				String aux = "";
				char[] chars = new char[buffer];
				int charsRead = -1;

				while ((charsRead = sorter.read(reader, chars)) != -1) {
					try (Writer auxWriter = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(counter++ + ".txt"), "utf-8"))) {
						auxWriter.write(chars);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		// Not Working
		String[] myArgs = new String[2];
		myArgs[0] = "/Users/edoreld/Desktop/a.txt";
		myArgs[1] = "/Users/edoreld/Desktop/b.txt";

		Sorter.compute(2, 3, myArgs[0]);
	}
}
