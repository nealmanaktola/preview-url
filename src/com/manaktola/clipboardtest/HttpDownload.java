package com.manaktola.clipboardtest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

interface AsyncTaskCompleteListener<T> {
	   public void onTaskComplete(T result);
	}

class HttpDownload extends AsyncTask<String, Void, String> {
    private AsyncTaskCompleteListener<String> callback;
    
    public HttpDownload(AsyncTaskCompleteListener<String> cb) {
        this.callback = cb;
    }

    protected void onPostExecute(String result) {;
       Log.d("HttpDownload", "onPostExecute");
       callback.onTaskComplete(result);
   }

	@Override
	protected String doInBackground(String... params) {
		
		String myUri = params[0];
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet();
		
		try {
			get.setURI(new URI(myUri));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			return null;
		}
				
		HttpResponse response;
		String bodyHtml;
		try {
			response = httpClient.execute(get);
			bodyHtml = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			bodyHtml = null;
			e.printStackTrace();
		}

		// Build up result		
		return bodyHtml;
	}  
}