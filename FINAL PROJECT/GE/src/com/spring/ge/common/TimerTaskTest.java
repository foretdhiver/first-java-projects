package com.spring.ge.common;

import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.ge.service.EmService;
import com.spring.ge.service.EmServiceImpl;
//import com.spring.ge.service.TestService;
//import com.spring.ge.service.TestServiceImpl;
import com.spring.ge.vo.EmInfoVO;
import com.spring.ge.vo.EmPrInfoVO;

public class TimerTaskTest extends TimerTask {
	
	Logger logger = Logger.getLogger(TimerTaskTest.class);

	@Autowired
	private EmService emService;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		EmPrInfoVO epvo = null;
		
		epvo = new EmPrInfoVO();
		//TestService ts = new TestServiceImpl();
		//String result = ts.aa();
		//System.out.println("result >>> : " + result);
		
		EmService es = new EmServiceImpl();
		String result1 = es.test();
		System.out.println("result1 >>> : " + result1);
		
		
		int result2 = 0;
		
		try{
			String emno="E202003090003";
////			String empw="123qwe";
////			evo.setEmid(emid);
			epvo.setEmno(emno);
//			System.out.println(epvo.getEmno());
			List<EmPrInfoVO> list = emService.emDetail(epvo);
//			
			System.out.println(list.size());
			
			result2 = es.ctToLog();
		}catch(Exception e){
			System.out.println(e);
		}
//		System.out.println("result2 >>> : " + result2);
	}
}
