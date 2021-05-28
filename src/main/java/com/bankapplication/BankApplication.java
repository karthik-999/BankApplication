package com.bankapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	
	//Added Bean - Developer 
	 @Bean
	    public ModelMapper modelMapper(){
	        ModelMapper modelMapper = new ModelMapper();
	        return modelMapper;
	    }
}
