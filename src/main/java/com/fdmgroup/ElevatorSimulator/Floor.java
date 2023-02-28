package com.fdmgroup.ElevatorSimulator;

import java.util.ArrayList;

public class Floor {
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private String floorName;
	public Boolean elevatorHere;
	
	public void addPassenger(Passenger passenger) {
		this.passengers.add(passenger);
	}
	
	public Floor(String floorName) {
		this.floorName = floorName;
	}
	
	public synchronized boolean getElevatorHere() {
		if (elevatorHere == null) {
			elevatorHere = true;
			return false;
		} else {
			return true;
		}
	}
	
	public synchronized void leaveFloor() {
		this.elevatorHere = null;
	}

	public synchronized void doStuffAtFloor(String name, int passengers) throws InterruptedException {
		System.out.println(name + " is at " + getFloorName() + " floor to pick up " + passengers + " passengers");
		Thread.sleep(5000);
	}

	public ArrayList<Passenger> getPassengers() {
		return this.passengers;
	}

	public String getFloorName() {
		return this.floorName;
	}


	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

}
