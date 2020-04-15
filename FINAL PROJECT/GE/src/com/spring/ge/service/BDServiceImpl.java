package com.spring.ge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.BDDao;
import com.spring.ge.vo.BDVO;

@Service
@Transactional
public class BDServiceImpl implements BDService {
	
	@Autowired
	private BDDao bDDao;

	@Override
	public List<BDVO> boardDepartmentList(BDVO bdvo) {
		// TODO Auto-generated method stub
		List<BDVO> list = new ArrayList<BDVO>();
		list = bDDao.boardDepartmentList(bdvo);
		return list;
	}
	@Override
	public BDVO boardDepartmentDetail(BDVO bdvo) {
		// TODO Auto-generated method stub
		BDVO detail = new BDVO();
		detail = bDDao.boardDepartmentDetail(bdvo);
		return detail;
	}
	@Override
	public BDVO boardDepartmentChebun(BDVO bdvo) {
		// TODO Auto-generated method stub
		BDVO chae = new BDVO();
		chae = bDDao.boardDepartmentChebun(bdvo);
		return chae;
	}
	@Override
	public int boardDepartmentInsert(BDVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDDao.boardDepartmentInsert(bdvo);
		return result;
	}
	@Override
	public int boardDepartmentDelete(BDVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDDao.boardDepartmentDelete(bdvo);
		return result;
	}
	@Override
	public int boardDepartmentUpdate(BDVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDDao.boardDepartmentUpdate(bdvo);
		return result;
	}
	@Override
	public int boardDepartmentViewCntUp(BDVO bdvo) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDDao.boardDepartmentViewCntUp(bdvo);
		return result;
	}
	@Override
	public List<BDVO> boardDeptMainList(BDVO bdvo) {
		// TODO Auto-generated method stub
		List<BDVO> list = new ArrayList<BDVO>();
		list = bDDao.boardDeptMainList(bdvo);
		return list;
	}
}
