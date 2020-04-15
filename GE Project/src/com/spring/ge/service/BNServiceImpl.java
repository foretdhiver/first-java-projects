package com.spring.ge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.BNDao;
import com.spring.ge.vo.BNVO;

@Service
@Transactional
public class BNServiceImpl implements BNService {
	
	@Autowired
	private BNDao bNDao;

	@Override
	public List<BNVO> boardNoticeList(BNVO bnvo) {
		// TODO Auto-generated method stub
		List<BNVO> list = new ArrayList<BNVO>();
		list = bNDao.boardNoticeList(bnvo);
		return list;
	}
	@Override
	public BNVO boardNoticeDetail(BNVO bnvo) {
		// TODO Auto-generated method stub
		BNVO detail = new BNVO();
		detail = bNDao.boardNoticeDetail(bnvo);
		return detail;
	}
	@Override
	public BNVO boardNoticeChebun(BNVO bnvo) {
		// TODO Auto-generated method stub
		BNVO chae = new BNVO();
		chae = bNDao.boardNoticeChebun(bnvo);
		return chae;
	}
	@Override
	public int boardNoticeInsert(BNVO bnvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bNDao.boardNoticeInsert(bnvo);
		return result;
	}
	@Override
	public int boardNoticeDelete(BNVO bnvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bNDao.boardNoticeDelete(bnvo);
		return result;
	}
	@Override
	public int boardNoticeUpdate(BNVO bnvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bNDao.boardNoticeUpdate(bnvo);
		return result;
	}
	@Override
	public int boardNoticeViewCntUp(BNVO bnvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bNDao.boardNoticeViewCntUp(bnvo);
		return result;
	}
	@Override
	public List<BNVO> boardNoticeMainList(BNVO bnvo){
		// TODO Auto-generated method stub
		List<BNVO> list = new ArrayList<BNVO>();
		list = bNDao.boardNoticeMainList(bnvo);
		return list;
	}
}
