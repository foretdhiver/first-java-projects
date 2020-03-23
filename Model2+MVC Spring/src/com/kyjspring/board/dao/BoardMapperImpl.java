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
		System.out.println("[log] BoardMapperImpl.insertBoard ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/insertBoard");
		System.out.println("[log] BoardMapperImpl.insertBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> chaebunBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.chaebunBoard ���� >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectList(PACKAGE_PATH+"chaebunBoard");
		System.out.println("[log] BoardMapperImpl.chaebunBoard ���� <<< ");		
		return list;
	}

	@Override
	public List<BoardVO> listBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.listBoard ���� >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectList(PACKAGE_PATH+"listBoard");
		System.out.println("[log] BoardMapperImpl.listBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> selectBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.selectBoard ���� >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectList(PACKAGE_PATH+"selectBoard");
		System.out.println("[log] BoardMapperImpl.selectBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> pwValueCheck(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.pwValueCheck ���� >>> ");
		SqlSession session = getSqlSession();
		List<BoardVO> list = session.selectOne(PACKAGE_PATH+"/pwValueCheck");
		System.out.println("[log] BoardMapperImpl.pwValueCheck ���� <<< ");
		return list;
	}

	@Override
	public int updateBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.updateBoard ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateBoard");
		System.out.println("[log] BoardMapperImpl.updateBoard ���� <<< ");
		return list;
	}

	@Override
	public int updateBoard_w(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.updateBoard_w ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/updateBoard_w");
		System.out.println("[log] BoardMapperImpl.updateBoard_w ���� <<< ");
		return list;
	}

	@Override
	public int deleteBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardMapperImpl.deleteBoard ���� >>> ");
		SqlSession session = getSqlSession();
		int list = session.insert(PACKAGE_PATH+"/deleteBoard");
		System.out.println("[log] BoardMapperImpl.deleteBoard ���� <<< ");
		return list;
	}
}
