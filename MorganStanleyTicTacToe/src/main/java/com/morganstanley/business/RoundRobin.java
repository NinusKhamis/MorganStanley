/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.business;

import java.util.List;
import java.util.Iterator;
import com.morganstanley.domain.user.IUser;

/**
* The following class implements a simple round robing iteration
* mechanism.  
*/ 
public class RoundRobin {
	private Iterator<IUser> it;
	private List<IUser> list;
	  
	public RoundRobin(List<IUser> list) {
		this.list = list;
		it = list.iterator();
	}
	 
	public IUser next() {
		if (!it.hasNext())
			it = list.iterator();
		
		IUser user = it.next();
		return user;
	}
}
