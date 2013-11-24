package com.songdongze.mycontacts_test;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class ContactsProvider extends ContentProvider {
	
	public static final String TAG = "ContactsProvider";
	
	private DBHelper dbHelper;
	private SQLiteDatabase contactsDB;
	
	public static final String AUTHORITY = "com.songdongze.mycontacts_test.provider.ContactsProvider";
	public static final String CONTACTS_TABLE = DBHelper.CONTACTS_TABLE;
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTACTS_TABLE);
	
	public static final int CONTACTS = 1;
	public static final int CONTACT_ID = 2;
	private static final UriMatcher uriMatcher;
	static 
	{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, CONTACTS_TABLE, CONTACTS);
		uriMatcher.addURI(AUTHORITY, CONTACTS_TABLE + "/#", CONTACT_ID);
	}
	
	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		contactsDB = dbHelper.getWritableDatabase();
		return (contactsDB == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(CONTACTS_TABLE);
		
		switch(uriMatcher.match(uri)) {
			case CONTACT_ID:
				qb.appendWhere(ContactColumn._ID + "=" + uri.getPathSegments().get(1));
				break;
			default:
				break;
		}
		String orderBy;
		if (TextUtils.isEmpty(sortOrder))
		{
			orderBy = ContactColumn._ID;
		}
		else
		{
			orderBy = sortOrder;
		}
		Cursor c = qb.query(contactsDB, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
		
	}

	@Override
	public String getType(Uri uri) {
		switch(uriMatcher.match(uri)){
			case CONTACTS: 
				return "vnd.android.cursor.dir/vnd.songdongze.mycontacts_test";
			case CONTACT_ID:
				return "vnd.android.cursor.item/vnd.songdongze.mycontacts_test";
			default:
				throw new IllegalArgumentException("Unsupported URI" + uri);
				
		}
		
	
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(uriMatcher.match(uri) != CONTACTS) {
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
		ContentValues valuesCopy;
		if(values != null) {
			valuesCopy = new ContentValues(values);
			Log.e(TAG + " insert", "initialVaules is not null");
		}
		else {
			valuesCopy = new ContentValues();
		}
		
		if (values.containsKey(ContactColumn.NAME) == false)
		{
			values.put(ContactColumn.NAME, "");
		}
		if (values.containsKey(ContactColumn.COMPANY) == false)
		{
			values.put(ContactColumn.COMPANY, "");
		}
		if (values.containsKey(ContactColumn.COMPANYPHONE) == false)
		{
			values.put(ContactColumn.COMPANYPHONE, "");
		}
		if (values.containsKey(ContactColumn.EMAIL) == false)
		{
			values.put(ContactColumn.EMAIL, "");
		}
		if (values.containsKey(ContactColumn.PRIVATEPHONE) == false)
		{
			values.put(ContactColumn.PRIVATEPHONE, "");
		}
		if (values.containsKey(ContactColumn.QQ) == false)
		{
			values.put(ContactColumn.QQ, "");
		}
		Log.e(TAG + " insert", valuesCopy.toString());
		long rowId = contactsDB.insert(CONTACTS_TABLE, null, valuesCopy);
		if(rowId > 0) {
			Uri noteUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(noteUri, null);
			Log.e(TAG + " insert", noteUri.toString());
			return noteUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count;
		switch(uriMatcher.match(uri)) {
			case CONTACTS:
				count = contactsDB.delete(CONTACTS_TABLE, selection, selectionArgs);
				break;
			case CONTACT_ID:
				String contactID = uri.getPathSegments().get(1);
				count = contactsDB.delete(CONTACTS_TABLE,
										  ContactColumn._ID 
										  + "=" + contactID
										  + (!TextUtils.isEmpty(selection)? " AND (" + selection + ")" : ""), 
										  selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count;
		switch(uriMatcher.match(uri)) {
			case CONTACTS:
				count = contactsDB.update(CONTACTS_TABLE, values, selection, selectionArgs);
				break;
			case CONTACT_ID:
				String contactID = uri.getPathSegments().get(1);
				count = contactsDB.update(CONTACTS_TABLE, values, ContactColumn._ID + "=" + contactID
						+ (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""), selectionArgs);
				break; 
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);	
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
