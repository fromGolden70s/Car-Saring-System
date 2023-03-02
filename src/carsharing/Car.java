package carsharing;

public class Car {
    private int carId;
    private String carName;
    private boolean rented;
    private int carCompanyId;

    Car(int carId, String carName, int carCompanyId) {
        this.carId = carId;
        this.carName = carName;
        this.carCompanyId = carCompanyId;
        this.rented = rented;
    }

    public int getCarCompanyId() {
        return carCompanyId;
    }

    public void setCarCompanyId(int carCompanyId) {
        this.carCompanyId = carCompanyId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public boolean isRented() {
        return rented;
    }
    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
