package com.fdmgroup.ElevatorSimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
//		Building building = Building.getInstance();
//		FileReaderThread frt1 = new FileReaderThread(new Elevator("Elevator one"), "/Users/adamhardy/eclipse-workspace/ElevatorSimulator/src/main/resources/elevator.txt");
//		FileReaderThread frt2 = new FileReaderThread(new Elevator("Elevator two"), "/Users/adamhardy/eclipse-workspace/ElevatorSimulator/src/main/resources/elevator.txt");
//		
//		
//		
//		frt2.start();
//		frt1.start();
//		frt2.join();
//		frt1.join();
		
		FileReader fr = new FileReader("/Users/adamhardy/eclipse-workspace/ElevatorSimulator/src/main/resources/elevator.txt");
		BufferedReader br = new BufferedReader(fr);
		Building building = Building.getInstance();
		
		ArrayList<Passenger> listPassengers = new ArrayList<Passenger>();
		
		while (br.ready()) {
			String line = br.readLine();
			String[] argsLine = line.split(" ");
			
			if(argsLine[0].equals("G")) {
				argsLine[0] = "0";
			}
			
			if(argsLine[1].equals("G")) {
				argsLine[1] = "0";
			}
			
			Integer floorFrom = Integer.parseInt(argsLine[0]);
			Integer floorTo = Integer.parseInt(argsLine[1]);
			Integer passengers = Integer.parseInt(argsLine[2]);
			
			for (int i = 0; i < passengers ; i++) {
				listPassengers.add(new Passenger(floorFrom, floorTo));
			}
		}
		
		for (Passenger i : listPassengers) {
			for ( Floor j : building.getFloors() ) {
				if(j.getFloorName().equals(i.getLocation().toString())) {
					j.addPassenger(i);
				}
			}
		}
		
		Elevator e1 = new Elevator("Elevator one");
		Elevator e2 = new Elevator("Elevator two");
		
		e1.start();
		e2.start();
		e1.join();
		e2.join();
		
		
//		for (Floor j : building.getFloors()) {
//			System.out.println(j.getFloorName() + " has " + j.getPassengers().size() + " people waiting.");
//		}
		
		

	}

}
