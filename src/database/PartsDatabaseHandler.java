package database;

import java.util.ArrayList;
import java.util.List;

import wrappers.PartsDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PartsDatabaseHandler extends SQLiteOpenHelper{

	/**
	 * @param args
	 */
	
	// All Static variables
	// Instance
	private static PartsDatabaseHandler sInstance = null;
	
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "PartsDatabase";
 
    // Parts Table Name
    private static final String TABLE_PARTS = "parts";
 
    // Parts Table Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_UID = "uid";
    private static final String KEY_PNAME = "pname";
    private static final String KEY_PDES = "pdes";
    private static final String KEY_PRICE = "price";
    private static final String KEY_PADDITIONAL = "padditional";
    private static final String KEY_PCATEGORY = "pcategory";
    private static final String KEY_PMAKE = "pmake";
    private static final String KEY_PMODEL = "pmodel";
    private static final String KEY_PYEAR = "pyear";
    
    public static PartsDatabaseHandler getInstance(Context context) {
        
        // Use the application context, which will ensure that you 
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
          sInstance = new PartsDatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
      }
	
    private PartsDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    
    private PartsDatabaseHandler(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}
	
	// Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PARTS_TABLE = "CREATE TABLE " + TABLE_PARTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_UID + " INTEGER,"
        		+ KEY_PNAME + " TEXT,"
        		+ KEY_PDES + " TEXT,"
        		+ KEY_PRICE + " INT,"
        		+ KEY_PADDITIONAL + " TEXT,"
        		+ KEY_PCATEGORY + " TEXT,"
        		+ KEY_PMAKE + " TEXT,"
        		+ KEY_PMODEL + " TEXT,"
                + KEY_PYEAR + " INT" + ");";
        db.execSQL(CREATE_PARTS_TABLE);
    }
 
    // Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop Older table If It Existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTS);
 
        // Create Tables Again
        onCreate(db);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// CRUD Operations (Create, Read, Update, Delete)
	// Adding a New Part
	public void addPart(PartsDB part) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_UID, part.get_UID()); 
	    values.put(KEY_PNAME, part.get_productName());
	    values.put(KEY_PDES, part.get_productDescription());
	    values.put(KEY_PRICE, part.get_productPrice());
	    values.put(KEY_PADDITIONAL, part.get_productAdditional());
	    values.put(KEY_PCATEGORY, part.get_productCategory());
	    values.put(KEY_PMAKE, part.get_make());
	    values.put(KEY_PMODEL, part.get_model());
	    values.put(KEY_PYEAR, part.get_year());
	 
	    // Inserting Row
	    db.insert(TABLE_PARTS, null, values);
	    db.close(); // Closing hasDatabase connection		
	}
	 
	// Getting a Single Part
	public PartsDB getPart(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		 
	    Cursor cursor = db.query(TABLE_PARTS, new String[] { 
	    		KEY_ID,
	    		KEY_UID,
	    		KEY_PNAME, 
	    		KEY_PDES,
	    		KEY_PRICE,
	    		KEY_PADDITIONAL,
	    		KEY_PCATEGORY,
	    		KEY_PMAKE,
	    		KEY_PMODEL,
	    		KEY_PYEAR
	    		}, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    PartsDB part = new PartsDB(
	    		Integer.parseInt(cursor.getString(0)),
	    		cursor.getString(1), 
	            cursor.getString(2),
	            cursor.getString(3),
	            cursor.getString(4),
	            cursor.getString(5),
	            cursor.getString(6),
	            cursor.getString(7),
	            cursor.getString(8),
	            cursor.getString(9)
	            );
	    return part;
	}
	 
	// Getting All Parts
	public List<PartsDB> getAllParts() {
		List<PartsDB> partList = new ArrayList<PartsDB>();
	    // Select All query
	    String selectQuery = "SELECT * FROM " + TABLE_PARTS;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // Looping through all Rows & Adding to the list
	    if (cursor.moveToFirst()) {
	        do {
	        	PartsDB part = new PartsDB();
	            part.set_id(Integer.parseInt(cursor.getString(0)));
	            part.set_UID(Integer.parseInt(cursor.getString(1)));
	            part.set_productName(cursor.getString(2));
	            part.set_productDescription(cursor.getString(3));
	            part.set_id(Integer.parseInt(cursor.getString(4)));
	            part.set_productAdditional(cursor.getString(5));
	            part.set_productCategory(cursor.getString(6));
	            part.set_make(cursor.getString(7));
	            part.set_model(cursor.getString(8));
	            part.set_year(Integer.parseInt(cursor.getString(9)));
	            // Adding part to list
	            partList.add(part);
	        } while (cursor.moveToNext());
	    }
	    return partList;
	}
	
	// Getting Particular Parts
	public ArrayList<PartsDB> getCarParts(String make, String model, String year) {
		ArrayList<PartsDB> partList = new ArrayList<PartsDB>();
		
		String selectQuery = "SELECT * FROM " + TABLE_PARTS +" WHERE pmake = '" + make.trim() + "' AND pmodel = '" + model.trim() + "' AND pyear = '" + year.trim() + "'";
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // Looping through all Rows & Adding to the list
		    if (cursor.moveToFirst()) {
		        do {
		        	PartsDB part = new PartsDB();
		            part.set_id(Integer.parseInt(cursor.getString(0)));
		            part.set_UID(Integer.parseInt(cursor.getString(1)));
		            part.set_productName(cursor.getString(2));
		            part.set_productDescription(cursor.getString(3));
		            part.set_id(Integer.parseInt(cursor.getString(4)));
		            part.set_productAdditional(cursor.getString(5));
		            part.set_productCategory(cursor.getString(6));
		            part.set_make(cursor.getString(7));
		            part.set_model(cursor.getString(8));
		            part.set_year(Integer.parseInt(cursor.getString(9)));
		            // Adding part to list
		            partList.add(part);
		        } while (cursor.moveToNext());
		    }
		    return partList;
		}
	
	// Getting a Part via it's UID
	public PartsDB getPart(String uid) {
		String selectQuery = "SELECT * FROM " + TABLE_PARTS +" WHERE UID = '" + uid.trim() + "'";
		 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    
	    PartsDB part = new PartsDB();
	    
	    if (cursor.moveToFirst()) {
	        part.set_id(Integer.parseInt(cursor.getString(0)));
	        part.set_UID(Integer.parseInt(cursor.getString(1)));
	        part.set_productName(cursor.getString(2));
	        part.set_productDescription(cursor.getString(3));
	        part.set_productPrice(Integer.parseInt(cursor.getString(4)));
	        part.set_productAdditional(cursor.getString(5));
	        part.set_productCategory(cursor.getString(6));
	        part.set_make(cursor.getString(7));
	        part.set_model(cursor.getString(8));
	        part.set_year(Integer.parseInt(cursor.getString(9)));
	    }
	 		
		return part;
	}
	
	// Getting Part Description from Part Name
	public String getPartNameDesc(String pname) {
		String selectQuery = "SELECT pdes FROM " + TABLE_PARTS +" WHERE pname = '" + pname.trim() + "'";
		 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    String p_des = pname;
	    
	    if (cursor.moveToFirst()) {
		    p_des = cursor.getString(0);
	    }
	    else{
	    	// TODO: Exception Handling
	    }
		 		
		return p_des;
	}
	 
	// Getting Parts Count
	public int getPartsCount() {
		String countQuery = "SELECT * FROM " + TABLE_PARTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
	}
	
	// Getting Part UID from Part Name
	public int getPartUID(String pname) {
		String selectQuery = "SELECT uid FROM " + TABLE_PARTS +" WHERE pname = '" + pname.trim() + "'";
		 
		int p_uid = 0;
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    
	    if (cursor.moveToFirst()) {
	    	p_uid = Integer.parseInt(cursor.getString(0));
	    }
	    else{
	    	// TODO: Exception Handling
	    }
		 		
		return p_uid;
	}
	
	// Updating a Single Part
	public int updatePart(PartsDB part) {
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_UID, part.get_UID()); 
	    values.put(KEY_PNAME, part.get_productName());
	    values.put(KEY_PDES, part.get_productDescription());
	    values.put(KEY_PRICE, part.get_productPrice());
	    values.put(KEY_PADDITIONAL, part.get_productAdditional());
	    values.put(KEY_PCATEGORY, part.get_productCategory());
	    values.put(KEY_PMAKE, part.get_make());
	    values.put(KEY_PMODEL, part.get_model());
	    values.put(KEY_PYEAR, part.get_year());
	 
	    // Updating a Single Row
	    return db.update(TABLE_PARTS, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(part.get_id()) });
	}
	 
	// Deleting a Single Part
	public void deletePart(PartsDB part) {
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_PARTS, KEY_ID + " = ?",
	            new String[] { String.valueOf(part.get_id()) });
	    db.close();
	}
}
