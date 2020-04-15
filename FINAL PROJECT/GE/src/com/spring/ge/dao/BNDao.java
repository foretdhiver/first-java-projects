package com.spring.ge.dao;

import java.util.ArrayList;
import java.util.List;

import com.spring.ge.vo.BNVO;

public interface BNDao {
	//조회
	public List<BNVO> boardNoticeList(BNVO bnvo);
	//detail조회
	public BNVO boardNoticeDetail(BNVO bnvo);
	//채번
	public BNVO boardNoticeChebun(BNVO bnvo);
	//입력
	public int boardNoticeInsert(BNVO bnvo);
	//삭제
	public int boardNoticeDelete(BNVO bnvo);
	//수정
	public int boardNoticeUpdate(BNVO bnvo);
	//조회수 증가
	public int boardNoticeViewCntUp(BNVO bnvo);
	
	public List<BNVO> boardNoticeMainList(BNVO bnvo);
}
