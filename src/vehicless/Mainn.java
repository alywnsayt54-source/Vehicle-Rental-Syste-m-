package vehicless;

import java.time.LocalDate;
import java.util.Scanner;

public class Mainn {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();
        initData(system);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("         VEHICLE RENTAL MANAGEMENT SYSTEM");
            System.out.println("=".repeat(60));
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                   VEHICLES MENU                      |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|  1. Add Vehicle         2. All Vehicles             |");
            System.out.println("|  3. Search Vehicle      4. Remove Vehicle           |");
            System.out.println("|  5. Available Vehicles                              |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                   CUSTOMERS MENU                     |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|  6. Add Customer        7. All Customers            |");
            System.out.println("|  8. Search Customer     9. Update Customer          |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                     RENTAL MENU                      |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("| 10. Rent Vehicle      11. Return Vehicle            |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                    CONTRACTS MENU                    |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("| 12. Active Contracts   13. Expired Contracts        |");
            System.out.println("| 14. Contracts by Customer ID                        |");
            System.out.println("| 15. Contracts by Customer Name                      |");
            System.out.println("| 16. Contracts by Customer Type                      |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                   RENTED VEHICLES                    |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("| 17. All Rented Vehicles   18. Rented Motorcycles    |");
            System.out.println("| 19. Late Return Vehicles                            |");
            System.out.println("| 20. Customers by Vehicle                            |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("|                     REPORTS MENU                     |");
            System.out.println("+------------------------------------------------------+");
            System.out.println("| 21. VIP Customers Report                            |");
            System.out.println("| 22. Most Rented Vehicles Report                     |");
            System.out.println("| 23. Revenue Report                                  |");
            System.out.println("| 24. Exit                                            |");
            System.out.println("+------------------------------------------------------+");
            System.out.print("\nEnter your choice (1-24): ");
            
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addVehicleMenu(system, scanner);
                    break;
                case 2: 
                    system.displayAllVehicles(); 
                    break;
                case 3: 
                    System.out.print("Enter plate number: ");
                    system.searchVehicleByPlate(scanner.nextLine()); 
                    break;
                case 4: 
                    System.out.print("Enter plate number: ");
                    system.removeVehicle(scanner.nextLine()); 
                    break;
                case 5: 
                    system.displayAvailableVehicles(); 
                    break;
                case 6:
                    addCustomerMenu(system, scanner);
                    break;
                case 7: 
                    system.displayAllCustomers(); 
                    break;
                case 8: 
                    System.out.print("Enter customer name: ");
                    system.searchCustomerByName(scanner.nextLine()); 
                    break;
                case 9:
                    System.out.print("Enter Customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New Name: ");
                    String name = scanner.nextLine();
                    System.out.print("New Address: ");
                    String address = scanner.nextLine();
                    System.out.print("New Phone: ");
                    String phone = scanner.nextLine();
                    system.updateCustomer(id, name, address, phone);
                    break;
                case 10:
                    System.out.print("Enter plate number: ");
                    String plate = scanner.nextLine();
                    System.out.print("Enter customer ID: ");
                    int custId = scanner.nextInt();
                    System.out.print("Enter rental days: ");
                    int days = scanner.nextInt();
                    system.rentVehicle(plate, custId, days);
                    break;
                case 11:
                    System.out.print("Enter contract ID: ");
                    system.returnVehicle(scanner.nextInt());
                    break;
                case 12: 
                    system.displayActiveContracts(); 
                    break;
                case 13: 
                    system.displayExpiredContracts(); 
                    break;
                case 14:
                    System.out.print("Enter customer ID: ");
                    system.displayContractsForCustomer(scanner.nextInt());
                    break;
                case 15:
                    System.out.print("Enter customer name: ");
                    system.displayContractsByCustomerName(scanner.nextLine());
                    break;
                case 16:
                    System.out.print("Enter type (Individual/Organization): ");
                    system.displayContractsByCustomerType(scanner.nextLine());
                    break;
                case 17: 
                    system.displayRentedVehicles(); 
                    break;
                case 18: 
                    system.displayRentedVehiclesByType("motorcycle"); 
                    break;
                case 19: 
                    system.displayLateReturnedVehicles(); 
                    break;
                case 20:
                    System.out.print("Enter vehicle plate: ");
                    system.displayCustomersWhoRentedVehicle(scanner.nextLine());
                    break;
                case 21: 
                    system.getVIPCustomers(); 
                    break;
                case 22: 
                    system.getMostRentedVehicles(); 
                    break;
                case 23: 
                    system.getTotalRevenue(); 
                    break;
                case 24:
                    System.out.println("\nThank you for using Vehicle Rental System!");
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default: 
                    System.out.println("Invalid choice. Please enter 1-24.");
            }
        }
    }

    private static void initData(RentalSystem system) {
        System.out.println("\nLoading initial data...");
        
        // Add Vehicles
        system.addVehicle(new Car("123ABC", "Toyota", "Camry", 50, 5, true, "Petrol"));
        system.addVehicle(new Car("456DEF", "Tesla", "2023", 100, 5, true, "Electric"));
        system.addVehicle(new Motorcycle("789GHI", "Honda", "CBR", 30, 250, false));
        system.addVehicle(new Motorcycle("111JKL", "Harley", "Davidson", 60, 1200, true));
        system.addVehicle(new Truck("222MNO", "Volvo", "FH16", 150, 5000, true));
        system.addVehicle(new Truck("333PQR", "Mercedes", "Actros", 120, 4000, false));
        
        // Add Customers
        system.addCustomer(new Individual("Ahmed Ali", "Damascus", "0991234567", "DL123", LocalDate.of(1985, 5, 15)));
        system.addCustomer(new Individual("Sara Khaled", "Aleppo", "0987654321", "DL456", LocalDate.of(1970, 3, 20)));
        system.addCustomer(new Organization("Tech Solutions", "Damascus", "0111234567", "CR101", 15.0));
        system.addCustomer(new Organization("Construction Co", "Homs", "0117654321", "CR202", 10.0));
        
        System.out.println("Initial data loaded successfully!");
    }
    
    private static void addVehicleMenu(RentalSystem system, Scanner scanner) {
        System.out.print("Enter plate number: ");
        String plate = scanner.nextLine();
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter daily price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter type (Car/Motorcycle/Truck): ");
        String type = scanner.nextLine();
        
        if (type.equalsIgnoreCase("Car")) {
            System.out.print("Enter number of seats: ");
            int seats = scanner.nextInt();
            System.out.print("Has AC? (true/false): ");
            boolean ac = scanner.nextBoolean();
            scanner.nextLine();
            System.out.print("Enter fuel type (Petrol/Diesel/Electric): ");
            String fuel = scanner.nextLine();
            system.addVehicle(new Car(plate, brand, model, price, seats, ac, fuel));
            
        } else if (type.equalsIgnoreCase("Motorcycle")) {
            System.out.print("Enter engine capacity (cc): ");
            double engine = scanner.nextDouble();
            System.out.print("Has sidecar? (true/false): ");
            boolean sidecar = scanner.nextBoolean();
            system.addVehicle(new Motorcycle(plate, brand, model, price, engine, sidecar));
            
        } else if (type.equalsIgnoreCase("Truck")) {
            System.out.print("Enter load capacity (kg): ");
            double load = scanner.nextDouble();
            System.out.print("Has cooler? (true/false): ");
            boolean cooler = scanner.nextBoolean();
            system.addVehicle(new Truck(plate, brand, model, price, load, cooler));
        } else {
            System.out.println("Invalid vehicle type!");
        }
    }
    
    private static void addCustomerMenu(RentalSystem system, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter type (Individual/Organization): ");
        String type = scanner.nextLine();
        
        if (type.equalsIgnoreCase("Individual")) {
            System.out.print("Enter driving license number: ");
            String license = scanner.nextLine();
            System.out.print("Enter birth year (YYYY): ");
            int year = scanner.nextInt();
            System.out.print("Enter birth month (1-12): ");
            int month = scanner.nextInt();
            System.out.print("Enter birth day: ");
            int day = scanner.nextInt();
            scanner.nextLine();
            system.addCustomer(new Individual(name, address, phone, license, LocalDate.of(year, month, day)));
            
        } else if (type.equalsIgnoreCase("Organization")) {
            System.out.print("Enter commercial register number: ");
            String cr = scanner.nextLine();
            System.out.print("Enter discount rate (%): ");
            double discount = scanner.nextDouble();
            system.addCustomer(new Organization(name, address, phone, cr, discount));
        } else {
            System.out.println("Invalid customer type!");
        }
    }
}