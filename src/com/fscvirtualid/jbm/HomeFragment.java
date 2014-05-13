package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){}
	
	Button scan;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        scan = (Button) rootView.findViewById(R.id.buttonScan);
        
        scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub				
				Fragment fragment = new PinUnlockFragment();      	
            	FragmentManager fragmentManager = getFragmentManager();
            	fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
			}
		});

        return rootView;
    }

}


