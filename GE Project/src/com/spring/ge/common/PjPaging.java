package com.spring.ge.common;

import com.spring.ge.vo.CommonVO;

public class PjPaging {
	
	//페이징을 위한 설정 작업
	//@param cvo
	public static void setPage(CommonVO cvo){
		int page_ = Util.nvl(cvo.getPage_(),1);
		int pageSize_ = Util.nvl(cvo.getPagesize_(),10);
		
		 if(cvo.getPage_()==null){
			 cvo.setPage_(page_+"");
		 }//if
		 if(cvo.getPagesize_()==null){
			 cvo.setPagesize_(pageSize_+"");
		 }//if
		 
		 int start_row = (page_-1)*pageSize_+1;
		 int end_row = (page_-1)*pageSize_+pageSize_;
		 
		 cvo.setStart_row_(start_row+"");
		 cvo.setEnd_row_(end_row+"");
		 
	}//setPage
	
}//class
