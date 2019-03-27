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
		Address res = aCtrl.findById(1);
		System.out.println(res.getAddress() + " " + res.getZipcode() + " " + res.getCity() + " " + res.getCountry());
	}
}
