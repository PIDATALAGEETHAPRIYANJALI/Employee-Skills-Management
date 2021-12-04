package com.employeeskillmanagement.entities;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component("RandomPassword")
public class Generation_Of_Password {
	
	public String generatingRandomPassword(int length) {
		
    String symbols = "-/.^&*_!@%=+>)"; 
    String cap_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    String small_letters = "abcdefghijklmnopqrstuvwxyz"; 
    String numbers = "0123456789"; 
    
    String finalString = cap_letters + small_letters + numbers + symbols; 

    Random random = new Random(); 

    StringBuilder password = new StringBuilder(length);

    for (int i = 0; i < length; i++) 
    {
    	password.append((finalString.charAt(random.nextInt(finalString.length())))); 

    } 
    return password.toString();
	}

}





