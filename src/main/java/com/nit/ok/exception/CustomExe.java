package com.nit.ok.exception;

public class CustomExe extends Exception {


	private static final long serialVersionUID = 1L;
	
	
	public CustomExe(String message)
	{
		super(message);
	}
	
	public static String NotFoundException(String id)
	{
		return"todo with "+id+"not found";
	}
	
	public static String todoAlreadyexists()
	{
		return"Todo with given name already exist";
	}

}
