package model;

public class Address {
	private int id;
	private String country;
	private String address;
	private String city;
	private String zipcode;
	
	public Address() {}
	
	public Address(int id) {
		this.id = id;
	}

	public Address(int id, String country, String address, String city, String zipcode) {
		this.id = id;
		this.country = country;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
