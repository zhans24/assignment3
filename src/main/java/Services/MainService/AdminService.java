package Services.MainService;

import Repo.AdminRep;
import Repo.ClientRep;
import Repo.MenuRep;
import Services.MainService.Service;
import Services.OrderService;

import java.util.Scanner;


public class AdminService {
    Scanner scanner = new Scanner(System.in);
    ClientRep clientRep = new ClientRep();
    MenuRep menuRep = new MenuRep();
    public AdminService(int a,String login) throws Exception {

        System.out.println("\nWelcome "+AdminRep.getStatus(login).toLowerCase()+"!\n");
        while (true) {
            System.out.println("1. Admin panel");
            System.out.println("2. Order");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    if (a==1) Service.Admin(clientRep, menuRep);
                    else Service.Director(clientRep,menuRep);
                    break;
                case 2:
                    new OrderService(menuRep,login);
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

