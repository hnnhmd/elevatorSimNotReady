package com.fdmgroup.ElevatorSimulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderThread extends Thread{
	private Elevator elevator;
	private String fileName;
	
	public FileReaderThread (Elevator elevator, String fileName) {
		this.elevator = elevator;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		FileReader fr;
//		int count = 0;
		try {
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			try {
				while (br.ready()) {
					String line = br.readLine();
					String[] args = line.split(" ");
//					if (count < 2) {
//						continue;
//					}
//					count ++;
					
					if(args[0].equals("G")) {
						args[0] = "0";
					}
					
					if(args[1].equals("G")) {
						args[1] = "0";
					}
					
					Integer floorFrom = Integer.parseInt(args[0]);
					Integer floorTo = Integer.parseInt(args[1]);
					
					
					
					try {
						this.elevator.doElevatorStuff(floorFrom, floorTo);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
