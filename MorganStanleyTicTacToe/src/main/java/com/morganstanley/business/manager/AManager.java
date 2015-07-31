/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.business.manager;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.morganstanley.application.servlet.HttpServletRequestHelper;
import com.morganstanley.business.helper.MessageHelper;

/**
* The following abstract class is a generalization of all "Managers" the application
* will contain
*/ 
public class AManager extends HttpServletRequestHelper {	
	protected MessageHelper msgHelper;
	protected List<Long> id;
	
	public void init(HttpServletRequest httpRequest, HttpServletResponse httpResponse) 	{
		super.init(httpRequest, httpResponse);
		this.msgHelper = new MessageHelper();						
	}
	
	public void setHTTPRequest(HttpServletRequest request) {
		this.httpRequest = request;		
	}
	
	public HttpServletRequest getHTTPRequest() {
		return this.httpRequest;		
	}
	
}