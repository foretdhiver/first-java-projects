package com.spring.ge.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Chaebun {
	
	public static final String GUBUN_EA 	= "EA";		//전자결재테이블번호
	public static final String GUBUN_EAS	= "EAS"; 	//대결번호
	public static final String GUBUN_EAR	= "EAR";	//전결번호
	public static final String GUBUN_EAL	= "EAL";    //결재라인번호
	public static final String GUBUN_EASTNO = "EASTNO"; //결재스텝번호
	public static final String GUBUN_EALOG  = "EALOG"; 	//결재로그번호
	public static final String GUBUN_E 		= "E";		//사번:Employee 
	public static final String GUBUN_EP 	= "EP";		//개인정보:EmployeePrivate
	public static final String GUBUN_EV 	= "EV";		//인사평가:Evaluation
	public static final String GUBUN_TR 	= "TR";		//교육:Training
	public static final String GUBUN_CT 	= "CT";		//근태:ClockTime
	public static final String GUBUN_B 		= "B";		//게시판:Board
	public static final String GUBUN_D 		= "D";		//부서:Department
	public static final String GUBUN_T 		= "T";		//업부:Task
	public static final String GUBUN_N 		= "N";		//공지:Notice
	public static final String GUBUN_A 		= "A";		//익명:Anonymous 
	public static final String GUBUN_PC 	= "PC";		//ProjectCalendar
	public static final String GUBUN_PB 	= "PB";		//ProjectBoard
	public static final String GUBUN_PR 	= "PR";		//ProjectReference
	public static final String GUBUN_C 		= "C";		//댓글:Comment
	public static final String GUBUN_MG  	= "MG"; 	//쪽지:Message
	public static final String GUBUN_CAL  	= "CAL"; 	//일정관리 캘린더
	public static final String GUBUN_LOG = "LOG"; // Log (근태 로그)
	
/* =====================================================================================
   1.날짜 형식			
   =====================================================================================*/	
	//yyyyMMdd
	public static String ymdFormat1(){
		Date d = null;
		d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(d);
		
		return date;
	}
	//yyyy-MM-dd
	public static String ymdFormat2(){
		Date d = null;
		d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		
		return date;
	}
	//yyyy:MM:dd
	public static String ymdFormat3(){
		Date d = null;
		d = new Date();
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
		String date = sdf.format(d);
			
		return date;
	}
	public static String ymdFormat(){
		Date d = null;
		d = new Date();
		
		SimpleDateFormat abc = new SimpleDateFormat("yyyyMMdd");
		String date = abc.format(d);
		
		return date;
	}	// 은민용입니다~~~
/* =====================================================================================
   2.숫자형식			
   =====================================================================================*/
	//0001
	public static String numberFormat1(String _no){		
		String no = _no;
		
		if(no.length() == 1){
			no = "000" + no;}
		if(no.length() == 2){
			no = "00" + no;}
		if(no.length() == 3){
			no = "0" + no;}	
		return no;
	}
	//001
	public static String numberFormat2(String _no){
		String no = _no;
		
		if(no.length() == 1){
			no = "00" + no;}
		if(no.length() == 2){
			no = "0" + no;}		
		return no;
	}
	//01
	public static String numberFormat3(String _no){
		String no = _no;
		
		if(no.length() == 1){
			no = "0" + no;}		
		return no;
	}
	public static String logChaebunFormat(String _no){
		String no = _no;
	
		if(no.length() == 1){
			no = "0000" + no;
		}
		if(no.length() == 2){
			no = "000" + no;
		}
		if(no.length() == 3){
			no = "00" + no;
		}	
		if(no.length() == 4){
			no = "0" + no;
		}	
		return no;
	}	// 은민용
public static String chaebunFormat(String _no){
		
		String no = _no;
		
		if(no.length() == 1){
			no = "000" + no;
		}
		if(no.length() == 2){
			no = "00" + no;
		}
		if(no.length() == 3){
			no = "0" + no;
		}	
		return no;
	}	// 은민용

	public static String refChaebunFormat(String _no){
		
		String no = _no;
		
		if(no.length() == 1){
			no = "00" + no;
		}
		if(no.length() == 2){
			no = "0" + no;
		}		
		return no;
	}	// 은민용
/* =====================================================================================
   3.사용하는 함수			
   =====================================================================================*/
	
	
	public static String chaebunEA(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EA +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEAS(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EAS +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEAR(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EAR +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEAL(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EAL +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEASTNO(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EASTNO +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEALOG(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EALOG +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunE(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_E +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEP(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EP +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunEV(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_EV +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunTR(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_TR +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunCT(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_CT +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunBD(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_B + GUBUN_D +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunDC(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_D + GUBUN_C + 
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunBT(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_B + GUBUN_T +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunTC(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_T + GUBUN_C + 
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunBN(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_B + GUBUN_N +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunBA(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_B + GUBUN_A +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunAC(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_A + GUBUN_C + 
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);
		
		
		return chaebun;
	}
	public static String chaebunPC(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_PC +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunPB(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_PB +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunPBC(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_PB + GUBUN_C +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunPR(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_PR +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunPRC(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_PR + GUBUN_C +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunMG(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_MG +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	public static String chaebunCAL(String _no){
		String chaebun = _no;
		
		chaebun = GUBUN_CAL +
				  Chaebun.ymdFormat1() + 
				  Chaebun.numberFormat1(chaebun);
		System.out.println("날짜 : " + Chaebun.ymdFormat1());
		System.out.println("채번 : " + chaebun);

		return chaebun;
	}
	//-------------------
		public static String chaebunCreateE(String _no){
			System.out.println("(log) Chaebun.chaebunCreateE() 함수 시작");
			String chaebun = _no;
			System.out.println("(log) chaebun >>> : " + chaebun);
			
			chaebun = GUBUN_E +
					  Chaebun.ymdFormat() +
					  Chaebun.chaebunFormat(chaebun);
			System.out.println("(log) 날짜 : " + Chaebun.ymdFormat());
			System.out.println("(log) 채번 : " + chaebun);
			
			System.out.println("(log) chaebun >>> : " + chaebun);
			System.out.println("(log) Chaebun.chaebunCreateE() 함수 끝");
			return chaebun;
		}	// 은민용
		
		public static String chaebunCreateEP(String _no){
			System.out.println("(log) Chaebun.chaebunCreateEP() 함수 시작");
			String chaebun = _no;
			System.out.println("(log) chaebun >>> : " + chaebun);
			
			chaebun = GUBUN_EP +
					Chaebun.ymdFormat() +
					Chaebun.chaebunFormat(chaebun);
			System.out.println("(log) 날짜 : " + Chaebun.ymdFormat());
			System.out.println("(log) 채번 : " + chaebun);
			
			System.out.println("(log) chaebun >>> : " + chaebun);
			System.out.println("(log) Chaebun.chaebunCreateEP() 함수 끝");
			return chaebun;
		}	// 은민용
		
		public static String chaebunCreateCT(String _no){
			System.out.println("(log) Chaebun.chaebunCreateCT() 함수 시작");
			String chaebun = _no;
			System.out.println("(log) chaebun >>> : " + chaebun);
			
			chaebun = GUBUN_CT +
					Chaebun.ymdFormat() +
					Chaebun.chaebunFormat(chaebun);
			System.out.println("(log) 날짜 : " + Chaebun.ymdFormat());
			System.out.println("(log) 채번 : " + chaebun);
			
			System.out.println("(log) chaebun >>> : " + chaebun);
			System.out.println("(log) Chaebun.chaebunCreateCTHis() 함수 끝");
			return chaebun;
		}	// 은민용
		
		public static String chaebunCreateLOG(String _no){
			System.out.println("(log) Chaebun.chaebunCreateLOG() 함수 시작");
			String chaebun = _no;
			System.out.println("(log) chaebun >>> : " + chaebun);
			
			chaebun = GUBUN_LOG +
					Chaebun.ymdFormat() +
					Chaebun.logChaebunFormat(chaebun);
			System.out.println("(log) 날짜 : " + Chaebun.ymdFormat());
			System.out.println("(log) 채번 : " + chaebun);
			
			System.out.println("(log) chaebun >>> : " + chaebun);
			System.out.println("(log) Chaebun.chaebunCreateLOG() 함수 끝");
			return chaebun;
		}	// 은민용
		
		public static String chaebunCreateTR(String _no){
			System.out.println("(log) Chaebun.chaebunCreateTR() 함수 시작");
			String chaebun = _no;
			System.out.println("(log) chaebun >>> : " + chaebun);
			
			chaebun = GUBUN_TR +
					Chaebun.ymdFormat() +
					Chaebun.chaebunFormat(chaebun);
			System.out.println("(log) 날짜 : " + Chaebun.ymdFormat());
			System.out.println("(log) 채번 : " + chaebun);
			
			System.out.println("(log) chaebun >>> : " + chaebun);
			System.out.println("(log) Chaebun.chaebunCreateE() 함수 끝");
			return chaebun;
		}	// 은민용
		
		public static String chaebunCreateEV(String _no){
			System.out.println("(log) Chaebun.chaebunCreateEV() 함수 시작");
			String chaebun = _no;
			System.out.println("(log) chaebun >>> : " + chaebun);
			
			chaebun = GUBUN_EV +
					Chaebun.ymdFormat() +
					Chaebun.chaebunFormat(chaebun);
			System.out.println("(log) 날짜 : " + Chaebun.ymdFormat());
			System.out.println("(log) 채번 : " + chaebun);
			
			System.out.println("(log) chaebun >>> : " + chaebun);
			System.out.println("(log) Chaebun.chaebunCreateEV() 함수 끝");
			return chaebun;
		}	// 은민용

}
