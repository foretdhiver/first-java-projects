package com.spring.ge.common;

import java.util.Calendar;
import java.util.Timer;

public class TimeScheduler {

	public TimeScheduler(){
		
//		// 데몬 쓰레드가 뭔지 모르겠지만 false로 해놓으면 데몬쓰레드로 지정하지 않는다
		Timer timer = new Timer(false);
		Calendar date = Calendar.getInstance();
		// Calendar.DAY_OF_WEEK : 현재 요일 (일요일은 1, 토요일은 7)
		date.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		// Calendar.AM_PM : 오전/오후 리턴
		date.set(Calendar.AM_PM, Calendar.PM);
		// Calendar.HOUR : 시 리턴
		date.set(Calendar.HOUR, 2);
		// Calendar.MINUTE : 분 리턴
		date.set(Calendar.MINUTE, 23);
		// Calendar.SECOND : 초 리턴
		date.set(Calendar.SECOND, 15);
		// Calendar.MILLISECOND : 초 리턴
		date.set(Calendar.MILLISECOND, 0);
		
		
		// timer.schedule(TimerTask task, Date time)
		// : 지정한 시간(time)에 지정한 작업(task)을 수행한다.
		timer.schedule(new TimerTaskTest(), 0, 24*60*60*1000);
		
		
		
	}	// end of TimeScheduler.TimeScheduler()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new TimeScheduler();
		}
}	// end of TimeScheduler
