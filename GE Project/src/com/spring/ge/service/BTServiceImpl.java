package com.spring.ge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.BTDao;
import com.spring.ge.vo.BTVO;

@Service
@Transactional
public class BTServiceImpl implements BTService {
	
	@Autowired
	private BTDao bTDao;

	@Override
	public List<BTVO> boardTaskList(BTVO bdvo) {
		// TODO Auto-generated method stub
		List<BTVO> list = new ArrayList<BTVO>();
		list = bTDao.boardTaskList(bdvo);
		return list;
	}
	@Override
	public BTVO boardTaskDetail(BTVO bdvo) {
		// TODO Auto-generated method stub
		BTVO detail = new BTVO();
		detail = bTDao.boardTaskDetail(bdvo);
		return detail;
	}
	@Override
	public BTVO boardTaskChebun(BTVO bdvo) {
		// TODO Auto-generated method stub
		BTVO chae = new BTVO();
		chae = bTDao.boardTaskChebun(bdvo);
		return chae;
	}
	@Override
	public int boardTaskInsert(BTVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTDao.boardTaskInsert(bdvo);
		return result;
	}
	@Override
	public int boardTaskDelete(BTVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTDao.boardTaskDelete(bdvo);
		return result;
	}
	@Override
	public int boardTaskUpdate(BTVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTDao.boardTaskUpdate(bdvo);
		return result;
	}
	@Override
	public int boardTaskViewCntUp(BTVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTDao.boardTaskViewCntUp(bdvo);
		return result;
	}

}
