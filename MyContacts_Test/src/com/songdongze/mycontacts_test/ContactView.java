package com.songdongze.mycontacts_test;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
	
	private Cursor mCursor;
	private Uri mUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUri = getIntent().getData();
		setContentView(R.layout.viewuser);
//Log.d("onCreate", "Activity ContactView");
		findViews();
		setOnClickMethods();
		mCursor = managedQuery(mUri, ContactColumn.PROJECTION, null, null, null);
        mCursor.moveToFirst();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//Log.d("onResume", "Activity ContactView");
		if (mCursor != null)
		{
			// 读取并显示联系人信息
			mCursor.moveToFirst();
			
			textView_Name.setText(mCursor.getString(ContactColumn.NAME_COLUMN));
			textView_Company.setText(mCursor.getString(ContactColumn.COMPANY_COLUMN));
			textView_PrivatePhone2.setText(mCursor.getString(ContactColumn.PRIVATEPHONE_COLUMN));
			textView_CompanyPhone2.setText(mCursor.getString(ContactColumn.COMPANYPHONE_COLUMN));
			textView_Email2.setText(mCursor.getString(ContactColumn.EMAIL_COLUMN));
			textView_QQ2.setText(mCursor.getString(ContactColumn.QQ_COLUMN));
		}
		else
		{
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
			}
		});
		imageButton_DeleteContact.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(ContactView.this, "Delete Contact", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
