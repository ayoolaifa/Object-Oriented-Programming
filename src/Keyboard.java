public class Keyboard extends Product{
    private KeyboardLayout KeyboardLayout;
    private KeyboardType KeyboardType;

    public Keyboard(int barcode, KorM KeyboardOrMouse, KeyboardType KeyboardType, String brand, String colour,
                    Connectivity connectivity, int quantity, double originalCost, double retailPrice, KeyboardLayout KeyboardLayout) {
        super(barcode, KeyboardOrMouse, brand, colour, connectivity, quantity, originalCost, retailPrice);
        this.KeyboardLayout = KeyboardLayout;
        this.KeyboardType = KeyboardType;
    }

    public KeyboardType getKeyboardType() {
        return KeyboardType;
    }

    public KeyboardLayout getKeyboardLayout() {
        return KeyboardLayout;
    }

    @Override
    public String toString() {
        return getBarcode() + ", " + getKeyboardOrMouse() + ", " + getKeyboardType() + ", " + getBrand()
                + ", " + getColour() + ", " + getConnectivity() + ", " + getQuantity() + ", " + getOriginalCost() + ", " + getRetailPrice()
                + ", " + getKeyboardLayout();
    }
}
