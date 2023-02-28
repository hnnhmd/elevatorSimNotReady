package com.fdmgroup.ElevatorSimulator;

import java.util.ArrayList;

public class Building {
	private static Building building;
	private static boolean initialized = false;
	private Building() {};
	
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	
	public ArrayList<Floor> getFloors() {
		return this.floors;
	}
	
	
	public void addFloor(Floor floor) {
		if (!this.floors.contains(floor)) {
			floors.add(floor);
		}
	}

	public static Building getInstance() {
		if (initialized) {
			return building;
		} else {
			building = new Building();
			initialized = true;
			return building;
		}
	}
}
