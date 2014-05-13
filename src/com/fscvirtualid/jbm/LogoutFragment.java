package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogoutFragment extends Fragment {
	
	public LogoutFragment(){}
	
	Button logout;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_logout, container, false);
         
        logout = (Button) rootView.findViewById(R.id.buttonLogout);
        
        logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub	
				
				/*
				// on your logout method
				Intent broadcastIntent = new Intent();
				broadcastIntent.setAction("com.package.ACTION_LOGOUT");
				getActivity().sendBroadcast(broadcastIntent); */
				
				Intent intent = new Intent(getActivity(), SecuredActivity.class);
				startActivity(intent);
			}
		});
        
        
        return rootView;
    }
}
