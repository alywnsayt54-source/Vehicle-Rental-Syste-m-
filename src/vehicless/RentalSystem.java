package vehicless;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RentalSystem {
    private List<Vehicless> vehicles;// الواجهات 
    private List<Customer> customers;
    private List<RentalContracte> contracts;  // تغيير: RentalContracte
    private List<Invoice> invoices;  // I كبيرة, voic - وليس Invoce
    private ReportGenerator reportGenerator;

    public RentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        contracts = new ArrayList<>();  // تغيير: RentalContracte
        invoices = new ArrayList<>();
        reportGenerator = new ReportGenerator(vehicles, customers, contracts);
    }

    // ==================== إدارة المركبات ====================
    public void addVehicle(Vehicless v) { 
        vehicles.add(v); 
        System.out.println("Vehicle added: " + v.getPlateNumber());
    }
    
    public void removeVehicle(String plateNumber) {
        boolean removed = vehicles.removeIf(v -> v.getPlateNumber().equals(plateNumber));
        System.out.println(removed ? "Vehicle removed." : "Vehicle not found.");
    }
    
    public Vehicless findVehicleByPlate(String plateNumber) {
        return vehicles.stream().filter(v -> v.getPlateNumber().equals(plateNumber)).findFirst().orElse(null);
    }
    
    public void searchVehicleByPlate(String plateNumber) {
        Vehicless v = findVehicleByPlate(plateNumber);
        System.out.println(v != null ? v : "Vehicle not found.");
    }
    
    public void displayAllVehicles() { 
        if (vehicles.isEmpty()) System.out.println("No vehicles available.");
        else vehicles.forEach(System.out::println); 
    }
    
    public void displayAvailableVehicles() {
        List<Vehicless> available = vehicles.stream().filter(Vehicless::isAvailable).collect(Collectors.toList());
        if (available.isEmpty()) System.out.println("No available vehicles.");
        else available.forEach(System.out::println);
    }

    // ==================== إدارة العملاء ====================
    public void addCustomer(Customer c) { 
        customers.add(c); 
        System.out.println("Customer added: " + c.getName());
    }
    
    public Customer findCustomerById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    
    public void searchCustomerByName(String name) {
        List<Customer> result = customers.stream()
            .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
        if (result.isEmpty()) System.out.println("No customers found.");
        else result.forEach(System.out::println);
    }
    
    public void displayAllCustomers() { 
        if (customers.isEmpty()) System.out.println("No customers available.");
        else customers.forEach(System.out::println); 
    }
    
    public void updateCustomer(int id, String name, String address, String phone) {
        Customer c = findCustomerById(id);
        if (c != null) {
            c.setName(name);
            c.setAddress(address);
            c.setPhone(phone);
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // ==================== عقود الإيجار ====================
    public void rentVehicle(String plateNumber, int customerId, int days) {
        Vehicless v = findVehicleByPlate(plateNumber);
        Customer c = findCustomerById(customerId);
        
        if (v == null) {
            System.out.println("Vehicle not found.");
        } else if (c == null) {
            System.out.println("Customer not found.");
        } else if (!v.isAvailable()) {
            System.out.println("Vehicle is not available.");
        } else {
            v.setAvailable(false);
            RentalContracte contract = new RentalContracte(v, c, LocalDate.now(), days);  // تغيير: RentalContracte
            contracts.add(contract);
            System.out.println("Contract created successfully! Contract ID: " + contract.getContractId());
        }
    }

    public void returnVehicle(int contractId) {// استقبال رقم العقد 
        RentalContracte contract = contracts.stream()  // تغيير: RentalContracte
            .filter(c -> c.getContractId() == contractId && c.isActive())
            .findFirst().orElse(null);
        
        if (contract != null) {
            contract.returnVehicle(LocalDate.now());
            Invoice invoice = new Invoice(contract);
            invoices.add(invoice);
            invoice.printInvoice();
            System.out.println("Vehicle returned successfully!");
        } else {
            System.out.println("Active contract not found.");
        }
    }

    // ==================== عرض العقود ====================
    public void displayActiveContracts() {
        List<RentalContracte> active = contracts.stream().filter(RentalContracte::isActive).collect(Collectors.toList());  // تغيير: RentalContracte
        if (active.isEmpty()) System.out.println("No active contracts.");
        else active.forEach(System.out::println);
    }

    public void displayExpiredContracts() {
        List<RentalContracte> expired = contracts.stream().filter(c -> !c.isActive()).collect(Collectors.toList());  // تغيير: RentalContracte
        if (expired.isEmpty()) System.out.println("No expired contracts.");
        else expired.forEach(System.out::println);
    }

    public void displayContractsForCustomer(int customerId) {
        List<RentalContracte> result = contracts.stream()  // تغيير: RentalContracte
            .filter(c -> c.getCustomer().getId() == customerId)
            .collect(Collectors.toList());
        if (result.isEmpty()) System.out.println("No contracts for this customer.");
        else result.forEach(System.out::println);
    }

    public void displayContractsByCustomerName(String name) {
        List<RentalContracte> result = contracts.stream()  // تغيير: RentalContracte
            .filter(c -> c.getCustomer().getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
        if (result.isEmpty()) System.out.println("No contracts found.");
        else result.forEach(System.out::println);
    }

    public void displayContractsByCustomerType(String type) {
        List<RentalContracte> result = contracts.stream()  // تغيير: RentalContracte
            .filter(c -> (type.equalsIgnoreCase("Individual") && c.getCustomer() instanceof Individual) ||
                        (type.equalsIgnoreCase("Organization") && c.getCustomer() instanceof Organization))
            .collect(Collectors.toList());
        if (result.isEmpty()) System.out.println("No contracts found for this type.");
        else result.forEach(System.out::println);
    }

    // ==================== عرض المركبات المؤجرة ====================
    public void displayRentedVehicles() {
        List<Vehicless> rented = contracts.stream()
            .filter(RentalContracte::isActive)  // تغيير: RentalContracte
            .map(RentalContracte::getVehicle)   // تغيير: RentalContracte
            .collect(Collectors.toList());
        if (rented.isEmpty()) System.out.println("No rented vehicles.");
        else rented.forEach(System.out::println);
    }

    public void displayRentedVehiclesByType(String type) {
        List<Vehicless> rented = contracts.stream()
            .filter(RentalContracte::isActive)  // تغيير: RentalContracte
            .map(RentalContracte::getVehicle)   // تغيير: RentalContracte
            .filter(v -> type.equalsIgnoreCase("motorcycle") && v instanceof Motorcycle)
            .collect(Collectors.toList());
        
        if (rented.isEmpty()) System.out.println("No rented " + type + "s found.");
        else rented.forEach(System.out::println);
    }

    public void displayLateReturnedVehicles() {
        List<RentalContracte> late = contracts.stream()  // تغيير: RentalContracte
            .filter(c -> !c.isActive() && c.getLateDays() > 0)
            .collect(Collectors.toList());
        
        if (late.isEmpty()) System.out.println("No vehicles with late returns.");
        else late.forEach(c -> System.out.println(c.getVehicle().getPlateNumber() + " - Late: " + c.getLateDays() + " days"));
    }

    public void displayCustomersWhoRentedVehicle(String plateNumber) {
        List<Customer> customersList = contracts.stream()
            .filter(c -> c.getVehicle().getPlateNumber().equalsIgnoreCase(plateNumber))
            .map(RentalContracte::getCustomer)  // تغيير: RentalContracte
            .distinct()
            .collect(Collectors.toList());
        
        if (customersList.isEmpty()) System.out.println("No customers rented this vehicle.");
        else customersList.forEach(System.out::println);
    }

    // ==================== التقارير ====================
    public void getVIPCustomers() { reportGenerator.generateVIPReport(); }
    public void getMostRentedVehicles() { reportGenerator.generateMostRentedReport(); }// تستدعي اكثر 5 اتاجرت 
    public void getTotalRevenue() { reportGenerator.generateRevenueReport(); }
    
    public List<Vehicless> getVehicles() { return vehicles; }
    public List<Customer> getCustomers() { return customers; }
    public List<RentalContracte> getContracts() { return contracts; }  // تغيير: RentalContracte
}