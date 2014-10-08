package com.manaktola.clipboardtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class PreviewUrl implements AsyncTaskCompleteListener<String>  {
	
	private String fullUrl;
	
	private String title;
	private String description;
	private String imageUrl;
	
	private TextView tvTitle;
	private TextView tvDescription;
	private SmartImageView ivImage;

	private Context mContext;
	
	
	public PreviewUrl(String fullUrl, Context context) {
		this.mContext = context;
		setupViews();
		
		//Download HTML
		HttpDownload downloadUrl = new HttpDownload(this);
		downloadUrl.execute(fullUrl);
	}
	
	private void setupViews() {
		Activity mainActivity = (MainActivity) mContext;
		//Using views from MainActivity for Testing
		tvTitle = (TextView) mainActivity.findViewById(R.id.tvTitle);
		tvDescription = (TextView) mainActivity.findViewById(R.id.tvDescription);	
		ivImage = (SmartImageView) mainActivity.findViewById(R.id.ivImage);
				
	}
	
	//Callback Function when HTML Download is complete. 
	@Override
	public void onTaskComplete(String html) {
		if (html != null)
		{
			Log.d("PreviewUrl", html);
			Document doc = Jsoup.parse(html);
			
			//Find all metaTags
			Elements metaTags = doc.getElementsByTag("meta");
			
			//Look for title/description/image
			for (Element meta : metaTags) {
				if (meta.attr("property").equals("og:title"))
				{
					title = meta.attr("content");
					tvTitle.setText(title);
				}
				if (meta.attr("property").equals("og:description"))
				{
					description = meta.attr("content");
					tvDescription.setText(description);
				}
				if (meta.attr("property").equals("og:image"))
				{
					imageUrl = meta.attr("content");
					ivImage.setImageUrl(imageUrl);
				}
				
			}			
		}
		
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	

}
