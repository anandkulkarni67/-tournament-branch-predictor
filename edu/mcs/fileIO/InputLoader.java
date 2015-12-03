package edu.mcs.fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import edu.mcs.model.InputEntry;

public class InputLoader {
	private String fileName;

	public InputLoader(String fileName) {
		super();
		this.fileName = fileName;
	}

	public List<InputEntry> load(){
		List<InputEntry> inputEntryList = new LinkedList<InputEntry>();
		try {
			File file = new File(this.fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			InputEntry inputEntry;
			while ((line = br.readLine()) != null) {
					inputEntry = new InputEntry(line.substring(0, 1), line.substring(1, 2), line.substring(2, 3));
					inputEntryList.add(inputEntry);
			}
			br.close();
		}catch (FileNotFoundException e) {
			System.err.println("Please enter a valid filename/filepath");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Encountered error while reading an input file");
			e.printStackTrace();
			System.exit(0);
		}
		return inputEntryList;
	}
}
