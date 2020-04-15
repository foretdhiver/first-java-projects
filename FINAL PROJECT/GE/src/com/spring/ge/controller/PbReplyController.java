package com.spring.ge.controller;

import java.util.List;

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
import com.spring.ge.service.PbReplyService;
import com.spring.ge.vo.PbReplyVO;
import com.spring.ge.vo.ReplyVO;

@Controller
@RequestMapping(value="/pjReply")
public class PbReplyController {
	
	@Autowired
	private PbReplyService pbReplyService;
	
	//list
	@RequestMapping(value="/all/{pbno}.ge", method = RequestMethod.GET)
	public ResponseEntity<List<PbReplyVO>> list(@PathVariable("pbno") String pbno){
	
		System.out.println("(log)댓글 리스트 컨트롤러 들어왔다 >>> ");
		ResponseEntity<List<PbReplyVO>> entity = null;
		
		try{
			System.out.println("(log)댓글 리스트 if 들어왔다 >>> ");
			entity = new ResponseEntity<>(pbReplyService.PbReplyList(pbno),HttpStatus.OK);
			
			System.out.println("(log)댓글 리스트 쿼리갔다왔어 >>> ");		
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("(log)댓글 리스트 끝 >> ");
		return entity;
	}
	//insert
	@ResponseBody
	@RequestMapping(value = "/pbReplyInsert", method = RequestMethod.POST)
	public ResponseEntity<String> PbReplyInsert(@RequestBody PbReplyVO prvo){
	
		System.out.println("(log)댓글쓰기 들어왔다 >>> ");
		
		ResponseEntity<String> entity = null;
		int result  = 0;
		
		//채번
		System.out.println("(log)댓글 채번 하자하자 >>> ");
		PbReplyVO prcb = null;
		prcb = pbReplyService.PbChaebun(prvo);
		System.out.println("(log)채번 쿼리 갔다왔다 >>> ");
		prcb.getPbr_no();
		System.out.println("	prcb.getPbr_no() >>> " + prcb.getPbr_no());
		
		String pcb = "";
		pcb=prcb.getPbr_no();
		prvo.setPbr_no(Chaebun.chaebunPBC(pcb));
		System.out.println("(log)채번 끝 >> ");	
		
		try{
			result = pbReplyService.PbReplyInsert(prvo);
			if(result==1){
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch(Exception e){
			System.out.println("ERROR >>> "+e.getMessage());
			e.printStackTrace();
		}
	
		System.out.println("(log)댓글 쓰기 끝 >> ");	
		return entity;
	}
	//댓글 수정하기
	@ResponseBody
	@RequestMapping(value="/{pbr_no}.ge", 
					method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("pbr_no") String pbr_no, @RequestBody PbReplyVO prvo){
		
		System.out.println("(log) 댓글 수정하기 컨트롤러 들어왔다 >>>> ");
		
		ResponseEntity<String> entity = null;
		int result = 0;
		
		try{
			System.out.println("(log) 댓글 수정하기 컨트롤러 try 들어왔다 >>>> ");
			prvo.setPbr_no(pbr_no);
			System.out.println("	prvo >>> " + prvo);
			
			result = pbReplyService.PbReplyUpedate(prvo);
			System.out.println("	result >>> " + result);
			System.out.println("(log)쿼리갔다왔다 >>> ");
			
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		System.out.println("(log) 댓글 수정하기 컨트롤러 끝 >>>> ");
		return entity;
	}
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping(value="/{pbr_no}.ge", 
					method=RequestMethod.DELETE)
	public ResponseEntity<String> replyDelete(@PathVariable("pbr_no") String pbr_no){
		
		System.out.println("(log) 댓글 삭제하기 컨트롤러 들어왔다 >>>> ");
		
		ResponseEntity<String> entity = null;
		
		try{
			System.out.println("(log) 댓글 삭제하기 컨트롤러 try 들어왔다 >>>> ");
			
			pbReplyService.PbReplyDelete(pbr_no);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("(log) 댓글 삭제하기 컨트롤러 끝 >>>> ");
		return entity;	
		
	}
		
	
	
}//class
