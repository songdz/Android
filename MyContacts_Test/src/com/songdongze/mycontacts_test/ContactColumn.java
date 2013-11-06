package com.songdongze.mycontacts_test;

import android.provider.BaseColumns;

public class ContactColumn implements BaseColumns {

	public static final String NAME = "name";
	
	public static final String COMPANY = "company";
	
	public static final String PRIVATEPHONE = "privatePhone";
	
	public static final String COMPANYPHONE = "companyPhone";
	
	public static final String EMAIL = "email";
	
	public static final String QQ = "qq";
	
	public static final int _ID_COLUMN = 0;
	
	public static final int NAME_COLUMN = 1;
	
	public static final int COMPANY_COLUMN = 2;
	
	public static final int PRIVATEPHONE_COLUMN = 3;
	
	public static final int COMPANYPHONE_COLUMN = 4;
	
	public static final int EMAIL_COLUMN = 5;
	
	public static final int QQ_COLUMN = 6;
	
	public static final String[] PROJECTION = {_ID, NAME, COMPANY, PRIVATEPHONE, COMPANYPHONE, EMAIL, QQ};

	public ContactColumn() {
		
	}
	
	
	
	
	
	
	
	
	
}
