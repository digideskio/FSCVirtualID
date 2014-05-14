package com.fscvirtualid.jbm;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class HoursFragment extends Fragment {
	
	public HoursFragment(){}
	
	TextView pop;
	TextView market;
	TextView eco;
	TextView cafe;
	TextView bookstore;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_hours, container, false);
         
        pop = (TextView) rootView.findViewById(R.id.textViewPOP);
        market = (TextView) rootView.findViewById(R.id.textViewMarket);
        eco = (TextView) rootView.findViewById(R.id.textViewEco);
        cafe = (TextView) rootView.findViewById(R.id.textViewBooknBeans);
        bookstore = (TextView) rootView.findViewById(R.id.textViewBookstore);
        
        pop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.campusdish.com/en-US/CSE/Farmingdale/Locations/POPsDining.htm"));
				startActivity(browserIntent);
			}
		});
        
        market.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.campusdish.com/en-US/CSE/Farmingdale/Locations/CampusCenterMarket.htm"));
				startActivity(browserIntent);
			}
		});

        eco.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.campusdish.com/en-US/CSE/Farmingdale/Locations/EcoGrounds.htm"));
				startActivity(browserIntent);
			}
		});
        
        cafe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.campusdish.com/en-US/CSE/Farmingdale/Locations/BooksNBeansCafe.htm"));
				startActivity(browserIntent);
			}
		});
        
        bookstore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://farmingdale.bncollege.com/webapp/wcs/stores/servlet/BNCBLocationAndContactView?catalogId=10001&langId=-1&storeId=30053"));
				startActivity(browserIntent);
			}
		});

		

        
        
        
        
        
        
        return rootView;
    }
}
