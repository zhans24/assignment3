package Services.MainService;

import Repo.AdminRep;
import Repo.ClientRep;
import Repo.MenuRep;
import Services.ManageService.ClientService;
import Services.ManageService.DirectorService;
import Services.ManageService.MenuService;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
@Setter
@Getter
public class Service {
    public static final String Password="";
    protected static Scanner scanner;
    protected ClientRep clientRep;
    protected MenuRep menuRep;
    protected AdminRep adminRep;

    public Service(AdminRep adminRep) {
        this.adminRep = adminRep;
        scanner=new Scanner(System.in);
    }

    public Service(ClientRep clientRep){
        this.clientRep =clientRep;
        scanner=new Scanner(System.in);
    }
    public Service(MenuRep menuRep){
        this.menuRep =menuRep;
        scanner=new Scanner(System.in);
    }

    public static void Admin(ClientRep clientRep,MenuRep menuRep) throws Exception {
        Scanner scanner1=new Scanner(System.in);
        while (true) {
            System.out.println("1. Manage clients");
            System.out.println("2. Manage menu");
            System.out.println("3. Back to main menu");
            System.out.print("\nEnter your choice:");
            int choice = scanner1.nextInt();

            switch (choice) {
                case 1:
                    new ClientService(clientRep);
                    break;
                case 2:
                    new MenuService(menuRep);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        }
    }

    public static void Director(ClientRep clientRep,MenuRep menuRep) throws Exception {
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.println("1. Manage admins");
            System.out.println("2. Manage clients");
            System.out.println("3. Manage menu");
            System.out.println("4. Back to main menu");
            System.out.print("\nEnter your choice:");
            int choice = scanner1.nextInt();

            switch (choice) {
                case 1:
                    new DirectorService(new AdminRep());
                case 2:
                    new ClientService(clientRep);
                    break;
                case 3:
                    new MenuService(menuRep);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        }

    }
}
