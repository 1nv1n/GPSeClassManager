package com.example.cs442projectteam8;

import java.util.List;

import wrappers.StoresDB;

import database.StoresDatabaseHandler;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class StoreListFragment extends ListFragment {
	
	String sname, s_puid;
	Context context;
	StoresDatabaseHandler sdb = StoresDatabaseHandler.getInstance(context);
	
	public StoreListFragment(){
		// Empty Constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		s_puid = getArguments().getString("PartUID");
		
		String[] query = new String[3];
		new QueryDbTask().execute(query);
	}	
	
	private class QueryDbTask extends AsyncTask<String, Void, List<StoresDB>> {

		@Override
		protected List<StoresDB> doInBackground(String... stores) {
			//return db.getStores(null);
			return sdb.getStores(s_puid);
		}
		
		protected void onPostExecute(List<StoresDB> storeList) {
			MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getActivity(), storeList);
			setListAdapter(adapter);		
			getListView().setOnItemClickListener(OnStoreClickListener);			
		}
	};
	
	class MySimpleArrayAdapter extends ArrayAdapter<StoresDB> {
		  private final Context context;
		  private final List<StoresDB> storeList;

		  public MySimpleArrayAdapter(Context context, List<StoresDB> storeList) {
		    super(context, R.layout.store_row, storeList);
		    this.context = context;
		    this.storeList = storeList;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
			  
			if (convertView == null) {
			  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      convertView = inflater.inflate(R.layout.store_row, parent, false);
			}
			
		    TextView textView = (TextView) convertView.findViewById(R.id.StoreName);
		    StoresDB store = storeList.get(position);
		    textView.setText(store.get_storeName());
		    
		    return convertView;
		  }
		}; 
	
		private OnItemClickListener OnStoreClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int id, long pos) {
				LinearLayout ll = (LinearLayout)view;
				TextView tv = (TextView)ll.findViewById(R.id.StoreName);	
				sname = tv.getText().toString(); 
				Intent intent = new Intent(getActivity(),StoreInfoActivity.class);
				intent.putExtra("StoreName", sname);
				startActivity(intent);
			}			
		};
}