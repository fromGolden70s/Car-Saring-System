package carsharing;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class CustomerMenu {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    DataAccess da = new DataAccess();
    public void chooseCustomerMenu(List<Customer> customer) {
        System.out.println("\nCustomer list:");
        for (Customer c : customer) {
            System.out.println(c.getCustomerId() + ". " + c.getCustomerName());
        }
        System.out.println("0. Back");
        int customerNo = scanner.nextInt();
        String eatEnter = scanner.nextLine();
        if (customerNo == 0) {
            menu.homeMenu();
        } else {
            Customer chosen = customer.stream()
                    .filter(a -> customerNo == a.getCustomerId())
                    .findFirst()
                    .orElse(null);
            if (chosen == null) {
                System.out.println("Sorry, wrong input");
                menu.homeMenu();
            }
            customerChosenMenu(chosen);
        }
    }

    public void customerChosenMenu(Customer customer) {
        System.out.println("1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back");
        String choice = scanner.nextLine();
        Optional<Integer> optional = Optional.ofNullable(customer.getRentedCarId());
        switch (choice) {
            case "1":
                if (optional.isPresent() && customer.getRentedCarId() != 0) {
                    System.out.println("You've already rented a car!");
                    customerChosenMenu(customer);
                }
                List<Company> comp = da.getAllCompanies();
                if (comp.size() == 0) {
                    System.out.println("The company list is empty!");
                    customerChosenMenu(customer);
                } else {
                    chooseCompanyMenu(comp, customer);
                }
                break;
            case "2":
                if (optional.isEmpty() || customer.getRentedCarId() == 0) {
                    System.out.println("You didn't rent a car!\n");
                    customerChosenMenu(customer);
                } else {
                    da.returnCar(customer.getCustomerId(), customer.getRentedCarId());
                    System.out.println("You've returned a rented car!\n");
                    customer.setRentedCarId(null);
                    customerChosenMenu(customer);
                }
                break;
            case "3":
                if (optional.isEmpty() || customer.getRentedCarId() == 0) {
                    System.out.println("You didn't rent a car!\n");
                    customerChosenMenu(customer);
                } else {
                    Car rentedCar = da.getCarById(customer.getRentedCarId());
                    System.out.println("Your rented car:\n" + rentedCar.getCarName());
                    Company co = da.getCompanyById(rentedCar.getCarCompanyId());
                    System.out.println("Company:\n" + co.getName());
                    customerChosenMenu(customer);
                }
                break;
            case "0":
                menu.homeMenu();
                break;
            default:
        }
    }


    public void chooseCompanyMenu(List<Company> comp, Customer customer) {
        System.out.println("\nChoose a company:");
        for (Company c : comp) {
            System.out.println(c.getId() + ". " + c.getName());
        }
        System.out.println("0. Back");
        int companyNo = scanner.nextInt();
        String temp = scanner.nextLine();
        if (companyNo == 0) {
            customerChosenMenu(customer);
        } else {
            Company chosen = comp.stream()
                    .filter(a -> companyNo == a.getId())
                    .findFirst()
                    .orElse(null);
            if (chosen == null) {
                System.out.println("Sorry, wrong input");
                chooseCompanyMenu(comp, customer);
            }
            rentCar(chosen, customer);
        }
    }

    public void rentCar(Company company, Customer customer) {
        List<Car> car = da.getAllCars(company.getId());
        if (car.size() == 0) {
            System.out.println("The car list is empty!");
            customerChosenMenu(customer);
        } else {
            System.out.println("\nChoose a car:");
            for (Car c : car) {
                System.out.println(c.getCarId() + ". " + c.getCarName());
            }
            System.out.println("0. Back");
        }
        int choice = scanner.nextInt();
        String eatEnter = scanner.nextLine();
        if (choice == 0) {
            customerChosenMenu(customer);
        }
        da.rent(choice, customer.getCustomerId());
        Car chosen = car.stream()
                .filter(a -> choice == a.getCarId())
                .findFirst()
                .orElse(null);
        System.out.println("\nYou rented '" + chosen.getCarName() + "'");
        customer.setRentedCarId(choice);
        customerChosenMenu(customer);
    }

}
