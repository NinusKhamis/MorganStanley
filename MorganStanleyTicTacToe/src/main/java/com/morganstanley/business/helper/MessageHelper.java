/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.business.helper;

/**
* The following class implements the messaging layer in charge of
* providing messages to the View from the Model.
*/ 
public class MessageHelper {
    private String status;    
    
	public void setMessage(String status){
		this.status = status;
	}

    public String getMessage(){
    	if(this.status==null) return "";
    	else return this.status; 
	}    

    public void appendMessage(String status) {
		if(this.status == null && !status.isEmpty())
			this.status = status + "<br>" ;
		else if(!status.isEmpty())
			this.status += status + "<br>";
	}   
    
}