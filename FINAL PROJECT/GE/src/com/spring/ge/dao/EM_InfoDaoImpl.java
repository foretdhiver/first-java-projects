package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.EmInfoVO;

@Repository("eM_InfoDao")
public class EM_InfoDaoImpl implements EM_InfoDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<EmInfoVO> em_Info(EmInfoVO param) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("em_Info");
	}
	@Override
	public EmInfoVO em_InfoList(EmInfoVO a) {
		// TODO Auto-generated method stub
		System.out.println("id >>" + a.getEmid());
		return sqlSession.selectOne("em_InfoList", a);
	}

}
