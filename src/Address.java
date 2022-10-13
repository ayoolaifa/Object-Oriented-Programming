public class Address {
    private int HouseNumber;
    private String PostCode;
    private String City;

    public Address(int HouseNumber, String PostCode, String City){
        this.HouseNumber = HouseNumber;
        this.PostCode = PostCode;
        this.City = City;
    }

    public int getHouseNumber() {return HouseNumber;}

    public String getPostCode() {return PostCode;}

    public String getCity() {return City;}

    @Override
    public String toString(){
        return getHouseNumber() + ", " + getPostCode() + "," + getCity();
    }
}
