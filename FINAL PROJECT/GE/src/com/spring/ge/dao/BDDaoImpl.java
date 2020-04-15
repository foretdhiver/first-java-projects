package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.ge.vo.BDVO;

public class BDDaoImpl implements BDDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BDVO> boardDepartmentList(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardDepartmentList");
	}
	@Override
	public BDVO boardDepartmentDetail(BDVO bdvo) {
		// TODO Auto-generated method stub
		System.out.println("bdvo.getbdno() >>> " + bdvo.getBdno());
		return sqlSession.selectOne("boardDepartmentDetail", bdvo);
	}
	@Override
	public BDVO boardDepartmentChebun(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardDepartmentChebun");
	}
	@Override
	public int boardDepartmentInsert(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("boardDepartmentInsert");
	}
	@Override
	public int boardDepartmentDelete(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardDepartmentDelete", bdvo);
	}
	@Override
	public int boardDepartmentUpdate(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardDepartmentUpdate", bdvo);
	}
	@Override
	public int boardDepartmentViewCntUp(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("boardDepartmentViewCntUp");
	}
	@Override
	public List<BDVO> boardDeptMainList(BDVO bdvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardDeptMainList");
	}
	
	
	
}
