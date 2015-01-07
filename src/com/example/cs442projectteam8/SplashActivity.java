package com.example.cs442projectteam8;

import database.PartsDatabaseHandler;
import database.StoresDatabaseHandler;

import wrappers.PartsDB;
import wrappers.StoresDB;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class SplashActivity extends Activity {
	
	boolean hasInternet, hasDatabase, hasGPS, appValidated;
	Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Context context = getApplicationContext();
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// Checking for Database Availability
		hasDatabase = dbCheck();		
		if(hasDatabase){
			toast = Toast.makeText(context, "Database Connected", Toast.LENGTH_SHORT);
			toast.show();
			appValidated=true;
		}
		else{
			toast = Toast.makeText(context, "Error: Database Unavailable", Toast.LENGTH_SHORT);
			toast.show();
			appValidated=false;
		}
				
		// Checking for Network Availability
		hasInternet = isNetworkAvailable();		
		if (hasInternet) {			
			toast = Toast.makeText(context, "Internet Active", Toast.LENGTH_SHORT);
			toast.show();
			appValidated=true;
		}
		else {
			toast = Toast.makeText(context, "Error: Network Connection Unavailable", Toast.LENGTH_SHORT);
			toast.show();			
			appValidated=false;
		}
		
		// Checking for GPS Availability
		hasGPS = gpsCheck();		
		if(hasGPS){
			toast = Toast.makeText(context, "GPS Found", Toast.LENGTH_SHORT);
			toast.show();
			appValidated=true;
		}
		else{
			toast = Toast.makeText(context, "Error: GPS Unavailable", Toast.LENGTH_SHORT);
			toast.show();
			appValidated=false;
		}
			
		// If DB & Internet & GPS found, Launch the WelcomeScreen after a delay
		if(appValidated){
			new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                	Intent intent = new Intent(SplashActivity.this,WelcomeActivity.class);
                	startActivity(intent);
                	finish();
                }
            }, 4000);
		}
		else{
			toast = Toast.makeText(context, "Failed to Initializa", Toast.LENGTH_SHORT);
			toast.show();
			finish();
		}				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	private boolean dbCheck(){
		Context context = getApplicationContext();
		PartsDatabaseHandler pdb = PartsDatabaseHandler.getInstance(context);
		StoresDatabaseHandler sdb = StoresDatabaseHandler.getInstance(context);

		if(pdb != null && sdb != null){			
			SQLiteDatabase checkDB = null;
			
			// Check Parts Database
		    try {
		        checkDB = SQLiteDatabase.openDatabase(context.getDatabasePath("PartsDatabase").toString(), null,
		                  SQLiteDatabase.OPEN_READONLY);
		        checkDB.close();
		        hasDatabase=true;
		    } catch (SQLiteException e) { // Database doesn't exist yet
		    	Log.d("Insert: ", "Inserting Parts");		    	
		    	
		    	// TODO: Add more Data !
		        
		    	pdb.addPart(new PartsDB("1", "CRV1995T", "HondaCRVTires", "200", "Additional", "Tires", "Honda", "CRV", "1995"));
		        pdb.addPart(new PartsDB("2", "CVC2000T", "HondaCivicTires", "150", "Additional", "Tires", "Honda", "Civic", "2000"));
		        pdb.addPart(new PartsDB("3", "CRV1990T", "HondaCRVTires", "175", "Additional", "Tires", "Honda", "CRV", "1990"));
		        pdb.addPart(new PartsDB("4", "FF2000T", "FordFocusTires", "225", "Additional", "Tires", "Ford", "Focus", "2000"));
		        pdb.addPart(new PartsDB("5", "CVC1990T", "HondaCivicTires", "100", "Additional", "Tires", "Honda", "Civic", "1990"));
		        pdb.addPart(new PartsDB("6", "CRV1995E", "HondaCRVExhaust", "200", "Additional", "Exhaust", "Honda", "CRV", "1995"));
		        pdb.addPart(new PartsDB("7", "CVC2000E", "HondaCivicExhaust", "150", "Additional", "Exhaust", "Honda", "Civic", "2000"));
		        pdb.addPart(new PartsDB("8", "CRV1990E", "HondaCRVExhaust", "175", "Additional", "Exhaust", "Honda", "CRV", "1990"));
		        pdb.addPart(new PartsDB("9", "FF2000E", "FordFocusExhaust", "225", "Additional", "Exhaust", "Ford", "Focus", "2000"));
		        pdb.addPart(new PartsDB("10", "CVC1990E", "HondaCivicExhaust", "100", "Additional", "Exhaust", "Honda", "Civic", "1990"));
		        
		        hasDatabase=true;
		    }
		    
		    // Check Stores Database
		    try {
		        checkDB = SQLiteDatabase.openDatabase(context.getDatabasePath("StoresDatabase").toString(), null,
		                  SQLiteDatabase.OPEN_READONLY);
		        checkDB.close();
		        hasDatabase=true;
		    } catch (SQLiteException e) { // Database doesn't exist yet
		    	Log.d("Insert: ", "Inserting Stores");		    	
		    	
		    	// TODO: Add more Data !
		    	
		        sdb.addStore(new StoresDB("1","5","Walmart","Save Money. Live Better.","4650 W North Ave","Chicago","IL","60639"));
		        sdb.addStore(new StoresDB("2","5","Target","Get More. Pay Less.","2656 N Elston Ave","Chicago","IL","60647"));		        
		        
		        hasDatabase=true;
		    }
		}
		else{ hasDatabase=true; }	    
	    return hasDatabase;
	}
	
	private boolean gpsCheck(){
		Context context = getApplicationContext();
		PackageManager pm = context.getPackageManager();
		hasGPS = pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);

		if (hasGPS) {
			//If GPS is hardware embedded for your device but it is not enabled
			/*
			 * LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
				if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				    //Ask the user to enable GPS
				    AlertDialog.Builder builder = new AlertDialog.Builder(this);
				    builder.setTitle("Location Manager");
				    builder.setMessage("Would you like to enable GPS?");
				    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
				            //Launch settings, allowing user to make a change
				            Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				            startActivity(i);
				        }
				    });
				    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				        @Override
				        public void onClick(DialogInterface dialog, int which) {
				            //No location service, no Activity
				            finish();
				        }
				    });
				    builder.create().show();
				}
			 */
		    Log.d("GPS", "GPS Available");
		} else {
		    Log.d("GPS", "GPS Unavailable");
		}
		
		return hasGPS;
	}
}
