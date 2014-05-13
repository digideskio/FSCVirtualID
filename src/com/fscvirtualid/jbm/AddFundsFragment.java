package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddFundsFragment extends Fragment {
	
	public AddFundsFragment(){}
	
	Button add;
	EditText amount;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_addfunds, container, false);
        
        add = (Button) rootView.findViewById(R.id.buttonAdd);
        amount = (EditText) rootView.findViewById(R.id.EditTextAmount);
        
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Fragment fragment = new PaymentTypeFragment();      	
            	FragmentManager fragmentManager = getFragmentManager();
            	fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
				
			}
		});
         
        return rootView;
    }
}
