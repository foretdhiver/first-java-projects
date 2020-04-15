package com.spring.ge.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.ge.vo.BNVO;

public class BNDaoImpl implements BNDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BNVO> boardNoticeList(BNVO bnvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardNoticeList");
	}
	@Override
	public BNVO boardNoticeDetail(BNVO bnvo) {
		// TODO Auto-generated method stub
		System.out.println("bdvo.getBnno() >>> " + bnvo.getBnno());
		return sqlSession.selectOne("boardNoticeDetail", bnvo);
	}
	@Override
	public BNVO boardNoticeChebun(BNVO bnvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardNoticeChebun");
	}
	@Override
	public int boardNoticeInsert(BNVO bnvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardNoticeInsert");
	}
	@Override
	public int boardNoticeDelete(BNVO bnvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardNoticeDelete", bnvo);
	}
	@Override
	public int boardNoticeUpdate(BNVO bnvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardNoticeUpdate", bnvo);
	}
	@Override
	public int boardNoticeViewCntUp(BNVO bnvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardNoticeViewCntUp");
	}
	@Override
	public List<BNVO> boardNoticeMainList(BNVO bnvo){
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardNoticeMainList",bnvo);
	}
	
	
	
}
