package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.ReplyVO;



public interface ReplyDAO {

	public List<ReplyVO> replyList(String abno);
	
	public int replyInsert(ReplyVO rvo);
	public int replyUpedate(ReplyVO rvo);
	public int replyDelete(String abno);
	
	public ReplyVO chaebun(ReplyVO rvo);
	
}//class
