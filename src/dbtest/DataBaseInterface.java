package dbtest;

import java.util.ArrayList;

import wrappers.*;

public interface DataBaseInterface {
	
	public ArrayList<Part> getCarParts(String make, String Model, String year);
	
	public Part getPart(String uid);
	
	public ArrayList<StoresDB> getStores(ArrayList<String> stores);
	
	public StoresDB getStore(String uid);

}
