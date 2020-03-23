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
		System.out.println("[log] BoardServiceImpl.insertBoard 진입 >>> ");
		int list = boardMapper.insertBoard(param);
		System.out.println("[log] BoardServiceImpl.insertBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> chaebunBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.chaebunBoard 진입 >>> ");
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boardMapper.chaebunBoard(param);
		System.out.println("[log] BoardServiceImpl.chaebunBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> listBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.listBoard 진입 >>> ");
		List<BoardVO> list = boardMapper.listBoard(param);
		System.out.println("[log] BoardServiceImpl.listBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> selectBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.selectBoard 진입 >>> ");
		List<BoardVO> list = boardMapper.selectBoard(param);
		System.out.println("[log] BoardServiceImpl.selectBoard 종료 <<< ");
		return list;
	}

	@Override
	public List<BoardVO> pwValueCheck(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.pwValueCheck 진입 >>> ");
		List<BoardVO> list = boardMapper.pwValueCheck(param);
		System.out.println("[log] BoardServiceImpl.pwValueCheck 종료 <<< ");
		return list;
	}

	@Override
	public int updateBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.updateBoard 진입 >>> ");
		int list = boardMapper.updateBoard(param);
		System.out.println("[log] BoardServiceImpl.updateBoard 종료 <<< ");
		return list;
	}

	@Override
	public int updateBoard_w(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.updateBoard_w 진입 >>> ");
		int list = boardMapper.updateBoard_w(param);
		System.out.println("[log] BoardServiceImpl.updateBoard_w 종료 <<< ");
		return list;
	}

	@Override
	public int deleteBoard(BoardVO param) {
		// TODO Auto-generated method stub
		System.out.println("[log] BoardServiceImpl.deleteBoard 진입 >>> ");
		int list = boardMapper.deleteBoard(param);
		System.out.println("[log] BoardServiceImpl.deleteBoard 종료 <<< ");
		return list;
	}
	
}
