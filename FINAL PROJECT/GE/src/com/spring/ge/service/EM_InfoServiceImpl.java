package com.spring.ge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ge.dao.EM_InfoDao;
import com.spring.ge.vo.EmInfoVO;

@Service
@Transactional
public class EM_InfoServiceImpl implements EM_InfoService {
	
	@Autowired
	private EM_InfoDao eM_InfoDao;

	@Override
	public List<EmInfoVO> em_Info(EmInfoVO param) {
		// TODO Auto-generated method stub
		List<EmInfoVO> list = new ArrayList<EmInfoVO>();
		list = eM_InfoDao.em_Info(param);
		return list;
	}
	@Override
	public EmInfoVO em_InfoList(EmInfoVO param) {
		// TODO Auto-generated method stub
		EmInfoVO evo = null;
		evo = new EmInfoVO();
		evo = eM_InfoDao.em_InfoList(param);
		return evo;
	}

}
