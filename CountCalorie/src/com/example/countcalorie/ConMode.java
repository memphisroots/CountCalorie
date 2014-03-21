package com.example.countcalorie;

public class ConMode {
   String add = "/countcalorie/addUser.php";
   String check = "/countcalorie/userAvailable.php";
   String daily = "/countcalorie/addDaily.php";
   
   public static int MODE_DAILY = 3;
   public static int MODE_CHECK = 2;
   public static int MODE_ADD = 1;
   
   public String getMode(int i){
	   String mode;
	   
	   switch(i){
	   case 1:
		   mode = add;
		   break;
	   case 2:
		   mode = check;
		   break;
	   case 3:
		   mode = daily;
	   default:
		   mode = add;
	   }
	return mode;
   }
}
