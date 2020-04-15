package com.spring.ge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.PjCalendarDao;
import com.spring.ge.vo.PjCalendarVO;
import com.spring.ge.vo.ProjectBoardVO;

@Service("pjCalendarService")
@Transactional
public class PjCalendarServiceImpl implements PjCalendarService {

	@Autowired
	private PjCalendarDao pjCalendarDao;
	
	
	@Override
	public List<PjCalendarVO> calList(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		List<PjCalendarVO> clist = null;
		clist = pjCalendarDao.calList(cvo);
		return clist;
	}

	
	@Override
	public int calInsert(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		int calInsert = 0;
		calInsert = pjCalendarDao.calInsert(cvo);
		return calInsert;
	}

	@Override
	public int calUpdate(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		int calUpdate = 0;
		calUpdate = pjCalendarDao.calUpdate(cvo);
		return calUpdate;
	}

	@Override
	public int calDelete(PjCalendarVO cvo) {
		// TODO Auto-generated method stub.
		int calDelete = 0;
		calDelete = pjCalendarDao.calDelete(cvo);
		return calDelete;
	}

	@Override
	public PjCalendarVO calChaebun(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		PjCalendarVO cdch = null;
		cdch = pjCalendarDao.calChaebun(cvo);
		return cdch;
	}


	@Override
	public PjCalendarVO calDetail(PjCalendarVO cvo) {
		// TODO Auto-generated method stub
		
		PjCalendarVO calDetail = null;
		calDetail = pjCalendarDao.calDetail(cvo);
		
		return calDetail;
	}

}
