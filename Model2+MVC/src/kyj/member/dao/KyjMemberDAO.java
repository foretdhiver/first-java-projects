package kyj.member.dao;

import java.util.ArrayList;

import kyj.member.vo.KyjMemberVO;

public interface KyjMemberDAO {
	public boolean insertKyjMember(KyjMemberVO kvo);
	public boolean updateKyjMember(KyjMemberVO kvo);
	public boolean updateKyjMember1(KyjMemberVO kvo);
	public boolean deleteKyjMember(KyjMemberVO kvo);
	
	public ArrayList<KyjMemberVO> selectKyjMember();
	public ArrayList<KyjMemberVO> searchKyjMember(KyjMemberVO kvo);
	public ArrayList<KyjMemberVO> likeSearchKyjMember(KyjMemberVO kvo);
	
	// �α��� ��� 1 select
	public ArrayList<KyjMemberVO> loginKyjMember(KyjMemberVO kvo);
	
	// ���̵� �ߺ�üũ
	public int idValueCkeck(KyjMemberVO kvo);
	
} // end of KyjMemberDAO