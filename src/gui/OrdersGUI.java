package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ctrl.DataAccessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrdersGUI extends JPanel {
	private static OrdersGUI instance = null;
	private JTable ordersTable;
	private OrdersTableModel ordersTableModel;
	private NewOrderGUI newOrderGUI;
	
	public static OrdersGUI getInstance() {
		if(instance == null)
			instance = new OrdersGUI();
		return instance;
	}
	
	private OrdersGUI() {
		setLayout(new BorderLayout(0, 0));
		
		Container container = new Container();
		add(container, BorderLayout.CENTER);
		container.setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		container.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewOrder = new JButton("New order");
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOrder();
			}
		});
		panel.add(btnNewOrder, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		ordersTable = new JTable();
		scrollPane.setViewportView(ordersTable);
		
		init();
	}
	
	private void init() {
		try {
			ordersTableModel = new OrdersTableModel();
			ordersTable.setModel(ordersTableModel);
			displayOrders();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void newOrder() {
		System.out.println("New order button clicked!");
		newOrderGUI = new NewOrderGUI();
		newOrderGUI.setVisible(true);
	}
	
	public void displayOrders() {
		try {
			ordersTableModel.setModelData();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
}
