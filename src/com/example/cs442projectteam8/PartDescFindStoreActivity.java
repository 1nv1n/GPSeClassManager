package com.example.cs442projectteam8;

import database.PartsDatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PartDescFindStoreActivity extends Activity {
	
	String pname, puid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_part_desc_find_store);
		
		Intent intent = getIntent();
		if(intent.getExtras() == null){
			// TODO: Exception Handling
		}
		pname = intent.getStringExtra("PartName");
		puid = intent.getStringExtra("PartUID");
		
		Context context = getApplicationContext();
		PartsDatabaseHandler pdb = PartsDatabaseHandler.getInstance(context);
		
		TextView p_name = (TextView)findViewById(R.id.part_name);
		TextView p_desc = (TextView)findViewById(R.id.part_desc);
		
		p_name.setText(pname);
		p_desc.setText("("+puid+") "+pdb.getPartNameDesc(pname));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.part_desc_find_store, menu);
		return true;
	}

	public void onFindStoreClick(View v) {		
		Intent intent = new Intent(getApplicationContext(), StoreListActivity.class);
		intent.putExtra("PartUID", puid);
		startActivity(intent);
	}	
}
