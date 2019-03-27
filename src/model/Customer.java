package model;

public class Customer {
	private int id;
	private String name;
	private String phoneno;
	private Address address;
	
	public Customer() {}
	
	public Customer(int id) {
		this.id = id;
	}

	public Customer(int id, String name, String phoneno, Address address) {
		this.id = id;
		this.name = name;
		this.phoneno = phoneno;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
