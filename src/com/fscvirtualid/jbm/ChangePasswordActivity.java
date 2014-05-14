package com.fscvirtualid.jbm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.fscvirtualid.jbm.JSONParser;
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

public class ChangePasswordActivity extends FragmentActivity implements OnClickListener{

	private Button mSubmit;
	
	EditText ramid;
	EditText newPassword;
	EditText confirmPassword;
	
	// Progress Dialog
    private ProgressDialog pDialog;
    
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    //testing from a real server:
    private static final String LOGIN_URL = "http://farvlu.farmingdale.edu/~schwj13/androidvirtualid/webservice/changepassword.php";
    
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);
		
		//setup input fields
		ramid = (EditText)findViewById(R.id.EditTextRamID);
		newPassword = (EditText)findViewById(R.id.EditTextNewPassword);
		confirmPassword = (EditText)findViewById(R.id.EditTextConfirmPassword);
		
		//setup buttons
		mSubmit = (Button)findViewById(R.id.ButtonForgotPasswordSubmit);
		
		//register listeners
		mSubmit.setOnClickListener(this);
		
	}
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ButtonForgotPasswordSubmit:
				new AttemptLogin().execute();
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
           pDialog = new ProgressDialog(ChangePasswordActivity.this);
           pDialog.setMessage("One moment...");
           pDialog.setIndeterminate(false);
           pDialog.setCancelable(true);
           pDialog.show();
       }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
           int success;
           String inputNewPassword = newPassword.getText().toString().toLowerCase();
           String inputConfirmPassword = confirmPassword.getText().toString().toLowerCase();
           String inputRamID = ramid.getText().toString().toLowerCase();
           try {
               // Building Parameters ($_POST in php)
               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("ramid", inputRamID));
               params.add(new BasicNameValuePair("newpassword", inputNewPassword));
               params.add(new BasicNameValuePair("confirmpassword", inputConfirmPassword));

               Log.d("request!", "starting");
               // getting product details by making HTTP request
               JSONObject json = jsonParser.makeHttpRequest(
                      LOGIN_URL, "POST", params);

               // check your log for json response
               Log.d("Updating Password...", json.toString());
               
               // json success tag
               success = json.getInt(TAG_SUCCESS);
               if (success == 1) {

               	Log.d("Password has been updated!", json.toString());
               	
               	//start activity
               	Intent i = new Intent(ChangePasswordActivity.this, LoginActivity.class);
             
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
           	Toast.makeText(ChangePasswordActivity.this, file_url, Toast.LENGTH_LONG).show();
           }
       }
	}
}

    