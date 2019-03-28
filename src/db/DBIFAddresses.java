package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Address;

public interface DBIFAddresses {
	Address findById(int id, boolean fullAssociation) throws DataAccessException;
	List<Address> findAll(boolean fullAssociation) throws DataAccessException;
	Address insert(Address address) throws DataAccessException;
	boolean delete(int id) throws DataAccessException;
}
