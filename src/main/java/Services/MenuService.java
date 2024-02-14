package Services;

import Repo.MenuRep;
import Services.AbstractService.Service;
import category.Menu;

import java.util.List;

public class MenuService extends Service {

    public MenuService(MenuRep menuRep)  {
        super(menuRep);
        boolean returnToAdminPanel = false;
        while (!returnToAdminPanel) {
            System.out.println("1. Add doner");
            System.out.println("2. Delete doner");
            System.out.println("3. Update doner");
            System.out.println("4. View doner");
            System.out.println("5. All doners");
            System.out.println("6. Back");
            System.out.print("\nEnter your choice: ");
            int c=scanner.nextInt();
            System.out.println();
            switch (c) {
                case 1:
                    AddDoner();
                    break;
                case 2:
                    DeleteDoner();
                    break;
                case 3:
                    UpdateDoner();
                    break;
                case 4:
                    ViewDoner();
                    break;
                case 5:
                    ShowAll();
                    break;
                case 6:
                    returnToAdminPanel=true;
                    break;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        };
    }




    public static void AddDoner(){
        Menu menu=new Menu();
        System.out.print("Enter the type of doner:");
        String doner=scanner.next();
        while (doner.isEmpty() || !doner.matches("[a-zA-Z ]+")){
            System.out.println("Invalid type of doner");
            System.out.print("\nTry again:");
            doner=scanner.next();
        }
        menu.setDoner(doner);
        System.out.print("Enter the price:");
        while (!scanner.hasNextInt()){
            System.out.println("Please enter a valid number for the price.");
            scanner.next();
            System.out.print("Enter the price:");
        }
        int price= scanner.nextInt();
        menu.setPrice(price);

        menuRep.AddMenu(menu);

        System.out.println("*****************************\nDoner added to the menu\n*****************************");
    }


    public static void DeleteDoner(){
        System.out.print("Enter doner's id:");
        menuRep.DeleteMenu(scanner.nextInt());
        System.out.println("Successfully deleted");
    }

    public static void ViewDoner(){
        System.out.print("Enter doner's id:");
        Menu doner=menuRep.getByID(scanner.nextInt());
        if (doner!=null){
            System.out.println("ID:"+doner.getId());
            System.out.println("Doner:"+doner.getDoner());
            System.out.println("Price:"+doner.getPrice());
        }else System.out.println("We hasn't that doner");
    }

    public static void UpdateDoner(){
        System.out.print("Enter doner's id:");
        Menu doner=menuRep.getByID(scanner.nextInt());

        if (doner!=null){
            System.out.print("Enter doner to change:");
            String donername= scanner.next();

            if (donername.isEmpty() || !donername.matches("[a-zA-Z ]+") ){
                System.out.println("Impossible doner");
            }else doner.setDoner(donername);


            System.out.print("Enter the price to change: ");
            if (scanner.hasNextInt()) {
                int price = scanner.nextInt();
                doner.setPrice(price);
            } else {
                System.out.println("Price is empty or not a valid number");
                scanner.next();
            }

            menuRep.Update(doner);

            System.out.println("--------------------------\nDoner updated\n--------------------------\n");

        }else System.out.println("That doner NOT found");
    }


    public static void ShowAll() {
        List<Menu> menus=menuRep.getAll();
        for (Menu menu:menus){
            System.out.println("ID:"+menu.getId());
            System.out.println("Doner:"+menu.getDoner());
            System.out.println("Price:"+menu.getPrice());
            System.out.println("------------------------------");
        }
    }


}
