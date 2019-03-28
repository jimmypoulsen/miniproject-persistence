package ctrl;

import model.Order;
import model.OrderLine;

import java.util.List;
import java.util.ArrayList;

import db.DBOrders;

public class OrdersController {
	private DBOrders dbOrders;
	
	public OrdersController() throws DataAccessException {
		dbOrders = new DBOrders();
	}
	
	public Order findById(int id) throws DataAccessException {
		return dbOrders.findById(id);
	}
	
	public List<Order> findAll() throws DataAccessException {
		return dbOrders.findAll();
	}
	
	public ArrayList<OrderLine> findOrderLines() throws DataAccessException {
		return dbOrders.findOrderLines(1);
	}
}
