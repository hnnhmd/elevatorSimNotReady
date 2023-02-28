package com.fdmgroup.ElevatorSimulator;

import java.util.ArrayList;

public class Building {
	private static Building building;
	private static boolean initialized = false;
	private Building() {};
	
//	private ArrayList<Floor> floors = new ArrayList<Floor>();
	
	private Floor[] floors = {
			new Floor("0"), 
			new Floor("1"), 
			new Floor("2"), 
			new Floor("3"), 
			new Floor("4"), 
			new Floor("5"), 
			new Floor("6"), 
			new Floor("7"),
			new Floor("8"),
			new Floor("9"),
			new Floor("10")
	};
 	
	
	
	public Floor[] getFloors() {
		return this.floors;
	}
	
	
//	public void addFloor(Floor floor) {
//		if (!this.floors.contains(floor)) {
//			floors.add(floor);
//		}
//	}

	public static Building getInstance() {
		if (initialized) {
//			System.out.println("old");
			return building;
		} else {
			building = new Building();
//			System.out.println("new");
			initialized = true;
			return building;
		}
	}
}
