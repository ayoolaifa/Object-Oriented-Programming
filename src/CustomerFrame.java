import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class CustomerFrame extends JFrame {

	protected static final String NULL = null;
	private JPanel contentPane;
	private JTextField Total;
	private JTable ViewProductTable;
	private DefaultTableModel dtmProducts;
	private DefaultTableModel dtmBasket;
	private DefaultTableModel dtmSearch;
	private JTable ShoppingBasketTable;
	private JTable SearchTable;
	private JComboBox BrandcomboBox;
	private JComboBox BarcodecomboBox;
	private JComboBox NoBcomboBox;

	public CustomerFrame(User user) throws FileNotFoundException {
		Functions fn = new Functions();
		ArrayList<Product> productList = fn.readStockFile();
		Customer c1 = (Customer) user;
		ArrayList<Product> Shopping_Basket = c1.getShoppingBasket();
		DecimalFormat df = new DecimalFormat("0.00");

		String[] headers = {"Barcode", "Item", "Brand", "Product Type", "Colour", "Connectivity",
				"Quantity", "Price", "Buttons No", "Keyboard Layout"};
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 885, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 851, 392);
		contentPane.add(tabbedPane);
		
		JPanel ViewProducts = new JPanel();
		tabbedPane.addTab("View Mouse Product", null, ViewProducts, null);
		
		ViewProducts.setLayout(null);
		
		JLabel lblEnterBarcode = new JLabel("Enter Barcode:");
		lblEnterBarcode.setBounds(10, 10, 102, 26);
		lblEnterBarcode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ViewProducts.add(lblEnterBarcode);
		
		Total = new JTextField();
		Total.setEditable(false);
		Total.setBounds(396, 330, 107, 28);
		
		Total.setColumns(10);

		BarcodecomboBox = new JComboBox();
		BarcodecomboBox.setBounds(111, 14, 102, 22);
		ViewProducts.add(BarcodecomboBox);
		
		ArrayList<Integer> BarcodeArrayList = new ArrayList<Integer>();
		for (Product p : productList) {
			BarcodeArrayList.add(p.getBarcode());
		}
		Collections.sort(BarcodeArrayList);
		
		BarcodecomboBox.addItem("");
		for (int i: BarcodeArrayList) {
			BarcodecomboBox.addItem(String.valueOf(i));
		}
		
		JButton AddShoppingBasketBtn = new JButton("Add To Shopping Basket");
		AddShoppingBasketBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BarcodecomboBox.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Select A Barcode", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					int barcode = Integer.parseInt((String) BarcodecomboBox.getSelectedItem());
					for (Product p : productList) {
						if (p.getBarcode() == barcode) {
							boolean Added = c1.UpdateShoppingBasket(p);
							if(Added == true) {
								JOptionPane.showMessageDialog(null, p.getColour() + ", " + p.getBrand() + " " + 
								p.getKeyboardOrMouse() +   " has been added to basket.",
										"Success", JOptionPane.INFORMATION_MESSAGE);
								c1.setTotal();	
							}
						}
					}
					Total.setText(String.valueOf(df.format(c1.getTotal())));
					dtmBasket.setRowCount(0);
					for (Product p : Shopping_Basket) {					
						if (p.getKeyboardOrMouse() == KorM.keyboard) {
							Keyboard k = (Keyboard) p;
							Object[] rowdata = new Object[] {k.getBarcode(), k.getKeyboardOrMouse(), k.getBrand(), k.getKeyboardType(),
									k.getColour(), k.getConnectivity(), k.getQuantity(), "£"+k.getRetailPrice(), 
									"",k.getKeyboardLayout()};
							dtmBasket.addRow(rowdata);
						}else {
							Mouse m = (Mouse) p;
							Object[] rowdata = new Object[] {m.getBarcode(), m.getKeyboardOrMouse(), m.getBrand(), m.getMouseType(),
									m.getColour(), m.getConnectivity(), m.getQuantity(), "£"+m.getRetailPrice(), 
									m.getNumOfButtons(),""};
							dtmBasket.addRow(rowdata);
							}
					}
					clearFields();
				}
			}
		});
		AddShoppingBasketBtn.setBounds(333, 10, 189, 26);
		ViewProducts.add(AddShoppingBasketBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 826, 309);
		ViewProducts.add(scrollPane);
		
		ViewProductTable = new JTable();
		scrollPane.setViewportView(ViewProductTable);
		
		
		dtmProducts = new DefaultTableModel();
		ViewProductTable.setModel(dtmProducts);
		
		dtmProducts.setColumnIdentifiers(headers);
		ArrayList<Product> CustomerProducts = c1.ViewProduct();
		
		for (Product p : CustomerProducts) {
			if (p.getKeyboardOrMouse() == KorM.keyboard) {
				Keyboard k = (Keyboard) p;
				Object[] rowdata = new Object[] {k.getBarcode(), k.getKeyboardOrMouse(), k.getBrand(), k.getKeyboardType(),
						k.getColour(), k.getConnectivity(), k.getQuantity(),"£"+k.getRetailPrice(), 
						"",k.getKeyboardLayout()};
				dtmProducts.addRow(rowdata);
			}else {
				Mouse m = (Mouse) p;
				Object[] rowdata = new Object[] {m.getBarcode(), m.getKeyboardOrMouse(), m.getBrand(), m.getMouseType(),
						m.getColour(), m.getConnectivity(), m.getQuantity(),"£"+m.getRetailPrice(), 
						m.getNumOfButtons(),""};
				dtmProducts.addRow(rowdata);
				}
		}			
		JPanel SearchProduct = new JPanel();
		tabbedPane.addTab("Search Product", null, SearchProduct, null);
		SearchProduct.setLayout(null);
		
		JLabel lblEnterBrand = new JLabel("Enter Brand:");
		lblEnterBrand.setBounds(10, 10, 102, 26);
		lblEnterBrand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SearchProduct.add(lblEnterBrand);
		
		JLabel lblEnterNoOfButtons = new JLabel("Enter Number Of Buttons On Mouse:");
		lblEnterNoOfButtons.setBounds(10, 50, 215, 26);
		lblEnterNoOfButtons.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SearchProduct.add(lblEnterNoOfButtons);
		
		
		JPanel ShoppingBasket = new JPanel();
		tabbedPane.addTab("Shopping Basket", null, ShoppingBasket, null);
		ShoppingBasket.setLayout(null);
		
		JButton Checkoutbtn = new JButton("Checkout");
		Checkoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Shopping_Basket.size() > 0) {
					PaymentFrame pf = new PaymentFrame(dtmProducts, dtmBasket, Total, c1);
					pf.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Shopping Basket is Empty", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		Checkoutbtn.setBounds(662, 327, 174, 33);
		ShoppingBasket.add(Checkoutbtn);
		
		JButton ClearBasketbtn = new JButton("Clear Basket");
		ClearBasketbtn.setBounds(10, 327, 150, 33);
		ClearBasketbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmBasket.setRowCount(0);
				c1.ClearShoppingBasket(false);
				c1.setTotal();
				Total.setText(String.valueOf(df.format(c1.getTotal())));
			}
		});
		ShoppingBasket.add(ClearBasketbtn);
		ShoppingBasket.add(Total);
		
		JLabel lblNewLabel = new JLabel("Total: \u00A3");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(287, 329, 107, 28);
		ShoppingBasket.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 826, 306);
		ShoppingBasket.add(scrollPane_1);
		
		ShoppingBasketTable = new JTable();
		scrollPane_1.setViewportView(ShoppingBasketTable);
		
		dtmBasket = new DefaultTableModel();
		ShoppingBasketTable.setModel(dtmBasket);
		dtmBasket.setColumnIdentifiers(headers);		

		ArrayList<String> BrandArrayList = new ArrayList<String>();
		for (Product p : productList) {
			if(!(BrandArrayList.contains(p.getBrand()))){
				BrandArrayList.add(p.getBrand());
			}
		}
		
		Collections.sort(BrandArrayList);
		ArrayList<Integer> NoBArrayList = new ArrayList<Integer>();
		for (Product p : productList) {
			if(p.getKeyboardOrMouse() == KorM.mouse) {
				Mouse m = (Mouse) p;
				if(!(NoBArrayList.contains(m.getNumOfButtons()))){
					NoBArrayList.add(m.getNumOfButtons());
				}
			}
		}
		Collections.sort(NoBArrayList);
		
		BrandcomboBox = new JComboBox();
		BrandcomboBox.setBounds(123, 11, 102, 26);
		SearchProduct.add(BrandcomboBox);
		BrandcomboBox.addItem("");
		for (String s : BrandArrayList) {
			BrandcomboBox.addItem(s);
		}
		
		
		NoBcomboBox = new JComboBox();
		NoBcomboBox.setBounds(248, 47, 87, 33);
		SearchProduct.add(NoBcomboBox);
		NoBcomboBox.addItem("");
		for(int i : NoBArrayList) {
			NoBcomboBox.addItem(String.valueOf(i));
		}
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 126, 826, 229);
		SearchProduct.add(scrollPane_2);
		
		SearchTable = new JTable();
		scrollPane_2.setViewportView(SearchTable);
		
		dtmSearch = new DefaultTableModel();
		dtmSearch.setColumnIdentifiers(headers);
		SearchTable.setModel(dtmSearch);
		
		JButton Searchbtn = new JButton("Search");
		Searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Brand = BrandcomboBox.getSelectedItem().toString();
				
				if (Brand.equals("")) {
					JOptionPane.showMessageDialog(null, "Select A Brand", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (NoBcomboBox.getSelectedItem().toString().equals("")) {
					dtmSearch.setRowCount(0);
					for (Product p : productList) {
						if(p.getBrand().equals(Brand)) {
							if (p.getKeyboardOrMouse() == KorM.keyboard) {
								Keyboard k = (Keyboard) p;
								Object[] rowdata = new Object[] {k.getBarcode(), k.getKeyboardOrMouse(), k.getBrand(), k.getKeyboardType(),
										k.getColour(), k.getConnectivity(), k.getQuantity(), "£"+k.getRetailPrice(), 
										"",k.getKeyboardLayout()};
								dtmSearch.addRow(rowdata);
							}else {
								Mouse m = (Mouse) p;
								Object[] rowdata = new Object[] {m.getBarcode(), m.getKeyboardOrMouse(), m.getBrand(), m.getMouseType(),
										m.getColour(), m.getConnectivity(), m.getQuantity(),"£"+ m.getRetailPrice(), 
										m.getNumOfButtons(),""};
								dtmSearch.addRow(rowdata);
							}
						}
					}
				}
				else{
				dtmSearch.setRowCount(0);
				int NoB = Integer.parseInt(NoBcomboBox.getSelectedItem().toString());
					for (Product p : productList) {
						if(p.getBrand().equals(Brand)) {
							if (p.getKeyboardOrMouse() == KorM.mouse) {
								Mouse m = (Mouse) p;
								if(m.getNumOfButtons() == NoB) {
									Object[] rowdata = new Object[] {m.getBarcode(), m.getKeyboardOrMouse(), m.getBrand(), m.getMouseType(),
											m.getColour(), m.getConnectivity(), m.getQuantity(), "£"+m.getRetailPrice(), 
											m.getNumOfButtons(),""};
									dtmSearch.addRow(rowdata);
								}
							}
						}
					}
				}
				clearFields();
			}	
		});
		Searchbtn.setBounds(10, 86, 199, 26);
		SearchProduct.add(Searchbtn);
		
		
		
	}
	
	private void clearFields() {
		BrandcomboBox.setSelectedIndex(0);
		NoBcomboBox.setSelectedIndex(0);
		BarcodecomboBox.setSelectedIndex(0);
	}
}
