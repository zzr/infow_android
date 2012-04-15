package com.work6.infow.model;

import android.os.Parcel;
import android.os.Parcelable;



public class Channel implements Parcelable{
	private String channelId;
	private String channelName;
	private String channelSearchStr;
	private String channelType;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelSearchStr() {
		return channelSearchStr;
	}
	public void setChannelSearchStr(String channelSearchStr) {
		this.channelSearchStr = channelSearchStr;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public Channel(){}
	public Channel(Parcel in) {
		channelId=in.readString();
		channelName=in.readString();
		channelSearchStr=in.readString();
		channelType=in.readString();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeString(channelId);
		arg0.writeString(channelName);
		arg0.writeString(channelSearchStr);
		arg0.writeString(channelType);
	}
	public static final Creator<Channel> CREATOR = new Creator<Channel>() {
		@Override
		public Channel createFromParcel(Parcel in) {
			return new Channel(in);
		}

		@Override
		public Channel[] newArray(int size) {
			return new Channel[size];
		}
	};

	public static Parcelable.Creator<Channel> getCreator() {
		return CREATOR;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
