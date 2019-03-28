package ctrl;

import model.Order;
import model.OrderLine;
import model.Product;

import java.util.List;
import java.util.ArrayList;

import db.DBOrders;
import db.DBProducts;

public class OrdersController {
	private DBOrders dbOrders;
	private DBProducts dbProducts;
	private Order currOrder;
	
	public OrdersController() throws DataAccessException {
		dbOrders = new DBOrders();
		dbProducts = new DBProducts();
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
	
	public void newOrder(int customerId) {
		currOrder = new Order();
		currOrder.setCustomerId(customerId);
	}
	
	public Order getCurrOrder() {
		return currOrder;
	}
	
	public boolean addOrderLineToCurrOrder(int quantity, int productId) {
		boolean res = false;
		Product p = null;

		try {
			p = dbProducts.findById(productId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		if(p != null) {
			double subtotal = quantity * p.getPurchasePrice();
			currOrder.addOrderLine(new OrderLine(quantity, subtotal, p, currOrder));
			res = true;
		} else {
			res = false;
		}
		return res;
	}
	
	public Order addOrder(Order o) throws DataAccessException {
		double total = 0;
		for(int i = 0; i < o.getOrderLines().size(); i++) {
			total += o.getOrderLines().get(i).getSubtotal();
		}
		o.setTotal(total);
		o.setDeliveryStatus("delivered");
		o.setDeliveryDate("now");
		return dbOrders.insert(o);
	}
}
