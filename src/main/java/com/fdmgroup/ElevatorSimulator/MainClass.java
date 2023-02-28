package com.fdmgroup.ElevatorSimulator;

public class MainClass {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		FileReaderThread frt1 = new FileReaderThread(new Elevator("Elevator one"), "/Users/adamhardy/eclipse-workspace/ElevatorSimulator/src/main/resources/elevator.txt");
		FileReaderThread frt2 = new FileReaderThread(new Elevator("Elevator two"), "/Users/adamhardy/eclipse-workspace/ElevatorSimulator/src/main/resources/elevator.txt");
		
		
		
		frt2.start();
		frt1.start();
		frt2.join();
		frt1.join();

	}

}
