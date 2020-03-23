package com.kyjspring.member.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.kyjspring.member.vo.MemberVO;

public class MemberMapperImpl extends SqlSessionDaoSupport implements MemberMapper {

	private final String PACKAGE_PATH = "com.kyjspring.member.dao.MemberDAO";
	
	@Override
	public int insertMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.insertMember ���� >>> ");
		SqlSession session = getSqlSession();
		System.out.println("[log] MemberMapperImpl.insertMember ���� <<< ");
		int list = session.insert(PACKAGE_PATH+"/insertMember");
		return list;
	}

	@Override
	public List<MemberVO> listMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.listMember ���� >>> ");
		SqlSession session = getSqlSession();
		List<MemberVO> list = session.selectList(PACKAGE_PATH+"listMember");
		System.out.println("[log] MemberMapperImpl.listMember ���� <<< ");
		return list;
	}

	@Override
	public MemberVO selectMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.selectMember ���� >>> ");
		SqlSession session = getSqlSession();
		System.out.println("[log] MemberMapperImpl.selectMember ���� <<< ");
		return (MemberVO)session.selectOne(PACKAGE_PATH+"selectMember");
	}

	@Override
	public List<MemberVO> chaebunMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.chaebunMember ���� >>> ");
		SqlSession session = getSqlSession();
		List<MemberVO> list = session.selectList(PACKAGE_PATH+"chaebunMember");
		System.out.println("[log] MemberMapperImpl.chaebunMember ���� <<< ");
		return list;
	}

	@Override
	public int updateMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.updateMember ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateMember");
		System.out.println("[log] MemberMapperImpl.updateMember ���� <<< ");
		return list;
	}

	@Override
	public int deleteMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.deleteMember ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/deleteMember");
		System.out.println("[log] MemberMapperImpl.deleteMember ���� <<< ");
		return list;
	}

//	@Override
//	public void logOut(HttpSession session) {
//		// TODO Auto-generated method stub	
//		System.out.println("[log] MemberMapperImpl.logOut ���� >>> ");
//		session.invalidate();
//		System.out.println("[log] MemberMapperImpl.logOut ���� <<< ");
//	}

	@Override
	public int updateMember_w(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.updateMember_w ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateMember_w");
		System.out.println("[log] MemberMapperImpl.updateMember_w ���� <<< ");
		return list;
	}

	@Override
	public List<MemberVO> idValueCkeck(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.idValueCkeck ���� >>> ");
		SqlSession session = getSqlSession();
		System.out.println("[log] MemberMapperImpl.idValueCkeck ���� <<< ");
		return session.selectOne(PACKAGE_PATH+"/idValueCkeck");
	}

	@Override
	public List<MemberVO> logIn(MemberVO param) {
		System.out.println("[log] MemberMapperImpl.loginMember ���� >>> ");
		SqlSession session = getSqlSession();
		List<MemberVO> list = session.selectOne(PACKAGE_PATH+"logIn");
		System.out.println("[log] MemberMapperImpl.loginMember ���� <<< ");
		return list;
	}
} // end of MemberMapperImpl class