package com.fdmgroup.ElevatorSimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Elevator{
	private int passenger;
	private int floorCounts;
	private int currFloor;
	private String name;
	
	public Elevator(String name) {
		this.name = name;
	}
	
	public void doElevatorStuff(Integer floorFrom, Integer floorTo) throws IOException, InterruptedException {
		Building building = Building.getInstance();
		System.out.println(this.name + " is at " + currFloor + " floor.");
			
			
		ArrayList<Floor> currFloors = building.getFloors();
		ArrayList<String> currFloorsName = new ArrayList<String>();
		for (Floor i : currFloors) {
			currFloorsName.add(i.getFloorName());
		}
			
		if (!currFloorsName.contains(floorFrom.toString())) {
			building.addFloor(new Floor(floorFrom.toString()));
		}
		if (!currFloorsName.contains(floorTo.toString())) {
			building.addFloor(new Floor(floorTo.toString()));
		}
			
		//go to floorFrom
		int floorDiff = Math.abs(getCurrFloor() - floorFrom);
		System.out.println(this.name + " is going to " + floorFrom + " and you need to to travel " + floorDiff + " storeys");
		Thread.sleep(1000 * floorDiff);
		//sleep for 3 stimulated seconds * floorDiff
			
		//sleep for 5 stimulated seconds & prevents access to floor
		for (Floor i : currFloors) {
			if(i.getFloorName() == floorFrom.toString()) {
//				if (i.getElevatorHere()) {
//					break;
//				}
				i.doStuffAtFloor();
				Thread.sleep(5000);
			}
		}
		
			
		setFloorCounts(Math.abs(floorTo - floorFrom));
		//sleep for 3 stimulated seconds * setFloorCounts
		System.out.println(this.name + " is going to " + floorTo + " and you need to to travel " + this.floorCounts + " storeys");
		Thread.sleep(1000 * floorDiff);
			
		this.setCurrFloor(floorTo);
		
	}
	
	public void loadPassenger(int newPassenger) {
		this.passenger = this.passenger + newPassenger;
		
	}
	
	public void unloadPassenger(int goPassenger) {
		this.passenger = this.passenger - goPassenger;
	}

	public int getFloorCounts() {
		return this.floorCounts;
	}

	public void setFloorCounts(int floorCounts) {
		this.floorCounts = floorCounts;
	}

	public int getCurrFloor() {
		return this.currFloor;
	}

	public void setCurrFloor(int currFloor) {
		this.currFloor = currFloor;
	}

}
