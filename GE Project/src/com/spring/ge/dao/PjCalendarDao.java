package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.PjCalendarVO;

public interface PjCalendarDao {

	public List<PjCalendarVO> calList(PjCalendarVO cvo);
	
	public PjCalendarVO calDetail(PjCalendarVO cvo);
	
	public int calInsert(PjCalendarVO cvo);
	public int calUpdate(PjCalendarVO cvo);
	public int calDelete(PjCalendarVO cvo);
	
	public PjCalendarVO calChaebun(PjCalendarVO cvo);
	
	
}//class
