package com.kyjspring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kyjspring.board.vo.BoardVO;

public class BoardMapperImpl extends SqlSessionDaoSupport implements BoardMapper {
	private final String PACKAGE_PATH = "com.kyjspring.board.dao.BoardDAO";

	@Override
	public int insertBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.insertBoard 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/insertBoard");
		System.out.println("[log] BoardMapperImpl.insertBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> chaebunBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.chaebunBoard 진입 >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectList(PACKAGE_PATH+"chaebunBoard");
		System.out.println("[log] BoardMapperImpl.chaebunBoard 종료 <<< ");		
		return list;
	}

	@Override
	public List<BoardVO> listBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.listBoard 진입 >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectList(PACKAGE_PATH+"listBoard");
		System.out.println("[log] BoardMapperImpl.listBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> selectBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.selectBoard 진입 >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectList(PACKAGE_PATH+"selectBoard");
		System.out.println("[log] BoardMapperImpl.selectBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> pwValueCheck(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.pwValueCheck 진입 >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectOne(PACKAGE_PATH+"/pwValueCheck");
		System.out.println("[log] BoardMapperImpl.pwValueCheck 종료 <<< ");
		return list;
	}

	@Override
	public int updateBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.updateBoard 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateBoard");
		System.out.println("[log] BoardMapperImpl.updateBoard 종료 <<< ");
		return list;
	}

	@Override
	public int updateBoard_w(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.updateBoard_w 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateBoard_w");
		System.out.println("[log] BoardMapperImpl.updateBoard_w 종료 <<< ");
		return list;
	}

	@Override
	public int deleteBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.deleteBoard 진입 >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/deleteBoard");
		System.out.println("[log] BoardMapperImpl.deleteBoard 종료 <<< ");
		return list;
	}
}
