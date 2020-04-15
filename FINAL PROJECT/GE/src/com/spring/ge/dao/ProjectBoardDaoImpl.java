package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.PjCalendarVO;
import com.spring.ge.vo.ProjectBoardVO;

@Repository
public class ProjectBoardDaoImpl implements ProjectBoardDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ProjectBoardVO> pjBoardList(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub

		return sqlSession.selectList("pjBoardList");
	}

	@Override
	public int pjBoardInsert(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		
		return sqlSession.insert("pjBoardInsert" ,pjvo);
	}

	@Override
	public int pjBoardUpate(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("pjBoardUpate",pjvo);
	}

	@Override
	public int pjBoardDelete(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("pjBoardDelete",pjvo);
	}

	@Override
	public int pjConfirm(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("pjConfirm");
	}

	@Override
	public int pjBoardListCnt(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("pjBoardListCnt");
	}

	@Override
	public ProjectBoardVO pjChaebun(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("pjChaebun",pjvo);
	}
//
	@Override
	public ProjectBoardVO pjBoardDetail(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("pjBoardDetail",pjvo);
	}

}
