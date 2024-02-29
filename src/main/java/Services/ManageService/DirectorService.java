package Services.ManageService;

import Repo.AdminRep;
import Repo.ClientRep;
import Services.MainService.AdminService;
import Services.MainService.Service;
import category.Client;

import java.util.List;

public class DirectorService extends Service {
    public DirectorService(AdminRep adminRep) throws Exception {
        super(adminRep);
        System.out.println("----------------------------------\nWelcome to the admin panel\n----------------------------------");
        boolean returnToAdminPanel = false;
        while (!returnToAdminPanel) {
            System.out.println("1. Add admin");
            System.out.println("2. Delete admin");
            System.out.println("3. Update admin");
            System.out.println("4. View admin");
            System.out.println("5. Show all admins");
            System.out.println("6. Back");
            System.out.print("\nEnter your choice: ");
            int c=scanner.nextInt();
            System.out.println();
            switch (c) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                    DeleteAdmin();
                    break;
                case 3:
                    UpdateAdmin();
                    break;
                case 4:
                    ViewAdmin();
                    break;
                case 5:
                    AllAdmins();
                    break;
                case 6:
                    returnToAdminPanel=true;
                    break;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        }
    }

    public void ViewAdmin() throws Exception {
        System.out.print("Enter admin's ID:");
        int id = scanner.nextInt();
        Client client= adminRep.getByID(id);
        if (client!=null){
            System.out.println("ID:"+client.getId());
            System.out.println("NAME:"+client.getName());
            System.out.println("PHONE:"+client.getPhone());
            System.out.println("LOGIN:"+client.getLogin());
            System.out.println("STATUS:"+client.getStatus());
        }else System.out.println("ADMIN NOT FOUND");
    }

    public void addAdmin() throws Exception{
        System.out.println("1. Give admin access by login");
        System.out.println("2. Create admin");
        System.out.print("Choose:");
        int choice=scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter login:");
                String log=scanner.next();
                int id=adminRep.clientid(log);
                while (adminRep.getByID(id)==null || "director".equalsIgnoreCase(AdminRep.getStatus(log))){
                    System.out.print("Incorrect login.Enter again login:");
                    log=scanner.next();
                    id=adminRep.clientid(log);
                }
                adminRep.UpdateStatus(id);
                System.out.println("*************************\n");
                System.out.println("Access is given for "+log);
                System.out.println("***************************\n");
                break;
            case 2:
                System.out.print("Enter new admin name:");
                String name = scanner.next();
                while (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    System.out.println("Invalid name");
                    System.out.print("Write name correctly:");
                    name = scanner.next();
                }


                System.out.print("Enter admin phone:");
                String phone = scanner.next();
                System.out.print("Enter admin login:");
                String login = scanner.next();
                while (AdminRep.isLoginOccupied(login)){
                    System.out.println("☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢");
                    System.out.println("☢ Login is occupied.Enter another login,please! ☢\n");
                    System.out.print("Enter admin login:");
                    login = scanner.next();
                }
                System.out.println("Enter admin password:");
                String password = scanner.next();


                new AdminRep().AddAdm(Client.builder()
                        .setName(name)
                        .setPhone(phone)
                        .setStatus("Admin")
                        .setLogin(login)
                        .setPassword(password)
                        .build());
                System.out.println("****************\n");
                System.out.println("Admin added\n");
                System.out.println("****************\n");

                break;
            default:
                System.out.println("Please enter correctly choice!");
        }
    }

    public void AllAdmins() throws Exception{
        List<Client> clients=adminRep.getAll();
        for(Client client:clients){

            System.out.println("ID:"+client.getId());
            System.out.println("NAME:"+client.getName());
            System.out.println("PHONE:"+client.getPhone());
            System.out.println("STATUS:"+client.getStatus());
            System.out.println("==============================");
        }
    }

    public void DeleteAdmin() throws Exception {
        System.out.print("Enter admin's ID:");
        int id = scanner.nextInt();
        while (adminRep.getByID(id)==null) {
            System.out.println("☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢");
            System.out.print("It's not admin's id.Enter correct id:");
            id=scanner.nextInt();
        }
        adminRep.DeleteAdm(scanner.nextInt());
        System.out.println("Successfully deleted");

    }


    public void UpdateAdmin() throws Exception{
        System.out.print("Enter admin's ID:");
        int id = scanner.nextInt();
        while (adminRep.getByID(id)==null) {
            System.out.println("☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢");
            System.out.print("It's not admin's id.Enter correct id:");
            id=scanner.nextInt();
        }
        Client admin=adminRep.getByID(id);
        if (admin!=null){
            System.out.print("Enter admin name to change:");
            String name= scanner.next();

            while (name.isEmpty() || !name.matches("[a-zA-Z ]+") ){
                System.out.print("Impossible name.Enter again:");
                name=scanner.next();
            }


            System.out.print("Enter the phone to change:");
            String phone=scanner.next();

            while (phone.isEmpty()){
                System.out.print("The phone number is empty.Enter again:");
                phone=scanner.next();
            }

            admin.setName(name);
            admin.setPhone(phone);

            adminRep.UpdateAdm(admin);

            System.out.println("Admin updated");

        }else System.out.println("Admin NOT found");

    }
}
