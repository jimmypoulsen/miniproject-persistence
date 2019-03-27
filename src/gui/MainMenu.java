package gui;

import ctrl.*;
import model.*;

public class MainMenu {
	private AddressesController aCtrl;
	
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
			aCtrl.findAll(true).forEach(a -> {
				System.out.println(a.getAddress());
			});
		} catch (NullPointerException e) {
			System.out.println("Could not find any addresses");
		}
	}
}
