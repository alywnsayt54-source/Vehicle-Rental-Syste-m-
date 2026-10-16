package vehicless;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Individual extends Customer {
    private String drivingLicenseNumber;
    private LocalDate birthDate;

    public Individual(String name, String address, String phone,
                      String drivingLicenseNumber, LocalDate birthDate) {
        super(name, address, phone);
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.birthDate = birthDate;
    }

    @Override
    public double getDiscountRate() {
        // فكرة إبداعية: خصم 5% للأفراد فوق 50 سنة
        if (ChronoUnit.YEARS.between(birthDate, LocalDate.now()) > 50) {
            return 5.0;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Type: Individual, License: " + drivingLicenseNumber;
    }
}