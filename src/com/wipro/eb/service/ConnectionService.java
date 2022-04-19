package com.wipro.eb.service;
import com.wipro.eb.entity.Commercial;
import com.wipro.eb.entity.Domestic;
import com.wipro.eb.exception.InvalidConnectionException;
import com.wipro.eb.exception.InvalidReadingException;

public class ConnectionService {
	   
	public boolean validate(int currentReading, int previousReading, String type) throws InvalidReadingException,
	InvalidConnectionException
	{
		
		if(currentReading<previousReading ||currentReading<0||previousReading<0) {
             throw new InvalidReadingException();
         }
		if(!(type.equals("Domestic")|| type.equals("Commercial"))) {
			throw new InvalidConnectionException();
		}
		else {
			return true;
		}
	}
		public float calculateBillAmt(int currentReading, int previousReading, String type) {
			
			boolean res = false;
			float finalbill = 0.0f;
			try {
				res = validate(currentReading, previousReading, type);
			}
			catch (InvalidReadingException e) {
				// TODO: handle exception
				return -1;
			}
			catch(InvalidConnectionException e) {
				return -2;
			}
			if(res == true)
			{
				if(type.equals("Domestic")) {
					Domestic domestic = new Domestic(currentReading, previousReading, new float[] {2.3f,4.2f,5.5f});
					finalbill=domestic.computeBill();

				}
				 else if(type.equals("Commercial"))
				 {
		   			 Commercial commercial=new Commercial(previousReading, currentReading, new float[] {5.2f,6.8f,8.3f});
		   			 finalbill=commercial.computeBill();
		   		 }
		   		 
		   		 
			}
		   	 return finalbill;
		    }

			public String generateBill(int currentReading, int previousReading, String type) throws InvalidConnectionException,InvalidReadingException 
			{
				
			
				float endbill = calculateBillAmt(currentReading, previousReading, type);
				if(endbill==-1) {
					throw new InvalidReadingException();
				}
				else if(endbill ==-2) {
					throw new InvalidConnectionException();
				}
				else {
					return "Amount to be paid: "+endbill;
				}
			}

}
