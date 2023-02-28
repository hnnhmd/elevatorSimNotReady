package com.fdmgroup.ElevatorSimulator;

import java.util.Comparator;

public class PassengerComparator implements Comparator<Passenger> {

	@Override
	public int compare(Passenger o1, Passenger o2) {
		// TODO Auto-generated method stub
		return o1.getDestination() - o2.getDestination();
	}

}
