package com.spring.ge.common;

import com.spring.ge.vo.CommonVO;




public class Paging {
	
	//페이징을 위한 설정 작업
	//@param cvo
	public static void setPage(CommonVO cvo){
		int page = Util.nvl(cvo.getPage_(),1);
		int pageSize = Util.nvl(cvo.getPagesize_(),10);
		
		 if(cvo.getPage_()==null){
			 cvo.setPage_(page+"");
		 }//if
		 if(cvo.getPagesize_()==null){
			 cvo.setPagesize_(pageSize+"");
		 }//if
		 
		 int start_row = (page-1)*pageSize+1;
		 int end_row = (page-1)*pageSize+pageSize;
		 
		 cvo.setStart_row_(start_row+"");
		 cvo.setEnd_row_(end_row+"");
		 
	}//setPage
	
}//class
