package com.spring.ge.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {

	/**
	* �������̵� ������ ������ �����̸��� ����.
	* 
	*/
	private static final String EMID  = "EMID";
	private static final String EMPW  = "EMPW";
	private static final String EMNO  = "EMNO";
	//private static final String AUTHORIRY = "AUTHORIRY";
	/***
	* kill Session, ���ǿ� ��� ������ ��� �����.
	* 
	* @param     hReq       servlet request.
	* @return    servlet request�� null�̸� false�� �����ϰ�, 
	*            �ƴϸ� ���ǿ� ��� ������ ��� ����� true�� ����.
	* @exception Exception.
	*/
	public static boolean killSession(final HttpServletRequest hReq) 
		throws Exception
	{
		if (hReq == null) return false;
		
		try
		{
			HttpSession hSession = hReq.getSession(true);
			hSession.invalidate();
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return true;
	}
	
	/***
	* �������̵� ������ ���ǿ� ��´�.
	* 
	* @param     hReq       servlet request.
	* @param     userID     �������̵�.
	* @return    servlet request�� null�̰ų�, �������̵��� ���� ������ 
	*            false�� �����ϰ�, �ƴϸ� �������̵� ������ ���ǿ� ��� true�� ����.
	* @exception Exception.
	*/
	public static boolean setSession(final HttpServletRequest hReq,
							         final String emID,final String emNO,final String emPW ) 
		throws Exception
	{
		if (hReq == null) return false;
		if (emID  == null || emID.trim().length() == 0) return false;
		if (emNO  == null || emNO.trim().length() == 0) return false;
		if (emPW  == null || emPW.trim().length() == 0) return false;
		HttpSession hSession = hReq.getSession(true);
		
		try
		{	
			hSession.setAttribute(EMID, emID);
			hSession.setAttribute(EMNO, emNO);
			hSession.setAttribute(EMNO, emPW);
			hSession.setMaxInactiveInterval(60*60*10);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return true;
	}
	
	/***
	* �������̵� ������ ���ǿ��� �����´�.
	* 
	* @param     hReq       servlet request.
	* @return    servlet request�� null�̸� �� ���ڿ�("")�� �����ϰ�, 
	*            �ƴϸ� ������ ������ �ִ� �������̵� ����.
	* @exception Exception.
	*/
	public static String getSessionID(final HttpServletRequest hReq) 
		throws Exception
	{
		if (hReq == null) return "";
		String strSessionID;
		
		HttpSession hSession = hReq.getSession(false);
		
		try
		{	
			strSessionID = (String)hSession.getAttribute(EMID);			
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return strSessionID;
	}
	public static String getSessionNO(final HttpServletRequest hReq) 
			throws Exception
		{
			if (hReq == null) return "";
			String strSessionNO;
			
			HttpSession hSession = hReq.getSession(false);
			
			try
			{	
				strSessionNO = (String)hSession.getAttribute(EMNO);			
			}
			catch(Exception e)
			{
				throw e;
			}
			
			return strSessionNO;
		}
	public static String getSessionPW(final HttpServletRequest hReq) 
			throws Exception
		{
			if (hReq == null) return "";
			String strSessionPW;
			
			HttpSession hSession = hReq.getSession(false);
			
			try
			{	
				strSessionPW = (String)hSession.getAttribute(EMPW);			
			}
			catch(Exception e)
			{
				throw e;
			}
			
			return strSessionPW;
		}
	
}//class
