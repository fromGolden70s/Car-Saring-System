package carsharing;


public class Main {


    public static void main(String[] args) {
        CreateDbFile.create(args[1]);
        CreateDbFile.createTable();

        Menu menu = new Menu();
        menu.homeMenu();




    }


}