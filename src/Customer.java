import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Customer extends User{
    ArrayList<Product> ShoppingBasket = new ArrayList<>();
    double total;
    public Customer(int UserID, String Username, String Name, Address Addr, String Role, ArrayList<Product> ShoppingBasket, double total) {
        super(UserID, Username, Name, Addr, Role);
        this.ShoppingBasket = ShoppingBasket;
        this.total = total;
    }

    public double getTotal() {
		return total;
	}
    
    public double setTotal() {
		this.total = 0;
		for (Product p : this.ShoppingBasket) {
			total += p.getRetailPrice();
		}
		return total;
	}
    
    public ArrayList<Product> getShoppingBasket() {
        return ShoppingBasket;
    }

    public boolean UpdateShoppingBasket(Product p){
    	boolean EnoughQuantity = p.updateMinusQuantity(1);
    	if (EnoughQuantity == true) {
    		this.ShoppingBasket.add(p);
    		return true;
    	}else {
    		return false;
    	}     
    }
    
    public void ClearShoppingBasket(boolean Payment) {
    	ArrayList<Product> ShoppingBasket = this.getShoppingBasket();
    	if(Payment == false) {
	    	for (Product p : ShoppingBasket) {
	    		p.updateAddQuantity(1);
	    	}
    	}
    	this.ShoppingBasket.removeAll(ShoppingBasket);	
    }
}
