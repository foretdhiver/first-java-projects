package com.spring.ge.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.ge.vo.CalendarVO;

@Repository
public class CalendarDaoImpl implements CalendarDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int calendarInsert(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("calendarInsert", clvo);
	}
	@Override
	public CalendarVO calendarChaebun(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("calendarChaebun", clvo);
	}
	@Override
	public List<CalendarVO> calendarList(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("calendarList", clvo);
	}
	@Override
	public int calendarDelete(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("calendarDelete", clvo);
	}
	@Override
	public List<CalendarVO> calendarListOne(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("calendarListOne", clvo);
	}
	@Override
	public int calendarUpdate(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("calendarUpdate", clvo);
	}	
	@Override
	public List<CalendarVO> adminCalendarList(CalendarVO clvo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("adminCalendarList", clvo);
	}
	
	
	
}
