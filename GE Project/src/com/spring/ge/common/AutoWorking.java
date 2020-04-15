package com.spring.ge.common;

public class AutoWorking extends Thread{
	
	private int mYear, mMonth, mDay, mHour, mMinute;

	private AutoWorking( int year
						,int month
						,int day
						,int hour
						,int minute){
		mYear = year;
		mMonth = month;
		mDay = day;
		mHour = hour;
		mMinute = minute;
	}
	
	public void run(){
		try{
			
		}catch(Exception e){
			System.out.println("¿¡·¯°¡ >>> : " + e);
		}
	}
	
}	// end of AutoWorking
