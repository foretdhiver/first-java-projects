package com.kyjspring.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyjspring.member.dao.MemberMapper;
import com.kyjspring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public int insertMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.insertMember ���� >>> ");
		int list = memberMapper.insertMember(param);
		System.out.println("[log] MemberServiceImpl.insertMember ���� <<< ");
		return list;
	}

	@Override
	public List<MemberVO> listMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.listMember ���� >>> ");
		List<MemberVO> list = memberMapper.listMember(param);
		System.out.println("[log] MemberServiceImpl.listMember ���� <<< ");
		return list;
	}

	@Override
	public MemberVO selectMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.selectMember ���� >>> ");
		System.out.println("[log] MemberServiceImpl.selectMember ���� <<< ");
		return (MemberVO)memberMapper.selectMember(param);
	}

	@Override
	public List<MemberVO> chaebunMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.chaebunMember ���� >>> ");
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = memberMapper.chaebunMember(param);
		System.out.println("[log] MemberServiceImpl.chaebunMember ���� <<< ");
		return list;
	}

	@Override
	public int updateMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.updateMember ���� >>> ");
		int list = memberMapper.updateMember(param);
		System.out.println("[log] MemberServiceImpl.updateMember ���� <<< ");
		return list;
	}

	@Override
	public int deleteMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.deleteMember ���� >>> ");
		int list = memberMapper.deleteMember(param);
		System.out.println("[log] MemberServiceImpl.deleteMember ���� <<< ");
		return list;
	}

	@Override
	public int updateMember_w(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.updateMember_w ���� >>> ");
		int list = memberMapper.updateMember_w(param);
		System.out.println("[log] MemberServiceImpl.updateMember_w ���� <<< ");
		return list;
	}

	@Override
	public List<MemberVO> idValueCkeck(MemberVO param) {
		System.out.println("[log] MemberServiceImpl.idValueCkeck ���� >>> ");
		System.out.println("[log] MemberServiceImpl.idValueCkeck ���� <<< ");
		return memberMapper.idValueCkeck(param);
		// TODO Auto-generated method stub
	}


	@Override
	public List<MemberVO> logIn(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberServiceImpl.logIn ���� >>> ");
		List<MemberVO> list = memberMapper.logIn(param);
		System.out.println("[log] MemberServiceImpl.logIn ���� <<< ");
		return list;
	}


} // end of MemberServiceImpl class
