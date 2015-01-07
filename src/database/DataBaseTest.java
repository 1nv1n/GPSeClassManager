package database;

import java.util.ArrayList;

import dbtest.DataBaseInterface;
import dbtest.Part;

import wrappers.StoresDB;

public class DataBaseTest implements DataBaseInterface {

	@Override
	public ArrayList<Part> getCarParts(String make, String Model, String year) {
		ArrayList<Part> partList = new ArrayList<Part>();
		Part part = new Part("Tires","Michelin","uid",400);
		partList.add(part);
		part = new Part("Brakes","PowerStop","uid",500);
		partList.add(part);
		
		return partList;
	}

	@Override
	public Part getPart(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StoresDB> getStores(ArrayList<String> stores) {

		ArrayList<StoresDB> storeList = new ArrayList<StoresDB>();
		StoresDB store = new StoresDB("001","001","Walmart","Save Money. Live Better.","4650 W North Ave","Chicago","IL","60639");
		storeList.add(store);
		store = new StoresDB("002","001","Target","Get More. Pay Less.","2656 N Elston Ave","Chicago","IL","60647");
		storeList.add(store);
		return storeList;
	}

	@Override
	public StoresDB getStore(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

}
