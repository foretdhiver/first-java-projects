package com.spring.ge.common;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class EaFileUploadUtil {

	private static final int SIZE_LIMIT = 5240880;	
	private MultipartRequest m;

	public boolean fileUpload(HttpServletRequest request, String filePaths){
		System.out.println("파일업로드 클래스 fileUpload함수 진입 ");
		boolean fb = false;
		try{
			m = new MultipartRequest( request
                                     ,filePaths
                                     ,SIZE_LIMIT
                                     ,"EUC-KR"
                                     ,new EaFileName());
			request.setCharacterEncoding("EUC-KR");
			return fb = true;
		}catch(Exception e){
			System.out.println("BabyFileUploadUtil.fileUpload() >>> : " + e);
		}
		return fb;		
	}// end of fileUpload(request, filePaths)
	
	public String getParameter(String s){
		return m.getParameter(s);
	}// end of String getParameter()
	
	public Enumeration<String> getFileNames(){
		Enumeration<String> en = m.getFileNames();
		Vector<String> v = new Vector<String>();
		
		while (en.hasMoreElements()){
			String f = en.nextElement().toString();
			v.add(m.getFilesystemName(f));
		}
		return v.elements();
	}// end of Enumeration getFileName()
	
	public String getFileName(String f){
		return m.getFilesystemName(f);
	}// end of String getFileName()
	
}// end of FileUploadUtil class