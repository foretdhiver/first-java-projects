package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.PjCalendarVO;
import com.spring.ge.vo.ProjectBoardVO;
import com.spring.ge.vo.ReplyVO;

public interface PjCalendarService {

	public List<PjCalendarVO> calList(PjCalendarVO cvo);
	public PjCalendarVO calDetail(PjCalendarVO cvo);
	
	public int calInsert(PjCalendarVO cvo);
	public int calUpdate(PjCalendarVO cvo);
	public int calDelete(PjCalendarVO cvo);
	
	public PjCalendarVO calChaebun(PjCalendarVO cvo);
	
	
}//class
