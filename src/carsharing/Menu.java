package carsharing;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void homeMenu() {
        DataAccess da = new DataAccess();
        System.out.println("1. Log in as a manager\n2. Log in as a customer\n3. Create a customer\n0. Exit");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                managerMenu();
                break;
            case "2":
                List<Customer> customer = da.getAllCustomers();
                if (customer.size() == 0) {
                    System.out.println("The customer list is empty!");
                    homeMenu();
                } else {
                    CustomerMenu cm = new CustomerMenu();
                    cm.chooseCustomerMenu(customer);
                }
                break;
            case "3":
                System.out.println("Enter the customer name:");
                String name = scanner.nextLine();
                da.createCustomer(name);
                System.out.println("The customer was added!\n");
                homeMenu();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Incorrect entry");
                homeMenu();
                break;
        }
    }


    public void managerMenu() {
        DataAccess da = new DataAccess();
        System.out.println("\n1. Company list\n2. Create a company\n0. Back");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                List<Company> comp = da.getAllCompanies();
                if (comp.size() == 0) {
                    System.out.println("The company list is empty!");
                    managerMenu();
                } else {
                    chooseCompanyMenu(comp);
                }
                break;
            case "2":
                System.out.println("Enter the company name:");
                String name = scanner.nextLine();
                da.createCompany(name);
                System.out.println("The company was created!");
                managerMenu();
            case "0":
                homeMenu();
                break;
            default:
                System.out.println("Incorrect entry");
                managerMenu();
                break;
        }
    }

    public void chooseCompanyMenu(List<Company> comp) {
        System.out.println("\nChoose a company:");
        for (Company c : comp) {
            System.out.println(c.getId() + ". " + c.getName());
        }
        System.out.println("0. Back");
        int companyNo = scanner.nextInt();
        String temp = scanner.nextLine();
        if (companyNo == 0) {
            managerMenu();
        } else {
            Company chosen = comp.stream()
                .filter(a -> companyNo == a.getId())
                .findFirst()
                .orElse(null);
            if (chosen == null) {
            System.out.println("Sorry, wrong input");
                managerMenu();
            }
            System.out.println("\n" + chosen.getName() + " company");
            companyChosenMenu(chosen);
        }
    }

    public void companyChosenMenu(Company company) {
        DataAccess da = new DataAccess();
        System.out.println("\n1. Car list\n2. Create a car\n0. Back");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                List<Car> car = da.getAllCars(company.getId());
                if (car.size() == 0) {
                    System.out.println("The car list is empty!");
                    companyChosenMenu(company);
                } else {
                    System.out.println("\nCar list:");
                    int count = 1;
                    for (Car c : car) {
                        System.out.println(count + ". " + c.getCarName());
                        count++;
                    }
                    companyChosenMenu(company);
                }
                break;
            case "2":
                System.out.println("Enter the car name:");
                String name = scanner.nextLine();
                if (name.length() < 2) {
                    System.out.println("The car list is empty!");
                    companyChosenMenu(company);
                } else { da.createCar(name, company.getId());
                    System.out.println("The car was added!");
                    companyChosenMenu(company);
                }
                break;
            case "0":
                List<Company> comp = da.getAllCompanies();
                chooseCompanyMenu(comp);
                break;
            default:
                System.out.println("Incorrect entry");
                companyChosenMenu(company);
        }
        System.out.println("");
    }

}
