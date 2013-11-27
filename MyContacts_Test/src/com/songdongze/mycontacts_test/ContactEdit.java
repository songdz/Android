package com.songdongze.mycontacts_test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactEdit extends Activity {
	
	private EditText editText_Name;
	private EditText editText_Company;
	private EditText editText_PrivatePhone;
	private EditText editText_CompanyPhone;
	private EditText editText_Email;
	private EditText editText_QQ;
	private Button button_Cancel;
	private Button button_OK;
	
	private void findViews() {
		editText_Name = (EditText)findViewById(R.id.editText_Name);
		editText_Company = (EditText)findViewById(R.id.editText_Company);
		editText_PrivatePhone = (EditText)findViewById(R.id.editText_PrivatePhone);
		editText_CompanyPhone = (EditText)findViewById(R.id.editText_CompanyPhone);
		editText_Email = (EditText)findViewById(R.id.editText_Email);
		editText_QQ = (EditText)findViewById(R.id.editText_QQ);
		button_Cancel = (Button)findViewById(R.id.button_Cancel);
		button_OK = (Button)findViewById(R.id.button_OK);
	}
	
	private void setOnClickedMethods() {
		button_OK.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name = editText_Name.getText().toString().trim();
				String company = editText_Company.getText().toString().trim();
				String privatePhone = editText_PrivatePhone.getText().toString().trim();
				String companyPhone = editText_CompanyPhone.getText().toString().trim();
				String email = editText_Email.getText().toString().trim();
				String qq = editText_QQ.getText().toString().trim();
				if(TextUtils.isEmpty(name)) {
					Toast.makeText(ContactEdit.this, R.string.nameNotEmpty, Toast.LENGTH_SHORT).show();
				} 
				else {
					ContentValues values = new ContentValues();
					values.put(ContactColumn.NAME, name);
					values.put(ContactColumn.COMPANY, company);
					values.put(ContactColumn.PRIVATEPHONE, privatePhone);
					values.put(ContactColumn.COMPANYPHONE, companyPhone);
					values.put(ContactColumn.EMAIL, email);
					values.put(ContactColumn.QQ, qq);
					String action = getIntent().getAction();
					if(Intent.ACTION_INSERT.equals(action)) {
						getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
					} else if(Intent.ACTION_EDIT.equals(action)) {
						getContentResolver().update(getIntent().getData(), values, null, null);
					}
					finish();
				}				
			}
		});
		button_Cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editorcontacts);
		findViews();
		setOnClickedMethods();
		if(Intent.ACTION_EDIT.equals(getIntent().getAction())) {
			displayContact(getIntent().getData());
		}
		
	}

	
	@SuppressWarnings("deprecation")
	private void displayContact(Uri uri) {
		Cursor cursor = managedQuery(uri, ContactColumn.PROJECTION, null, null, null);
        if (cursor != null) {
			// 读取并显示联系人信息
			cursor.moveToFirst();			
			editText_Name.setText(cursor.getString(ContactColumn.NAME_COLUMN));
			editText_Company.setText(cursor.getString(ContactColumn.COMPANY_COLUMN));
			editText_PrivatePhone.setText(cursor.getString(ContactColumn.PRIVATEPHONE_COLUMN));
			editText_CompanyPhone.setText(cursor.getString(ContactColumn.COMPANYPHONE_COLUMN));
			editText_Email.setText(cursor.getString(ContactColumn.EMAIL_COLUMN));
			editText_QQ.setText(cursor.getString(ContactColumn.QQ_COLUMN));
		}		
        else {
			setTitle("错误信息");
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	
	
}
