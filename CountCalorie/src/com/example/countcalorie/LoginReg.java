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
import android.widget.Spinner;
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
		//use for checking
		//uncomment this before launch
		/*if(!errorFreeReg()){
			return;
		}*/
		
		//Check Username Availability
		String username = ((EditText) findViewById(R.id.newusername)).getText().toString();
		
		Toast mytoast = Toast.makeText(this,username, Toast.LENGTH_LONG);
		mytoast.show();
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("user",username));
 
        
		new ConMan(nameValuePairs,ConMode.MODE_CHECK, this).execute();
		
		//convert height
		EditText eth1 = (EditText) findViewById(R.id.heightftinput);
		EditText eth2 = (EditText) findViewById(R.id.heightinchinput);
		String height = Double.toString(Converter.convHeight(eth1.getText().toString(), eth2.getText().toString()));
		Log.i("Height:",height);
		
		//
		
		
		
	}
	
	public void LoginButton(View v){
		
	}
	
    //use to error check the input fields
	public boolean errorFreeReg(){
		EditText et;
		Toast mytoast;
		boolean test;
		boolean errorFree = true;
		
		//test usernameTester
		et = (EditText) findViewById(R.id.newusername);
		test = ErrorCheck.checkUsername(et.getText().toString());
		if(!test){
		    mytoast = Toast.makeText(this,"Invalid User Name! (Letters and Numbers only)", Toast.LENGTH_LONG);
			mytoast.show();
			return test;
		}
		
		//check age
		et = (EditText) findViewById(R.id.ageinput);
		test = ErrorCheck.ensureNumber(et.getText().toString());
		if(!test){
		    mytoast = Toast.makeText(this,"Invalid Age!", Toast.LENGTH_LONG);
			mytoast.show();
			return test;
		}
		
		//check sex
		Spinner s = (Spinner) findViewById(R.id.sexinput);
		String val = s.getSelectedItem().toString();
		if(val.equalsIgnoreCase("sex.")){
			mytoast = Toast.makeText(this,"Choose a Sex", Toast.LENGTH_LONG);
			mytoast.show();
			return false;
		}
		
		//check weight
		et = (EditText) findViewById(R.id.weightinput);
		test = ErrorCheck.doubleOkCheck(et.getText().toString());
		if(!test){
			mytoast = Toast.makeText(this,"Invalid Weight!", Toast.LENGTH_LONG);
			mytoast.show();
			return test;
		}
		
		//check goal
		et = (EditText) findViewById(R.id.weightgoal);
		test = ErrorCheck.doubleOkCheck(et.getText().toString());
		if(!test){
			mytoast = Toast.makeText(this,"Invalid Loss Goal!", Toast.LENGTH_LONG);
			mytoast.show();
			return test;
		}
		
		//check height feet
		et = (EditText) findViewById(R.id.heightftinput);
		test = ErrorCheck.ensureNumber(et.getText().toString());
		if(!test){
			mytoast = Toast.makeText(this,"Ivalid Height in Feet!", Toast.LENGTH_LONG);
			mytoast.show();
			return test;
		}
		
		//check height inches
		et = (EditText) findViewById(R.id.heightinchinput);
		test = ErrorCheck.ensureNumber(et.getText().toString().trim());
		if(!test){
			mytoast = Toast.makeText(this,"Ivalid Height in Inches!", Toast.LENGTH_LONG);
			mytoast.show();
			return test;
		}
		
		return errorFree;
	}
}
