package com.songdongze.mycontacts_test;

import android.app.Activity;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewuser);
		findViews();
		setOnClickMethods();
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
