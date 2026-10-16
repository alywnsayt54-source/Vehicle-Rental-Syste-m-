package vehicless;

public class Motorcycle extends Vehicless {
    private double engineCapacity; // سعة المحرك بالسي سي
    private boolean hasSidecar; // وجود عربة جانبية

    public Motorcycle(String plateNumber, String brand, String model, double dailyRentalPrice,
                      double engineCapacity, boolean hasSidecar) {
        super(plateNumber, brand, model, dailyRentalPrice);
        this.engineCapacity = engineCapacity;
        this.hasSidecar = hasSidecar;
    }

    @Override
    public double calculateAdditionalCost() {
        // الدراجات ذات العربة الجانبية تزيد 2%
        if (hasSidecar) {
            return dailyRentalPrice * 0.02;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Type: Motorcycle, Engine: " + engineCapacity + "cc, Sidecar: " + hasSidecar;
    }
}