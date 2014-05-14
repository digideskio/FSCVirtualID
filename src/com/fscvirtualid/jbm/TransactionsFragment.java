package com.fscvirtualid.jbm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fscvirtualid.jbm.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionsFragment extends Fragment {
	
	public TransactionsFragment(){}
	
//	TextView transactions;
//	
//	String ramID;
//	
//	String userTransactionNumber;
//	String userDate;
//	String userTime;
//	String userEstablishment;
//	String userAmount;
//	String returnString;
//	
//	
//	// Progress Dialog
//    private ProgressDialog pDialog;
//    
//    // JSON parser class
//    JSONParser jsonParser = new JSONParser();
//    
//    //testing from a real server:
//    private static final String LOGIN_URL = "http://farvlu.farmingdale.edu/~schwj13/androidvirtualid/webservice/jsonscript.php";
//    
//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_MESSAGE = "message";
//
//    private static final String TRANSACTION_NUMBER = "transactionnumber";
//    private static final String DATE = "date";
//    private static final String TIME = "time";
//    private static final String ESTABLISHMENT = "establishment";
//    private static final String AMOUNT = "amount";
//    
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	
        View rootView = inflater.inflate(R.layout.fragment_transactions, container, false);
        
        //setup input fields
        //transactions = (TextView) rootView.findViewById(R.id.textViewTranscations);
        
        //new AttemptLoadTransactions().execute();

        return rootView;

	}
//	
//	class AttemptLoadTransactions extends AsyncTask<String, String, String> {
//
//		 /**
//       * Before starting background thread Show Progress Dialog
//       * */
//		boolean failure = false;
//		
//      @Override
//      protected void onPreExecute() {
//          super.onPreExecute();
//          pDialog = new ProgressDialog(getActivity());
//          pDialog.setMessage("Loading Transactions...");
//          pDialog.setIndeterminate(false);
//          pDialog.setCancelable(true);
//          pDialog.show();
//      }
//		
//		@Override
//		protected String doInBackground(String... args) {
//			
//			// TODO Auto-generated method stub
//			// Check for success tag
//			int success;
//			
//			//String currentUserRamid = ramid.getText().toString().toLowerCase();
//			ramID = getActivity().getIntent().getStringExtra("userRamIDLoginActivity");
//			
//			transactions.setText(ramID);
//			
//
//			try {
//				// Building Parameters
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				params.add(new BasicNameValuePair("ramid", ramID));
//
//				Log.d("request!", "starting");
//				
//				// getting product details by making HTTP request
//				JSONObject json = jsonParser.makeHttpRequest(
//				LOGIN_URL, "POST", params);
//
//				// check your log for json response
//				Log.d("Load attempt", json.toString());
//              
//				// json success tag
//				success = json.getInt(TAG_SUCCESS);
//				if (success == 1) {
//
//					Log.d("Load Successful!", json.toString());
//              	
//					//assign value to userPinNumber and start activity
//					//Intent i = new Intent(getActivity(), MainActivity.class);
//           	
//					
//	              	
//	              	//i.putExtra("userTransactionNumberLoginActivity", userTransactionNumber);
//	              	//i.putExtra("userDateLoginActivity", userDate);
//	              	//i.putExtra("userTimeLoginActivity", userTime);
//	              	//i.putExtra("userEstablishmentLoginActivity", userEstablishment);
//	              	//i.putExtra("userAmountLoginActivity", userAmount);
// 
//	              	 // declare parameters that are passed to PHP script i.e. the name "ramid" and its value submitted by user   
//	                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//	              	
//	              	 // define the parameter
//	                postParameters.add(new BasicNameValuePair("ramid",ramID));
//	                String response = null;
//	              	
//	                try
//	                {
//	                	response = CustomHttpClient.executeHttpPost(LOGIN_URL,postParameters);
//	              	
//	                	// store the result returned by PHP script that runs MySQL query
//	                	String result = response.toString();  
//	                	
//		                //parse json data
//		                try{
//
//		                	returnString = "";
//		                	JSONArray jArray = new JSONArray(result);
//		                    for(int i1=0;i1<jArray.length();i1++)
//		                    {
//		                    	JSONObject json_data = jArray.getJSONObject(i1);
//		                    	
//		                    	userTransactionNumber = json_data.getString(TRANSACTION_NUMBER);
//		    	              	userDate = 				json_data.getString(DATE);
//		    	              	userTime = 				json_data.getString(TIME);
//		    	              	userEstablishment = 	json_data.getString(ESTABLISHMENT);
//		    	              	userAmount = 			json_data.getString(AMOUNT);
//		                    	
//		                                Log.i("log_tag",
//		                                	" Transaction Number: " 	+ userTransactionNumber +
//		                                    ", Date: " 					+ userDate +
//		                                    ", Time: " 					+ userTime +
//		                                    ", Establishment: "			+ userEstablishment +
//		                                    ", amount: " 				+ userAmount
//		                                );
//		                                
//		                                //Get an output to the screen
//		                                returnString =  
//		                                userTransactionNumber + " "
//		                                + userDate + " "
//		                                + userTime + " "
//		                                + userEstablishment + " "
//		                                + userAmount;
//		                                transactions.append(returnString);
//
//		                        }
//		                }
//		                catch(JSONException e){
//		                        Log.e("log_tag", "Error parsing data "+e.toString());
//		                }
//
//
//	                }
//	                catch (Exception e) {
//	                    Log.e("log_tag","Error in http connection!!" + e.toString());     
//	                   }
//	              	
//
//	              	
//	              	
//
//	              	//finish();
//	  				//startActivity(i);
//	  				
//	  				return json.getString(TAG_MESSAGE);
//	  				
//	  			}else{
//              	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
//              	return json.getString(TAG_MESSAGE);
//              	
//              }
//          }
//          catch (JSONException e) {
//              e.printStackTrace();
//          }
//          return null;	
//		}
//		
//
//		/**
//       * After completing background task Dismiss the progress dialog
//       * **/
//      protected void onPostExecute(String file_url) {
//          // dismiss the dialog once product deleted
//          pDialog.dismiss();
//          if (file_url != null){
//          	Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
//          }
//      }
//	}
//	

}
