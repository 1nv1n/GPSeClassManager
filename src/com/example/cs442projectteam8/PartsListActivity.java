package com.example.cs442projectteam8;

import java.util.List;

import database.PartsDatabaseHandler;

import wrappers.PartsDB;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class PartsListActivity extends ListActivity {

	String pname, puid;
	private Context con;
	PartsDatabaseHandler pdb = PartsDatabaseHandler.getInstance(con);
	
	class MySimpleArrayAdapter extends ArrayAdapter<PartsDB> {
		  private final Context context;
		  private final List<PartsDB> partList;

		  public MySimpleArrayAdapter(Context context, List<PartsDB> partList) {
		    super(context, R.layout.part_row, partList);
		    this.context = context;
		    this.partList = partList;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
			  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      convertView = inflater.inflate(R.layout.part_row, parent, false);
			}			
			
		    TextView textView = (TextView) convertView.findViewById(R.id.Category);
		    PartsDB part = partList.get(position);
		    textView.setText(part.get_productCategory());
		    //textView.setOnClickListener(OnMenuClickListener);
		    
		    textView = (TextView) convertView.findViewById(R.id.PartName);
		    textView.setText(part.get_productName());
		    //textView.setOnClickListener(OnDownloadListener);
		    return convertView;
		  }
		}; 	
	
	private OnItemClickListener OnPartClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int id, long pos) {
			LinearLayout ll = (LinearLayout)view;
			TextView tv = (TextView)ll.findViewById(R.id.PartName);	
			pname = tv.getText().toString();            
			//Toast.makeText(getBaseContext(), tv.getText().toString(), Toast.LENGTH_SHORT).show();
            
			Intent intent = new Intent(getApplicationContext(),PartDescFindStoreActivity.class);
			puid=Integer.toString(pdb.getPartUID(pname));
			intent.putExtra("PartName", pname);
			intent.putExtra("PartUID",puid);
			startActivity(intent);
		}
	};
	
	private class QueryDbTask extends AsyncTask<String, Void, List<PartsDB>> {

		@Override
		protected List<PartsDB> doInBackground(String... params) {
			
			// return db.getCarParts(params[0],params[1],params[2]);
	        // return pdb.getCarParts("Honda", "CRV", "2000");
			return pdb.getCarParts(params[0],params[1],params[2]);
		}
		
		protected void onPostExecute(List<PartsDB> partList) {
			if (partList.isEmpty()) {
				Toast.makeText(getApplicationContext(),"Could Not Find Parts For Selection", Toast.LENGTH_LONG).show();
				finish();
			}
			MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getApplicationContext(), partList);
			getListView().setAdapter(adapter);
			getListView().setOnItemClickListener(OnPartClickListener);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Create a progress bar to display while the list loads        
		ProgressBar progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);
        
        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

		Intent intent = getIntent();
		if(intent.getExtras() == null){
			// TODO: Exception Handling
		}
		String[] query = intent.getStringArrayExtra("MakeModelYear");
				
		new QueryDbTask().execute(query[0],query[1],query[2]);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.parts_list, menu);
		return true;
	}
}
