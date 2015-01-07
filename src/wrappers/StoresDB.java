package wrappers;

public class StoresDB {
	
	/**
	 * @param args
	 */
	
	//Private Variables
	int _id, _UID, _PUID;
	String _storeName, _storeDescription, _storeAddress, _storeCity, _storeState, _storeZipCode;
	double _longitude, _latitude;
	
	public StoresDB(){
		// Empty Constructor
	}
	
	// Constructor with ID
	public StoresDB(int id, String uid, String puid, String name, String description, String address, String city, String state, String zipcode) {
		this._id=id;
		this._UID=Integer.parseInt(uid);
		this._PUID=Integer.parseInt(puid);
		this._storeName = name;
		this._storeDescription = description;
		this._storeAddress = address;
		this._storeCity = city;
		this._storeState = state;
		this._storeZipCode = zipcode;
	}
	
	// Constructor without ID
	public StoresDB(String uid, String puid, String name, String description, String address, String city, String state, String zipcode) {
		this._UID=Integer.parseInt(uid);
		this._PUID=Integer.parseInt(puid);
		this._storeName = name;
		this._storeDescription = description;
		this._storeAddress = address;
		this._storeCity = city;
		this._storeState = state;
		this._storeZipCode = zipcode;
	}
	
	// Getters & Setters
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_UID() {
		return _UID;
	}

	public void set_UID(int _UID) {
		this._UID = _UID;
	}
	
	public int get_PUID() {
		return _PUID;
	}

	public void set_PUID(int _PUID) {
		this._PUID = _PUID;
	}

	public String get_storeName() {
		return _storeName;
	}

	public void set_storeName(String _storeName) {
		this._storeName = _storeName;
	}

	public String get_storeDescription() {
		return _storeDescription;
	}

	public void set_storeDescription(String _storeDescription) {
		this._storeDescription = _storeDescription;
	}

	public String get_storeAddress() {
		return _storeAddress;
	}

	public void set_storeAddress(String _storeAddress) {
		this._storeAddress = _storeAddress;
	}

	public String get_storeCity() {
		return _storeCity;
	}

	public void set_storeCity(String _storeCity) {
		this._storeCity = _storeCity;
	}

	public String get_storeState() {
		return _storeState;
	}

	public void set_storeState(String _storeState) {
		this._storeState = _storeState;
	}

	public String get_storeZipCode() {
		return _storeZipCode;
	}

	public void set_storeZipCode(String _storeZipCode) {
		this._storeZipCode = _storeZipCode;
	}

	public double get_longitude() {
		return _longitude;
	}

	public void set_longitude(double _longitude) {
		this._longitude = _longitude;
	}

	public double get_latitude() {
		return _latitude;
	}

	public void set_latitude(double _latitude) {
		this._latitude = _latitude;
	}
	
	// Redundant
	public void setLocation(String address, String city, String state, String zipcode){		
		this._storeAddress = address;
		this._storeCity = city;
		this._storeState = state;
		this._storeZipCode = zipcode;		
	}
	
	public void setCordinates(){
		// Get Long/Lat from address
	}
	
	public void setCordinates(Double lon, Double lat){
		_longitude = lon;
		_latitude = lat;
	}	
}
