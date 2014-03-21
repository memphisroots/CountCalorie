package com.example.countcalorie;

public class ErrorCheck {

	//check the user name for validity
	public static boolean checkUsername(String userName){
		boolean isGood = false;
		
	    
		for(int i =0;i< (userName.length());i++){
			if(Character.isDigit(userName.charAt(i))){
				isGood = true;
			}
			else if(Character.isLetter(userName.charAt(i))){
				isGood = true;
			}
			else{
				isGood = false;
				break;
			}
		}
	    
		return isGood;
	}
}
