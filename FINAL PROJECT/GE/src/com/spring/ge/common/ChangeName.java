package com.spring.ge.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class ChangeName {
	
	public static String deptcdName(String deptcd_){
		String deptcd = "";
		deptcd = deptcd_;
		
		if(deptcd.equals("20")){
			deptcd = "관리부	";
		}
		if(deptcd.equals("30")){
			deptcd = "영업지원부";
		}
		if(deptcd.equals("40")){
			deptcd = "영업부";
		}
		if(deptcd.equals("50")){
			deptcd = "기술지원부";
		}
		return deptcd;
	}
	
	public static String jobcdName(String jobcd_){
		String jobcd = "";
		jobcd = jobcd_;
		
		if(jobcd.equals("01")){
			jobcd = "관리자";
		}
		if(jobcd.equals("02")){
			jobcd = "사장";
		}
		if(jobcd.equals("03")){
			jobcd = "부장";
		}
		if(jobcd.equals("04")){
			jobcd = "차장";
		}
		if(jobcd.equals("05")){
			jobcd = "과장";
		}
		if(jobcd.equals("06")){
			jobcd = "대리";
		}
		if(jobcd.equals("07")){
			jobcd = "사원";
		}
		return jobcd;
	}
	
	public static String month_Number(String month){
		String number = "";
		number = month;
		
		if(number.equals("01")){
			month = "Jan";
		}else if(number.equals("02")){
			month = "Feb";
		}else if(number.equals("03")){
			month = "Mar";
		}else if(number.equals("04")){
			month = "Apr";
		}else if(number.equals("05")){
			month = "May";
		}else if(number.equals("06")){
			month = "Jun";
		}else if(number.equals("07")){
			month = "Jul";
		}else if(number.equals("08")){
			month = "Aug";
		}else if(number.equals("09")){
			month = "Sep";
		}else if(number.equals("10")){
			month = "Oct";
		}else if(number.equals("11")){
			month = "Nov";
		}else if(number.equals("12")){
			month = "Dec";
		}
		
		
		return month;
	}
	
	public static String month_Text(String month){
		String text = "";
		text = month;
		
		if(text.equals("Jan")){
			month = "01";
		}else if(text.equals("Feb")){
			month = "02";
		}else if(text.equals("Mar")){
			month = "03";
		}else if(text.equals("Apr")){
			month = "04";
		}else if(text.equals("May")){
			month = "05";
		}else if(text.equals("Jun")){
			month = "06";
		}else if(text.equals("Jul")){
			month = "07";
		}else if(text.equals("Aug")){
			month = "08";
		}else if(text.equals("Sep")){
			month = "09";
		}else if(text.equals("Oct")){
			month = "10";
		}else if(text.equals("Nov")){
			month = "11";
		}else if(text.equals("Dec")){
			month = "12";
		}
		
		
		return month;
	}

	public static String dayOfWeek(String date){
		String dayOfWeek = "";
		dayOfWeek = date;
		
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
		Date dd;
		try {
			dd = ff.parse(dayOfWeek);
			Calendar cal = Calendar.getInstance() ;
			cal.setTime(dd);
			int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			if(dayNum==1){
				dayOfWeek = "Sun";
			}else if(dayNum==2){
				dayOfWeek = "Mon";
				
			}else if(dayNum==3){
				dayOfWeek = "Tue";
				
			}else if(dayNum==4){
				dayOfWeek = "Wed";
				
			}else if(dayNum==5){
				dayOfWeek = "Thu";
				
			}else if(dayNum==6){
				dayOfWeek = "Fri";
				
			}else if(dayNum==7){
				dayOfWeek = "Sat";
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dayOfWeek;
	}
}
