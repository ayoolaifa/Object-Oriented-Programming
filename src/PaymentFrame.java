import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PaymentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Cardnum;
	private JTextField Email;
	private JPasswordField SPin;

	public PaymentFrame(DefaultTableModel dtmProduct, DefaultTableModel dtmBasket, JTextField Total, Customer c1) {
		Functions fn = new Functions();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 371, 230);
		contentPane.add(tabbedPane);
		
		JPanel CreditCard = new JPanel();
		tabbedPane.addTab("Credit Card", null, CreditCard, null);
		CreditCard.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Card Number: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 10, 139, 40);
		CreditCard.add(lblNewLabel_1);
		
		Cardnum = new JTextField();
		Cardnum.setBounds(152, 20, 205, 24);
		CreditCard.add(Cardnum);
		Cardnum.setColumns(10);
		Cardnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( ((c < '0') || (c>'9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Security Pin: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 60, 139, 40);
		CreditCard.add(lblNewLabel_1_1);
		ArrayList<Product> productList = fn.readStockFile();
		
		JButton btnPay_cc = new JButton("Pay Now");
		btnPay_cc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_number = Cardnum.getText();
				String s_pin = String.valueOf(SPin.getPassword());

					
				if(Cardnum.getText().equals("") || Cardnum.getText().length() != 6) {
					JOptionPane.showMessageDialog(null, "Invalid Input. Enter Your Card Number", 
							"Error", JOptionPane.ERROR_MESSAGE);
					clearFields();
				}else if (String.valueOf(SPin.getPassword()).equals("") ||String.valueOf(SPin.getPassword()).length() != 3){
					JOptionPane.showMessageDialog(null, "Invalid Input. Enter Your Security Pin", 
							"Error", JOptionPane.ERROR_MESSAGE);
					clearFields();
				}else {
					ArrayList<Product> p = c1.getShoppingBasket();
					if(p.size() > 0) {
						for (Product item : p) {
							for (int i = 0; i < productList.size(); i++) {
								Product p1 = productList.get(i);
								if (p1.getBarcode() == item.getBarcode()) {
									productList.set(i, item);
								}
							}	
						}
						
						dtmBasket.setRowCount(0);
						c1.ClearShoppingBasket(true);
						
						
						fn.rewriteStockFile(productList);
						JOptionPane.showMessageDialog(null, c1.getTotal() + " paid using Credit Card, and the delivery address is " 
						+ c1.getAddr(),
								"Success", JOptionPane.INFORMATION_MESSAGE);
						
						c1.setTotal();
						Total.setText(String.valueOf(c1.getTotal()));
						PaymentFrame paymentFrame = new PaymentFrame(null, null, null, null);
						paymentFrame.dispose();
						clearFields();
						
						ArrayList<Product> CustomerProducts = null;
						try {
							CustomerProducts = c1.ViewProduct();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						dtmProduct.setRowCount(0);
						for (Product p1 : CustomerProducts) {
							if (p1.getKeyboardOrMouse() == KorM.keyboard) {
								Keyboard k = (Keyboard) p1;
								Object[] rowdata = new Object[] {k.getBarcode(), k.getKeyboardOrMouse(), k.getBrand(), k.getKeyboardType(),
										k.getColour(), k.getConnectivity(), k.getQuantity(),"£"+k.getRetailPrice(), 
										"",k.getKeyboardLayout()};
								dtmProduct.addRow(rowdata);
							}else {
								Mouse m = (Mouse) p1;
								Object[] rowdata = new Object[] {m.getBarcode(), m.getKeyboardOrMouse(), m.getBrand(), m.getMouseType(),
										m.getColour(), m.getConnectivity(), m.getQuantity(),"£"+m.getRetailPrice(), 
										m.getNumOfButtons(),""};
								dtmProduct.addRow(rowdata);
							}
						}				
					}else {
						JOptionPane.showMessageDialog(null, "Shopping Basket Is Empty. Close Tab", 
								"Error", JOptionPane.ERROR_MESSAGE);
						clearFields();
					}
				}
			}
		});
		btnPay_cc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPay_cc.setBounds(117, 110, 172, 40);
		CreditCard.add(btnPay_cc);
		
		SPin = new JPasswordField();
		SPin.setBounds(152, 70, 129, 30);
		CreditCard.add(SPin);
		SPin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( ((c < '0') || (c>'9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		JPanel PayPal = new JPanel();
		tabbedPane.addTab("PayPal", null, PayPal, null);
		PayPal.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Email Address: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(22, 10, 131, 35);
		PayPal.add(lblNewLabel_2);
		
		Email = new JTextField();
		Email.setToolTipText("xyz@gmail.com");
		Email.setBounds(160, 19, 196, 19);
		PayPal.add(Email);
		Email.setColumns(10);
		
		final String EMAIL_PATTERN = 
			    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		
		JButton btnPay_pp = new JButton("Pay Now");
		btnPay_pp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = Email.getText();
				
				if(Email.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Your Email Address", 
							"Error", JOptionPane.ERROR_MESSAGE);
					clearFields();
				}else if (!(email.matches(EMAIL_PATTERN))){
						JOptionPane.showMessageDialog(null, "Email is invalid", "Error", JOptionPane.ERROR_MESSAGE);
						clearFields();
				}else {
					ArrayList<Product> p = c1.getShoppingBasket();
					if(p.size() > 0) {
						for (Product item : p) {
							for (int i = 0; i < productList.size(); i++) {
								Product p1 = productList.get(i);
								if (p1.getBarcode() == item.getBarcode()) {
									productList.set(i, item);
								}
							}	
						}
						
						dtmBasket.setRowCount(0);
						c1.ClearShoppingBasket(true);
						
						
						fn.rewriteStockFile(productList);
						JOptionPane.showMessageDialog(null, c1.getTotal() + " paid using PayPal, and the delivery address is " + c1.getAddr(),
								"Success", JOptionPane.INFORMATION_MESSAGE);
						
						c1.setTotal();
						Total.setText(String.valueOf(c1.getTotal()));
						
						PaymentFrame paymentFrame = new PaymentFrame(null, null, null, null);
						paymentFrame.dispose();
						clearFields();
						
						ArrayList<Product> CustomerProducts = null;
						try {
							CustomerProducts = c1.ViewProduct();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						dtmProduct.setRowCount(0);
						for (Product p1 : CustomerProducts) {
							if (p1.getKeyboardOrMouse() == KorM.keyboard) {
								Keyboard k = (Keyboard) p1;
								Object[] rowdata = new Object[] {k.getBarcode(), k.getKeyboardOrMouse(), k.getBrand(), k.getKeyboardType(),
										k.getColour(), k.getConnectivity(), k.getQuantity(),"£"+k.getRetailPrice(), 
										"",k.getKeyboardLayout()};
								dtmProduct.addRow(rowdata);
							}else {
								Mouse m = (Mouse) p1;
								Object[] rowdata = new Object[] {m.getBarcode(), m.getKeyboardOrMouse(), m.getBrand(), m.getMouseType(),
										m.getColour(), m.getConnectivity(), m.getQuantity(),"£"+m.getRetailPrice(), 
										m.getNumOfButtons(),""};
								dtmProduct.addRow(rowdata);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Shopping Basket Is Empty. Close Tab", 
								"Error", JOptionPane.ERROR_MESSAGE);
						clearFields();
					}
				}
			}
		});
		btnPay_pp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPay_pp.setBounds(99, 55, 172, 40);
		PayPal.add(btnPay_pp);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 381, 31);
		contentPane.add(lblNewLabel);
	}
	
	private void clearFields() {
		Cardnum.setText("");
		Email.setText("");
		SPin.setText("");
	}
}
