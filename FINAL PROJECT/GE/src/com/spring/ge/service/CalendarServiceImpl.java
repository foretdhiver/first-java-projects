package com.spring.ge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.CalendarDao;
import com.spring.ge.vo.CalendarVO;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService {
	
	@Autowired
	private CalendarDao calendarDao;

	@Override
	public int calendarInsert(CalendarVO clvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = calendarDao.calendarInsert(clvo);
		return result;
	}
	@Override
	public CalendarVO calendarChaebun(CalendarVO clvo) {
		// TODO Auto-generated method stub
		CalendarVO clVO = null;
		clVO = new CalendarVO();
		clVO = calendarDao.calendarChaebun(clvo);
		return clVO;
	}
	@Override
	public List<CalendarVO> calendarList(CalendarVO clvo) {
		// TODO Auto-generated method stub
		List<CalendarVO> calendarList = null;
		calendarList = calendarDao.calendarList(clvo);
		return calendarList;
	}
	@Override
	public int calendarDelete(CalendarVO clvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = calendarDao.calendarDelete(clvo);
		return result;
	}
	@Override
	public List<CalendarVO> calendarListOne(CalendarVO clvo) {
		// TODO Auto-generated method stub
		List<CalendarVO> calendarListOne = null;
		calendarListOne = calendarDao.calendarListOne(clvo);
		return calendarListOne;
	}
	@Override
	public int calendarUpdate(CalendarVO clvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = calendarDao.calendarUpdate(clvo);
		return result;
	}
	@Override
	public List<CalendarVO> adminCalendarList(CalendarVO clvo) {
		// TODO Auto-generated method stub
		List<CalendarVO> adminCalendarList = null;
		adminCalendarList = calendarDao.adminCalendarList(clvo);
		return adminCalendarList;
	}

}
