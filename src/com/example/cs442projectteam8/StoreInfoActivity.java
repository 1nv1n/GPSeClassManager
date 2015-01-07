package com.example.cs442projectteam8;

import java.util.ArrayList;
import java.util.List;

import dbtest.Part;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StoreInfoActivity extends ListActivity {
	
	String sname;
	
	class MySimpleArrayAdapter extends ArrayAdapter<Part> {
		  private final Context context;
		  private final List<Part> partList;

		  public MySimpleArrayAdapter(Context context, List<Part> partList) {
		    super(context, R.layout.product_row, partList);
		    this.context = context;
		    this.partList = partList;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
			  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      convertView = inflater.inflate(R.layout.product_row, parent, false);
			}
			
		    TextView textView = (TextView) convertView.findViewById(R.id.brand);
		    Part part = partList.get(position);
		    textView.setText(part.getName());
		    //textView.setOnClickListener(OnMenuClickListener);
		    
		    textView = (TextView) convertView.findViewById(R.id.price);
		    textView.setText(Double.toString((part.getPrice())));
		    //textView.setOnClickListener(OnDownloadListener);
		    return convertView;
		  }
		}; 
	
	private OnClickListener OnMenuClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			//ListView lv = (ListView) findViewById(R.id.StoreListView);			
			int pos = getListView().getPositionForView(v);
			if (pos != ListView.INVALID_POSITION) {
				//TODO some processing
				//Intent intent = new Intent(getApplicationContext(), PartOrderActivity.class);
				//startActivity(intent);
			}
		}
	};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<Part> partList = new ArrayList<Part>();		
		
		MySimpleArrayAdapter adapter;
		adapter = new MySimpleArrayAdapter(getApplicationContext(), partList);
		
		getListView().setAdapter(adapter);
		
		Intent intent = getIntent();
		if(intent.getExtras() == null){
			// TODO: Exception Handling
		}
		sname = intent.getStringExtra("StoreName");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_info, menu);
		return true;
	}

}
