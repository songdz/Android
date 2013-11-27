package com.songdongze.mycontacts_test;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity 
//				implements LoaderManager.LoaderCallbacks<Cursor> 
	{
	
//	private ArrayList<String> item = getArrayList();
	private static final int ADDCONTACT = Menu.FIRST;
	private static final int EXIT = Menu.FIRST + 1;
	private static final int DELETECONTACT = Menu.FIRST + 2;
	private SimpleCursorAdapter scAdapter;
	
/*	private ArrayList<String> getArrayList() {
		ArrayList<String> item = new ArrayList<String>();
		item.add("aaa");
		item.add("bbb");
		item.add("ccc");
		return item;
	}
	*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getListView().setEmptyView(findViewById(R.id.empty_contacts));
		this.registerForContextMenu(getListView());
		setAdapter();
		getListView().setTextFilterEnabled(true);
	}
	
	@SuppressWarnings("deprecation")
	private void setAdapter() {
		Cursor cursor = managedQuery(ContactsProvider.CONTENT_URI, ContactColumn.PROJECTION, null, null, null);
		scAdapter = new SimpleCursorAdapter(this, 
					android.R.layout.simple_list_item_2, 
					cursor, 
					new String[] {ContactColumn.NAME, ContactColumn.PRIVATEPHONE}, 
					new int[] {android.R.id.text1, android.R.id.text2 }
					);

		setListAdapter(scAdapter);
		
/*		scAdapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_2, 
				null, 
				new String[] {ContactColumn.NAME, ContactColumn.PRIVATEPHONE}, 
				new int[] {android.R.id.text1, android.R.id.text2 }
				//,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER}
				);
				getLoaderManager().initLoader(0, null, this);*/
		
		//setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item));
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
				intent.setAction(Intent.ACTION_INSERT);
				intent.setData(ContactsProvider.CONTENT_URI);
				startActivity(intent);
				return true;
			case EXIT:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Toast.makeText(getApplicationContext(), "position :" + position + "id :" + id, Toast.LENGTH_SHORT).show();
		Uri uri = ContentUris.withAppendedId(ContactsProvider.CONTENT_URI, id);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(uri);
		startActivity(intent);
		/*Log.d("startActivity", "Activity MainActivity");
		try {
			Thread.sleep(5000);
			Log.d("startActivity", "Activity MainActivity in Sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)menuInfo;
//		String name = (String)getListView().getAdapter().getItem(info.position);
		Cursor cursor = (Cursor) getListAdapter().getItem(info.position);
		if (cursor == null)
		{
			return;
		}
		String name = cursor.getString(ContactColumn.NAME_COLUMN);
		menu.setHeaderTitle(R.string.deletecontact);
		menu.add(0, DELETECONTACT, 0, " " + name + " ?");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch(item.getItemId()) {
			case DELETECONTACT:
				deleteRow(((AdapterContextMenuInfo)item.getMenuInfo()).id);
				return true;
		}
		return false;
	}

	private void deleteRow(long id) {
/*		item.remove(id);
		((BaseAdapter)getListAdapter()).notifyDataSetChanged();
		getListView().invalidate();*/
		Uri deleteIdUri = ContentUris.withAppendedId(ContactsProvider.CONTENT_URI, id);
		getContentResolver().delete(deleteIdUri, null, null);
	}


/*	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		 return new CursorLoader(this, ContactsProvider.CONTENT_URI,
                ContactColumn.PROJECTION, null, null, null);
	}


	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		scAdapter.swapCursor(data);
	}


	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		scAdapter.swapCursor(null);
	}*/
	
/*	@Override
	protected void onPause() {
		super.onPause();
		try {
			Log.d("startActivity", "onPause");
			Thread.sleep(5000);
			Log.d("startActivity", "Activity MainActivity in onPause");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		try {
			Log.d("startActivity", "onStop");
			Thread.sleep(5000);
			Log.d("startActivity", "Activity MainActivity in onStop");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

}
