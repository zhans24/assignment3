package Services.AbstractService;

import Repo.ClientRep;
import Repo.MenuRep;
import Services.ClientService;
import Services.MenuService;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
@Setter
@Getter
public abstract class Service {
    public static final String Password="0000";
    protected static Scanner scanner;
    protected ClientRep clientRep;
    protected static MenuRep menuRep;
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

}
