package com.example.cs442projectteam8;

import database.PartsDatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PartDescOrderActivity extends Activity {
	String pname, puid, pprice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_part_desc_order);
		
		Intent intent = getIntent();
		if(intent.getExtras() == null){
			// TODO: Exception Handling
		}
		pname = intent.getStringExtra("PartName");
		puid = intent.getStringExtra("PartUID");
		pprice = intent.getStringExtra("PartPrice");
		Context context = getApplicationContext();
		PartsDatabaseHandler pdb = PartsDatabaseHandler.getInstance(context);
		
		TextView p_name = (TextView)findViewById(R.id.part_name);
		TextView p_desc = (TextView)findViewById(R.id.part_desc);
		TextView p_price = (TextView)findViewById(R.id.part_price);
		
		
		
		p_name.setText(pname);
		p_desc.setText("("+puid+") "+pdb.getPart(puid).get_productDescription());
		p_price.setText("Price : $" + pdb.getPart(puid).get_productPrice());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.part_desc_order, menu);
		return true;
	}

	
	public void onOrderClick(View v) {		
		Intent intent = new Intent(getApplicationContext(), StoreListActivity.class);
		intent.putExtra("PartUID", puid);
		startActivity(intent);
	}
}
