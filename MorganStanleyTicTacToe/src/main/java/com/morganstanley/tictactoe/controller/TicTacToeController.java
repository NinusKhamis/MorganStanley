/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */
package com.morganstanley.tictactoe.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.morganstanley.application.servlet.Servlet;
import com.morganstanley.business.manager.AManager;
import com.morganstanley.business.manager.ObjectManager;

/** 
* This class acts as a "Meta Front Controller" capable of linking many view to
* many related models respectively. Does so using Reflections.
*/ 
@Controller
public class TicTacToeController extends Servlet {
	private static final long serialVersionUID = 7343541027400545634L;
	
	@RequestMapping(value = "/tictactoe", method = RequestMethod.GET)
	public String test() {
		return "tictactoe";
	}
	
	@RequestMapping(value = "/frontController", method = RequestMethod.POST)	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {		
		String managerName = request.getParameter("manager");		
		String action = (String) request.getParameter("action");

		Class<?>[] parametersType = new Class[2];
		Object[] parameters = new Object[2];

		try{
			ObjectManager om = new ObjectManager();
			Class<? extends AManager> c = om.getManager(managerName).getClass();
			AManager manager = (AManager) c.newInstance();			
			parametersType[0] = HttpServletRequest.class;
			parametersType[1] = HttpServletResponse.class;
			parameters[0] = request;
			parameters[1] = response;
			
			Method m = c.getMethod("init", parametersType);
			m.invoke(manager, parameters);
			
			// call the actual action to perform
			Method m1 = c.getMethod(action);
			m1.invoke(manager);				
		}
		catch (Exception e){
			e.printStackTrace();			
		}		
	}		
}