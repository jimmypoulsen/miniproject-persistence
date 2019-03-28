package db;

import java.util.List;
import ctrl.DataAccessException;
import model.Order;

public interface DBIFOrders{
	Order findById(int id) throws DataAccessException;
	List<Order> findAll() throws DataAccessException;
	Order insert(Order order) throws DataAccessException;
	boolean delete(Order order) throws DataAccessException;
}
