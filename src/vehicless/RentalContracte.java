package vehicless;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalContracte {
    private int contractId;
    private Vehicless vehicle;
    private Customer customer;
    private LocalDate startDate;
    private int rentalDays;
    private LocalDate actualReturnDate;
    private boolean isActive;
    private double finalCost;
    private static int nextId = 1;

    public RentalContracte(Vehicless vehicle, Customer customer, LocalDate startDate, int rentalDays) {
        this.contractId = nextId++;
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.rentalDays = rentalDays;
        this.isActive = true;
        this.actualReturnDate = null;
    }

    public int getContractId() { return contractId; }
    public Vehicless getVehicle() { return vehicle; }
    public Customer getCustomer() { return customer; }
    public boolean isActive() { return isActive; }
    public LocalDate getStartDate() { return startDate; }
    public int getRentalDays() { return rentalDays; }
    public double getFinalCost() { return finalCost; }

    public void returnVehicle(LocalDate returnDate) {
        this.actualReturnDate = returnDate;
        this.isActive = false;
        this.vehicle.setAvailable(true);
        calculateFinalCost();
    }

    public long getLateDays() {
        if (actualReturnDate == null) return 0;
        return Math.max(0, ChronoUnit.DAYS.between(startDate.plusDays(rentalDays), actualReturnDate));
    }

    private void calculateFinalCost() {
        long daysUsed = ChronoUnit.DAYS.between(startDate, actualReturnDate);
        long lateDays = Math.max(0, daysUsed - rentalDays);
        
        double baseCost = rentalDays * vehicle.getDailyRentalPrice();
        double additionalCost = vehicle.calculateAdditionalCost() * rentalDays;
        double latePenalty = lateDays * (vehicle.getDailyRentalPrice() * 0.10);
        double totalBeforeDiscount = baseCost + additionalCost + latePenalty;
        double discount = totalBeforeDiscount * (customer.getDiscountRate() / 100.0);
        this.finalCost = totalBeforeDiscount - discount;
    }

    @Override
    public String toString() {
        return "Contract ID: " + contractId + ", Vehicle: " + vehicle.getPlateNumber() +
               ", Customer: " + customer.getName() + ", Active: " + isActive;
    }
}