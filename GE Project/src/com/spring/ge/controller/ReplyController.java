package com.spring.ge.controller;

import java.util.List;

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
import com.spring.ge.service.ReplyService;
import com.spring.ge.vo.ReplyVO;


@Controller
@RequestMapping(value="/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	//list
	@ResponseBody
	@RequestMapping(value = "/all/{abno}.ge", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("abno") String abno){
		
		ResponseEntity<List<ReplyVO>> entity = null;
		try{
			entity = new ResponseEntity<>(replyService.replyList(abno),HttpStatus.OK);
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return entity;
	}//list
	
	//댓글쓰기
	@ResponseBody
	@RequestMapping(value = "/replyInsert", method = RequestMethod.POST)
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo){
	
		ResponseEntity<String> entity = null;
		int result  = 0;
		
		//채번
		ReplyVO rcb = null;
		rcb = replyService.chaebun(rvo);
		rcb.getR_no();
		System.out.println("rcb.getR_no() >>> "+ rcb.getR_no());
		
		String acb = "";
		acb=rcb.getR_no();
		rvo.setR_no(Chaebun.chaebunAC(acb));
		
		try{
			result = replyService.replyInsert(rvo);
			if(result==1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch(Exception e){
			System.out.println("dd"+e.getMessage());
			e.printStackTrace();
		}
		return entity;
	}
	//댓글 수정하기
	@ResponseBody
	@RequestMapping(value="/{r_no}.ge", 
					method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("r_no") String r_no, @RequestBody ReplyVO rvo){
		
		ResponseEntity<String> entity = null;
		try{
			rvo.setR_no(r_no);
			replyService.replyUpedate(rvo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value="/{r_no}.ge", 
					method=RequestMethod.DELETE)
	public ResponseEntity<String> replyDelete(@PathVariable("r_no") String r_no){
		
		ResponseEntity<String> entity = null;
		try{
			replyService.replyDelete(r_no);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;	
		
		}
		
	
	
	
	
}//class
