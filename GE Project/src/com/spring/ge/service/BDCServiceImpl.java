package com.spring.ge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.BDCDao;
import com.spring.ge.vo.BDComVO;

@Service
@Transactional
public class BDCServiceImpl implements BDCService {
	
	@Autowired
	private BDCDao bDCDao;

	@Override
	public List<BDComVO> commentList(BDComVO bdc) {
		// TODO Auto-generated method stub
		System.out.println("commentList ServiceImpl ¡¯¿‘");
		List<BDComVO> commentList = null;
		commentList = bDCDao.commentList(bdc);
		System.out.println("commentList ServiceImpl ≥°");
		return commentList;
	}
	@Override
	public BDComVO commentChaebun(BDComVO bdc) {
		// TODO Auto-generated method stub
		BDComVO bdC = null;
		bdC = bDCDao.commentChaebun(bdc);
		return bdC;
	}
	@Override
	public int commentInsert(BDComVO bdc) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDCDao.commentInsert(bdc);
		return result;
	}
	@Override
	public int commentDelete(BDComVO bdc) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDCDao.commentDelete(bdc);
		return result;
	}
	@Override
	public int commentUpdate(BDComVO bdc) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bDCDao.commentUpdate(bdc);
		return result;
	}
}
