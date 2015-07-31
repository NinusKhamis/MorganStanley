/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.domain.user;

import java.util.Map;
import java.util.TreeMap;

/**
* The following interface represents the users
* of the application.
*/ 
public interface IUser {
	public UserToken getUserToken();
	public void setUserToken(UserToken token);	
	
	public enum UserToken {		
		XUser('X'),
		OUser('O');
		
		private final char value;
		private static final Map<Character, UserToken> stringToTypeMap = new TreeMap<Character, UserToken>();
		
	    static {
	        for (UserToken type : UserToken.values()) {
	            stringToTypeMap.put(type.value, type);
	        }
	    }
	    
	    private UserToken(char value) {
	        this.value = value;
	    }

	    public static UserToken fromString(String token) {
	        UserToken type = stringToTypeMap.get(Integer.valueOf(token));
	        if (type == null) 
	            return UserToken.XUser;
	        return type;
	    }
	    
	    public char getValue() {
	        return value;
	    }
	}
}