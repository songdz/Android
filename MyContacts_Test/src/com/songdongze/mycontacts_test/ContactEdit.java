package com.songdongze.mycontacts_test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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
					getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
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
