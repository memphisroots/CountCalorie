package com.example.countcalorie;

public class ErrorCheck {

	//check the user name for validity
	public static boolean checkUsername(String userName){
		boolean isGood = false;
		
		//check empty
		if(checkEmpty(userName)){
			return isGood;
		}
	    
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
	
	public static boolean ensureNumber(String num){
		boolean isGood = false;
		
		//check empty
		if(checkEmpty(num)){
			return isGood;
		}
		
		for(int i =0;i< (num.length());i++){
			if(Character.isDigit(num.charAt(i))){
				isGood = true;
			}
			else{
				isGood = false;
				break;
			}
		}
		return isGood;
	}
	
	public static boolean doubleOkCheck(String num){
		boolean isGood = false;
		
		//check empty
		if(checkEmpty(num)){
			return isGood;
		}
		
		for(int i =0;i< (num.length());i++){
			if(Character.isDigit(num.charAt(i))){
				isGood = true;
			}
			else if(num.charAt(i) == '.'){
				isGood = true;
			}
			else{
				isGood = false;
				break;
			}
		}
		
		
		return isGood;
	}
	
	
	//check to see if string is empty
	private static boolean checkEmpty(String check){
		boolean isGood = false;
		
		if(check.isEmpty()){
			return true;
		}
		
		return isGood;
	}
}
