package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PaymentTypeFragment extends Fragment {
	
	public PaymentTypeFragment(){}
	
	Button confirm;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_paymenttype, container, false);
        
        confirm = (Button) rootView.findViewById(R.id.ButtonForgotPasswordSubmit);
        
        confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Fragment fragment = new ConfirmationFragment();      	
            	FragmentManager fragmentManager = getFragmentManager();
            	fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
				
			}
		});
         
        return rootView;
    }
}
