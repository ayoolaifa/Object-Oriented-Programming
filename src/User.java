import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class User {
    private int UserID;
    private String Role;
    private String Username;
    private String Name;
    private Address Addr;

    public User(int UserID, String Username, String Name, Address Addr, String Role){
    	this.Role = Role;
        this.UserID = UserID;
        this.Username = Username;
        this.Name = Name;
        this.Addr = Addr;
    }
    
    public String getRole() {
    	return this.Role;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUsername() {
        return Username;
    }

    public String getName() {
        return Name;
    }

    public Address getAddr() {
        return Addr;
    }

    public String toString(){
        return getUserID() + " " + getUsername() + " " + getName() + " " + getAddr() + " "  + getRole() ;
    }
    
    public ArrayList<Product> ViewProduct() throws FileNotFoundException {
        Functions fn = new Functions();
        ArrayList<Product> productList = fn.readStockFile();
        RetailPriceComparator retailPriceComparator = new RetailPriceComparator();        
        Collections.sort(productList, retailPriceComparator);
        return productList;
    }
}
