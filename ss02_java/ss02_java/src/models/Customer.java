package models;

public class Customer extends Person{
    private String typeCustomer;
    public String address;

    public Customer() {
    }

    public Customer(int id, String name, int age, String gender, String idCard, String email, String typeCustomer, String address) {
        super(id, name, age, gender, idCard, email);
        this.typeCustomer = typeCustomer;
        this.address = address;
    }
    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "type_customer='" + typeCustomer + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

