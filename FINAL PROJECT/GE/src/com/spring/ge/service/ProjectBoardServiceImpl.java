package com.spring.ge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.ProjectBoardDao;
import com.spring.ge.vo.ProjectBoardVO;

@Service("pjboardService")
@Transactional
public class ProjectBoardServiceImpl implements ProjectBoardService {

	@Autowired
	private ProjectBoardDao projectBoardDao;
	
	@Override
	public List<ProjectBoardVO> pjBoardList(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		
		List<ProjectBoardVO> list = null;
		list = projectBoardDao.pjBoardList(pjvo);
		
		return list;
	}

	@Override
	public int pjBoardInsert(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		
		int result=0;
		result=projectBoardDao.pjBoardInsert(pjvo);
		
		return result;
	}

	@Override
	public int pjBoardUpate(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub

		int result=0;
		result=projectBoardDao.pjBoardUpate(pjvo);
		
		return result;
	}

	@Override
	public int pjBoardDelete(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return projectBoardDao.pjBoardDelete(pjvo);
	}

	@Override
	public int pjConfirm(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pjBoardListCnt(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		
		int result=0;
		result=projectBoardDao.pjBoardListCnt(pjvo);
		
		return result;
	}

	@Override
	public ProjectBoardVO pjChaebun(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
				
		ProjectBoardVO pjcb = null;
		pjcb = projectBoardDao.pjChaebun(pjvo);
		
		return pjcb;
	}

	@Override
	public ProjectBoardVO pjBoardDetail(ProjectBoardVO pjvo) {
		// TODO Auto-generated method stub
		
		ProjectBoardVO detail = null;
		detail = projectBoardDao.pjBoardDetail(pjvo);
		
		return detail;
	}

	
	
	
}
