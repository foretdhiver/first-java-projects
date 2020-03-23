package bitcamp.java142.ch5.kyjjf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import bitcamp.java142.ch5.kyjjf.common.KyjChaeBunClass;
import bitcamp.java142.ch5.kyjjf.sql.KyjSqlQueryMap;
import bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO;
import bitcamp.java142.ch5.kyjjf.common.KyjConnProperty;

public class KyjMemberDAOImpl implements KyjMemberDAO {

	@Override
	public boolean insertKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.insertKyjMember() 시작 >>> " );
		System.out.println("(log) kvo 참조변수 주소값 : " + _kvo);
		System.out.println("(log) 입력할 정보 출력 >>> : " + _kvo.getKid()+" "
													  + _kvo.getKpw()+" "
													  + _kvo.getKname()+" "
													  + _kvo.getKhp()+" "
													  + _kvo.getKbirth()+" "
													  + _kvo.getKemail()+" "
													  + _kvo.getKpostno()+" "
													  + _kvo.getKjuso());
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con.getAutoCommit >>> : " + con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("(log) con.getAutoCommit >>> : " + con.getAutoCommit());
			pstmt = con.prepareStatement(KyjSqlQueryMap.getInsertQuery());
			System.out.println("\n(log) KyjSqlQueryMap.getInsertQuery() >>> 쿼리문 확인 ");
			System.out.println(KyjSqlQueryMap.getInsertQuery());
			
			pstmt.setString(1, KyjChaeBunClass.ymdFormat());
			pstmt.setString(2, _kvo.getKid());
			pstmt.setString(3, _kvo.getKpw());
			pstmt.setString(4, _kvo.getKname());
			pstmt.setString(5, _kvo.getKhp());
			pstmt.setString(6, _kvo.getKbirth());
			pstmt.setString(7, _kvo.getKemail());
			pstmt.setString(8, _kvo.getKpostno());
			pstmt.setString(9, _kvo.getKjuso());
			nCnt = pstmt.executeUpdate();
			
			boolean b = !con.getAutoCommit();
			System.out.println("(log) boolean 확인 >>> : " + b);
			if(!con.getAutoCommit())con.commit();
			System.out.println("방금 넣은 사람 이름 >>>>>>  : " + _kvo.getKname());
			System.out.println("(log) insert된 행의 갯수 확인 >>> " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
		}catch(Exception e){
			System.out.println("(log) 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		if(nCnt==0) return false;
		System.out.println("(log) KyjMemberDAOImpl.insertKyjMember() 끝 >>> " );
		return true;
	}

	@Override
	public boolean updateKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.updateKyjMember() 시작 >>> " );
		System.out.println("(log) _kvo 참조변수 주소값 : " + _kvo);
		System.out.println("(log) 입력할 정보 출력 >>> : " + _kvo.getKemail());
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getUpdateQuery());
			System.out.println("(log) pstmt >>> : " + pstmt);
			System.out.println("\n(log) KyjSqlQueryMap.getUpdateQuery() >>> 쿼리문 확인 ");
			System.out.println(KyjSqlQueryMap.getUpdateQuery());
			pstmt.setString(1, _kvo.getKemail());
			pstmt.setString(2, _kvo.getKname());
			
			nCnt = pstmt.executeUpdate();
			
			if(!con.getAutoCommit()) con.commit();
			System.out.println("(log) nCnt >>> : " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
		}catch(Exception e){
			System.out.println("(log) 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		if(nCnt==0) return false;
		System.out.println("(log) KyjMemberDAOImpl.updateKyjMember() 끝 >>> " );
		return true;
	}

	@Override
	public boolean deleteKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.deleteKyjMember() 시작 >>> ");
		System.out.println("(log) _kvo 참조변수 주소값 : " + _kvo);
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getDeleteQuery());
			System.out.println("(log) pstmt >>> : " + pstmt);
			System.out.println("\n(log) KyjSqlQueryMap.getDeleteQuery() >>> 쿼리문 확인 ");
			System.out.println(KyjSqlQueryMap.getDeleteQuery());
			
			pstmt.setString(1, _kvo.getKname());
			nCnt = pstmt.executeUpdate();
			
			if(!con.getAutoCommit()) con.commit();
			System.out.println("(log) delete 잘 되었나 >>> " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
			
		}catch(Exception e){
			System.out.println("(log) 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		if (nCnt==0) return false;
		System.out.println("(log) KyjMemberDAOImpl.deleteKyjMember() 끝 >>> ");
		return true;
	}

	@Override
	public ArrayList<KyjMemberVO> selectKyjMember() {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.selectKyjMember() 시작 >>> ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		ArrayList<KyjMemberVO> aList = null;
		KyjMemberVO kvo= null;
		
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getSelectQuery());
			rsRs = pstmt.executeQuery();
			System.out.println("(log) rsRs >>> : " + rsRs);
			
//			boolean bool = rsRs!=null;
//			System.out.println("(log) 데이터 유무 확인 >>> " + bool);
//			System.out.println("(log) rsRs.next() >>> : " + rsRs.next());
			if(rsRs!=null){
				aList = new ArrayList<KyjMemberVO>();
				while(rsRs.next()){
					kvo = new KyjMemberVO();
					kvo.setKnumm(rsRs.getString("KMEM"));
					kvo.setKid(rsRs.getString("KID"));
					kvo.setKpw(rsRs.getString("KPW"));
					kvo.setKname(rsRs.getString("KNAME"));
					kvo.setKhp(rsRs.getString("KHP"));
					kvo.setKbirth(rsRs.getString("KBIRTH"));
					kvo.setKemail(rsRs.getString("KEMAIL"));
					kvo.setKpostno(rsRs.getString("KPOSTNO"));
					kvo.setKjuso(rsRs.getString("KJUSO"));
					kvo.setKdeleteyn(rsRs.getString("KDELETEYN"));
					kvo.setKinsertdate(rsRs.getString("KINSERTDATE"));
					kvo.setKupdatedate(rsRs.getString("KUPDATEDATE"));				
					aList.add(kvo);
					System.out.println("(log) aList >>> : " + aList);
				}
				System.out.println("(log) aList.size() >>> : " + aList.size());
			}
		}catch(Exception e){
			System.out.println("(log) 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("(log) KyjMemberDAOImpl.selectKyjMember() 끝 >>> ");
		return aList;
	}

	@Override
	public ArrayList<KyjMemberVO> searchKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.searchKyjMember() 시작 >>> ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		ArrayList<KyjMemberVO> aList = null;
		KyjMemberVO kvo = null;
		
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getSearchQuery());
			System.out.println("\n(log) KyjSqlQueryMap.getSearchQuery() 쿼리문 확인 >>> ");
			System.out.println(KyjSqlQueryMap.getSearchQuery());
			pstmt.setString(1, _kvo.getKname());
			System.out.println("(log) _kvo >>> : " + _kvo.getKname());
			rsRs = pstmt.executeQuery();
			System.out.println("(log) rsRs >>> : " + rsRs);
			
			boolean bool = rsRs!=null;
			System.out.println("(log) KyjSqlQueryMap.getSearchQuery() 데이터 유무 확인 >>> " + bool);
			if(rsRs!=null){
				aList = new ArrayList<KyjMemberVO>();
				while(rsRs.next()){
					kvo = new KyjMemberVO();
					kvo.setKnumm(rsRs.getString("KMEM"));
					kvo.setKid(rsRs.getString("KID"));
					kvo.setKpw(rsRs.getString("KPW"));
					kvo.setKname(rsRs.getString("KNAME"));
					kvo.setKhp(rsRs.getString("KHP"));
					kvo.setKbirth(rsRs.getString("KBIRTH"));
					kvo.setKemail(rsRs.getString("KEMAIL"));
					kvo.setKpostno(rsRs.getString("KPOSTNO"));
					kvo.setKjuso(rsRs.getString("KJUSO"));
					kvo.setKdeleteyn(rsRs.getString("KDELETEYN"));
					kvo.setKinsertdate(rsRs.getString("KINSERTDATE"));
					kvo.setKupdatedate(rsRs.getString("KUPDATEDATE"));				
					aList.add(kvo);
				}
				System.out.println("(log) aList.size() >>> : " + aList.size());
			}else{
				System.out.println("검색 실패");
			}
		}catch(Exception e){
			System.out.println("(log) 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("(log) KyjMemberDAOImpl.searchKyjMember() 끝 >>> ");
		return aList;
	}

	@Override
	public ArrayList<KyjMemberVO> likeSearchKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.likeSearchKyjMember() 시작 >>> ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		ArrayList<KyjMemberVO> aList = null;
		KyjMemberVO kvo = null;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getLikeSearchQuery());
			System.out.println("(log) pstmt >>> : " + pstmt);
			System.out.println("\n(log) KyjSqlQueryMap.getLikeSearchQuery() 쿼리문 확인 >>> ");
			System.out.println(KyjSqlQueryMap.getLikeSearchQuery());
			pstmt.setString(1, _kvo.getKname());
			System.out.println("(log) _kvo >>> : " + _kvo.getKname());
			rsRs = pstmt.executeQuery();
			System.out.println("(log) rsRs >>> : " + rsRs);
			
			boolean bool = rsRs!=null;
			System.out.println("(log) KyjSqlQueryMap.getLikeSearchQuery() 데이터 유무 확인 >>> " + bool);
			if(rsRs!=null){
				aList = new ArrayList<KyjMemberVO>();
				while(rsRs.next()){
					kvo = new KyjMemberVO();
					kvo.setKnumm(rsRs.getString("KMEM"));
					kvo.setKid(rsRs.getString("KID"));
					kvo.setKpw(rsRs.getString("KPW"));
					kvo.setKname(rsRs.getString("KNAME"));
					kvo.setKhp(rsRs.getString("KHP"));
					kvo.setKbirth(rsRs.getString("KBIRTH"));
					kvo.setKemail(rsRs.getString("KEMAIL"));
					kvo.setKpostno(rsRs.getString("KPOSTNO"));
					kvo.setKjuso(rsRs.getString("KJUSO"));
					kvo.setKdeleteyn(rsRs.getString("KDELETEYN"));
					kvo.setKinsertdate(rsRs.getString("KINSERTDATE"));
					kvo.setKupdatedate(rsRs.getString("KUPDATEDATE"));				
					aList.add(kvo);
//					
				}
				System.out.println("(log) aList.size() >>> : " + aList.size());
			}else{
				System.out.println("검색 실패");
			}
		}catch(Exception e){
			System.out.println("(log) 에러가 >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("(log) KyjMemberDAOImpl.likeSearchKyjMember() 끝 >>> ");
		return aList;
	}
	
	// 로그인
	    @Override
		public ArrayList<KyjMemberVO> loginKyjMember(KyjMemberVO _kvo){
			System.out.println("(log) KyjMemberDAOImpl.loginKyjMember() 시작 >>> ");
			System.out.println("(log) id pw >>> : " + _kvo.getKid()+" " + _kvo.getKpw()+" ");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rsRs = null;
			ArrayList<KyjMemberVO> aList = null;
			KyjMemberVO kvo= null;
			
			try{
				con = KyjConnProperty.getConnection();
				System.out.println("(log) con >>> : " + con);
				pstmt = con.prepareStatement(KyjSqlQueryMap.getLoginQuery());
				System.out.println("\n(log) KyjSqlQueryMap.getLoginQuery() 쿼리문 확인 >>> ");
				System.out.println(KyjSqlQueryMap.getLoginQuery());
				pstmt.setString(1, _kvo.getKid());
				pstmt.setString(2, _kvo.getKpw());
				rsRs = pstmt.executeQuery();
				System.out.println("(log) rsRs >>> : " + rsRs);
				
				boolean bool = rsRs!=null;
				System.out.println("(log) 데이터 유무 확인 >>> " + bool);
				if(rsRs!=null){
					aList = new ArrayList<KyjMemberVO>();
					while(rsRs.next()){
						kvo = new KyjMemberVO();
						kvo.setKid(rsRs.getString(1));
						kvo.setKpw(rsRs.getString(2));
						kvo.setKdeleteyn(rsRs.getString(3));			
						aList.add(kvo);
						System.out.println("(log) aList >>> : " + aList);
					}
					System.out.println("(log) aList.size() >>> : " + aList.size());
				}
			}catch(Exception e){
				System.out.println("(log) 에러가 >>> : " + e.getMessage());
			}finally{
				KyjConnProperty.conClose(con, pstmt, rsRs);
			}
			System.out.println("(log) KyjMemberDAOImpl.loginKyjMember() 끝 >>> ");
			return aList;
			
		}
	
}


