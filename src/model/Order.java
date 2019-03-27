package model;

import java.util.Date;
import java.util.ArrayList;

public class Order {
	private int id;
	private Date created;
	private String deliveryStatus;
	private String deliveryDate;
	private ArrayList<OrderLine> orderLines;
	
	public Order() {}
	
	public Order(int id) {
		this.id = id;
	}

	public Order(int id, Date created, String deliveryStatus, String deliveryDate) {
		this.id = id;
		this.created = created;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.orderLines = new ArrayList<OrderLine>();
	}
	
	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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
}
