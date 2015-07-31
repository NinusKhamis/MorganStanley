/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.business.manager;

/**
* The following class is in charge of mapping the "Views" to
* the "Models"   
*/ 
public class ObjectManager {	
	protected static final String[] MANAGER_PACKAGE_NAME ={"com.morganstanley.business.manager."};
	
	public AManager getManager(String managerName){
    	AManager manager = null;
		try{
			Class<?> c = getManagerClass(managerName);
			if (c != null) {
				Object o = c.newInstance();
				manager = (AManager) o;
			}
		}
		catch (InstantiationException e){} 
		catch (IllegalAccessException e){} 
		return manager;
    }
	
    private Class<?> getManagerClass(String managerName){        
    	if (managerName == null){
			throw new NullPointerException("Missing getManagerClass argument!");
		} 
		
    	Class<?> c = null;
		for (int i = 0; i < MANAGER_PACKAGE_NAME.length; i++){
			String cmdClassName = MANAGER_PACKAGE_NAME[i] + managerName;
			try{
				c = Class.forName(cmdClassName);
				if(c != null){
					break;
				}
			}
			catch (ClassNotFoundException e){}
		}
		return c;
    }    
}