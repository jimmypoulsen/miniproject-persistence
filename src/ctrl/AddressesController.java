package ctrl;

import model.Address;
import db.DBAddresses;

public class AddressesController {
	private DBAddresses dbAddresses;
	
	public AddressesController() throws DataAccessException {
		dbAddresses = new DBAddresses();
	}
	
	public Address findById(int id) throws DataAccessException {
		Address address = dbAddresses.findById(id, true);
		return address;
	}
}
