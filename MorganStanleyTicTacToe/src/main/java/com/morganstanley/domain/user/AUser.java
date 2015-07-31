/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.domain.user;

/**
* The following abstract class represents the users
* of the application.
*/ 
public abstract class AUser implements IUser {
	private UserToken enmUserToken;	
	
	protected AUser(UserToken enmUserToken){		
		this.enmUserToken = enmUserToken;			
	}
	
	public void setUserToken(UserToken enmUserToken) {
		this.enmUserToken = enmUserToken;		
	}
	
	public UserToken getUserToken(){
		return this.enmUserToken;
	}	
	
}