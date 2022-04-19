package com.wipro.eb.entity;

public class Domestic extends Connection {
	public Domestic(int currentReading, int previousReading,float slabs[]) {
      super(currentReading, previousReading, slabs);
	}
	
	//slab[] 2.3  4.2  5.5
	public float computeBill() {
		float amount = 0;
		int consumed = previousReading - currentReading;
		if(consumed<=50) {
		amount = consumed*slabs[0];
		}
		else if(consumed<50 && consumed<=100) {
		amount = 50*slabs[0]+(consumed - 50)*slabs[1];
		}
		else {
		amount=	50*slabs[0]+50*slabs[1]+(consumed-100)*slabs[2];
		}
		return amount;
		
	}
}
