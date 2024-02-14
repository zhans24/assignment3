package Services;

import Repo.ClientRep;
import Repo.MenuRep;
import Services.AbstractService.Service;
import category.Menu;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OrderService extends Service {
    private int total=0;

    public OrderService(MenuRep menuRep) {
        super(menuRep);
        LocalTime time= LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("************************************************");
        System.out.println("*  Welcome to the most delicious doner place!  *");
        System.out.println("************************************************");
        boolean returnToMain = false;
        while (!returnToMain) {
            System.out.println("1. Menu");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");
            int c = scanner.nextInt();
            System.out.println();
            switch (c) {
                case 1:
                    MenuService.ShowAll();
                    System.out.print("What would you like to order?\nEnter ID:");
                    int id=scanner.nextInt();
                    CalcBill(id);
                    while (true) {
                        System.out.println("__________________________________________\nNice choice! Would you like anything else?\n");
                        System.out.println("1. Order");
                        System.out.println("2. Bill");
                        System.out.print("Enter:");
                        c=scanner.nextInt();
                        System.out.println();
                        switch (c) {
                            case 1:
                                MenuService.ShowAll();
                                System.out.print("\nWhat would you like to order?\nEnter ID:");
                                id=scanner.nextInt();
                                System.out.println();
                                CalcBill(id);
                                break;
                            case 2:
                                for (int i = 0; i < 35; i++) {
                                    System.out.print("⃝ ");
                                }
                                System.out.println();
                                System.out.println("             BILL                   ");;
                                System.out.println("Order time:"+time);
                                System.out.println("Total:"+total+"\n");
                                for (int i = 0; i < 35; i++) {
                                    System.out.print("⃝ ");
                                }
                                System.out.println("\n");
                                return;
                            default:
                                System.out.println("Please enter corrcetly choice between 1-2");
                        }
                    }
                case 2:
                    returnToMain = true;
                    break;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        }
    }
    public void CalcBill(int id){
        Menu doner=menuRep.getByID(id);
        total+=doner.getPrice();
    }


}