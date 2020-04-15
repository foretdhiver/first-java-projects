package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.ge.vo.BTVO;

public class BTDaoImpl implements BTDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BTVO> boardTaskList(BTVO btvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardTaskList");
	}
	@Override
	public BTVO boardTaskDetail(BTVO btvo) {
		// TODO Auto-generated method stub
		System.out.println("bdvo.getbdno() >>> " + btvo.getBtno());
		return sqlSession.selectOne("boardTaskDetail", btvo);
	}
	@Override
	public BTVO boardTaskChebun(BTVO btvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardTaskChebun");
	}
	@Override
	public int boardTaskInsert(BTVO btvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardTaskInsert");
	}
	@Override
	public int boardTaskDelete(BTVO btvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardTaskDelete", btvo);
	}
	@Override
	public int boardTaskUpdate(BTVO btvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardTaskUpdate", btvo);
	}
	@Override
	public int boardTaskViewCntUp(BTVO btvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardTaskViewCntUp");
	}
	
}
