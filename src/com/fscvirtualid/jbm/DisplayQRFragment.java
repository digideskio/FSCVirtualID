package com.fscvirtualid.jbm;

import java.io.InputStream;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class DisplayQRFragment extends Fragment {
	
	//https://www.the-qrcode-generator.com/mycodes/tvlMcXEIg
	//private static final String MY_URL_STRING = "http://uqr.to/fsc-app/qr/Matthew_Gelbman";

	public DisplayQRFragment(){}
	
	TextView displayRamID;
	AsyncTask<String, Void, Bitmap> qr;
	
	String ramID;
	String firstName;
	String lastName;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_displayqr, container, false);

        displayRamID = (TextView) rootView.findViewById(R.id.textViewRamID);

        //qr = new DownloadImageTask((ImageView) rootView.findViewById(R.id.imageViewQR)).execute(MY_URL_STRING);     
        //qr = new GenerateQRTask((ImageView) rootView.findViewById(R.id.imageViewQR)).execute(MY_URL_STRING);
        
        ramID = getActivity().getIntent().getStringExtra("userRamIDLoginActivity");
        displayRamID.setText(ramID);
        
        //firstName = getActivity().getIntent().getStringExtra("userFirstNameLoginActivity");
        //lastName = getActivity().getIntent().getStringExtra("userLastNameLoginActivity");
        
        //displayFirstName.setText(firstName);
        
        
    	// ImageView to display the QR code in.  This should be defined in 
    	// your Activity's XML layout file
    	ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewQR);

    	
    	String qrData = ramID;
    	int qrCodeDimention = 500;

    	QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null,
    	        Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

    	try {
    	    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
    	    imageView.setImageBitmap(bitmap);
    	} catch (WriterException e) {
    	    e.printStackTrace();
    	}
        
        
        return rootView;
    }
    
	private class GenerateQRTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public GenerateQRTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}
    
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    	  ImageView bmImage;

    	  public DownloadImageTask(ImageView bmImage) {
    	      this.bmImage = bmImage;
    	  }

    	  protected Bitmap doInBackground(String... urls) {
    	      String urldisplay = urls[0];
    	      Bitmap mIcon11 = null;
    	      try {
    	        InputStream in = new java.net.URL(urldisplay).openStream();
    	        mIcon11 = BitmapFactory.decodeStream(in);
    	      } catch (Exception e) {
    	          Log.e("Error", e.getMessage());
    	          e.printStackTrace();
    	      }
    	      return mIcon11;
    	  }

    	  protected void onPostExecute(Bitmap result) {
    	      bmImage.setImageBitmap(result);
    	  }
    	}

}


