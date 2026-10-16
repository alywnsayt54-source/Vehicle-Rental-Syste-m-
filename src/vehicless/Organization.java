package vehicless;

public class Organization extends Customer {
    private String commercialRegister;
    private double discountRate;

    public Organization(String name, String address, String phone,
                        String commercialRegister, double discountRate) {
        super(name, address, phone);
        this.commercialRegister = commercialRegister;
        this.discountRate = discountRate;
    }

    @Override
    public double getDiscountRate() {
        return discountRate;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Type: Organization, Discount: " + discountRate + "%";
    }
}