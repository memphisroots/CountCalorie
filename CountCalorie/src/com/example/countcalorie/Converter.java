package com.example.countcalorie;

public class Converter {
	public static int FT_INCHES = 12;
	//male bmr
	public static int MALE_BMR_FIRST = 66;
	public static double MALE_BMR_SECOND = 6.23;
	public static double MALE_BMR_THIRD = 12.7;
	public static double MALE_BMR_FOURTH = 6.8;
	//female bmr
	public static int FEMALE_BMR_FIRST = 655;
	public static double FEMALE_BMR_SECOND = 4.35;
	public static double FEMALE_BMR_THIRD = 4.7;
	public static double FEMALE_BMR_FOURTH = 4.7;
	
	
	
	public static int maleBMR(String sweight,String sH1, String sH2,String sage){
		int weight = Integer.parseInt(sweight); 
		double height = convHeight(sH1,sH2);
		int age = Integer.parseInt(sage);
		
		return  (int) ((int) MALE_BMR_FIRST + (MALE_BMR_SECOND*weight) + (MALE_BMR_THIRD*height) - (MALE_BMR_FOURTH*age));
		
		
	}
	
	public static int femaleBMR(String sweight, String sHeight,String sage){
		int weight = Integer.parseInt(sweight); 
		double height = Double.parseDouble(sHeight);
		int age = Integer.parseInt(sage);
		
		return  (int) ((int) FEMALE_BMR_FIRST + (FEMALE_BMR_SECOND*weight) + (FEMALE_BMR_THIRD*height) - (FEMALE_BMR_FOURTH*age));
		
		
	}
	
	public static double convHeight(String h1, String h2){
		
		return (FT_INCHES*Double.parseDouble(h1)) + Double.parseDouble(h2);
	}
}
