import java.util.Comparator;

public class RetailPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        if (p1.getRetailPrice() < p2.getRetailPrice())
            return -1;
        if (p1.getRetailPrice() > p2.getRetailPrice())
            return 1;
        else return 0;

    }
}
