package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.BTVO;

public interface BTDao {
	//조회
	public List<BTVO> boardTaskList(BTVO btvo);
	//detail조회
	public BTVO boardTaskDetail(BTVO btvo);
	//채번
	public BTVO boardTaskChebun(BTVO btvo);
	//입력
	public int boardTaskInsert(BTVO btvo);
	//삭제
	public int boardTaskDelete(BTVO btvo);
	//수정
	public int boardTaskUpdate(BTVO btvo);
	//조회수 증가
	public int boardTaskViewCntUp(BTVO btvo);

}
