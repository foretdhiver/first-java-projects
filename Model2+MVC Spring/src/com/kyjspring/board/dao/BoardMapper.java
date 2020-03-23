package com.kyjspring.board.dao;

import java.util.List;
import com.kyjspring.board.vo.BoardVO;

public interface BoardMapper {
	public int insertBoard(BoardVO param);
	public List<BoardVO> selectBoard(BoardVO param);
	public List<BoardVO> listBoard(BoardVO param);
	public int updateBoard(BoardVO param);
	public int updateBoard_w(BoardVO param);
	public int deleteBoard(BoardVO param);


	// 채번
	public List<BoardVO> chaebunBoard(BoardVO param);
	// 비밀번호체크
	public List<BoardVO> pwValueCheck(BoardVO param);
}
