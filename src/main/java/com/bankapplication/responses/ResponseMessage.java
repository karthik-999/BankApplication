package com.bankapplication.responses;

import lombok.Data;

@Data
public class ResponseMessage {

	String message;
	
	public  ResponseMessage(String message){
		super();
		this.message= message;
	}
	
	
}
