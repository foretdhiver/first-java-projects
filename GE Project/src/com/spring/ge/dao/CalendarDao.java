package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.CalendarVO;

public interface CalendarDao {
	//�Է�
	public int calendarInsert(CalendarVO clvo);
	//ä��
	public CalendarVO calendarChaebun(CalendarVO clvo);
	//������� ���� �ҷ�����
	public List<CalendarVO> calendarList(CalendarVO clvo);
	//����
	public int calendarDelete(CalendarVO clvo);
	//Ŭ���� ���� ��ȸ
	public List<CalendarVO> calendarListOne(CalendarVO clvo);
	//����
	public int calendarUpdate(CalendarVO clvo);
	
	public List<CalendarVO> adminCalendarList(CalendarVO clvo);
}