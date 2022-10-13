import javax.swing.JOptionPane;

public abstract class Product {
    private int barcode;
    private KorM KeyboardOrMouse;
    private String brand;
    private String colour;
    private Connectivity connectivity;
    private int quantity;
    private double originalCost;
    private double retailPrice;


    public Product(int barcode, KorM KeyboardOrMouse, String brand, String colour,
                   Connectivity connectivity, int quantity, double originalCost, double retailPrice){
        this.KeyboardOrMouse = KeyboardOrMouse;
        this.barcode = barcode;
        this.brand = brand;
        this.colour = colour;
        this.connectivity = connectivity;
        this.quantity = quantity;
        this.originalCost = originalCost;
        this.retailPrice = retailPrice;

    }

    public int getBarcode() {return barcode;}

    public String getBrand() {return brand;}

    public String getColour() {return colour;}

    public Connectivity getConnectivity() {return connectivity;}

    public int getQuantity() {return quantity;}

    public double getOriginalCost() {return originalCost;}

    public double getRetailPrice() {return retailPrice;}

    public KorM getKeyboardOrMouse() {return KeyboardOrMouse;}

    public boolean updateMinusQuantity(int itemsRemoved) {
    	if (this.quantity > 0) {
    		this.quantity -= itemsRemoved;
    		return true;
    	}else {
    		JOptionPane.showMessageDialog(null, "No More Of This Product", "Error", JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    }
    
    public abstract String toString();
    public void updateAddQuantity(int itemsAdded) {
    	this.quantity += itemsAdded;
    }
}
