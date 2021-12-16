package com.employeeskillmanagement.entities;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component("RandomPassword")
public class PasswordGenerator {
		public String generateRandomPassword(int len) {
			
//			String symbol = "-/.^&*_!@%=+>)"; 
	        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	        String small_letter = "abcdefghijklmnopqrstuvwxyz"; 
	        String numbers = "0123456789"; 

	        String finalString = cap_letter + small_letter +  numbers ;
//	        		+ symbol; 

	        Random random = new Random(); 

	        StringBuilder password = new StringBuilder(len); 

	        for (int i = 0; i < len; i++) 
	        { 
	            password.append(finalString.charAt(random.nextInt(finalString.length()))); 

	        } 
	        return password.toString();
			
}
}