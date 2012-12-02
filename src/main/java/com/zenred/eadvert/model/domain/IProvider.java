package com.zenred.eadvert.model.domain;

public interface IProvider extends IUser{

	public abstract String getProvider();

	public abstract void setProvider(String provider);

	public abstract String toString();

}