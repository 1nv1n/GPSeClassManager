package dbtest;

import java.util.ArrayList;

import wrappers.StoresDB;

public class SQLiteDatabase implements DataBaseInterface{
	
	public SQLiteDatabase(){
		//set up hasDatabase connection here
	}

	@Override
	public ArrayList<Part> getCarParts(String make, String Model, String year) {
		
		return null;
	}

	@Override
	public Part getPart(String uid) {
		
		return null;
	}

	@Override
	public ArrayList<StoresDB> getStores(ArrayList<String> stores) {
		
		return null;
	}

	@Override
	public StoresDB getStore(String uid) {
		
		return null;
	}

}
