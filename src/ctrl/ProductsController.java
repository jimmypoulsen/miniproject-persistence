package ctrl;

import model.Product;

import java.util.List;

import db.DBProducts;

public class ProductsController {
	private DBProducts dbProducts;
	
	public ProductsController() throws DataAccessException {
		dbProducts = new DBProducts();
	}
	
	public Product findById(int id) throws DataAccessException {
		return dbProducts.findById(id);
	}
	
	public List<Product> findAll() throws DataAccessException {
		return dbProducts.findAll();
	}
}