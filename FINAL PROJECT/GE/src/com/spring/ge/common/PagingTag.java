package com.spring.ge.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PagingTag extends TagSupport {
	private static final long serialVersionUID=1L;
	/*
	 * @param page 현재 페이지 번호
	 * @param total 전체 조회된 row수
	 * @param list_size 페이지에 보여주는 레코드 수
	 * @param page_size 페이지 네비게이터에 표시되는 페이지버튼의 갯수
	 * */

	private int page=1;
	private int total=1;
	private int list_size=10;
	private int page_size=10;
	
	@Override
	public int doStartTag() throws JspException{
		
		try{
			pageContext.getOut().println(getPaging());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}

	//setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getList_size() {
		return list_size;
	}

	public void setList_size(int list_size) {
		this.list_size = list_size;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
public String getPaging(){
		
	String ret="";
	
	if(page<1)
		page=1;
	if(total<1)
		return"";
	
	  //page가 1이고 page_size가 2이면
	  //currentFirst = 1
	  int currentFirst = ((page-1)/page_size)*page_size+1;
	  
	  //currentFirst = 2
	  int currentLast = ((page-1)/page_size)*page_size+page_size;
	  
	  //nextFirst = 3
	  int nextFirst = (((page-1)/page_size)+1)*page_size+1;
	  
	  //nextFirst = 0
	  int prevLast = (((page-1)/page_size)-1)*page_size+1;
	  
	  int lastPage=1;
	  lastPage = total/list_size;
	  
	  //lastPage(총 페이지 수)는 total이 11이고, list_size가 10이면 1을 가진다.
	  //그래서 총 페이지 수가 나누어 떨어지지 않으면 나머지 레코드를 출력할 페이지가 필요
	  if(total%list_size!=0) lastPage=lastPage+1;
	  //currentPage가 lastpage보다 크면 마지막 페이지로 수정
	  currentLast = (currentLast>lastPage)?lastPage:currentLast;
	  
	  ret += "<div class='paginate'>";
	  
	  if(page>1){
		  ret+="<a href=\"javascript:goPage('1')\"><span><img src='../images/common/btn_paginate_first.gif' alt='처음' /></span></a>";
	  }else{
		  ret+="<span><img src='../images/common/btn_paginate_first.gif' alt='처음' /><span>";
	  }//if(page>1)
	  
	  if(prevLast>0){
		  ret+="<a href=\"javascript:goPage('"+prevLast+"');\"><span><img src='../images/common/btn_paginate_prev.gif' alt='이전' /></span></a>";
	  }else{
		  ret+="<span><img src='../images/common/btn_paginate_prev.gif' alt='이전' /><span>";
	  }// if(prevLast>0)
	  
	  for(int j=currentFirst; j<currentFirst+page_size && j<=lastPage; j++){
		  if(j<= currentLast){
			  if(j==page){
				  ret+="<a href='#' class='on textAn'>" +j+ "</a>";
			  }else{
				  ret+="<a href=\"javascript:goPage("+j+");\" class='textAn'>"+j+"</a>";
			  }//if-else
		  }//if(j<= currentlast)
	  }//for
	  
	 if(nextFirst<=lastPage){
		 ret+="<a href=\"javascript:goPage('"+nextFirst+"')\"><span><img src='../images/common/btn_paginate_next.gif' alt='다음'/></span></a>";
	 }else{
		 ret+="<span><img src='../images/common/btn_paginate_next.gif' alt='다음'/></span>";
	 }//if(nextFirst<=lastPage)
	
	if(page<lastPage){
		 ret+="<a href=\"javascript:goPage('"+lastPage+"')\"><span><img src='../images/common/btn_paginate_last.gif' alt='마지막'/></span></a>";
	}else{
		ret+="<span><img src='../images/common/btn_paginate_last.gif' alt='마지막'/></span>";
	}
	
	ret += "</div>";
	
	return ret;
}//getPaging




	
	
}//class
