package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConfirmationFragment extends Fragment {
	
	public ConfirmationFragment(){}
	
    private final int DISPLAY_LENGTH = 3000;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_confirmation, container, false);
        
        new Handler().postDelayed(new Runnable()
        {
       	 @Override
       	 public void run() {
       		 
       		 //Create an Intent that will start the Menu-Activity.
				Fragment fragment = new BalanceFragment();      	
            	FragmentManager fragmentManager = getFragmentManager();
            	fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            }
        }, DISPLAY_LENGTH);
         
        return rootView;
    }
}
