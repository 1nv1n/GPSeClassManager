package database;

import java.util.ArrayList;
import java.util.List;

import wrappers.StoresDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class StoresDatabaseHandler extends SQLiteOpenHelper{

	/**
	 * @param args
	 */
	
	// All Static variables
	// Instance
	private static StoresDatabaseHandler sInstance = null;
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "StoresDatabase";
 
    // Stores Table Name
    private static final String TABLE_STORES = "stores";
 
    // Stores Table Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";
    private static final String KEY_PUID = "puid";
    private static final String KEY_SNAME = "sname";
    private static final String KEY_SDES = "sdes";
    private static final String KEY_SADDRESS = "saddress";
    private static final String KEY_SCITY = "scity";
    private static final String KEY_SSTATE = "sstate";
    private static final String KEY_SZIPCODE = "szipcode";
    
    public static StoresDatabaseHandler getInstance(Context context) {
        
        // Use the application context, which will ensure that you 
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
          sInstance = new StoresDatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
      }
	
    private StoresDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    
    private StoresDatabaseHandler(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}
	
	// Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORES_TABLE = "CREATE TABLE " + TABLE_STORES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_UID + " INTEGER,"
                + KEY_PUID + " INTEGER,"
        		+ KEY_SNAME + " TEXT,"
        		+ KEY_SDES + " TEXT,"
        		+ KEY_SADDRESS + " TEXT,"
        		+ KEY_SCITY + " TEXT,"
        		+ KEY_SSTATE + " TEXT,"
        		+ KEY_SZIPCODE + " TEXT" + ");";
        db.execSQL(CREATE_STORES_TABLE);
    }
 
    // Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop Older table If It Existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);
 
        // Create Tables Again
        onCreate(db);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// CRUD Operations (Create, Read, Update, Delete)
	// Adding a New Store
	public void addStore(StoresDB store) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_UID, store.get_UID());
	    values.put(KEY_PUID, store.get_PUID());
	    values.put(KEY_SNAME, store.get_storeName());
	    values.put(KEY_SDES, store.get_storeDescription());
	    values.put(KEY_SADDRESS, store.get_storeAddress());
	    values.put(KEY_SCITY, store.get_storeCity());
	    values.put(KEY_SSTATE, store.get_storeState());
	    values.put(KEY_SZIPCODE, store.get_storeZipCode());
	 
	    // Inserting Row
	    db.insert(TABLE_STORES, null, values);
	    db.close(); // Closing Database connection		
	}
	 
	// Getting a Single Store
	public StoresDB getStore(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		 
	    Cursor cursor = db.query(TABLE_STORES, new String[] { 
	    		KEY_ID,
	    		KEY_UID,
	    		KEY_PUID,
	    		KEY_SNAME, 
	    		KEY_SDES,
	    		KEY_SADDRESS,
	    		KEY_SCITY,
	    		KEY_SSTATE,
	    		KEY_SZIPCODE,
	    		}, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    StoresDB store = new StoresDB(
	    		Integer.parseInt(cursor.getString(0)),
	    		cursor.getString(1), 
	            cursor.getString(2),
	            cursor.getString(3),
	            cursor.getString(4),
	            cursor.getString(5),
	            cursor.getString(6),
	            cursor.getString(7),
	            cursor.getString(8)
	            );
	    return store;
	}
	
	// Getting a Store via it's UID
	public List<StoresDB> getStores(String s_puid) {
		List<StoresDB> storeList = new ArrayList<StoresDB>();
		
		String selectQuery = "SELECT * FROM " + TABLE_STORES +" WHERE puid = '" + String.valueOf(s_puid.trim()) + "'";
		 
		SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // Looping through all Rows & Adding to the list
	    if (cursor.moveToFirst()) {
	        do {
	        	StoresDB store = new StoresDB();
	            store.set_id(Integer.parseInt(cursor.getString(0)));
	            store.set_UID(Integer.parseInt(cursor.getString(1)));
		        store.set_PUID(Integer.parseInt(cursor.getString(2)));
		        store.set_storeName(cursor.getString(3));
		        store.set_storeDescription(cursor.getString(4));
		        store.set_storeAddress(cursor.getString(5));
		        store.set_storeCity(cursor.getString(6));
		        store.set_storeState(cursor.getString(7));
		        store.set_storeZipCode(cursor.getString(8));
	            // Adding Store to List
	            storeList.add(store);
	        } while (cursor.moveToNext());
	    }
	    return storeList;
	}
	 
	// Getting All Stores
	public List<StoresDB> getAllStores() {
		List<StoresDB> storeList = new ArrayList<StoresDB>();
	    // Select All Query
	    String selectQuery = "SELECT * FROM " + TABLE_STORES;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // Looping through all Rows & Adding to the list
	    if (cursor.moveToFirst()) {
	        do {
	        	StoresDB store = new StoresDB();
	            store.set_id(Integer.parseInt(cursor.getString(0)));
	            store.set_UID(Integer.parseInt(cursor.getString(1)));
		        store.set_PUID(Integer.parseInt(cursor.getString(2)));
		        store.set_storeName(cursor.getString(3));
		        store.set_storeDescription(cursor.getString(4));
		        store.set_storeAddress(cursor.getString(5));
		        store.set_storeCity(cursor.getString(6));
		        store.set_storeState(cursor.getString(7));
		        store.set_storeZipCode(cursor.getString(8));
	            // Adding Store to List
	            storeList.add(store);
	        } while (cursor.moveToNext());
	    }
	    return storeList;
	}
	 
	// Getting Stores Count
	public int getStoresCount() {
		String countQuery = "SELECT * FROM " + TABLE_STORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
	}
	
	// Updating a Single Store
	public int updateStore(StoresDB store) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_UID, store.get_UID());
	    values.put(KEY_PUID, store.get_PUID());
	    values.put(KEY_SNAME, store.get_storeName());
	    values.put(KEY_SDES, store.get_storeDescription());
	    values.put(KEY_SADDRESS, store.get_storeAddress());
	    values.put(KEY_SCITY, store.get_storeCity());
	    values.put(KEY_SSTATE, store.get_storeState());
	    values.put(KEY_SZIPCODE, store.get_storeZipCode());
	 
	    // Updating a Single Row
	    return db.update(TABLE_STORES, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(store.get_id()) });
	}
	 
	// Deleting a Single Store
	public void deleteStore(StoresDB store) {
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_STORES, KEY_ID + " = ?",
	            new String[] { String.valueOf(store.get_id()) });
	    db.close();
	}
}
