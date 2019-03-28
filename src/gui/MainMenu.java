package gui;

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
		aCtrl = new AddressesController();
		try {
			Address res = aCtrl.findById(1, true);
			System.out.println(res.getAddress() + " " + res.getZipcode() + " " + res.getCity() + " " + res.getCountry());
		} catch (NullPointerException e) {
			System.out.println("Could not find an address with that id");
		}
		
		try {
			aCtrl.findAll(true).forEach(res -> {
				System.out.println(res.getAddress() + " " + res.getZipcode() + " " + res.getCity() + " " + res.getCountry());
			});
		} catch (NullPointerException e) {
			System.out.println("Could not find any addresses");
		}
		
		pCtrl = new ProductsController();
		try {
			Product res = pCtrl.findById(1);
			System.out.println(res.getName() + " koster: " + res.getPurchasePrice() + " kr.");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		try {
			pCtrl.findAll().forEach(res -> {
				System.out.println(res.getName() + " koster: " + res.getPurchasePrice() + " kr.");
			});
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		oCtrl = new OrdersController();
		try {
			System.out.println(oCtrl.findById(1));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
