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
	
	private static final String INSERT_ORDER_LINE_Q = "insert into order_lines (quantity, subtotal, order_id, product_id) values (?, ?, ?, ?)";
	private PreparedStatement insertOLPS;
	
	private static final String DELETE_Q = "delete from orders where id = ?";
	private PreparedStatement deletePS;
	
	private static final String FIND_ORDER_LINES_BY_ID_Q = "select * from order_lines where order_id = ?";
	private PreparedStatement findOrderLinesByIdPS;
	
	private static final String GET_LAST_ORDER_ID_Q = "SELECT IDENT_CURRENT('orders') AS id";
	private PreparedStatement getLastOrderIdPS;
	
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
			this.insertOLPS = con.prepareStatement(INSERT_ORDER_LINE_Q);
			this.deletePS = con.prepareStatement(DELETE_Q);
			this.findOrderLinesByIdPS = con.prepareStatement(FIND_ORDER_LINES_BY_ID_Q);
			this.getLastOrderIdPS = con.prepareStatement(GET_LAST_ORDER_ID_Q);
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
		try {
			insertPS.setDouble(1, order.getTotal());
			insertPS.setString(2, order.getDeliveryStatus());
			insertPS.setString(3, order.getDeliveryDate());
			insertPS.setInt(4, order.getCustomerId());
		} catch (SQLException e) {
			throw new DataAccessException("Could not bind", e);
		}
		try {
			insertPS.executeUpdate();
			for(int i = 0; i < order.getOrderLines().size(); i++) {
				insertOrderLine(getLastOrderId(), order.getOrderLines().get(i));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new DataAccessException("Could not insert", e);
		}
		return order;
	}
	
	public OrderLine insertOrderLine(int orderId, OrderLine orderLine) throws DataAccessException {
		try {
			insertOLPS.setInt(1, orderLine.getQuantity());
			insertOLPS.setDouble(2, orderLine.getSubtotal());
			insertOLPS.setInt(3, orderId);
			insertOLPS.setInt(4, orderLine.getProduct().getId());
		} catch (SQLException e) {
			throw new DataAccessException("Could not bind", e);
		}
		try {
			insertOLPS.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not insert", e);
		}
		return orderLine;
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
	
	public int getLastOrderId() throws DataAccessException {
		int lastOrderId = 0;
		try {
			ResultSet rs = getLastOrderIdPS.executeQuery();
			if(rs.next())
				lastOrderId = rs.getInt("id");
		} catch (SQLException e) {
			throw new DataAccessException("Could not read result", e);
		}
		return lastOrderId;
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
				ArrayList<OrderLine> orderLines = findOrderLines(rs.getInt("id"));
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
