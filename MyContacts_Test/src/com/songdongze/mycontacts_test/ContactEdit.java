package com.songdongze.mycontacts_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ContactEdit extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editorcontacts);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
}
