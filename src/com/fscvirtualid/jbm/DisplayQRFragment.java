package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DisplayQRFragment extends Fragment {
	
	public DisplayQRFragment(){}
	
	TextView displayRamID;
	
	String ramID;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_displayqr, container, false);

        displayRamID = (TextView) rootView.findViewById(R.id.textViewRamID);
        
        ramID = getActivity().getIntent().getStringExtra("userRamIDLoginActivity");
        
        displayRamID.setText(ramID);
        
        return rootView;
    }

}


