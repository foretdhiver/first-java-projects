package bitcamp.java142.ch5.kyjjf.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import bitcamp.java142.ch5.kyjjf.sql.KyjSqlQueryMap;
import bitcamp.java142.ch5.kyjjf.common.KyjConnProperty;

public class KyjChaeBunClass {
	
	public static final String BIZ_MEMBER_E = "E";
	
	public static String ymdFormat(){
		System.out.println("(log) KyjChaeBunClass.ymdFormat() 시작 >>> : ");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(d);
		System.out.println("(log) 변수 date 값 >>> : " + date);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		
		String commNO = "";
		
		try{
			conn = KyjConnProperty.getConnection();
			System.out.println("(log) connection 성공");
			pstmt = conn.prepareStatement(KyjSqlQueryMap.KYJ_MEMBER_KMEM_CHABUN);
			rsRs = pstmt.executeQuery();
			System.out.println("(log) KyjChabun >>> : " + KyjSqlQueryMap.KYJ_MEMBER_KMEM_CHABUN);
			if(rsRs!=null){
				while(rsRs.next()){
					commNO = rsRs.getString("COMMNO");
				}
			}
			System.out.println("(log) commNO >>> : " + commNO);
			if(1==commNO.length()){
				commNO = date + "000" + commNO;
			}
			if(2==commNO.length()){
				commNO = date + "00" + commNO;
			}
			if(3==commNO.length()){
				commNO = date + "0" + commNO;
			}
			System.out.println("(log) commNO >>> : " + commNO);
			commNO = BIZ_MEMBER_E + commNO;
			System.out.println("(log) commNO >>> : " + commNO);
			
		}catch(Exception e){
			System.out.println("DB 에러 " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(conn, pstmt, rsRs);
		}
		System.out.println("(log) KyjChaeBunClass.ymdFormat() 끝 >>> : ");
		return commNO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjChaeBunClass.main() 시작 >>> : ");

		String ymdFormat = KyjChaeBunClass.ymdFormat();
		System.out.println("(log) commNumber >>> : " + ymdFormat);
		System.out.println("(log) KyjChaeBunClass.main() 끝 >>> : ");


	} // end of main()
} // end of KyjChaeBunClass
