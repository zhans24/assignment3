import Repo.ClientRep;
import Repo.MenuRep;
import Services.AbstractService.Service;
import Services.ClientService;
import Services.MenuService;
import Services.OrderService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        ClientRep clientRep=new ClientRep();
        MenuRep menuRep=new MenuRep();
        while (true) {
            System.out.println("1. Admin panel");
            System.out.println("2. Order");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin password:");
                    String pass=scanner.next();
                    while (!pass.equals(Service.Password)){
                        System.out.println("Incorrect\n");
                        System.out.print("Enter password:");
                        pass=scanner.next();
                        System.out.println();
                    }
                    System.out.println();
                    Service.Admin(clientRep,menuRep);
                    break;

                case 2:
                    new OrderService(menuRep);
                    break;
                case 3:
                    System.out.println("   ***      ***   ");
                    System.out.println("  *****    *****   ");
                    System.out.println(" *******  *******  ");
                    System.out.println("** See you soon! ** ");
                    System.out.println("******************* ");
                    System.out.println(" *****************  ");
                    System.out.println("   *************  ");
                    System.out.println("     *********   ");
                    System.out.println("      *******    ");
                    System.out.println("        ***      ");
                    return;
                default:
                    System.out.println("Incorrect choice.Choose between 1-3");
            }
        }
    }
}