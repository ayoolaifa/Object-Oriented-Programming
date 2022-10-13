import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Functions {
    public ArrayList<Product> readStockFile(){
     ArrayList<Product> productList = new ArrayList<>();
     Scanner fileScanner = null;
     try {
         File inputFile = new File("Stock.txt");
         fileScanner = new Scanner(inputFile);
         while (fileScanner.hasNextLine()){
             String line = fileScanner.nextLine();
             String[] products = line.split(",");
             if(products[1].trim().equals(KorM.keyboard.toString())){
                 Keyboard k1 = new Keyboard(Integer.valueOf(products[0].trim()), KorM.keyboard, KeyboardType.valueOf(products[2].trim()),
                         products[3].trim(), products[4].trim(), Connectivity.valueOf(products[5].trim()), Integer.valueOf(products[6].trim()),
                         Double.parseDouble(products[7].trim()),  Double.parseDouble(products[8].trim()), KeyboardLayout.valueOf(products[9].trim()));
                 productList.add(k1);
             }else if (products[1].trim().equals(KorM.mouse.toString())){
                 Mouse m1 = new Mouse(Integer.valueOf(products[0].trim()), KorM.mouse, MouseType.valueOf(products[2].trim()),
                         products[3].trim(), products[4].trim(), Connectivity.valueOf(products[5].trim()), Integer.valueOf(products[6].trim()),
                         Double.parseDouble(products[7].trim()),  Double.parseDouble(products[8].trim()), Integer.valueOf(products[9].trim()));
                 productList.add(m1);
             }
         }
     }
     catch(Exception e) {
         System.err.println(e.getMessage());
         e.printStackTrace();
     } finally {
         if(fileScanner != null) {
             fileScanner.close();
         }
     }
     RetailPriceComparator retailPriceComparator = new RetailPriceComparator();
     Collections.sort(productList, retailPriceComparator);
     return(productList);
    }

    public ArrayList<User> readUserFile() {
     ArrayList<User> UserList = new ArrayList<>();
     ArrayList<Product> ShoppingBasket = new ArrayList<>();
     Scanner fileScanner = null;

     try {
         File inputFile = new File("UserAccount.txt");
         fileScanner = new Scanner(inputFile);
         while (fileScanner.hasNextLine()) {
             String line = fileScanner.nextLine();
             String[] User = line.split(",");
             Address addr = new Address(Integer.valueOf(User[3].trim()), User[4].trim(), User[5]);
             if (User[6].trim().equals("admin")) {
                 Admin a1 = new Admin(Integer.valueOf(User[0].trim()), User[1].trim(), User[2].trim(), addr, User[6].trim());
                 UserList.add(a1);
             } else if (User[6].trim().equals("customer")) {
                 Customer c1 = new Customer(Integer.valueOf(User[0].trim()), User[1].trim(), User[2].trim(), addr, User[6].trim(), ShoppingBasket, 0.0);
                 UserList.add(c1);
             }

         }
     } catch (Exception e) {
         System.err.println(e.getMessage());
         e.printStackTrace();
     } finally {
         if (fileScanner != null) {
             fileScanner.close();
         }
     }
     return (UserList);
    }

    public void writeToStockFile(Product item){
        FileWriter outputFile = null;
        try{
            outputFile = new FileWriter("Stock.txt",true);
            outputFile.write(item.toString()+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                outputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    public void rewriteStockFile(ArrayList<Product> productList){
        FileWriter outputFile = null;
        try{
            outputFile = new FileWriter("Stock.txt",false);
            for (Product p : productList) {
            	outputFile.write(p.toString()+ "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                outputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
