package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.BDComVO;

@Repository("bDCDao")
public class BDCDaoImpl implements BDCDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BDComVO> commentList(BDComVO bdc) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("commentList", bdc);
	}
	@Override
	public BDComVO commentChaebun(BDComVO bdc) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("commentChaebun", bdc);
	}
	@Override
	public int commentInsert(BDComVO bdc) {
		// TODO Auto-generated method stub
		return sqlSession.insert("commentInsert", bdc);
	}
	@Override
	public int commentDelete(BDComVO bdc) {
		// TODO Auto-generated method stub
		return sqlSession.update("commentDelete", bdc);
	}
	@Override
	public int commentUpdate(BDComVO bdc) {
		// TODO Auto-generated method stub
		return sqlSession.update("commentUpdate", bdc);
	}
	
	
	
	
	

}
