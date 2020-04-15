package com.spring.ge.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.ReplyDAO;
import com.spring.ge.vo.ReplyVO;


@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	Logger logger = Logger.getLogger(ReplyServiceImpl.class);

	@Autowired
	private ReplyDAO replyDao;
	
	
	@Override
	public List<ReplyVO> replyList(String abno) {
		// TODO Auto-generated method stub
				
		List<ReplyVO> myList = null;
		myList=replyDao.replyList(abno);
		
		return myList;
	}

	@Override
	public int replyInsert(ReplyVO rvo) {
		// TODO Auto-generated method stub
		
		int result = 0;
		result = replyDao.replyInsert(rvo);
		
		return result;
	}

	@Override
	public int replyUpedate(ReplyVO rvo) {
		// TODO Auto-generated method stub
		
		int result =0;
		result = replyDao.replyUpedate(rvo);
		return result;
	}

	@Override
	public int replyDelete(String abno) {
		// TODO Auto-generated method stub
		
		int result =0;
		result = replyDao.replyDelete(abno);
		return result;
	}

	@Override
	public ReplyVO chaebun(ReplyVO rvo) {
		// TODO Auto-generated method stub
		
		ReplyVO chaebun = null;
		chaebun=replyDao.chaebun(rvo);
		
		return chaebun;
	}

}
