package edu.wsu.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataManager {

	int[] tape_DiskTransferSizeA;
	int[] tape_DiskTransferTimeA;
	int[] diskSizeA;
	int[] disk_MemoryTransferSizeA;
	int[] disk_MemoryTransferTimeA;
	int[] memorySizeA;
	int[] inputSpaceA;
	int[] DMAA;
	int[] jobLine;

	public void retrieveFile() throws FileNotFoundException {

		Scanner scan = new Scanner(new File("data.txt"));
		String returnValue = "";

		// secondary(tape) to primary(disk) transfer SIZE
		int tape_DiskTransferSize = scan.nextInt();
		returnValue += "Tape to Disk Transfer Size: " + tape_DiskTransferSize + "\n";

		// secondary(tape) to primary(disk) transfer TIME
		int tape_DiskTransferTime = scan.nextInt();
		returnValue += "Tape to Disk Transfer Time: " + tape_DiskTransferTime + "\n";

		// primary(disk) SIZE
		int diskSize = scan.nextInt();
		returnValue += "Disk Size: " + diskSize + "\n";

		// primary(disk) to memory transfer SIZE
		int disk_MemoryTransferSize = scan.nextInt();
		returnValue += "Disk to Memory Transfer Size: " + disk_MemoryTransferSize + "\n";

		// primary(disk) to memory transfer TIME
		int disk_MemoryTransferTime = scan.nextInt();
		returnValue += "Disk to Memory Transfer Time: " + disk_MemoryTransferTime + "\n";

		// memory SIZE
		int memorySize = scan.nextInt();
		returnValue += "Memory Size: " + memorySize + "\n";

		// job input space
		int inputSpace = scan.nextInt();
		returnValue += "Job Input Space Size: " + inputSpace + "\n";

		// DMA buffer transfer SIZE
		int DMA = scan.nextInt();
		returnValue += "DMA Buffer Transfer Size: " + DMA + "\n";

		// count Tokens in job line
		scan.nextLine();
		String str = scan.nextLine();
		StringTokenizer line = new StringTokenizer(str);
		int lineSize = line.countTokens();

		// initialize arrays
		tape_DiskTransferSizeA = new int[tape_DiskTransferSize];
		tape_DiskTransferTimeA = new int[tape_DiskTransferTime];
		diskSizeA = new int[diskSize];
		disk_MemoryTransferSizeA = new int[disk_MemoryTransferSize];
		disk_MemoryTransferTimeA = new int[disk_MemoryTransferTime];
		memorySizeA = new int[memorySize];
		inputSpaceA = new int[inputSpace];
		DMAA = new int[DMA];

		// initialize job array
		jobLine = new int[lineSize];
		int counter = 0;

		for (int i = 0; i < ((lineSize - 1) / 2); i++) {
			// cpu burst time conversion
			String before = line.nextToken();
			int cpuBurstTime = Integer.parseInt(before);
			returnValue += "CPU Burst Time: " + cpuBurstTime + "|	";
			// add to jobLine
			jobLine[counter] = cpuBurstTime;
			counter++;

			// job units of data conversion
			String before2 = line.nextToken();
			int dataUnits = Integer.parseInt(before2);
			returnValue += "Units of Data: " + dataUnits + "\n";
			// add to jobLine
			jobLine[counter] = dataUnits;
			counter++;
		}

		// final cpuBurstTime conversion
		String before3 = line.nextToken();
		int cpuBurstTimeFinal = Integer.parseInt(before3);
		returnValue += "CPU Burst Time: " + cpuBurstTimeFinal;

		// add to jobLine
		jobLine[counter] = cpuBurstTimeFinal;

		// output returnValue
		scan.close();
		System.out.println(returnValue);
	}

	public void performTasks() {
		for (int i = 0; i < jobLine.length - 1; i = i + 2) {

		}

	}

}