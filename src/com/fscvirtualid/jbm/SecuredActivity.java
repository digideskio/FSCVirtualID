package com.fscvirtualid.jbm;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;

public class SecuredActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		
		//clear history stack
    	Intent intent = new Intent(this, LoginActivity.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); 
    	startActivity(intent);
		
        //Intent mainIntent = new Intent(SecuredActivity.this, LoginActivity.class);
    	//SecuredActivity.this.startActivity(mainIntent);
    }
}
