package db;

import java.util.List;
import ctrl.DataAccessException;
import model.Product;

public interface DBIFProducts {
	Product findById(int id) throws DataAccessException;
	List<Product> findAll() throws DataAccessException;
	Product insert(Product product) throws DataAccessException;
	boolean delete(Product product) throws DataAccessException;
}
