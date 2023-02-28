package com.fdmgroup.ElevatorSimulator;

public class Floor {
	private int passenger;
	private String floorName;
	private static Boolean elevatorHere;
	
	
	public Floor(String floorName) {
		this.setFloorName(floorName);
	}
	
	public boolean getElevatorHere() {
		if (elevatorHere == null) {
			elevatorHere = true;
			return false;
		} else {
			return true;
		}
	}

	public synchronized void doStuffAtFloor() throws InterruptedException {
		System.out.println("You are at " + getFloorName() + "floor");
		Thread.sleep(5000);
	}

	public void loadPassenger(int newPassenger) {
		this.passenger = this.passenger + newPassenger;
	}
	
	public void unloadPassenger(int goPassenger) {
		this.passenger = this.passenger - goPassenger;
	}


	public String getFloorName() {
		return this.floorName;
	}


	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

}
