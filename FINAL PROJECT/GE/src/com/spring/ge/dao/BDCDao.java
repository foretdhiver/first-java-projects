package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.BDComVO;

public interface BDCDao {
	//조회
	public List<BDComVO> commentList(BDComVO bdc);
	//채번
	public BDComVO commentChaebun(BDComVO bdc);
	//입력
	public int commentInsert(BDComVO bdc);	
	//삭제
	public int commentDelete(BDComVO bdc);
	//수정
	public int commentUpdate(BDComVO bdc);

}
