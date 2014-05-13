package com.fscvirtualid.jbm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.fscvirtualid.jbm.JSONParser;
import com.fscvirtualid.jbm.RegisterActivity;
import com.fscvirtualid.jbm.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

public class LoginActivity extends FragmentActivity implements OnClickListener{

	private Button mSubmit, mRegister;
	
	Button login;
	Button register;
	EditText email;
	EditText password;
	
	String userPinNumber;
	String userRamID;

	// Progress Dialog
    private ProgressDialog pDialog;
    
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    //testing from a real server:
    private static final String LOGIN_URL = "http://farvlu.farmingdale.edu/~schwj13/androidvirtualid/webservice/login.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String PIN_NUMBER = "pin";
    private static final String RAM_ID = "ramid";
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//setup input fields
		email = (EditText)findViewById(R.id.EditTextEmail);
		password = (EditText)findViewById(R.id.EditTextPassword);
		
		//setup buttons
		mSubmit = (Button)findViewById(R.id.buttonLogin);
		mRegister = (Button)findViewById(R.id.buttonRegister);
		
		//register listeners
		mSubmit.setOnClickListener(this);
		mRegister.setOnClickListener(this);
		
	}
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLogin:
				new AttemptLogin().execute();
			break;
		case R.id.buttonRegister:
				Intent i = new Intent(this, RegisterActivity.class);
				startActivity(i);
			break;

		default:
			break;
		}
	}
	
	class AttemptLogin extends AsyncTask<String, String, String> {

		 /**
        * Before starting background thread Show Progress Dialog
        * */
		boolean failure = false;
		
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pDialog = new ProgressDialog(LoginActivity.this);
           pDialog.setMessage("Attempting login...");
           pDialog.setIndeterminate(false);
           pDialog.setCancelable(true);
           pDialog.show();
       }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
           int success;
           String inputEmail = email.getText().toString().toLowerCase();
           String inputPassword = password.getText().toString().toLowerCase();
           try {
               // Building Parameters
               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("email", inputEmail));
               params.add(new BasicNameValuePair("password", inputPassword));

               Log.d("request!", "starting");
               // getting product details by making HTTP request
               JSONObject json = jsonParser.makeHttpRequest(
                      LOGIN_URL, "POST", params);

               // check your log for json response
               Log.d("Login attempt", json.toString());
               
               // json success tag
               success = json.getInt(TAG_SUCCESS);
               if (success == 1) {

               	Log.d("Login Successful!", json.toString());
               	
               	//assign value to userPinNumber and start activity
               	Intent i = new Intent(LoginActivity.this, MainActivity.class);
               	
               	userPinNumber = json.getString(PIN_NUMBER);
            	i.putExtra("userPinLoginActivity", userPinNumber);
            	
            	userRamID = json.getString(RAM_ID);
            	i.putExtra("userRamIDLoginActivity", userRamID);
            	
               	finish();
   				startActivity(i);
               	return json.getString(TAG_MESSAGE);
               }else{
               	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
               	return json.getString(TAG_MESSAGE);
               	
               }
           }
           catch (JSONException e) {
               e.printStackTrace();
           }
           return null;	
		}
		

		/**
        * After completing background task Dismiss the progress dialog
        * **/
       protected void onPostExecute(String file_url) {
           // dismiss the dialog once product deleted
           pDialog.dismiss();
           if (file_url != null){
           	Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
           }
       }
	}
}

    