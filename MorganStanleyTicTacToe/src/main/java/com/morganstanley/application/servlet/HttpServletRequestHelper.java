/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.application.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* The following class is in charge of implementing a simple
* HTTPServletRequest wrapper 
*/ 
public class HttpServletRequestHelper {	
	protected HttpServletRequest httpRequest = null;
	protected HttpServletResponse httpResponse = null;
	
	public void init(HttpServletRequest req, HttpServletResponse res) {
		this.httpRequest = req;
		this.httpResponse = res;		
	}	
	
	protected void forward(String target)
	  throws java.io.IOException, ServletException {
		this.httpRequest.getRequestDispatcher(target).forward(httpRequest, httpResponse);
	}		
}