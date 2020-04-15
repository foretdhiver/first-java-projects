package com.spring.ge.service;

import java.util.List;

import com.spring.ge.vo.BTComVO;

public interface BTCService {
	//조회
	public List<BTComVO> commentList(BTComVO btc);
	//채번
	public BTComVO commentChaebun(BTComVO btc);
	//입력
	public int commentInsert(BTComVO btc);
	//삭제
	public int commentDelete(BTComVO btc);
	//수정
	public int commentUpdate(BTComVO btc);
	
}
