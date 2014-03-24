package com.example.countcalorie;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      // Inflate the layout for this fragment
	  View view = inflater.inflate(R.layout.registerfragment, container, false);
        
      //add listener for checking availability
	  EditText username = (EditText) view.findViewById(R.id.newusername);
	  CheckUserNameListener cunl = new CheckUserNameListener(this.getActivity(),username);
	  cunl.addCaller((LoginReg) this.getActivity());
	  username.addTextChangedListener(cunl);
	  
	  return view;
	  
    }
}
