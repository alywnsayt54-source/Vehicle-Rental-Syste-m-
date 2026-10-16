package vehicless;

public abstract class Customer {
    protected int id;
    protected String name;
    protected String address;
    protected String phone;
    private static int nextId = 1;

    public Customer(String name, String address, String phone) {
        this.id = nextId++;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }

    public abstract double getDiscountRate();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Phone: " + phone;
    }
}