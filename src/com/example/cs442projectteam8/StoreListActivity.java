package com.example.cs442projectteam8;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

public class StoreListActivity extends Activity {
	
	String pname, puid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_store_list);

		Intent intent = getIntent();
		if(intent.getExtras() == null){
			// TODO: Exception Handling
		}
		puid = intent.getStringExtra("PartUID");

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();	
		StoreListFragment fragment = new StoreListFragment();
		
		Bundle args = new Bundle();
		args.putString("PartUID", puid);
		fragment.setArguments(args);
		
		fragmentTransaction.add(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_list, menu);
		return true;
	}
	
	public void onMapClick(View view){
		
		Intent intent = new Intent(getApplicationContext(), StoreMapActivity.class);
		startActivity(intent);
		
	}
}
