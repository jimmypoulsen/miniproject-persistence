package model;

public class OrderLine {
	private int id;
	private int quantity;
	private double subtotal;
	private Product product;
	private Order order;
	
	public OrderLine() {}
	
	public OrderLine(int id) {
		this.id = id;
	}

	public OrderLine(int quantity, double subtotal, Product p, Order o) {
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.product = p;
		this.order = o;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
