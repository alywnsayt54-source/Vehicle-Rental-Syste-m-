package vehicless;

public class Truck extends Vehicless {
    private double loadCapacity; // سعة الحمولة بالكيلوغرام
    private boolean hasCooler; // مبردة

    public Truck(String plateNumber, String brand, String model, double dailyRentalPrice,
                 double loadCapacity, boolean hasCooler) {
        super(plateNumber, brand, model, dailyRentalPrice);
        this.loadCapacity = loadCapacity;
        this.hasCooler = hasCooler;
    }

    @Override
    public double calculateAdditionalCost() {
        // الشاحنات المبردة تزيد 5%
        if (hasCooler) {
            return dailyRentalPrice * 0.05;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Type: Truck, Load: " + loadCapacity + "kg, Cooler: " + hasCooler;
    }
}