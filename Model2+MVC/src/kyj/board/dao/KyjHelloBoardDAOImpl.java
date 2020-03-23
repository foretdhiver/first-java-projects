package kyj.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kyj.board.common.KyjChaeBunClass;
import kyj.board.common.KyjConnProperty;
import kyj.board.sql.KyjHelloBoardSqlQueryMap;
import kyj.board.vo.KyjHelloBoardVO;
import kyj.member.vo.KyjMemberVO;

public class KyjHelloBoardDAOImpl implements KyjHelloBoardDAO {

	@Override
	public int insertKyjHelloBoard(KyjHelloBoardVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] KyjHelloBoardDAOImpl.insertKyjHelloBoard() 시작 >>> ");
		System.out.println("[log] _kvo 참조변수 주소값 >>> : " + _kvo);
		System.out.println("[log] 입력 할 정보 출력 >>> : " + _kvo.getKsubject()
													  + _kvo.getKname()
													  + _kvo.getKpw()
													  + _kvo.getKmemo());
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
			pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.getInsertQuery());
			
			pstmt.setString(1, KyjChaeBunClass.bFormat());
			pstmt.setString(2, _kvo.getKsubject());
			pstmt.setString(3, _kvo.getKname());
			pstmt.setString(4, _kvo.getKpw());
			pstmt.setString(5, _kvo.getKmemo());
			pstmt.setString(6, _kvo.getKdeleteyn());
			pstmt.setString(7, _kvo.getKimage());
			nCnt = pstmt.executeUpdate();
			
			boolean b = !con.getAutoCommit();
			System.out.println("[log] boolean 확인 >>> : " + b);
			if(!con.getAutoCommit())con.commit();
			System.out.println("[log] insert 된 행의 갯수 확인 >>> : " + nCnt);
		}catch(Exception e){
			System.out.println("[error] 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
//		if(nCnt==0) return 0;
		System.out.println("[log] KyjHelloBoardDAOImpl.insertKyjHelloBoard() 종료 <<<  ");
		return nCnt;
	}
	
	
	@Override
	public int updateKyjHelloBoard(KyjHelloBoardVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] KyjHelloBoardDAOImpl.updateKyjHelloBoard() 시작 >>> " );
		System.out.println("[log] _kvo 참조변수 주소값 : " + _kvo);
		System.out.println("[log] 입력할 정보 출력 >>> : " + _kvo.getKsubject() + " " + _kvo.getKmemo() + " " +_kvo.getKname() + " " +_kvo.getKpw() + " " +_kvo.getKimage() + " " +_kvo.getKno());
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
			pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.getUpdateQuery());
			pstmt.setString(1, _kvo.getKsubject());
			pstmt.setString(2, _kvo.getKname());
			pstmt.setString(6, _kvo.getKpw());
			pstmt.setString(3, _kvo.getKmemo());
			pstmt.setString(4, _kvo.getKimage());
			pstmt.setString(5, _kvo.getKno());
			
			nCnt = pstmt.executeUpdate();
			

			boolean b = !con.getAutoCommit();
			System.out.println("[log] boolean 확인 >>> : " + b);
			if(!con.getAutoCommit())con.commit();
			System.out.println("[log] update 된 행의 갯수 확인 >>> : " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
		}catch(Exception e){
			System.out.println("[log] 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		System.out.println("[log] KyjHelloBoardDAOImpl.updateKyjHelloBoard() 종료 <<<  " );

		return nCnt;
	}

	@Override
	public int deleteKyjHelloBoard(KyjHelloBoardVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] KyjHelloBoardDAOImpl.deleteKyjHelloBoard() 시작 >>> " );
		System.out.println("[log] _kvo 참조변수 주소값 : " + _kvo);		
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
			System.out.println("\n[log] KyjHelloBoardSqlQueryMap.getDeleteQuery() 쿼리문 확인 >>> ");
			System.out.println(KyjHelloBoardSqlQueryMap.getDeleteQuery());
			pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.getDeleteQuery());
			pstmt.setString(1, _kvo.getKno());
			pstmt.setString(2, _kvo.getKpw());
			nCnt = pstmt.executeUpdate();
			

			boolean b = !con.getAutoCommit();
			System.out.println("[log] boolean 확인 >>> : " + b);
			if(!con.getAutoCommit())con.commit();
			System.out.println("[log] update 된 행의 갯수 확인 >>> : " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
		}catch(Exception e){
			System.out.println("[log] 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		System.out.println("[log] KyjHelloBoardDAOImpl.deleteKyjHelloBoard() 종료 <<< " );

		return nCnt;
	}

	@Override
	public ArrayList<KyjHelloBoardVO> selectKyjHelloBoard() {
		// TODO Auto-generated method stub
		System.out.println("[log] KyjHelloBoardDAOImpl.selectKyjHelloBoard() 시작 >>> ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		ArrayList<KyjHelloBoardVO> aList = null;
		KyjHelloBoardVO kvo = null;

		try{
			con = KyjConnProperty.getConnection();
			System.out.println("[log] con >>> : " + con);
			pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.getSelectQuery());
			rsRs = pstmt.executeQuery();
			System.out.println("[log] rsRs >>> : " + rsRs);
			
			if(rsRs!=null){
				aList = new ArrayList<KyjHelloBoardVO>();
				while(rsRs.next()){
					kvo = new KyjHelloBoardVO();
					kvo.setKno(rsRs.getString(1));
					kvo.setKsubject(rsRs.getString(2));
					kvo.setKname(rsRs.getString(3));
					kvo.setKpw(rsRs.getString(4));
					kvo.setKmemo(rsRs.getString(5));
					kvo.setKdeleteyn(rsRs.getString(6));
					kvo.setKinsertdate(rsRs.getString(7));
					kvo.setKupdatedate(rsRs.getString(8));
					kvo.setKimage(rsRs.getString(9));
					aList.add(kvo);
					System.out.println("[log] aList >>> : " + aList);
				}
				System.out.println("[log] aList.size() >>> : " + aList.size());
			}
		}catch(Exception e){
			System.out.println("[log] 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("[log] KyjHelloBoardDAOImpl.selectKyjHelloBoard() 종료 <<< ");

		return aList;
	}


	@Override
	public ArrayList<KyjHelloBoardVO> selectKyjBoard(KyjHelloBoardVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("[log] KyjHelloBoardDAOImpl.selectKyjBoard() 시작 >>> ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		ArrayList<KyjHelloBoardVO> aList = null;
		KyjHelloBoardVO kvo = null;
		
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.getSearchQuery());
			System.out.println("\n[log] KyjHelloBoardSqlQueryMap.getSearchQuery() 쿼리문 확인 >>> ");
			System.out.println(KyjHelloBoardSqlQueryMap.getSearchQuery());
			pstmt.setString(1, _kvo.getKno());
			System.out.println("[log] _kvo >>> : " + _kvo.getKname());
			rsRs = pstmt.executeQuery();
			System.out.println("[log] rsRs >>> : " + rsRs);
			
			boolean bool = rsRs!=null;
			System.out.println("[log] KyjHelloBoardSqlQueryMap.getSearchQuery() 데이터 유무 확인 >>> " + bool);
			
			if(rsRs!=null){
				aList = new ArrayList<KyjHelloBoardVO>();
				while(rsRs.next()){
					kvo = new KyjHelloBoardVO();
					kvo.setKno(rsRs.getString(1));
					kvo.setKsubject(rsRs.getString(2));
					kvo.setKname(rsRs.getString(3));
					kvo.setKpw(rsRs.getString(4));
					kvo.setKmemo(rsRs.getString(5));
					kvo.setKdeleteyn(rsRs.getString(6));
					kvo.setKinsertdate(rsRs.getString(7));
					kvo.setKupdatedate(rsRs.getString(8));
					kvo.setKimage(rsRs.getString(9));
					aList.add(kvo);
					System.out.println("[log] aList >>> : " + aList);
				}
				System.out.println("[log] aList.size() >>> : " + aList.size());
			}else{
				System.out.println("검색 실패");
			}
		}catch(Exception e){
			System.out.println("[log] 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("[log] KyjHelloBoardDAOImpl.selectKyjBoard() 종료 <<< ");
		return aList;
	}


	@Override
	public int updateKyjHelloBoard_1(KyjHelloBoardVO _kvo) {
		// TODO Auto-generated method stub
				System.out.println("[log] KyjHelloBoardDAOImpl.updateKyjHelloBoard_1() 시작 >>> " );
				System.out.println("[log] _kvo 참조변수 주소값 : " + _kvo);
				System.out.println("[log] 입력할 정보 출력 >>> : " + _kvo.getKsubject() + " " + _kvo.getKmemo());
				
				Connection con = null;
				PreparedStatement pstmt = null;
				int nCnt = 0;
				try{
					con = KyjConnProperty.getConnection();
					System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
					con.setAutoCommit(false);
					System.out.println("[log] con.getAutuCommit >>> : " + con.getAutoCommit());
					pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.getUpdateQuery_1());
					pstmt.setString(1, _kvo.getKsubject());
					pstmt.setString(2, _kvo.getKname());
					pstmt.setString(5, _kvo.getKpw());
					pstmt.setString(3, _kvo.getKmemo());
					pstmt.setString(4, _kvo.getKno());
					nCnt = pstmt.executeUpdate();
					

					boolean b = !con.getAutoCommit();
					System.out.println("[log] boolean 확인 >>> : " + b);
					if(!con.getAutoCommit())con.commit();
					System.out.println("[log] update 된 행의 갯수 확인 >>> : " + nCnt);
					KyjConnProperty.conClose(con, pstmt);
				}catch(Exception e){
					System.out.println("[log] 에러가 >>> : " + e.getMessage());
				}finally{
					KyjConnProperty.conClose(con, pstmt);
				}
				System.out.println("[log] KyjHelloBoardDAOImpl.updateKyjHelloBoard_1() 종료 <<<  " );

				return nCnt;
	}


	@Override
	public int pwValCheck(KyjHelloBoardVO _kvo) {
		// TODO Auto-generated method stub
			System.out.println("(log) KyjMemberDAOImpl.pwValCheck() 시작 >>> " );
			System.out.println("(log) _kvo 참조변수 주소값 : " + _kvo);
			Connection con = null;
			PreparedStatement pstmt = null;
			int nCnt = 0;
			// 1= 게시물 삭제수정 가능
			// 0= 게시물 삭제수정 불가
			
			if(nCnt==0){
				try{
					con = KyjConnProperty.getConnection();
					System.out.println("(log) con >>> : " + con);
					pstmt = con.prepareStatement(KyjHelloBoardSqlQueryMap.pwValCheck());
					System.out.println("(log) pstmt >>> : " + pstmt);
					System.out.println("\n(log) KyjHelloBoardSqlQueryMap.pwValCheck() >>> 쿼리문 확인 ");
					System.out.println(KyjHelloBoardSqlQueryMap.pwValCheck());
					pstmt.setString(1, _kvo.getKpw());
					pstmt.setString(2, _kvo.getKno());
					nCnt = pstmt.executeUpdate();
					
					if(!con.getAutoCommit()) con.commit();
					System.out.println("(log) nCnt >>> : " + nCnt);
					KyjConnProperty.conClose(con, pstmt);
				}catch(Exception e){
					System.out.println("(log) 에러가 >>> : " + e.getMessage());
				}finally{
					KyjConnProperty.conClose(con, pstmt);
				}
			}
			
			System.out.println("(log) KyjMemberDAOImpl.pwValCheck() 끝 >>> " );
			return nCnt;			
			
	}

}