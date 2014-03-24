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
	Toast mytoast;
	
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
				transaction.commit();
		}
		
		if(v.getId() == R.id.regSel){
			
				RegisterFragment r = new RegisterFragment();
				// Replace whatever is in the fragment_container view with this fragment,
				// and add the transaction to the back stack
				transaction.replace(R.id.fragmentContainer, r);
				transaction.addToBackStack(null);
				transaction.commit();
			
		}
		
		// Commit the transaction
		//transaction.commit();
	}
	
	
	/*RegisterButton(View v) - void
	 * @Input: View
	 *  Method for registering new user. 
	 *     Check for user availablilty
	 *     Do error check
	 *     Check password is the same as confirm
	 *     Calculate BMR
	 *     Add all information to userInformation object
	 *     Execute SQL addUser
	 *     
	 */
	public void RegisterButton(View v){
		userInfo reg;
		
		//Rejection cases
		//1) username not Available
		if(userNameAvail ==0){
			mytoast = Toast.makeText(this,"Username Already Taken", Toast.LENGTH_LONG);
			mytoast.show();
        	return;
        }
        else if(userNameAvail == 2){
        	mytoast = Toast.makeText(this,"Username Conflict", Toast.LENGTH_LONG);
			mytoast.show();
            return;
        }
        
		String username = ((EditText) findViewById(R.id.newusername)).getText().toString();
		
		//Error Check all registration
		//uncomment this before launch
		if(!errorFreeReg()){
			return;
		}
		
        //Check password and confirm are the same
		String pass = ((EditText) findViewById(R.id.newpassword)).getText().toString();
		String conf = ((EditText) findViewById(R.id.newpassconfirm)).getText().toString();
		if(!pass.equals(conf)){
			mytoast = Toast.makeText(this,"Passwords Don't Match", Toast.LENGTH_LONG);
			mytoast.show();
			return;
		}
		
		//Calc BMR
		
		//convert height
		String eth1 = ((EditText) findViewById(R.id.heightftinput)).getText().toString();
		String eth2 = ((EditText) findViewById(R.id.heightinchinput)).getText().toString();
		String height = String.valueOf((Converter.convHeight(eth1, eth2)));
		
		
		//get Sex
		String sex = ((Spinner) findViewById(R.id.sexinput)).getSelectedItem().toString();
		String age = ((EditText) findViewById(R.id.ageinput)).getText().toString();
		String weight = ((EditText) findViewById(R.id.weightinput)).getText().toString();
		
		//Get BMR
		int BMR= 0;
		
		if(sex.equals("M")){
			BMR = Converter.maleBMR(weight, eth1, eth2, age);
		}
		else{
			BMR = Converter.femaleBMR(weight, eth1, eth2, age);
		}
		
		
		//get goal
		String getGoal = ((EditText) findViewById(R.id.weightgoal)).getText().toString();
		String goal = Converter.convGoal(getGoal);
		
		
		//set UserInformation in the userInfo Object
		reg = new userInfo(username,pass,weight,age,sex,height, goal, String.valueOf(BMR));
		List<NameValuePair> addUser = reg.getInfoSql();
		
		//Push to PHP handler through connection manager (ConMan)
		new ConMan(addUser,ConMode.MODE_ADD, this).execute();
		
		
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
