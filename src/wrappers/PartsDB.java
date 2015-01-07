package wrappers;

public class PartsDB {

	/**
	 * @param args
	 */
	
	//Private Variables
	int _id, _UID,_productPrice, _year;
	String _productName, _productDescription, _productCategory, _productAdditional, _make, _model;
	
	public PartsDB(){
		// Empty Constructor
	}
	
	// Constructor with ID
	public PartsDB(int id, String uid, String name, String description, String price, String additional, String category, String make, String model, String year){
		this._id=id;
		this._UID=Integer.parseInt(uid);
		this._productName=name;
		this._productDescription=description;
		this._productPrice=Integer.parseInt(price);
		this._productAdditional=additional;
		this._productCategory=category;
		this._make=make;
		this._model=model;
		this._year=Integer.parseInt(year);
	}
	
	// Constructor without ID
		public PartsDB(String uid, String name, String description, String price, String additional, String category, String make, String model, String year){
			this._UID=Integer.parseInt(uid);
			this._productName=name;
			this._productDescription=description;
			this._productPrice=Integer.parseInt(price);
			this._productAdditional=additional;
			this._productCategory=category;
			this._make=make;
			this._model=model;
			this._year=Integer.parseInt(year);
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

	public String get_productName() {
		return _productName;
	}

	public void set_productName(String _productName) {
		this._productName = _productName;
	}

	public String get_productDescription() {
		return _productDescription;
	}

	public void set_productDescription(String _productDescription) {
		this._productDescription = _productDescription;
	}

	public int get_productPrice() {
		return _productPrice;
	}

	public void set_productPrice(int _productPrice) {
		this._productPrice = _productPrice;
	}

	public String get_productAdditional() {
		return _productAdditional;
	}

	public void set_productAdditional(String _productAdditional) {
		this._productAdditional = _productAdditional;
	}

	public String get_productCategory() {
		return _productCategory;
	}

	public void set_productCategory(String _productCategory) {
		this._productCategory = _productCategory;
	}

	public String get_make() {
		return _make;
	}

	public void set_make(String _make) {
		this._make = _make;
	}

	public String get_model() {
		return _model;
	}

	public void set_model(String _model) {
		this._model = _model;
	}

	public int get_year() {
		return _year;
	}

	public void set_year(int _year) {
		this._year = _year;
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
