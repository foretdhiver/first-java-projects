package com.spring.ge.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ge.common.Chaebun;
import com.spring.ge.service.BDCService;
import com.spring.ge.service.BTCService;
import com.spring.ge.vo.BDComVO;
import com.spring.ge.vo.BTComVO;
import com.spring.ge.vo.EmInfoVO;

@Controller
@RequestMapping(value="comment")
public class BoardCommentController {
	Logger logger = Logger.getLogger(BoardCommentController.class);
	
	@Autowired	
	private BDCService bDCService;
	
	@Autowired	
	private BTCService bTCService;
	
	@ResponseBody
	@RequestMapping(value="/all/{bdno}.ge", method=RequestMethod.GET)
	public ResponseEntity<List<BDComVO>> list(@PathVariable("bdno") String bdno){
		logger.info("list 호출 성공");
		
		logger.info("bdno >>>> " + bdno);
		
		ResponseEntity<List<BDComVO>> entity = null;
		try{
			BDComVO bdc = new BDComVO();
			bdc.setBdno(bdno);
			
			List<BDComVO> commentList = bDCService.commentList(bdc);
			logger.info("commentList.size() >>> " + commentList.size());
			
			entity = new ResponseEntity<>(commentList, HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/insertComment", method=RequestMethod.POST)
	public ResponseEntity<String> insertComment(@RequestBody BDComVO bdc, HttpSession httpsession){
		logger.info("insertComment 호출 성공");
		ResponseEntity<String> entity = null;
		int result = 0;
		String chebun = "";
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		try{
			BDComVO bdC = null;
			bdC = new BDComVO();
			bdC = bDCService.commentChaebun(bdC);
			chebun = Chaebun.chaebunDC(bdC.getBdcomno());
			
			bdc.setBdcomno(chebun);
			logger.info("chebun >>> " + chebun);
			logger.info("Bdno >>> " + bdc.getBdno());
			logger.info("Emname >>> " + infoList.getEmname());
			logger.info("Bdcomcontent >>> " + bdc.getBdcomcontent());
			logger.info("Comcd >>> " + bdc.getComcd());
			logger.info("Emno >>> " + infoList.getEmno());
			logger.info("Jobcd >>> " + infoList.getJobcd());
			logger.info("Deptcd >>> " + infoList.getDeptcd());
			
			bdc.setEmname(infoList.getEmname());
			bdc.setEmno(infoList.getEmno());
			bdc.setJobcd(infoList.getJobcd());
			bdc.setDeptcd(infoList.getDeptcd());
			
			result = bDCService.commentInsert(bdc);
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{bdcomno}_d.ge", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> deleteComment(@PathVariable("bdcomno") String bdcomno, @RequestBody BDComVO bdc, HttpSession httpsession){
		logger.info("deleteComment 호출 성공");
		ResponseEntity<String> entity = null;
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		logger.info("bdcomno>>> " + bdcomno);
		logger.info("bdno	>>> " + bdc.getBdno());
		logger.info("emno	>>> " + infoList.getEmno());
		
		BDComVO bdC = null;
		bdC = new BDComVO();		
		try{
			bdC.setBdcomno(bdcomno);
			bdC.setBdno(bdc.getBdno());
			bdC.setEmno(infoList.getEmno());
			
			result = bDCService.commentDelete(bdC);
			
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{bdcomno}_u.ge", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> updateComment(@PathVariable("bdcomno") String bdcomno, @RequestBody BDComVO bdc, HttpSession httpsession){
		logger.info("updateComment 호출 성공");
		ResponseEntity<String> entity = null;
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		logger.info("bdcomno>>> " + bdcomno);
		logger.info("bdno	>>> " + bdc.getBdno());
		logger.info("emno	>>> " + infoList.getEmno());
		logger.info("content>>> " + bdc.getBdcomcontent());
		
		BDComVO bdC = null;
		bdC = new BDComVO();		
		try{
			bdC.setBdcomcontent(bdc.getBdcomcontent());
			bdC.setBdcomno(bdcomno);
			bdC.setEmno(infoList.getEmno());
			bdC.setBdno(bdc.getBdno());
			
			result = bDCService.commentUpdate(bdC);
			logger.info("result>>> " + result);
			
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@ResponseBody
	@RequestMapping(value="/allT/{btno}.ge", method=RequestMethod.GET)
	public ResponseEntity<List<BTComVO>> listT(@PathVariable("btno") String btno){
		logger.info("listT 호출 성공");
		
		logger.info("btno >>>> " + btno);
		
		ResponseEntity<List<BTComVO>> entity = null;
		try{
			BTComVO btc = new BTComVO();
			btc.setBtno(btno);
			
			List<BTComVO> commentList = bTCService.commentList(btc);
			logger.info("commentList.size() >>> " + commentList.size());
			
			entity = new ResponseEntity<>(commentList, HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/insertCommentT", method=RequestMethod.POST)
	public ResponseEntity<String> insertCommentT(@RequestBody BTComVO btc, HttpSession httpsession){
		logger.info("insertCommentT 호출 성공");
		ResponseEntity<String> entity = null;
		int result = 0;
		String chebun = "";
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		try{
			BTComVO btC = null;
			btC = new BTComVO();
			btC = bTCService.commentChaebun(btC);
			chebun = Chaebun.chaebunTC(btC.getBtcomno());
			
			btc.setBtcomno(chebun);
			logger.info("chebun >>> " + chebun);
			logger.info("Bdno >>> " + btc.getBtno());
			logger.info("Emname >>> " + infoList.getEmname());
			logger.info("Bdcomcontent >>> " + btc.getBtcomcontent());
			logger.info("Comcd >>> " + btc.getComcd());
			logger.info("Emno >>> " + infoList.getEmno());
			logger.info("Jobcd >>> " + infoList.getJobcd());
			logger.info("Deptcd >>> " + infoList.getDeptcd());
			
			btc.setEmname(infoList.getEmname());
			btc.setEmno(infoList.getEmno());
			btc.setJobcd(infoList.getJobcd());
			btc.setDeptcd(infoList.getDeptcd());
			
			result = bTCService.commentInsert(btc);
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{btcomno}_dT.ge", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> deleteCommentT(@PathVariable("btcomno") String btcomno, @RequestBody BTComVO btc, HttpSession httpsession){
		logger.info("deleteCommentT 호출 성공");
		ResponseEntity<String> entity = null;
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		logger.info("btcomno>>> " + btcomno);
		logger.info("btno	>>> " + btc.getBtno());
		logger.info("emno	>>> " + infoList.getEmno());
		
		BTComVO btC = null;
		btC = new BTComVO();		
		try{
			btC.setBtcomno(btcomno);
			btC.setBtno(btc.getBtno());
			btC.setEmno(infoList.getEmno());
			
			result = bTCService.commentDelete(btC);
			
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/{btcomno}_uT.ge", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> updateCommentT(@PathVariable("btcomno") String btcomno, @RequestBody BTComVO btc, HttpSession httpsession){
		logger.info("updateCommentT 호출 성공");
		ResponseEntity<String> entity = null;
		int result = 0;
		
		Object infoObj = httpsession.getAttribute("eminfo");
		EmInfoVO infoList = new EmInfoVO();
		infoList = (EmInfoVO)infoObj;
		
		logger.info("btcomno>>> " + btcomno);
		logger.info("btno	>>> " + btc.getBtno());
		logger.info("emno	>>> " + infoList.getEmno());
		logger.info("content>>> " + btc.getBtcomcontent());
		
		BTComVO btC = null;
		btC = new BTComVO();		
		try{
			btC.setBtcomcontent(btc.getBtcomcontent());
			btC.setBtcomno(btcomno);
			btC.setEmno(infoList.getEmno());
			btC.setBtno(btc.getBtno());
			
			result = bTCService.commentUpdate(btC);
			logger.info("result>>> " + result);
			
			if(result == 1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	

}
