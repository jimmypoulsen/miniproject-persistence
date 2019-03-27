package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ctrl.DataAccessException;
import model.Address;

public class DBAddresses implements DBIFAddresses {
	private static final String FIND_BY_ID_Q = "select * from addresses where id = ?";
	private PreparedStatement findByIdPS;
	
	private static final String FIND_ALL_Q = "select * from addresses";
	private PreparedStatement findAllPS;
	
	private static final String FIND_FROM_ZIP_CITY_BY_ZIP_Q = "select * from zip_cities where zip = ?";
	private PreparedStatement findFromZipCityByZipPS;
	
	private static final String FIND_COUNTRY_BY_ID_Q = "select name from countries where id = ?";
	private PreparedStatement findCountryByIdPS;
	
	public DBAddresses() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			this.findAllPS = con.prepareStatement(FIND_ALL_Q);
			this.findByIdPS = con.prepareStatement(FIND_BY_ID_Q);
			this.findFromZipCityByZipPS = con.prepareStatement(FIND_FROM_ZIP_CITY_BY_ZIP_Q);
			this.findCountryByIdPS = con.prepareStatement(FIND_COUNTRY_BY_ID_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare statement", e);
		}
	}
	
	@Override
	public Address findById(int id, boolean fullAssociation) throws DataAccessException {
		Address res = null;
		try {
			this.findByIdPS.setInt(1, id);
			ResultSet rs = this.findByIdPS.executeQuery();
			if(rs.next()) {
				res = buildObject(rs, fullAssociation);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		
		return res;
	}

	@Override
	public List<Address> findAll(boolean fullAssociation) throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAllPS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return buildObjects(rs, fullAssociation);
	}

	@Override
	public Address insert(Address employee) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	private String findCityFromZipCityByZip(String needle, String zip) throws DataAccessException {
		String res = null;
		try {
			this.findFromZipCityByZipPS.setString(1, zip);
			ResultSet rs = this.findFromZipCityByZipPS.executeQuery();
			if(rs.next()) {
				res = rs.getString(needle);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return res;
	}
	
	private String findCountryById(int id) throws DataAccessException {
		String res = null;
		try {
			this.findCountryByIdPS.setInt(1, id);
			ResultSet rs = this.findCountryByIdPS.executeQuery();
			if(rs.next()) {
				res = rs.getString("name");
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute query", e);
		}
		return res;
	}
	
	
	private Address buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Address a = new Address();
		try {
			a.setAddress(rs.getString("address"));
			a.setZipcode(rs.getString("zip"));
			if(fullAssociation) {
				a.setCity(findCityFromZipCityByZip("city", a.getZipcode()));
				a.setCountry(findCountryById(Integer.parseInt(findCityFromZipCityByZip("country_id", a.getZipcode()))));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return a;
	}
	
	private List<Address> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<Address> res = new ArrayList<>();
		try {
			while(rs.next()) {
				res.add(buildObject(rs, fullAssociation));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return res;
	}
}
