package com.fdmgroup.ElevatorSimulator;

public class Passenger {
	
	private Integer location;
	private Integer destination;
	
	public Passenger(int location, int destination) {
		this.setLocation(location);
		this.setDestination(destination);
	}

	public Integer getLocation() {
		return this.location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public Integer getDestination() {
		return this.destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

}
