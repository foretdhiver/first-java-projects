package com.spring.ge.dao;

import java.util.List;

import com.spring.ge.vo.AnBoardVO;

public interface AnBoardDao {

	public List<AnBoardVO> anBoardList(AnBoardVO bvo);
	
	public int anBoardInsert(AnBoardVO bvo);
	public int pwConfirm(AnBoardVO bvo);
	public int anBoardUpdate(AnBoardVO bvo);
	public int anBoardDelete(AnBoardVO bvo);
	public int anBoardListCnt(AnBoardVO bvo);

	public AnBoardVO chaebun(AnBoardVO bvo);
	public AnBoardVO anBoardDetail(AnBoardVO bvo);

	
}//class
