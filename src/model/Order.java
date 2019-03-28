package model;

import java.util.Date;
import java.util.ArrayList;

public class Order {
	private int id;
	private double total;
	private String deliveryStatus;
	private String deliveryDate;
	private int customerId;
	private ArrayList<OrderLine> orderLines;
	
	public Order() {
		orderLines = new ArrayList<>();
	}
	
	public Order(int id) {
		orderLines = new ArrayList<>();
		this.id = id;
	}

	public Order(int id, double total, String deliveryStatus, String deliveryDate, int customerId) {
		this.id = id;
		this.total = total;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.customerId = customerId;
		this.orderLines = new ArrayList<OrderLine>();
	}
	
	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}
	
	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public boolean addOrderLine(OrderLine oL) {
		return this.orderLines.add(oL);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int id) {
		this.customerId = id;
	}

	@Override
	public String toString() {
		String res = "Order:\n";
		res += "ID: " + getId() + "\n";
		res += "Total: " + getTotal() + "\n";
		res += "Delivery status: " + getDeliveryStatus() + "\n";
		res += "Delivery date: " + getDeliveryDate() + "\n";
		res += "-- Order lines --\n";
		if(this.getOrderLines().size() > 0) {
			for(OrderLine oL : getOrderLines()) {
				res += "Subtotal: " + oL.getSubtotal() + " Product: " + oL.getProduct().getName() + "\n";
			}
		}
		return res;
	}
	
	
}
