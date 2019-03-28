package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ctrl.*;
import model.Product;

public class NewOrderGUI extends JDialog {
	private OrdersController ordersCtrl;
	private ProductsController productsCtrl;
	private JTable orderLinesTable;
	private OrderLinesTableModel orderLinesTableModel;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCustomerId;
	private JLabel lblCustomerId;
	private JTextField textFieldProductId;
	private JTextField textFieldQuantity;
	private JLabel lblProductId;
	private JLabel lblQuantity;
	private JButton btnAddToOrder;
	private JScrollPane scrollPane;
	private JLabel lblOrderLines;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewOrderGUI dialog = new NewOrderGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewOrderGUI() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblCustomerId = new JLabel("Customer ID");
		}
		{
			textFieldCustomerId = new JTextField();
			textFieldCustomerId.setColumns(10);
		}
		
		lblProductId = new JLabel("Product ID");
		
		textFieldProductId = new JTextField();
		textFieldProductId.setColumns(10);
		
		lblQuantity = new JLabel("Quantity");
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		btnAddToOrder = new JButton("Add to order");
		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrderLine();
			}
		});
		
		lblOrderLines = DefaultComponentFactory.getInstance().createTitle("Order lines");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCustomerId)
							.addGap(5)
							.addComponent(textFieldCustomerId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProductId)
								.addComponent(lblQuantity))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldProductId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(116, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(159)
					.addComponent(btnAddToOrder)
					.addContainerGap(158, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(181)
					.addComponent(lblOrderLines)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblCustomerId)
							.addGap(18)
							.addComponent(lblProductId))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(textFieldCustomerId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldProductId)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblQuantity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textFieldQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAddToOrder)
					.addGap(36)
					.addComponent(lblOrderLines)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
		);
		
		orderLinesTable = new JTable();
		scrollPane.setViewportView(orderLinesTable);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addCustomer();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		init();
	}
	
	private void init() {
		try {
			ordersCtrl = new OrdersController();
			productsCtrl = new ProductsController();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		orderLinesTableModel = new OrderLinesTableModel();
		orderLinesTable.setModel(orderLinesTableModel);
		hideOrderLineContent();
	}
	
	private void hideOrderLineContent() {
		textFieldProductId.setVisible(false);
		textFieldQuantity.setVisible(false);
		lblProductId.setVisible(false);
		lblQuantity.setVisible(false);
		btnAddToOrder.setVisible(false);
		scrollPane.setVisible(false);
		lblOrderLines.setVisible(false);
		orderLinesTable.setVisible(false);
	}
	
	private void showOrderLineContent() {
		textFieldCustomerId.setVisible(false);
		lblCustomerId.setVisible(false);
		
		textFieldProductId.setVisible(true);
		textFieldQuantity.setVisible(true);
		lblProductId.setVisible(true);
		lblQuantity.setVisible(true);
		btnAddToOrder.setVisible(true);
		scrollPane.setVisible(true);
		lblOrderLines.setVisible(true);
		orderLinesTable.setVisible(true);
	}
	
	private void addCustomer() {
		int customerId = Integer.parseInt(textFieldCustomerId.getText());
		
		// check if customer exists
		ordersCtrl.newOrder(customerId);
		
		showOrderLineContent();
		
		for(ActionListener aL : okButton.getActionListeners()) {
			okButton.removeActionListener(aL);
		}
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOrder();
			}
		});
	}
	
	private void addOrderLine() {
		// only get started if a product id is entered
		if(!textFieldProductId.getText().equals("")) {
			int productId = Integer.parseInt(textFieldProductId.getText());
			
			int quantity = 1;
			if(!textFieldQuantity.getText().equals("")) {
				quantity = Integer.parseInt(textFieldQuantity.getText());
			}

			if(!ordersCtrl.addOrderLineToCurrOrder(quantity, productId)) {
				JOptionPane.showMessageDialog(this, "Product doesn't exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			textFieldProductId.setText("");
			textFieldQuantity.setText("");
			System.out.println(ordersCtrl.getCurrOrder());
			showOrderLines();
		}
	}
	
	private void showOrderLines() {
		orderLinesTableModel.setModelData(ordersCtrl.getCurrOrder().getOrderLines());
	}
	
	private void createOrder() {
		try {
			ordersCtrl.addOrder(ordersCtrl.getCurrOrder());
			cancel();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cancel() {
		textFieldProductId.setText("");
		textFieldQuantity.setText("");
		OrdersGUI.getInstance().displayOrders();
		this.setVisible(false);
	}
}
