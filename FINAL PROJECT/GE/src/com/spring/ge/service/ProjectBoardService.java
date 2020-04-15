package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.PjCalendarVO;
import com.spring.ge.vo.ProjectBoardVO;

public interface ProjectBoardService {

	public List<ProjectBoardVO> pjBoardList(ProjectBoardVO pjvo);
	
	public int pjBoardInsert(ProjectBoardVO pjvo);
	public int pjBoardUpate(ProjectBoardVO pjvo);
	public int pjBoardDelete(ProjectBoardVO pjvo);
	public int pjConfirm(ProjectBoardVO pjvo);
	public int pjBoardListCnt(ProjectBoardVO pjvo);
//	
	public ProjectBoardVO pjChaebun(ProjectBoardVO pjvo);
	public ProjectBoardVO pjBoardDetail(ProjectBoardVO pjvo);
	
}//class
