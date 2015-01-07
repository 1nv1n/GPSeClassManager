package com.example.cs442projectteam8;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;


public class StoreMapActivity extends FragmentActivity {
	
	private GoogleMap mMap;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_store_map);
		setUpMapIfNeeded();
		
		

		
		
		
	}
	
	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.
	    		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    		mMap.getUiSettings();
	    		mMap.setMyLocationEnabled(true);
	    		
	    		LocationManager locationManager;
	    	    String svcName = Context.LOCATION_SERVICE;
	    	    locationManager = (LocationManager)getSystemService(svcName);

	    	    String provider = LocationManager.GPS_PROVIDER;
	    	    Location l = locationManager.getLastKnownLocation(provider);
	    		
	    		LatLng latlng = new LatLng(l.getLatitude(), l.getLongitude());
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10));

	        }
	    }
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.store_list, menu);
		return true;
	}
	


}
