package com.songdongze.mycontacts_test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactView extends Activity {
	private ImageButton imageButton_EditContact;
	private ImageButton imageButton_DeleteContact;
	private TextView textView_Name;
	private TextView textView_Company;
	private TextView textView_PrivatePhone2;
	private TextView textView_CompanyPhone2;
	private TextView textView_Email2;
	private TextView textView_QQ2;
	
	private Cursor cursor;
	private Uri mUri;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUri = getIntent().getData();
		setContentView(R.layout.viewuser);
//Log.d("onCreate", "Activity ContactView");
		findViews();
		setOnClickMethods();
		cursor = managedQuery(mUri, ContactColumn.PROJECTION, null, null, null);
        cursor.moveToFirst();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//Log.d("onResume", "Activity ContactView");
		if (cursor != null)	{
			// 读取并显示联系人信息
			cursor.moveToFirst();
			
			textView_Name.setText(cursor.getString(ContactColumn.NAME_COLUMN));
			textView_Company.setText(cursor.getString(ContactColumn.COMPANY_COLUMN));
			textView_PrivatePhone2.setText(cursor.getString(ContactColumn.PRIVATEPHONE_COLUMN));
			textView_CompanyPhone2.setText(cursor.getString(ContactColumn.COMPANYPHONE_COLUMN));
			textView_Email2.setText(cursor.getString(ContactColumn.EMAIL_COLUMN));
			textView_QQ2.setText(cursor.getString(ContactColumn.QQ_COLUMN));
		}
		else {
			setTitle("错误信息");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	private void findViews() {
		imageButton_EditContact = (ImageButton)findViewById(R.id.imageButton_EditContact);
		imageButton_DeleteContact = (ImageButton)findViewById(R.id.imageButton_DeleteContact);
		textView_Name = (TextView)findViewById(R.id.textView_Name);
		textView_Company = (TextView)findViewById(R.id.textView_Company);
		textView_PrivatePhone2 = (TextView)findViewById(R.id.textView_PrivatePhone2);
		textView_CompanyPhone2 = (TextView)findViewById(R.id.textView_CompanyPhone2);
		textView_Email2 = (TextView)findViewById(R.id.textView_Email2);
		textView_QQ2 = (TextView)findViewById(R.id.textView_QQ2);

	}
	private void setOnClickMethods() {
		imageButton_EditContact.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(ContactView.this, "Edit Contact", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_EDIT);
				intent.setData(getIntent().getData());
				startActivity(intent);
			}
		});
		imageButton_DeleteContact.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(ContactView.this, "Delete Contact", Toast.LENGTH_SHORT).show();
				new AlertDialog.Builder(ContactView.this)
							   	.setTitle(R.string.deletecontact)
							   	.setMessage(getText(R.string.deletecontact) + cursor.getString(ContactColumn.NAME_COLUMN) + " ?")
							   	.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
								   public void onClick(DialogInterface dialog,
										   int which) {
									   getContentResolver().delete(mUri, null, null);
									   finish();
								}})
								.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {					
									public void onClick(DialogInterface dialog, int which) {
										
									}
								})
								.show();
			}
		});
	}
}
