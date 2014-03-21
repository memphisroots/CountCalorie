package com.example.countcalorie;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginReg extends FragmentActivity {
	FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//remove header
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_reg);
		
		
		 fragmentManager = getFragmentManager();
		 
		 LoginFragment logIn = new LoginFragment();
		 
		 FragmentTransaction ft = getFragmentManager().beginTransaction();
		 ft.add(R.id.fragmentContainer, logIn).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_reg, menu);
		return true;
	}

	
	public void psudoTabClick(View v){
		RadioButton temp;
		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		
		if(v.getId() == R.id.signSel){
			
				
				LoginFragment l = new LoginFragment();
				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.fragmentContainer, l);
				transaction.addToBackStack(null);
				
			
			
		}
		
		if(v.getId() == R.id.regSel){
			
				RegisterFragment r = new RegisterFragment();
				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.fragmentContainer, r);
				transaction.addToBackStack(null);
			
			
		}
		
		// Commit the transaction
		transaction.commit();
	}
	
	
	public void RegisterButton(View v){
		//Check for errors
		if(!(errorFreeReg())){
			return;
		}
		
		//Check Username Availability
		String username = ((EditText) findViewById(R.id.newusername)).getText().toString();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("user",username ));
 
        
		new ConMan(nameValuePairs,ConMode.MODE_CHECK, this).execute();
		
		//convert height
		EditText eth1 = (EditText) findViewById(R.id.heightftinput);
		EditText eth2 = (EditText) findViewById(R.id.heightinchinput);
		String height = Double.toString(Converter.convHeight(eth1.getText().toString(), eth2.getText().toString()));
		Log.i("Height:",height);
		/*Toast mytoast = Toast.makeText(this,t.getText().toString(), Toast.LENGTH_LONG);
    	mytoast.show();*/
		
		//test usernameTester
		boolean test = ErrorCheck.checkUsername("memphisroots");
		Log.i("TestThing:",String.valueOf(test));
	}
	
	public void LoginButton(View v){
		
	}
	
	public boolean errorFreeReg(){
		//Check that username is string
		String nono = ";:/\\_\'\"";
		return false;
	}
}
