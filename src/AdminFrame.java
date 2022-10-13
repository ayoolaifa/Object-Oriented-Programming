import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;

public class AdminFrame extends JFrame {
	
	private static final Object NULL = null;
	private JPanel contentPane;
	private JTextField Barcode;
	private JLabel lblNewLabel;
	private JTextField Brand;
	private JTextField Colour;
	private JTextField Quantity;
	private JTextField O_Cost;
	private JTextField R_Price;
	private JLabel lblNewLabel_1;
	private JButton add_K_Product;
	private JTextField Num_of_Buttons;
	private JLabel lblNewLabel_2;
	private JButton Add_M_Product;
	private JLabel lblBrand;
	private JLabel lblColour;
	private JLabel lblConnectivity;
	private JLabel lblQuantity;
	private JLabel lblRetailCost;
	private JLabel lblRetailPrice;
	private JLabel lblKeyboardType;
	private JLabel lblKeyboardLayout;
	private JLabel lblNumberOfButtons;
	private JComboBox M_Type;
	private JComboBox Connect;
	private JComboBox K_Type;
	private JComboBox K_Layout;
	private JLabel lblMouseType;
	private JTable ProductTable;
	private JScrollPane scrollPane;
	DefaultTableModel dtm;

	public AdminFrame(User user) {
		Admin a1 = (Admin) user;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 885, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 861, 402);
		contentPane.add(tabbedPane);
		
		
		
		
		Object headers[] = new String[]{"Barcode", "Mouse Or Keyboard", "Brand", "Product Type", "Colour", "Connectivity",
				"Quantity", "Original Price", "Retail Price", "Num Of Buttons", "Keyboard Layout"}
		;
				
		JPanel View_product = new JPanel();
		tabbedPane.addTab("View Product", null, View_product, null);
		View_product.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 836, 355);
		View_product.add(scrollPane);
		
		ProductTable = new JTable(dtm);
		scrollPane.setViewportView(ProductTable);

		dtm = new DefaultTableModel();
		ProductTable.setModel(dtm);
		dtm.setColumnIdentifiers(headers);
		
		
		try {
			ArrayList<Product> ProductList = a1.ViewProduct();
			dtm.setRowCount(0);
			for (int i = 0; i< ProductList.size(); i++) {
				if(ProductList.get(i).getKeyboardOrMouse() == KorM.mouse) {
					Mouse m = (Mouse) ProductList.get(i);
					Object[] objs = {m.getBarcode(), m.getKeyboardOrMouse(),m.getBrand(),m.getMouseType(),m.getColour(),
							m.getConnectivity(),m.getQuantity(),"£"+m.getOriginalCost(),"£"+m.getRetailPrice(),
							m.getNumOfButtons()};	
					dtm.addRow(objs);
				}else {
					Keyboard k = (Keyboard) ProductList.get(i);
					Object[] objs = {k.getBarcode(), k.getKeyboardOrMouse(),k.getBrand(),k.getKeyboardType(),k.getColour(),
							k.getConnectivity(),k.getQuantity(),"£"+k.getOriginalCost(),"£"+k.getRetailPrice(),
							"",k.getKeyboardLayout()};
					dtm.addRow(objs);
				}
				
			}
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		

		
		JPanel Add_product = new JPanel();
		tabbedPane.addTab("Add Product", null, Add_product, null);
		
		Barcode = new JTextField();
		Barcode.setBounds(115, 12, 96, 19);
		
		Barcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( ((c < '0') || (c>'9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		Add_product.setLayout(null);
		Add_product.add(Barcode);
		Barcode.setColumns(10);
		
		lblNewLabel = new JLabel("Barcode : ");
		lblNewLabel.setBounds(4, 10, 80, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblNewLabel);
		
		Brand = new JTextField();
		Brand.setBounds(115, 41, 96, 19);
		Add_product.add(Brand);
		Brand.setColumns(10);
		
		Colour = new JTextField();
		Colour.setBounds(115, 70, 96, 19);
		Add_product.add(Colour);
		Colour.setColumns(10);
		
		Quantity = new JTextField();
		Quantity.setBounds(115, 138, 96, 19);
		Add_product.add(Quantity);
		Quantity.setColumns(10);
		Quantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( ((c < '0') || (c>'9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		String decimalNumPattern = "\\b\\d{1,5}\\.\\d{1,2}\\b";
		
		O_Cost = new JTextField();
		O_Cost.setBounds(115, 172, 96, 19);
		Add_product.add(O_Cost);
		O_Cost.setColumns(10);
		
		Connect = new JComboBox();
		Connect.setBounds(115, 101, 96, 27);
		Connect.addItem("");
		Connect.setModel(new DefaultComboBoxModel(Connectivity.values()));
		Add_product.add(Connect);
		
		R_Price = new JTextField();
		R_Price.setBounds(115, 206, 96, 19);
		Add_product.add(R_Price);
		R_Price.setColumns(10);
		
		K_Type  = new JComboBox();
		K_Type.setBounds(716, 34, 117, 33);
		K_Type.addItem("");
		K_Type.setModel(new DefaultComboBoxModel(KeyboardType.values()));
		Add_product.add(K_Type);
		
		K_Layout  = new JComboBox();
		K_Layout.setBounds(716, 87, 117, 27);
		K_Layout.addItem("");
		K_Layout.setModel(new DefaultComboBoxModel(KeyboardLayout.values()));
		Add_product.add(K_Layout);
		
		lblNewLabel_1 = new JLabel("Keyboard Product");
		lblNewLabel_1.setBounds(637, 6, 117, 27);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Add_product.add(lblNewLabel_1);
		
		add_K_Product = new JButton("Add Keyboard Product");
		add_K_Product.setBounds(576, 131, 208, 33);
		
		add_K_Product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = Brand.getText();
				String colour = Colour.getText();
				
				boolean R_Price_Matches = Pattern.matches(decimalNumPattern,R_Price.getText().toString());
				boolean O_Cost_Matches = Pattern.matches(decimalNumPattern,O_Cost.getText().toString());
			
				if(Barcode.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Barcode", "Error", JOptionPane.ERROR_MESSAGE);
				}else if ( barcodeValidation(Barcode.getText().toString()) == false){
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Barcode", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(brand.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Brand", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(colour.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Colour", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(Connect.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Connectivity Type", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(Quantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Quantity", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (QuantityValidation(Quantity.getText().toString()) == false){
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Quantity", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(O_Cost.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please Enter Original Cost", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(O_Cost_Matches == false){
					JOptionPane.showMessageDialog(null, "Original Cost Format is Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(R_Price.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please Enter Retail Price", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(R_Price_Matches == false){
					JOptionPane.showMessageDialog(null, "Retail Price Format is Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(K_Type.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Keyboard Type", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(K_Layout.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Keyboard Layout", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int Bcode = Integer.parseInt(Barcode.getText().toString());
						Connectivity connectivity = (Connectivity) Connect.getSelectedItem();
						int quantity = Integer.parseInt(Quantity.getText().toString());
						double o_cost = Double.parseDouble(O_Cost.getText().toString());
						double r_price = Double.parseDouble(R_Price.getText().toString());
						KeyboardType keyboardType =  (KeyboardType) K_Type.getSelectedItem();
						KeyboardLayout keyboardLayout =  (KeyboardLayout) K_Layout.getSelectedItem();
						a1.AddKeyboardProduct(Bcode, brand, colour, connectivity, quantity, 
								o_cost, r_price, keyboardType, keyboardLayout);
						ArrayList<Product> ProductList = a1.ViewProduct();
						dtm.setRowCount(0);
						for (int i = 0; i< ProductList.size(); i++) {
							if(ProductList.get(i).getKeyboardOrMouse() == KorM.mouse) {
								Mouse m = (Mouse) ProductList.get(i);
								Object[] objs = {m.getBarcode(), m.getKeyboardOrMouse(),m.getBrand(),m.getMouseType(),m.getColour(),
										m.getConnectivity(),m.getQuantity(),"£"+m.getOriginalCost(),"£"+m.getRetailPrice(),
										m.getNumOfButtons()};	
								dtm.addRow(objs);
							}else {
								Keyboard k = (Keyboard) ProductList.get(i);
								Object[] objs = {k.getBarcode(), k.getKeyboardOrMouse(),k.getBrand(),k.getKeyboardType(),k.getColour(),
										k.getConnectivity(),k.getQuantity(),"£"+k.getOriginalCost(),"£"+k.getRetailPrice(),
										"",k.getKeyboardLayout()};
								dtm.addRow(objs);
							}
							
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					clearFields();
				}
			}
		});
		Add_product.add(add_K_Product);
		
		Num_of_Buttons = new JTextField();
		Num_of_Buttons.setBounds(394, 104, 117, 21);
		Add_product.add(Num_of_Buttons);
		Num_of_Buttons.setColumns(10);
		
		Num_of_Buttons.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( ((c < '0') || (c>'9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		lblNewLabel_2 = new JLabel("Mouse Product");
		lblNewLabel_2.setBounds(333, 6, 117, 27);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Add_product.add(lblNewLabel_2);
		
		Add_M_Product = new JButton("Add Mouse Product");
		Add_M_Product.setBounds(315, 131, 156, 33);
		
		Add_M_Product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = Brand.getText();
				String colour = Colour.getText();
				
				boolean R_Price_Matches = Pattern.matches(decimalNumPattern,R_Price.getText().toString());
				boolean O_Cost_Matches = Pattern.matches(decimalNumPattern,O_Cost.getText().toString());
				
				if(Barcode.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Barcode", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (barcodeValidation(Barcode.getText().toString()) == false){
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Barcode", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(brand.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Brand", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(colour.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Colour", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(Connect.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Connectivity Type", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(Quantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter A Quantity", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (QuantityValidation(Quantity.getText().toString()) == false){
					JOptionPane.showMessageDialog(null, "Please Enter A Valid Quantity", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(O_Cost.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please Enter Original Cost", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(O_Cost_Matches == false){
					JOptionPane.showMessageDialog(null, "Original Cost Format Is Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(R_Price.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please Enter Retail Price", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(R_Price_Matches == false){
					JOptionPane.showMessageDialog(null, "Retail Price Format Is Incorrect", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(M_Type.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Select The Keyboard Type", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(Num_of_Buttons.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter The Number Of Buttons", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (numOfButtonsValidation(Num_of_Buttons.getText().toString()) == false){
					JOptionPane.showMessageDialog(null, "Please Enter The Number Of Buttons That Is Above Two", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						int Bcode = Integer.parseInt(Barcode.getText().toString());
						Connectivity connectivity = (Connectivity) Connect.getSelectedItem();
						int quantity = Integer.parseInt(Quantity.getText());
						double o_cost = Double.parseDouble(O_Cost.getText().toString());
						double r_price = Double.parseDouble(R_Price.getText().toString());
						MouseType mouseType =   (MouseType) M_Type.getSelectedItem();
						int num_of_buttons = Integer.parseInt(Num_of_Buttons.getText().toString());
						
						a1.AddMouseProduct(Bcode, brand, colour, connectivity, quantity,
								o_cost, r_price, mouseType, num_of_buttons);
						ArrayList<Product> ProductList = a1.ViewProduct();
						dtm.setRowCount(0);
						for (int i = 0; i< ProductList.size(); i++) {
							if(ProductList.get(i).getKeyboardOrMouse() == KorM.mouse) {
								Mouse m = (Mouse) ProductList.get(i);
								Object[] objs = {m.getBarcode(), m.getKeyboardOrMouse(),m.getBrand(),m.getMouseType(),m.getColour(),
										m.getConnectivity(),m.getQuantity(),"£"+m.getOriginalCost(),"£"+m.getRetailPrice(),
										m.getNumOfButtons()};	
								dtm.addRow(objs);
							}else {
								Keyboard k = (Keyboard) ProductList.get(i);
								Object[] objs = {k.getBarcode(), k.getKeyboardOrMouse(),k.getBrand(),k.getKeyboardType(),k.getColour(),
										k.getConnectivity(),k.getQuantity(),"£"+k.getOriginalCost(),"£"+k.getRetailPrice(),
										"",k.getKeyboardLayout()};
								dtm.addRow(objs);
							}	
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					clearFields();
				}
			}
		});
		Add_product.add(Add_M_Product);
		
		lblBrand = new JLabel("Brand : ");
		lblBrand.setBounds(4, 44, 80, 19);
		lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblBrand);
		
		lblColour = new JLabel("Colour : ");
		lblColour.setBounds(4, 75, 80, 19);
		lblColour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblColour);
		
		lblConnectivity = new JLabel("Connectivity : ");
		lblConnectivity.setBounds(4, 108, 101, 19);
		lblConnectivity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblConnectivity);
		
		lblQuantity = new JLabel("Quantity : ");
		lblQuantity.setBounds(4, 141, 80, 19);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblQuantity);
		
		lblRetailCost = new JLabel("Original Cost : ");
		lblRetailCost.setBounds(4, 170, 101, 21);
		lblRetailCost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblRetailCost);
		
		lblRetailPrice = new JLabel("Retail Price : ");
		lblRetailPrice.setBounds(4, 204, 101, 21);
		lblRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblRetailPrice);
		
		lblKeyboardType = new JLabel("Keyboard Type : ");
		lblKeyboardType.setBounds(563, 41, 125, 19);
		lblKeyboardType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblKeyboardType);
		
		lblKeyboardLayout = new JLabel("Keyboard Layout : ");
		lblKeyboardLayout.setBounds(549, 89, 139, 19);
		lblKeyboardLayout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblKeyboardLayout);
		
		lblNumberOfButtons = new JLabel("Number Of Buttons : ");
		lblNumberOfButtons.setBounds(240, 103, 156, 19);
		lblNumberOfButtons.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblNumberOfButtons);
		
		
		M_Type = new JComboBox();
		M_Type.setBounds(394, 42, 117, 27);
		M_Type.addItem("");
		M_Type.setModel(new DefaultComboBoxModel(MouseType.values()));
		Add_product.add(M_Type);
		
		lblMouseType = new JLabel("Mouse Type : ");
		lblMouseType.setBounds(259, 48, 125, 19);
		lblMouseType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Add_product.add(lblMouseType);
		
		
	}


	private void clearFields() {
		Barcode.setText("");
		Brand.setText("");
		Colour.setText("");
		Num_of_Buttons.setText("");
		R_Price.setText("");
		O_Cost.setText("");
		Quantity.setText("");
		Connect.setSelectedIndex(0);
		M_Type.setSelectedIndex(0);
		K_Type.setSelectedIndex(0);
		K_Layout.setSelectedIndex(0);
	
		
			
	}


	public static boolean barcodeValidation(String Barcode){
	    boolean barcodeValidated = false;
	    if(String.valueOf(Barcode).length() == 6){
	        Functions fn = new Functions();
	        ArrayList<Product> productArrayList = fn.readStockFile();
	        int lineCount = 0;
	        int Count = 0;
	        for(Product item : productArrayList){
	            lineCount += 1;
	            if(Integer.parseInt(Barcode) != item.getBarcode()){
	                Count += 1;
	            }
	        }
	        if (lineCount == Count) {
	            barcodeValidated = true;
	        }
	    }
	    return barcodeValidated;
	}


	public static boolean QuantityValidation(String Quantity) {
	    boolean QuantityValidated = false;
	    
	    if (Integer.parseInt(Quantity) > 0 && Integer.parseInt(Quantity) < 10000 ) {
	        QuantityValidated = true;
	    }
	    return QuantityValidated;
	}
	
	public static boolean numOfButtonsValidation(String numOfButtons) {
	    boolean numOfButtonsValidated = false;
	    if (Integer.parseInt(numOfButtons) >= 2) {
	        numOfButtonsValidated = true;
	        }
	
	    return  numOfButtonsValidated;
	}
}
