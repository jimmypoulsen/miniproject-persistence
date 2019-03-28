package db;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import ctrl.DataAccessException;
import model.Order;
import model.OrderLine;
import model.Product;

public class DBOrders implements DBIFOrders {
	private DBIFProducts dbProducts;
	
	private static final String FIND_BY_ID_Q = "select * from orders where id = ?";
	private PreparedStatement findByIdPS;
	
	private static final String FIND_ALL_Q = "select * from orders";
	private PreparedStatement findAllPS;
	
	private static final String INSERT_Q = "insert into orders (total, deliveryStatus, deliveryDate, customer_id) values (?, ?, ?, ?)";
	private PreparedStatement insertPS;
	
	private static final String DELETE_Q = "delete from orders where id = ?";
	private PreparedStatement deletePS;
	
	private static final String FIND_ORDER_LINES_BY_ID_Q = "select * from order_lines where order_id = ?";
	private PreparedStatement findOrderLinesByIdPS;
	
	public DBOrders() throws DataAccessException {
		dbProducts = new DBProducts();
		init();
	}
	
	private void init() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			this.findByIdPS = con.prepareStatement(FIND_BY_ID_Q);
			this.findAllPS = con.prepareStatement(FIND_ALL_Q);
			this.insertPS = con.prepareStatement(INSERT_Q);
			this.deletePS = con.prepareStatement(DELETE_Q);
			this.findOrderLinesByIdPS = con.prepareStatement(FIND_ORDER_LINES_BY_ID_Q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Order findById(int id) throws DataAccessException {
		Order res = null;
		try {
			this.findByIdPS.setInt(1, id);
			ResultSet rs = this.findByIdPS.executeQuery();
			if(rs.next()) {
				res = buildObject(rs, true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Order> findAll() throws DataAccessException {
		ResultSet rs;
		try {
			rs = findAllPS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return buildObjects(rs);
	}

	@Override
	public Order insert(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Order order) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<OrderLine> findOrderLines(int orderId) throws DataAccessException {
		ResultSet rs;
		try {
			findOrderLinesByIdPS.setInt(1, orderId);
			rs = findOrderLinesByIdPS.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return buildOrderLines(rs);
	}

	private Order buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		Order o = new Order();
		try {
			o.setId(rs.getInt("id"));
			o.setTotal(rs.getFloat("total"));
			o.setDeliveryStatus(rs.getString("deliveryStatus"));
			o.setDeliveryDate(rs.getString("deliveryDate"));
			o.setCustomerId(rs.getInt("customer_id"));
			if(fullAssociation) {
				ArrayList<OrderLine> orderLines = findOrderLines(1);
				for(int i = 0; i < orderLines.size(); i++) {
					o.addOrderLine(orderLines.get(i));
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return o;
	}
	
	private List<Order> buildObjects(ResultSet rs) throws DataAccessException {
		List<Order> res = new ArrayList<>();
		try {
			while(rs.next()) {
				res.add(buildObject(rs, true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private OrderLine buildOrderLine(ResultSet rs) throws DataAccessException {
		OrderLine oL = new OrderLine();
		try {
			oL.setId(rs.getInt("id"));
			oL.setQuantity(rs.getInt("quantity"));
			oL.setSubtotal(rs.getFloat("subtotal"));
			oL.setProduct(dbProducts.findById(rs.getInt("product_id")));
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return oL;
	}
	
	private ArrayList<OrderLine> buildOrderLines(ResultSet rs) throws DataAccessException {
		ArrayList<OrderLine> res = new ArrayList<>();
		try {
			while(rs.next()) {
				res.add(buildOrderLine(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
