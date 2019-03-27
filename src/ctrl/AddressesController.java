package ctrl;

import model.Address;

import java.util.List;

import db.DBAddresses;

public class AddressesController {
	private DBAddresses dbAddresses;
	
	public AddressesController() throws DataAccessException {
		dbAddresses = new DBAddresses();
	}
	
	public Address findById(int id, boolean fullAssociation) throws DataAccessException {
		return dbAddresses.findById(id, fullAssociation);
	}
	
	public List<Address> findAll(boolean fullAssociation) throws DataAccessException {
		return dbAddresses.findAll(fullAssociation);
	}
}
