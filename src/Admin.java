import java.io.*;
import java.util.*;

public class Admin extends User {
    public Admin(int UserID, String Username, String Name, Address Addr, String Role) {

        super(UserID, Username, Name, Addr, Role);
    }

    public void AddKeyboardProduct(int barcode, String brand, String colour, Connectivity connectivity,
                                   int quantity, double originalCost, double retailPrice, KeyboardType KeyboardType,
                                   KeyboardLayout KeyboardLayout) throws IOException {

        Keyboard newProduct = new Keyboard(barcode, KorM.keyboard, KeyboardType, brand, colour,
                connectivity, quantity, originalCost, retailPrice, KeyboardLayout);

        Functions fn = new Functions();
        fn.writeToStockFile(newProduct);
    }

    public void AddMouseProduct(int barcode, String brand, String colour, Connectivity connectivity,
                                int quantity, double originalCost, double retailPrice, MouseType MouseType,
                                int numOfButtons) throws IOException {

        Mouse newProduct = new Mouse(barcode, KorM.mouse,MouseType, brand, colour,
                connectivity, quantity, originalCost, retailPrice, numOfButtons);
        Functions fn = new Functions();
        fn.writeToStockFile(newProduct);

    }
}
