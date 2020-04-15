package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.CalendarVO;

public interface CalendarDao {
	//입력
	public int calendarInsert(CalendarVO clvo);
	//채번
	public CalendarVO calendarChaebun(CalendarVO clvo);
	//사번으로 일정 불러오기
	public List<CalendarVO> calendarList(CalendarVO clvo);
	//삭제
	public int calendarDelete(CalendarVO clvo);
	//클릭한 일정 조회
	public List<CalendarVO> calendarListOne(CalendarVO clvo);
	//수정
	public int calendarUpdate(CalendarVO clvo);
	
	public List<CalendarVO> adminCalendarList(CalendarVO clvo);
}