package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import ctrl.DataAccessException;
import model.OrderLine;

public class OrderLinesTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = { "Product", "Price", "Quantity", "Subtotal" };
	private List<OrderLine> orderLinesList;
	
	public OrderLinesTableModel() {
		super();
		orderLinesList = new ArrayList<>();
	}
	
	public void setModelData(List<OrderLine> list) {
		this.orderLinesList = new ArrayList<>(list);
		super.fireTableDataChanged();
	}
	
	public OrderLine getValueAtRow(int ix) {
		return orderLinesList.get(ix);
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
		if(orderLinesList != null) {
			res = orderLinesList.size();
		}
		return res;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OrderLine currOrderLine = this.orderLinesList.get(rowIndex);
		String res = "<KEH?>";
		switch(columnIndex) {
			case 0:
				res = "" + currOrderLine.getProduct().getName();
				break;
			case 1:
				res = "" + currOrderLine.getProduct().getPurchasePrice() + " kr.";
				break;
			case 2:
				res = "" + currOrderLine.getQuantity();
				break;
			case 3:
				res = "" + currOrderLine.getSubtotal() + " kr.";
				break;
		}
		return res;
	}
}