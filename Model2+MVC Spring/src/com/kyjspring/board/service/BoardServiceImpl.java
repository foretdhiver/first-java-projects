package com.kyjspring.board.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyjspring.board.dao.BoardMapper;
import com.kyjspring.board.vo.BoardVO;
import com.kyjspring.member.dao.MemberMapper;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public int insertBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.insertBoard ���� >>> ");
		int list = boardMapper.insertBoard(param);
		System.out.println("[log] BoardServiceImpl.insertBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> chaebunBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.chaebunBoard ���� >>> ");
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boardMapper.chaebunBoard(param);
		System.out.println("[log] BoardServiceImpl.chaebunBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> listBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.listBoard ���� >>> ");
		List<BoardVO> list = boardMapper.listBoard(param);
		System.out.println("[log] BoardServiceImpl.listBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> selectBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.selectBoard ���� >>> ");
		List<BoardVO> list = boardMapper.selectBoard(param);
		System.out.println("[log] BoardServiceImpl.selectBoard ���� <<< ");
		return list;
	}

	@Override
	public List<BoardVO> pwValueCheck(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.pwValueCheck ���� >>> ");
		List<BoardVO> list = boardMapper.pwValueCheck(param);
		System.out.println("[log] BoardServiceImpl.pwValueCheck ���� <<< ");
		return list;
	}

	@Override
	public int updateBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.updateBoard ���� >>> ");
		int list = boardMapper.updateBoard(param);
		System.out.println("[log] BoardServiceImpl.updateBoard ���� <<< ");
		return list;
	}

	@Override
	public int updateBoard_w(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.updateBoard_w ���� >>> ");
		int list = boardMapper.updateBoard_w(param);
		System.out.println("[log] BoardServiceImpl.updateBoard_w ���� <<< ");
		return list;
	}

	@Override
	public int deleteBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.deleteBoard ���� >>> ");
		int list = boardMapper.deleteBoard(param);
		System.out.println("[log] BoardServiceImpl.deleteBoard ���� <<< ");
		return list;
	}
	
}
