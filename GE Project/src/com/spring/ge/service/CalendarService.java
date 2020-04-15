package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.CalendarVO;

public interface CalendarService {
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
