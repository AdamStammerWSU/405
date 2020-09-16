package edu.wsu.os;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		DataManager dm = new DataManager();
		
		dm.retrieveFile();
		dm.performTasks();
		dm.finalTime();
	}
}
