package com.fdmgroup.ElevatorSimulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elevator extends Thread{
	private int passenger;
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private int floorCounts;
	private int currFloor = 0;
	private String name;
	
	public Elevator(String name) {
		this.name = name;
	}
	
//	public void doElevatorStuff(Integer floorFrom, Integer floorTo, Integer passengers) throws IOException, InterruptedException {
//		Building building = Building.getInstance();
//		System.out.println(this.name + " is at " + currFloor + " floor and has unloaded all previous passengers.");
//			
//			
////		ArrayList<Floor> currFloors = building.getFloors();
////		ArrayList<String> currFloorsName = new ArrayList<String>();
////		for (Floor i : currFloors) {
////			currFloorsName.add(i.getFloorName());
////		}
////			
////		if (!currFloorsName.contains(floorFrom.toString())) {
////			building.addFloor(new Floor(floorFrom.toString()));
////		}
////		if (!currFloorsName.contains(floorTo.toString())) {
////			building.addFloor(new Floor(floorTo.toString()));
////		}
//		
////		ArrayList<Floor> currFloors = building.getFloors();
//			
//		//go to floorFrom
//		int floorDiff = Math.abs(getCurrFloor() - floorFrom);
//		System.out.println(this.name + " is going to " + floorFrom + " and you need to to travel " + floorDiff + " storeys");
//		Thread.sleep(1000 * floorDiff);
//		//sleep for 3 stimulated seconds * floorDiff
//			
//		//sleep for 5 stimulated seconds & prevents access to floor
//		for (int i = 0; i < building.getFloors().length; i ++) {
//			if(building.getFloors()[i].getFloorName().equals(floorFrom.toString())) {
//				building.getFloors()[i].doStuffAtFloor(this.name, passengers);
//				Thread.sleep(5000);
//			}
//		}
////		for (Floor i : currFloors) {
////			if(i.getFloorName().equals(floorFrom.toString())) {
////				
////				i.doStuffAtFloor();
////				Thread.sleep(5000);
////			}
////		}
//		
//			
//		setFloorCounts(Math.abs(floorTo - floorFrom));
//		//sleep for 3 stimulated seconds * setFloorCounts
//		System.out.println(this.name + " is going to " + floorTo + " and you need to to travel " + this.floorCounts + " storeys");
//		Thread.sleep(1000 * floorDiff);
//			
//		this.setCurrFloor(floorTo);
//		
//	}
	
	@Override
	public void run() {
		Building building = Building.getInstance();
		
		
		
		System.out.println(this.name + " started at " + this.currFloor + " floor.");
		for (Floor i : building.getFloors()) {
			boolean nobodyGoUp = false;
			
			//checking if any of the passengers are going down
			for ( Passenger passenger : this.passengers) {
				if (passenger.getDestination() > this.getCurrFloor()) {
					nobodyGoUp = true;
				}
			}
//			System.out.println(nobodyGoUp);
			
			//checking to go to the floor if free 
			if (i.getElevatorHere()) {
				//floor not free
				continue;
			} else {
				//floor is free and we go here
				int floorDiffAuto = Math.abs(Integer.parseInt(i.getFloorName()) - this.currFloor);
				//we set the floor at this level
				System.out.println(this.name + " is going to " + i.getFloorName() + " and needs to travel " + floorDiffAuto + " storeys");
				try {
					//travelling to the floor
					Thread.sleep(1000* floorDiffAuto);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//setting the floor to this level
				this.setCurrFloor(Integer.parseInt(i.getFloorName()));
				System.out.println(this.name + " is at " + this.currFloor + " floor now.");
				
				//loading passengers in this level
				boolean stillHave1 = true;
				if (!nobodyGoUp) {
					while (stillHave1) {
						stillHave1 = false;
						for (int count1 = 0; count1 < i.getPassengers().size(); count1 ++) {
							this.passengers.add(i.getPassengers().get(count1));
							i.getPassengers().remove(count1);
							stillHave1 = true;
						}
					}
				} else {
					while (stillHave1) {
						stillHave1 = false;
						for (int count1 = 0; count1 < i.getPassengers().size(); count1 ++) {
							if (i.getPassengers().get(count1).getDestination() > this.getCurrFloor()) {
								this.passengers.add(i.getPassengers().get(count1));
								i.getPassengers().remove(count1);
								stillHave1 = true;
							}
						}
					}
				}
				
				
//				for ( Passenger j : i.getPassengers() ) {
//					//if there is absolutely nobody going up
//					System.out.println("There are " + i.getPassengers().size() + " passengers on level " + this.currFloor);
//					if (!nobodyGoUp) {
//						//add everyone
//						this.passengers.add(j);
//						//remove from floor
//						i.getPassengers().remove(j);
////						System.out.println("adding passenger?");
//					} else {
//						//or else only add those ppl going up
//						if (j.getDestination() > this.getCurrFloor()) {
////							System.out.println("adding passenger");
//							this.passengers.add(j);
//							//remove passengers
//							i.getPassengers().remove(j);
//						}
//					}
//				}
					
					
					//loading ppl in takes time
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					i.leaveFloor();
					
					Collections.sort(this.passengers,new PassengerComparator());
					
					System.out.println(this.name + " passengers:");
					for (Passenger pass : this.passengers) {
						System.out.println("Passenger entered. Wants to go to " + pass.getDestination());
					}
					
					while (this.passengers.size() != 0) {
						int floorToGo = this.passengers.get(0).getDestination();
						int floorDiff = Math.abs(floorToGo - this.getCurrFloor());
						System.out.println(this.name + " is going to " + floorToGo + " and you need to to travel " + floorDiff + " storeys");
						try {
							Thread.sleep(1000 * floorDiff);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						this.setCurrFloor(floorToGo);
						System.out.println(this.name + " is at " + this.currFloor);
						boolean stillHave = true;
						while (stillHave) {
							stillHave = false;
							for (int lenPass = 0; lenPass < this.passengers.size(); lenPass++) {
//								System.out.println("Passenger wishes to go to level: " + this.passengers.get(lenPass).getDestination());
								if (this.passengers.get(lenPass).getDestination() == getCurrFloor()) {
									stillHave = true;
									this.passengers.remove(this.passengers.get(lenPass));
									System.out.println("passenger out");
									
								}
							}
						}
					
					}
//					break;
				}
		}
				
//				Collections.sort(this.passengers,new PassengerComparator());
//				
//				System.out.println(this.name + "passengers:");
//				for (Passenger pass : this.passengers) {
//					System.out.println("Passenger going to " + pass.getDestination());
//				}
//				
//				while (this.passengers.size() != 0) {
//					int floorToGo = this.passengers.get(0).getDestination();
//					int floorDiff = Math.abs(floorToGo - this.getCurrFloor());
//					System.out.println(this.name + " is going to " + floorToGo + " and you need to to travel " + floorDiff + " storeys");
//					try {
//						Thread.sleep(1000 * floorDiff);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					this.setCurrFloor(floorToGo);
//					System.out.println(this.name + " is at " + this.currFloor);
//					int counter = 0;
//					boolean stillHave = true;
//					while (stillHave) {
//						stillHave = false;
//						for (int lenPass = 0; lenPass < this.passengers.size(); lenPass++) {
//							System.out.println("Passenger wishes to go to level: " + this.passengers.get(lenPass).getDestination());
//							if (this.passengers.get(lenPass).getDestination() == getCurrFloor()) {
//								stillHave = true;
//								this.passengers.remove(this.passengers.get(lenPass));
//								System.out.println("passenger out");
//								
//							}
//						}
//					}
//				
//				}
				
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
