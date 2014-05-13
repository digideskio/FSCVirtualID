package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PinUnlockFragment extends Fragment {
	
	public PinUnlockFragment(){}
	
	ImageButton done;
	EditText pin;
	//final String secretPin = "1234";
	String pinCheck;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_unlockpin, container, false);
        
        done = (ImageButton) rootView.findViewById(R.id.imageButtonDone);
        pin = (EditText) rootView.findViewById(R.id.editTextPin);
        
        done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				// TODO Auto-generated method stub
				String inputPin = pin.getText().toString().toLowerCase();
				
				//obtain userPinNumber from LoginActivity and assign to pinCheck
		        pinCheck = getActivity().getIntent().getStringExtra("userPinLoginActivity");
    
                if (inputPin.equals(pinCheck))
                {
                	InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                	imm.hideSoftInputFromWindow(pin.getWindowToken(), 0);
                	
                	Fragment fragment = new DisplayQRFragment();
                	FragmentManager fragmentManager = getFragmentManager();
                	fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
                }
                else
                {
                    Log.d("Test", "onClickListener ist gestartet");
                	Toast.makeText(getActivity().getApplicationContext(), "Invalid Pin", Toast.LENGTH_LONG).show();
                }
			}
		});
       
        return rootView;
    }

}