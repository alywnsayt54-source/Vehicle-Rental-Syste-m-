package vehicless;

public class Invoice {
    private RentalContracte contract;

    public Invoice(RentalContracte contract) {
        this.contract = contract;
    }

    public void printInvoice() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                    INVOICE");
        System.out.println("=".repeat(50));
        System.out.println("Contract ID:      " + contract.getContractId());
        System.out.println("Customer:         " + contract.getCustomer().getName());
        System.out.println("Vehicle:          " + contract.getVehicle().getPlateNumber());
        System.out.println("Start Date:       " + contract.getStartDate());
        System.out.println("Rental Days:      " + contract.getRentalDays());
        System.out.println("Late Days:        " + contract.getLateDays());
        System.out.println("-".repeat(50));
        System.out.println("FINAL AMOUNT:     " + contract.getFinalCost() + " SYP");
        System.out.println("=".repeat(50));
    }
}