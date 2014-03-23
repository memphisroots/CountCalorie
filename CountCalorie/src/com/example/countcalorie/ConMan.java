package com.example.countcalorie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/*
 * 
 *  ConMan is an Abstracted manager for passing connections to server 
 *    It works in conjunction with ConMode to decide how to handle
 *    the connection.
 * 
 *    instead of passing directly to a SQL server. I pass to a WEB-PHP SQL handler that gives
 *    requests to the shared localhost sql server
 */

public class ConMan extends AsyncTask<String, Integer, String>{
	ConMode cm = new ConMode();
	List<NameValuePair> nameValuePairs;
	int mode;
	HttpResponse response = null;
	LoginReg caller;
	
	ConMan(List<NameValuePair> n, int m, LoginReg a){
		this.nameValuePairs = n;
		this.mode = m;
		this.caller = a;
		Log.i("URL:", "http://www.johncanthony.net"+cm.getMode(mode));
		Log.i("Username:", n.get(0).toString());
	}
	
	@Override
	protected String doInBackground(String... url) {
		 // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    
	    HttpPost httppost = new HttpPost("http://www.johncanthony.net"+cm.getMode(mode));
	    
	    try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		String responseStr = "nothing";
		try {
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//look at response
		if(!(responseStr.trim()).equals("nope:")){
		 Toast mytoast = Toast.makeText(this.caller,"Username Already Taken", Toast.LENGTH_LONG);
		 mytoast.show();
		}
		
        //Log.i("Response", responseStr);
    	
	}
	
	/*// this will become the actual async
	public HttpResponse phpCon(List<NameValuePair> nameValuePairs, int mode){
		
		
	}*/
	
	/*public void addUser(userInfo ui) {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("johncanthony.net/addUser.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("id", "12345"));
	        nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
	    	
	    	List<NameValuePair> nameValuePairs = ui.getInfoSql();
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} 
	
	public void checkUserName(Context c,String username){
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.johncanthony.net/countcalorie/userAvailable.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("user", username));
	        //nameValuePairs.add(new BasicNameValuePair("stringdata", "AndDev is Cool!"));
	    	
	    	
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        String responseStr = EntityUtils.toString(response.getEntity());
	        
	    	Toast mytoast = Toast.makeText(c,responseStr, Toast.LENGTH_LONG);
	    	mytoast.show();
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	
	}*/
	
	
}
