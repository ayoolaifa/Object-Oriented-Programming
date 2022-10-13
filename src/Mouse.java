public class Mouse extends Product {
    private MouseType MouseType;
    private int numOfButtons;

    public Mouse(int barcode, KorM KeyboardOrMouse, MouseType MouseType, String brand, String colour,
                 Connectivity connectivity, int quantity, double originalCost, double retailPrice, int numOfButtons) {
        super(barcode, KeyboardOrMouse, brand, colour, connectivity, quantity, originalCost, retailPrice);
        this.MouseType = MouseType;
        this.numOfButtons = numOfButtons;
    }


    public MouseType getMouseType() {
        return MouseType;
    }

    public int getNumOfButtons() {
        return numOfButtons;
    }

    @Override
    public String toString() {
        return getBarcode() + ", " + getKeyboardOrMouse() + ", " + getMouseType() + ", " + getBrand() + ", " +
                getColour() + ", " + getConnectivity() + ", " + getQuantity() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", " +
                getNumOfButtons();
    }
}
