package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.PbReplyVO;

public interface PbReplyDao {

	public List<PbReplyVO> PbReplyList(String pbno);
	
	public int PbReplyInsert(PbReplyVO prvo);
	public int PbReplyUpedate(PbReplyVO prvo);
	public int PbReplyDelete(String pbno);

	public PbReplyVO PbChaebun(PbReplyVO prvo);

	
}//class
