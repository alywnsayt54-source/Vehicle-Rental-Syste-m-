package vehicless;

import java.util.*;
import java.util.stream.Collectors;

public class ReportGenerator {
    private List<Vehicless> vehicles;
    private List<Customer> customers;
    private List<RentalContracte> contracts;

    public ReportGenerator(List<Vehicless> vehicles, List<Customer> customers, List<RentalContracte> contracts) {
        this.vehicles = vehicles;
        this.customers = customers;
        this.contracts = contracts;
    }

    // تقرير VIP العملاء (أكثر من 5 عقود منتهية)
    public void generateVIPReport() {
        Map<Customer, Long> contractCount = contracts.stream()
            .filter(c -> !c.isActive())
            .collect(Collectors.groupingBy(RentalContracte::getCustomer, Collectors.counting()));
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("VIP CUSTOMERS REPORT (5+ completed contracts)");
        System.out.println("=".repeat(50));
        
        boolean found = false;
        for (Map.Entry<Customer, Long> entry : contractCount.entrySet()) {
            if (entry.getValue() >= 5) {
                System.out.println(entry.getKey().getName() + " - " + entry.getValue() + " contracts");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No VIP customers found.");
        }
    }

    // تقرير أكثر المركبات تأجيراً
    public void generateMostRentedReport() {
        Map<Vehicless, Long> rentalCount = contracts.stream()
            .filter(c -> !c.isActive())
            .collect(Collectors.groupingBy(RentalContracte::getVehicle, Collectors.counting()));
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MOST RENTED VEHICLES REPORT");
        System.out.println("=".repeat(50));
        
        rentalCount.entrySet().stream()
            .sorted(Map.Entry.<Vehicless, Long>comparingByValue().reversed())
            .limit(5)
            .forEach(e -> System.out.println(e.getKey().getPlateNumber() + " (" + e.getKey().getBrand() + " " + 
                           e.getKey().getModel() + ") - " + e.getValue() + " times"));
    }

    // تقرير إيرادات الشركة
    public void generateRevenueReport() {
        double totalRevenue = contracts.stream()
            .filter(c -> !c.isActive())
            .mapToDouble(RentalContracte::getFinalCost)
            .sum();
        
        double averageRevenue = contracts.stream()
            .filter(c -> !c.isActive())
            .mapToDouble(RentalContracte::getFinalCost)
            .average()
            .orElse(0);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPANY REVENUE REPORT");
        System.out.println("=".repeat(50));
        System.out.println("Total Revenue:           " + totalRevenue + " SYP");
        System.out.println("Average Revenue:         " + String.format("%.2f", averageRevenue) + " SYP");
        System.out.println("Total Completed Contracts: " + contracts.stream().filter(c -> !c.isActive()).count());
    }
}