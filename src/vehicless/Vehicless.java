package vehicless;

public abstract class Vehicless {
    protected String plateNumber;
    protected String brand;
    protected String model;
    protected double dailyRentalPrice;
    protected boolean isAvailable;

    public Vehicless(String plateNumber, String brand, String model, double dailyRentalPrice) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.dailyRentalPrice = dailyRentalPrice;
        this.isAvailable = true;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getDailyRentalPrice() { return dailyRentalPrice; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public abstract double calculateAdditionalCost();

    @Override
    public String toString() {
        return "Plate: " + plateNumber + ", " + brand + " " + model + 
               ", Price: " + dailyRentalPrice + "/day, Available: " + isAvailable;
    }
}