package db;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import ctrl.DataAccessException;
import model.Product;

public class DBProducts implements DBIFProducts {
	private static final String FIND_BY_ID_Q = "select * from products where id = ?";
	private PreparedStatement findByIdPS;
	
	private static final String FIND_ALL_Q = "select * from products";
	private PreparedStatement findAllPS;
	
	public DBProducts() throws DataAccessException {
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			this.findByIdPS = con.prepareStatement(FIND_BY_ID_Q);
			this.findAllPS = con.prepareStatement(FIND_ALL_Q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Product findById(int id) throws DataAccessException {
		Product res = null;
		try {
			this.findByIdPS.setInt(1, id);
			ResultSet rs = this.findByIdPS.executeQuery();
			if(rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Product> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAllPS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return buildObjects(rs);
	}

	@Override
	public Product insert(Product product) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Product product) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	private Product buildObject(ResultSet rs) throws DataAccessException {
		Product p = new Product();
		try {
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPurchasePrice(rs.getFloat("purchasePrice"));
			p.setSalesPrice(rs.getFloat("salesPrice"));
			p.setStock(rs.getInt("stock"));
			p.setSupplierId(rs.getInt("supplier_id"));
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return p;
	}
	
	private List<Product> buildObjects(ResultSet rs) throws DataAccessException {
		List<Product> res = new ArrayList<>();
		try {
			while(rs.next()) {
				res.add(buildObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
