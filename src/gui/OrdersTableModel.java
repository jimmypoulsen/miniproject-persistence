package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import ctrl.DataAccessException;
import model.Order;
import db.DBOrders;

public class OrdersTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = { "ID", "Total", "Delivery status", "Delivery date", "Customer ID" };
	private DBOrders db;
	private List<Order> orderList;
	
	public OrdersTableModel() throws DataAccessException {
		super();
		db = new DBOrders();
		orderList = new ArrayList<>();
	}
	
	public void setModelData() throws DataAccessException {
		this.orderList = new ArrayList<>(db.findAll());
		super.fireTableDataChanged();
	}
	
	public Order getValueAtRow(int ix) {
		return orderList.get(ix);
	}
	
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public int getRowCount() {
		int res = 0;
		if(orderList != null) {
			res = orderList.size();
		}
		return res;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order currOrder = this.orderList.get(rowIndex);
		String res = "<KEH?>";
		switch(columnIndex) {
			case 0:
				res = "" + currOrder.getId();
				break;
			case 1:
				res = "" + currOrder.getTotal() + " kr.";
				break;
			case 2:
				res = currOrder.getDeliveryStatus();
				break;
			case 3:
				res = "" + currOrder.getDeliveryDate();
				break;
			case 4:
				res = "" + currOrder.getCustomerId();
				break;
		}
		return res;
	}
}