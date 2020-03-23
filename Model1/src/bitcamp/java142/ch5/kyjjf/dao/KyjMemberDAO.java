package bitcamp.java142.ch5.kyjjf.dao;

import java.util.ArrayList;

import bitcamp.java142.ch5.kyjjf.vo.KyjMemberVO;

public interface KyjMemberDAO {
	public boolean insertKyjMember(KyjMemberVO kvo);
	public boolean updateKyjMember(KyjMemberVO kvo);
	public boolean deleteKyjMember(KyjMemberVO kvo);
	
	public ArrayList<KyjMemberVO> selectKyjMember();
	public ArrayList<KyjMemberVO> searchKyjMember(KyjMemberVO kvo);
	public ArrayList<KyjMemberVO> likeSearchKyjMember(KyjMemberVO kvo);
	
	// �α��� ��� 1 select
	public ArrayList<KyjMemberVO> loginKyjMember(KyjMemberVO kvo);
	
} // end of KyjMemberDAO
