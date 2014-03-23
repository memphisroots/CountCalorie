package com.example.countcalorie;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class CheckUserNameListener implements TextWatcher {

	private Context mContext;
	private LoginReg caller;
	EditText mEdittextview;
	
	public CheckUserNameListener(Context context, EditText edittextview) {
	    super();
	    this.mContext = context;
	    this.mEdittextview= edittextview;
	}
	
	public void addCaller(LoginReg l){
		this.caller = l;
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
		//Check Username Availability
		String username = ((EditText) caller.findViewById(R.id.newusername)).getText().toString();
						
		//Toast mytoast = Toast.makeText(caller,username, Toast.LENGTH_LONG)
		//mytoast.show();
						
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("user",username));
				
		new ConMan(nameValuePairs,ConMode.MODE_CHECK, caller).execute();
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
		caller.setUserAvailability(2);
		
	}
	

}
