package com.spring.ge.common;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class EmFileUploadUtil {

	private static final int SIZE_LIMIT = 5240880;	
	private MultipartRequest m;

	public boolean fileUpload(HttpServletRequest request, String filePaths){
		
		boolean fb = false;
		
		try{
			m = new MultipartRequest( request
                                     ,filePaths
                                     ,SIZE_LIMIT
                                     ,"EUC-KR"
                                     ,new FileName());
			
			request.setCharacterEncoding("EUC-KR");
			return fb = true;
			
		}catch(Exception e){
			System.out.println("BabyFileUploadUtil.fileUpldad() >>> : " + e);
		}
		
		return fb;		
	}
	
	public String getParameter(String s){
		return m.getParameter(s);
	}
	
	public Enumeration<String> getFileNames()
	{
		Enumeration<String> en = m.getFileNames();
		Vector<String> v = new Vector<String>();
		
		while (en.hasMoreElements())
		{
			String f = en.nextElement().toString();
			v.add(m.getFilesystemName(f));
		}
		
		return v.elements();
	}
	
	public String getFileName(String f){
		return m.getFilesystemName(f);
	}
	
}