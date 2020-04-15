package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.BTComVO;

@Repository("bTCDao")
public class BTCDaoImpl implements BTCDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BTComVO> commentList(BTComVO btc) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("commentListT", btc);
	}
	@Override
	public BTComVO commentChaebun(BTComVO btc) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("commentChaebunT", btc);
	}
	@Override
	public int commentInsert(BTComVO btc) {
		// TODO Auto-generated method stub
		return sqlSession.insert("commentInsertT", btc);
	}
	@Override
	public int commentDelete(BTComVO btc) {
		// TODO Auto-generated method stub
		return sqlSession.update("commentDeleteT", btc);
	}
	@Override
	public int commentUpdate(BTComVO btc) {
		// TODO Auto-generated method stub
		return sqlSession.update("commentUpdateT", btc);
	}
	
	
	
	
	

}
