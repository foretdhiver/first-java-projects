package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.PjCalendarVO;

public class PjCalendarDaoImpl implements PjCalendarDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PjCalendarVO> calList(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList("calList");
	}

	@Override
	public PjCalendarVO calDetail(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("calDetail",cvo);
	}

	@Override
	public int calInsert(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		return sqlSession.insert("calInsert");
	}

	@Override
	public int calUpdate(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("calUpdate");
	}

	@Override
	public int calDelete(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("calDelete");
	}

	@Override
	public PjCalendarVO calChaebun(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
				
		return sqlSession.selectOne("calChaebun");
	}

}
