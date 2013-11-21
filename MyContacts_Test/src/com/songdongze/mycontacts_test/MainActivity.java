package com.songdongze.mycontacts_test;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	private ArrayList<String> item = getArrayList();
	private static final int ADDCONTACT = Menu.FIRST;
	private static final int EXIT = Menu.FIRST + 1;
	private static final int DELETECONTACT = Menu.FIRST + 2;
	
	private ArrayList<String> getArrayList() {
		ArrayList<String> item = new ArrayList<String>();
		item.add("aaa");
		item.add("bbb");
		item.add("ccc");
		return item;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getListView().setEmptyView(findViewById(R.id.empty_contacts));
		this.registerForContextMenu(getListView());
		setAdapter();
		getListView().setTextFilterEnabled(true);
	}
	
	private void setAdapter() {
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ADDCONTACT, 0, R.string.add_contact);
		menu.add(1, EXIT, 0, R.string.exit);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
			case ADDCONTACT: Intent intent = new Intent();
				intent.setAction(Intent.ACTION_EDIT);
				intent.addCategory("com.songdongze.mycontacts_test.category.ContactEdit");
				startActivity(intent);
				return true;
			case EXIT:
				this.finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Toast.makeText(getApplicationContext(), "position :" + position + "id :" + id, Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addCategory("com.songdongze.mycontacts_test.category.ContactView");
		startActivity(intent);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)menuInfo;
		String name = (String)getListView().getAdapter().getItem(info.position);
		menu.setHeaderTitle(R.string.deletecontact);
		menu.add(0, DELETECONTACT, 0, name + "?");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch(item.getItemId()) {
			case DELETECONTACT:
				deleteRow(((AdapterContextMenuInfo)item.getMenuInfo()).position);
				return true;
		}
		return false;
	}

	private void deleteRow(int position) {
		item.remove(position);
		((BaseAdapter)getListAdapter()).notifyDataSetChanged();
		getListView().invalidate();
	}
	
	

}
