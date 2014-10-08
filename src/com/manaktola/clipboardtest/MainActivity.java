package com.manaktola.clipboardtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;



public class MainActivity extends Activity {

	private EditText etCompose;
	private Context mContext;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		setupViews();
		
		//Checking for URL
		etCompose.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				//Generate a PreviewLink
				PreviewUrl previewLink; 
				if (isValidUrl(s.toString()))
					previewLink = new PreviewUrl(s.toString(),mContext);
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	protected boolean isValidUrl(String url) {
		// TODO Auto-generated method stub
		 

		return Patterns.WEB_URL.matcher(url).matches();
	}


	private void setupViews() {
		etCompose = (EditText) findViewById(R.id.etCompose);
	}
}
