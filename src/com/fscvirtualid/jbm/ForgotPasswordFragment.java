package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ForgotPasswordFragment extends Fragment {
	
	public ForgotPasswordFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_forgotpassword, container, false);
         
        return rootView;
    }
}
