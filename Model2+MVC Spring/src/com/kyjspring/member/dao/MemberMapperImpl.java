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
		System.out.println("[log] MemberMapperImpl.insertMember 진입 >>> ");
		SqlSession session = getSqlSession();
		System.out.println("[log] MemberMapperImpl.insertMember 종료 <<< ");
		int list = session.insert(PACKAGE_PATH+"/insertMember");
		return list;
	}

	@Override
	public List<MemberVO> listMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.listMember 진입 >>> ");
		SqlSession session = getSqlSession();
		List<MemberVO> list = session.selectList(PACKAGE_PATH+"listMember");
		System.out.println("[log] MemberMapperImpl.listMember 종료 <<< ");
		return list;
	}

	@Override
	public MemberVO selectMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.selectMember 진입 >>> ");
		SqlSession session = getSqlSession();
		System.out.println("[log] MemberMapperImpl.selectMember 종료 <<< ");
		return (MemberVO)session.selectOne(PACKAGE_PATH+"selectMember");
	}

	@Override
	public List<MemberVO> chaebunMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.chaebunMember 진입 >>> ");
		SqlSession session = getSqlSession();
		List<MemberVO> list = session.selectList(PACKAGE_PATH+"chaebunMember");
		System.out.println("[log] MemberMapperImpl.chaebunMember 종료 <<< ");
		return list;
	}

	@Override
	public int updateMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.updateMember 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateMember");
		System.out.println("[log] MemberMapperImpl.updateMember 종료 <<< ");
		return list;
	}

	@Override
	public int deleteMember(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.deleteMember 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/deleteMember");
		System.out.println("[log] MemberMapperImpl.deleteMember 종료 <<< ");
		return list;
	}

//	@Override
//	public void logOut(HttpSession session) {
//		// TODO Auto-generated method stub	
//		System.out.println("[log] MemberMapperImpl.logOut 진입 >>> ");
//		session.invalidate();
//		System.out.println("[log] MemberMapperImpl.logOut 종료 <<< ");
//	}

	@Override
	public int updateMember_w(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.updateMember_w 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateMember_w");
		System.out.println("[log] MemberMapperImpl.updateMember_w 종료 <<< ");
		return list;
	}

	@Override
	public List<MemberVO> idValueCkeck(MemberVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] MemberMapperImpl.idValueCkeck 진입 >>> ");
		SqlSession session = getSqlSession();
		System.out.println("[log] MemberMapperImpl.idValueCkeck 종료 <<< ");
		return session.selectOne(PACKAGE_PATH+"/idValueCkeck");
	}

	@Override
	public List<MemberVO> logIn(MemberVO param) {
		System.out.println("[log] MemberMapperImpl.loginMember 진입 >>> ");
		SqlSession session = getSqlSession();
		List<MemberVO> list = session.selectOne(PACKAGE_PATH+"logIn");
		System.out.println("[log] MemberMapperImpl.loginMember 종료 <<< ");
		return list;
	}
} // end of MemberMapperImpl class