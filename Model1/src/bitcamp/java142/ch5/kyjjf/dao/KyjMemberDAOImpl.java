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
		System.out.println("(log) KyjMemberDAOImpl.insertKyjMember() ���� >>> " );
		System.out.println("(log) kvo �������� �ּҰ� : " + _kvo);
		System.out.println("(log) �Է��� ���� ��� >>> : " + _kvo.getKid()+" "
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
			System.out.println("\n(log) KyjSqlQueryMap.getInsertQuery() >>> ������ Ȯ�� ");
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
			System.out.println("(log) boolean Ȯ�� >>> : " + b);
			if(!con.getAutoCommit())con.commit();
			System.out.println("��� ���� ��� �̸� >>>>>>  : " + _kvo.getKname());
			System.out.println("(log) insert�� ���� ���� Ȯ�� >>> " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
		}catch(Exception e){
			System.out.println("(log) ������ >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		if(nCnt==0) return false;
		System.out.println("(log) KyjMemberDAOImpl.insertKyjMember() �� >>> " );
		return true;
	}

	@Override
	public boolean updateKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.updateKyjMember() ���� >>> " );
		System.out.println("(log) _kvo �������� �ּҰ� : " + _kvo);
		System.out.println("(log) �Է��� ���� ��� >>> : " + _kvo.getKemail());
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getUpdateQuery());
			System.out.println("(log) pstmt >>> : " + pstmt);
			System.out.println("\n(log) KyjSqlQueryMap.getUpdateQuery() >>> ������ Ȯ�� ");
			System.out.println(KyjSqlQueryMap.getUpdateQuery());
			pstmt.setString(1, _kvo.getKemail());
			pstmt.setString(2, _kvo.getKname());
			
			nCnt = pstmt.executeUpdate();
			
			if(!con.getAutoCommit()) con.commit();
			System.out.println("(log) nCnt >>> : " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
		}catch(Exception e){
			System.out.println("(log) ������ >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		if(nCnt==0) return false;
		System.out.println("(log) KyjMemberDAOImpl.updateKyjMember() �� >>> " );
		return true;
	}

	@Override
	public boolean deleteKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.deleteKyjMember() ���� >>> ");
		System.out.println("(log) _kvo �������� �ּҰ� : " + _kvo);
		Connection con = null;
		PreparedStatement pstmt = null;
		int nCnt = 0;
		
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getDeleteQuery());
			System.out.println("(log) pstmt >>> : " + pstmt);
			System.out.println("\n(log) KyjSqlQueryMap.getDeleteQuery() >>> ������ Ȯ�� ");
			System.out.println(KyjSqlQueryMap.getDeleteQuery());
			
			pstmt.setString(1, _kvo.getKname());
			nCnt = pstmt.executeUpdate();
			
			if(!con.getAutoCommit()) con.commit();
			System.out.println("(log) delete �� �Ǿ��� >>> " + nCnt);
			KyjConnProperty.conClose(con, pstmt);
			
		}catch(Exception e){
			System.out.println("(log) ������ >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt);
		}
		if (nCnt==0) return false;
		System.out.println("(log) KyjMemberDAOImpl.deleteKyjMember() �� >>> ");
		return true;
	}

	@Override
	public ArrayList<KyjMemberVO> selectKyjMember() {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.selectKyjMember() ���� >>> ");
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
//			System.out.println("(log) ������ ���� Ȯ�� >>> " + bool);
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
			System.out.println("(log) ������ >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("(log) KyjMemberDAOImpl.selectKyjMember() �� >>> ");
		return aList;
	}

	@Override
	public ArrayList<KyjMemberVO> searchKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.searchKyjMember() ���� >>> ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rsRs = null;
		ArrayList<KyjMemberVO> aList = null;
		KyjMemberVO kvo = null;
		
		try{
			con = KyjConnProperty.getConnection();
			System.out.println("(log) con >>> : " + con);
			pstmt = con.prepareStatement(KyjSqlQueryMap.getSearchQuery());
			System.out.println("\n(log) KyjSqlQueryMap.getSearchQuery() ������ Ȯ�� >>> ");
			System.out.println(KyjSqlQueryMap.getSearchQuery());
			pstmt.setString(1, _kvo.getKname());
			System.out.println("(log) _kvo >>> : " + _kvo.getKname());
			rsRs = pstmt.executeQuery();
			System.out.println("(log) rsRs >>> : " + rsRs);
			
			boolean bool = rsRs!=null;
			System.out.println("(log) KyjSqlQueryMap.getSearchQuery() ������ ���� Ȯ�� >>> " + bool);
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
				System.out.println("�˻� ����");
			}
		}catch(Exception e){
			System.out.println("(log) ������ >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("(log) KyjMemberDAOImpl.searchKyjMember() �� >>> ");
		return aList;
	}

	@Override
	public ArrayList<KyjMemberVO> likeSearchKyjMember(KyjMemberVO _kvo) {
		// TODO Auto-generated method stub
		System.out.println("(log) KyjMemberDAOImpl.likeSearchKyjMember() ���� >>> ");
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
			System.out.println("\n(log) KyjSqlQueryMap.getLikeSearchQuery() ������ Ȯ�� >>> ");
			System.out.println(KyjSqlQueryMap.getLikeSearchQuery());
			pstmt.setString(1, _kvo.getKname());
			System.out.println("(log) _kvo >>> : " + _kvo.getKname());
			rsRs = pstmt.executeQuery();
			System.out.println("(log) rsRs >>> : " + rsRs);
			
			boolean bool = rsRs!=null;
			System.out.println("(log) KyjSqlQueryMap.getLikeSearchQuery() ������ ���� Ȯ�� >>> " + bool);
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
				System.out.println("�˻� ����");
			}
		}catch(Exception e){
			System.out.println("(log) ������ >>> : " + e.getMessage());
		}finally{
			KyjConnProperty.conClose(con, pstmt, rsRs);
		}
		System.out.println("(log) KyjMemberDAOImpl.likeSearchKyjMember() �� >>> ");
		return aList;
	}
	
	// �α���
	    @Override
		public ArrayList<KyjMemberVO> loginKyjMember(KyjMemberVO _kvo){
			System.out.println("(log) KyjMemberDAOImpl.loginKyjMember() ���� >>> ");
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
				System.out.println("\n(log) KyjSqlQueryMap.getLoginQuery() ������ Ȯ�� >>> ");
				System.out.println(KyjSqlQueryMap.getLoginQuery());
				pstmt.setString(1, _kvo.getKid());
				pstmt.setString(2, _kvo.getKpw());
				rsRs = pstmt.executeQuery();
				System.out.println("(log) rsRs >>> : " + rsRs);
				
				boolean bool = rsRs!=null;
				System.out.println("(log) ������ ���� Ȯ�� >>> " + bool);
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
				System.out.println("(log) ������ >>> : " + e.getMessage());
			}finally{
				KyjConnProperty.conClose(con, pstmt, rsRs);
			}
			System.out.println("(log) KyjMemberDAOImpl.loginKyjMember() �� >>> ");
			return aList;
			
		}
	
}


