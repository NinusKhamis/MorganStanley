/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */
package com.morganstanley.tictactoe;

import java.util.LinkedList;

/** 
* This class is in charge managing a single instance of a specific session "Singleton"
*/ 
public class ThreadLocalTrack {
	private static ThreadLocal<LinkedList<ThreadLocal<Object>>> tltThreadLocal = new ThreadLocal<LinkedList<ThreadLocal<Object>>>();
	private static ThreadLocal<Object> tlRegistry = null;
	
	public static void setThread(Object httpSession) {
		ThreadLocalTrack.registerThread(tlRegistry);
		init();
		tlRegistry.set(httpSession);
	}
	
	public static void registerThread(ThreadLocal<Object> tltThreadLocal) {
		getThreadLocal().add(tltThreadLocal);
	}
	
	private static synchronized void init(){
		if(tlRegistry==null) 
			tlRegistry = new ThreadLocal<Object>();
	}
	
	public static void deleteThread(){
		for(ThreadLocal<Object> oTL : getThreadLocal()){
			if(oTL==null) 
				continue;
			oTL.set(null);
		}
		
		tltThreadLocal.set(null);
	}
	
	private static LinkedList<ThreadLocal<Object>> getThreadLocal() {
		if(tltThreadLocal.get()==null)
			tltThreadLocal.set(new LinkedList<ThreadLocal<Object>>());
		return tltThreadLocal.get();
	}	
}
