package com.kyjspring.member.dao;

import java.util.List;
import com.kyjspring.member.vo.MemberVO;

public interface MemberMapper {
	public int insertMember(MemberVO param);
	public int updateMember(MemberVO param);
	public int updateMember_w(MemberVO param);
	public int deleteMember(MemberVO param);
	public List<MemberVO> listMember(MemberVO param);
	public MemberVO selectMember(MemberVO param);
	public List<MemberVO> chaebunMember(MemberVO param);
	
	// 로그인 체크
//	public void logOut(HttpSession session);
	public List<MemberVO> logIn(MemberVO param);

	//아이디 중복 체크
	public List<MemberVO> idValueCkeck(MemberVO param);
} // end of MemberMapper interface