package Services;

import Repo.AdminRep;
import Repo.MenuRep;
import Repo.OrderRep;
import Services.MainService.Service;
import Services.ManageService.MenuService;
import category.Menu;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//SELECT orders.id, clients.name, clients.phone, menu.doner FROM orders JOIN clients ON clients.client_id = orders.client_id JOIN menu ON menu.menu_id = orders.menu_doner;
public class OrderService extends Service {
    private int total=0;
    private List<String> doners=new ArrayList<>();
    public OrderService(MenuRep menuRep,String login) throws Exception {
        super(menuRep);
        LocalTime time= LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("************************************************");
        System.out.println("*  Welcome to the most delicious doner place!  *");
        System.out.println("************************************************");
        while (true) {
            System.out.println("1. Menu");
            System.out.println("2. Back");
            System.out.print("Enter your choice: ");
            int c = scanner.nextInt();
            System.out.println();
            switch (c) {
                case 1:
                    new MenuService(new MenuRep()).ShowAll();
                    System.out.print("What would you like to order?\nEnter ID:");
                    int id=scanner.nextInt();
                    OrderRep.AddOrder(adminRep.clientid(login),id);
                    while (true) {
                        System.out.println("__________________________________________\nNice choice! Would you like anything else?\n");
                        System.out.println("1. Order");
                        System.out.println("2. Bill");
                        System.out.print("Enter:");
                        c=scanner.nextInt();
                        System.out.println();
                        switch (c) {
                            case 1:
                                new MenuService(new MenuRep()).ShowAll();
                                System.out.print("\nWhat would you like to order?\nEnter ID:");
                                id=scanner.nextInt();
                                System.out.println();
                                OrderRep.AddOrder(adminRep.clientid(login),id);
                                break;
                            case 2:
                                for (int i = 0; i < 35; i++) {
                                    System.out.print("⃝ ");
                                }
                                System.out.println();
                                System.out.println("             BILL                   ");;
                                System.out.println("Order time:"+time);
                                System.out.println("You ordered:");
                                int counter = 1;
                                for (String doner : doners) {
                                    System.out.println(counter + ". " + doner);
                                    counter++;
                                }
                                System.out.println("\n"+"Total: "+total+"\n");
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
                    return;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        }
    }


}