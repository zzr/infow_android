package com.work6.infow.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
	String newsContent;
	String newsId;
	String newsTime;
	String newsTitle;
	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public News(){}
	public News(Parcel in) {
		newsContent=in.readString();
		newsId=in.readString();
		newsTime=in.readString();
		newsTitle=in.readString();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeString(newsContent);
		arg0.writeString(newsId);
		arg0.writeString(newsTime);
		arg0.writeString(newsTitle);
	}
	public static final Creator<News> CREATOR = new Creator<News>() {
		@Override
		public News createFromParcel(Parcel in) {
			return new News(in);
		}

		@Override
		public News[] newArray(int size) {
			return new News[size];
		}
	};

	public static Parcelable.Creator<News> getCreator() {
		return CREATOR;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

}
