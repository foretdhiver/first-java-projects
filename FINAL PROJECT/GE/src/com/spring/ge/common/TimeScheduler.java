package com.spring.ge.common;

import java.util.Calendar;
import java.util.Timer;

public class TimeScheduler {

	public TimeScheduler(){
		
//		// ���� �����尡 ���� �𸣰����� false�� �س����� ���󾲷���� �������� �ʴ´�
		Timer timer = new Timer(false);
		Calendar date = Calendar.getInstance();
		// Calendar.DAY_OF_WEEK : ���� ���� (�Ͽ����� 1, ������� 7)
		date.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		// Calendar.AM_PM : ����/���� ����
		date.set(Calendar.AM_PM, Calendar.PM);
		// Calendar.HOUR : �� ����
		date.set(Calendar.HOUR, 2);
		// Calendar.MINUTE : �� ����
		date.set(Calendar.MINUTE, 23);
		// Calendar.SECOND : �� ����
		date.set(Calendar.SECOND, 15);
		// Calendar.MILLISECOND : �� ����
		date.set(Calendar.MILLISECOND, 0);
		
		
		// timer.schedule(TimerTask task, Date time)
		// : ������ �ð�(time)�� ������ �۾�(task)�� �����Ѵ�.
		timer.schedule(new TimerTaskTest(), 0, 24*60*60*1000);
		
		
		
	}	// end of TimeScheduler.TimeScheduler()
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new TimeScheduler();
		}
}	// end of TimeScheduler
