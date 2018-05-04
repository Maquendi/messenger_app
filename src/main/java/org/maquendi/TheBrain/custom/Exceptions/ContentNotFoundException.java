package org.maquendi.TheBrain.custom.Exceptions;



public class ContentNotFoundException extends Exception{

	/**
	 * 
	 */
	
	private String errorMessage;
	
	private static final long serialVersionUID = 1L;
	
	
	public ContentNotFoundException(String message){
		super(message);
		errorMessage = message;
	}

	public String getError(){
		return errorMessage;
	}
	
	
	
}
