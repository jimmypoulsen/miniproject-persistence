package gui;

import java.sql.SQLException;

import ctrl.*;
import model.*;

public class MainMenu {
	private AddressesController aCtrl;
	private ProductsController pCtrl;
	private OrdersController oCtrl;
	
	public static void main(String[] args) throws DataAccessException {
		new MainMenu();
	}
	
	public MainMenu() throws DataAccessException {
		// find address by id
		aCtrl = new AddressesController();
		try {
			Address res = aCtrl.findById(10, true);
			System.out.println(res.getAddress() + " " + res.getZipcode() + " " + res.getCity() + " " + res.getCountry());
		} catch (NullPointerException e) {
			System.out.println("Could not find an address with that id");
		}
		
		// find all addresses
		try {
			aCtrl.findAll(true).forEach(res -> {
				System.out.println(res.getAddress() + " " + res.getZipcode() + " " + res.getCity() + " " + res.getCountry());
			});
		} catch (NullPointerException e) {
			System.out.println("Could not find any addresses");
		}
		
		// find product by id
		pCtrl = new ProductsController();
		try {
			Product res = pCtrl.findById(1);
			System.out.println(res.getName() + " koster: " + res.getPurchasePrice() + " kr.");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		// find all products
		try {
			pCtrl.findAll().forEach(res -> {
				System.out.println(res.getName() + " koster: " + res.getPurchasePrice() + " kr.");
			});
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		// find order by id
		oCtrl = new OrdersController();
		try {
			System.out.println(oCtrl.findById(1));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		// add a new order
		Order order = new Order(1000.00, "delivered", "now", 1);
		order.addOrderLine(new OrderLine(1, 500d, pCtrl.findById(1), order));
		order.addOrderLine(new OrderLine(1, 250d, pCtrl.findById(1), order));
		order.addOrderLine(new OrderLine(1, 250d, pCtrl.findById(1), order));
		try {
			oCtrl.addOrder(order);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		// print all orders
		try {
			for(Order o : oCtrl.findAll()) {
				System.out.println(o);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
