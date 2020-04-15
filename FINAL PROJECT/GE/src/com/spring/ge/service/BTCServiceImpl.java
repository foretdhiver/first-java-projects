package com.spring.ge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.BTCDao;
import com.spring.ge.vo.BTComVO;

@Service
@Transactional
public class BTCServiceImpl implements BTCService {
	
	@Autowired
	private BTCDao bTCDao;

	@Override
	public List<BTComVO> commentList(BTComVO btc) {
		// TODO Auto-generated method stub
		List<BTComVO> commentList = null;
		commentList = bTCDao.commentList(btc);
		return commentList;
	}
	@Override
	public BTComVO commentChaebun(BTComVO btc) {
		// TODO Auto-generated method stub
		BTComVO btC = null;
		btC = bTCDao.commentChaebun(btc);
		return btC;
	}
	@Override
	public int commentInsert(BTComVO btc) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTCDao.commentInsert(btc);
		return result;
	}
	@Override
	public int commentDelete(BTComVO btc) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTCDao.commentDelete(btc);
		return result;
	}
	@Override
	public int commentUpdate(BTComVO btc) {
		// TODO Auto-generated method stub
		int result = 0;
		result = bTCDao.commentUpdate(btc);
		return result;
	}
}
