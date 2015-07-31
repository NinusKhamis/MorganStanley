/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.application.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.morganstanley.tictactoe.ThreadLocalTrack;

/**
* The following class is in charge of implementing a simple
* thread local tracking of sessions 
*/ 
public abstract class Servlet extends HttpServlet{
	private static final long serialVersionUID = 7343541027400545634L;	
	
   	protected void execute(
		HttpServletRequest httpRequest,
		HttpServletResponse httpResponse)
		throws ServletException, java.io.IOException
	{
   		preProcessRequest(httpRequest, httpResponse);
		processRequest(httpRequest, httpResponse);
		postProcessRequest(httpRequest, httpResponse);
	}
   	
   	protected void preProcessRequest(HttpServletRequest request, HttpServletResponse response) {
		ThreadLocalTrack.setThread(request.getSession(true));
	}	
	
	protected abstract void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, java.io.IOException;
	
	protected void postProcessRequest(HttpServletRequest request, HttpServletResponse response) {
		ThreadLocalTrack.deleteThread();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, java.io.IOException {
    	execute(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, java.io.IOException {
    	doGet(request, response);
    }
}