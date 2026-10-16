package vehicless;

public class Car extends Vehicless {
    private int numSeats;
    private boolean hasAC;
    private String fuelType; // بنزين/ديزل/كهرباء

    public Car(String plateNumber, String brand, String model, double dailyRentalPrice,
               int numSeats, boolean hasAC, String fuelType) {
        super(plateNumber, brand, model, dailyRentalPrice);
        this.numSeats = numSeats;
        this.hasAC = hasAC;
        this.fuelType = fuelType;
    }

    @Override
    public double calculateAdditionalCost() {
        double extra = 0;
        // السيارات الكهربائية تزيد 5%
        if (fuelType.equalsIgnoreCase("كهرباء")) {
            extra += dailyRentalPrice * 0.05;
        }
        // فكرة إبداعية: سيارات الموديل الحديث (2022+) تزيد 8%
        try {
            int modelYear = Integer.parseInt(model);
            if (modelYear > 2022) extra += dailyRentalPrice * 0.08;
        } catch(NumberFormatException e) {}
        return extra;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Type: Car, Seats: " + numSeats + ", Fuel: " + fuelType;
    }
}