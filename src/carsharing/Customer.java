package carsharing;

public class Customer {
    private int customerId;
    private String customerName;
    private Integer rentedCarId;

    Customer(int customerId, String customerName, Integer rentedCarId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.rentedCarId = rentedCarId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(Integer rentedCarId) {
        this.rentedCarId = rentedCarId;
    }
}
