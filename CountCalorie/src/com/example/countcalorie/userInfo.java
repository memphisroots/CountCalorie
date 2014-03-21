package com.example.countcalorie;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class userInfo {
	
	private String username,pass,weight, age, sex, height, goal, bmr;
	
	public userInfo(String user,String pas, String weigh, String ag, String se, String heig,String go, String b){
		this.username = user;
		this.pass = pas;
		this.weight = weigh;
		this.age = ag;
		this.sex = se;
		this.height = heig;
		this.goal = go;
		this.bmr = b;
		
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPass(){
		return this.pass;
	}
	
	public String getWeight(){
		return this.weight;
	}
	
	public String getHeight(){
		return this.height;
	}
	
	public String getSex(){
		return this.sex;
	}
	
	public String getAge(){
		return this.age;
	}
	
	public String getGoal(){
		return this.goal;
	}

	public List<NameValuePair> getInfoSql(){
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("username", this.username));
        nameValuePairs.add(new BasicNameValuePair("pass", this.pass));
        nameValuePairs.add(new BasicNameValuePair("age", this.age));
        nameValuePairs.add(new BasicNameValuePair("weight", this.weight));
        nameValuePairs.add(new BasicNameValuePair("height", this.height));
        nameValuePairs.add(new BasicNameValuePair("sex", this.sex));
        nameValuePairs.add(new BasicNameValuePair("goal", this.goal));
        nameValuePairs.add(new BasicNameValuePair("bmr", this.bmr));
        
        
        
        return nameValuePairs;
	}
}
