package kyj.board.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kyj.board.common.KyjConnProperty;
import kyj.board.sql.KyjHelloBoardSqlQueryMap;

public class KyjChaeBunClass {

	public static final String BIZ_BOARD_B="B";
	public static String bFormat(){
		System.out.println("[log] KyjChaeBunClass.bFormat() 시작 >>> ");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		String commNO = "";
		
		try{
			conn=KyjConnProperty.getConnection();
			System.out.println("[log] connection 성공");
			pstmt=conn.prepareStatement(KyjHelloBoardSqlQueryMap.KYJ_BOARD_KNO_CHAEBUN);
			rsRs=pstmt.executeQuery();
			System.out.println("[log] KyjBoardChaeBun >>> : " + KyjHelloBoardSqlQueryMap.KYJ_BOARD_KNO_CHAEBUN);
			if(rsRs!=null){
				while(rsRs.next()){
					commNO = rsRs.getString("COMMNO");
				}
			}
			System.out.println("[log] commNO >>> : " + commNO);
			if(1==commNO.length()){
				commNO = "000" + commNO;
			}
			if(2==commNO.length()){
				commNO = "00" + commNO;
			}
			if(3==commNO.length()){
				commNO = "0" + commNO;
			}
			System.out.println("[log] commNO >>> : " + commNO);
			commNO = BIZ_BOARD_B + commNO;
			System.out.println("[log] B+commNO >>> : " + commNO);
			
		}catch(Exception e){
			System.out.println("[error] DB 에러 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(conn, pstmt, rsRs);
		}
		System.out.println("[log] KyjChaeBunClass.bFormat() 종료 <<< ");
		return commNO;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[log] common.KyjChaeBunClass 시작 >>> ");
		
		String bFormat=KyjChaeBunClass.bFormat();
		System.out.println("[log] commNumber >>> : " + bFormat);
		
		System.out.println("[log] common.KyjChaeBunClass 종료 <<< ");
	} // end of KyjChaeBunClass.main()
} // end of KyjChaeBunClass
