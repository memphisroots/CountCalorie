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
	int userNameAvail = 2;
	
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
		 
		 //add listener
		 EditText username = (EditText) findViewById(R.id.newusername);
		 CheckUserNameListener cunl = new CheckUserNameListener(this,username);
		 cunl.addCaller(this);
		 username.addTextChangedListener(cunl);
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
	
	
	/*RegisterButton(View v) - void
	 * @Input: View
	 *  Method for registering new user. 
	 *     Do error check
	 *     Check for user availablilty
	 *     Check password is the same as confirm
	 *     Get height conversion to inches
	 *     Calculate BMR
	 *     Add all information to userInformation object
	 *     Execute SQL addUser
	 *     
	 */
	public void RegisterButton(View v){
		//use for checking
		//uncomment this before launch
		/*if(!errorFreeReg()){
			return;
		}*/
		
		
 
        
		
        while(userNameAvail==2){}
        
        if(userNameAvail ==0){
        	return;
        }
		
		//convert height
		EditText eth1 = (EditText) findViewById(R.id.heightftinput);
		EditText eth2 = (EditText) findViewById(R.id.heightinchinput);
		String height = Double.toString(Converter.convHeight(eth1.getText().toString(), eth2.getText().toString()));
		Log.i("Height:",height);
		
		//
		
		
		
	}
	
	public void LoginButton(View v){
		
	}
	
	
    /*eroorFreeReg()
     *
     * Check through all registration input to validate information, and try to prevent
     *   injections. If any fail, cancel the registration, and require user to intervene
     * 
     * @Return: boolean
     * 
     */
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
	
	public void setUserAvailability(int level){
		this.userNameAvail = level;
	}
}
