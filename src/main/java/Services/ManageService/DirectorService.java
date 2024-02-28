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
                    addClient();
                    break;
                case 2:
                    DeleteClient();
                    break;
                case 3:
                    UpdateClient();
                    break;
                case 4:
                    viewClient();
                    break;
                case 5:
                    AllClients();
                    break;
                case 6:
                    returnToAdminPanel=true;
                    break;
                default:
                    System.out.println("Please enter correctly choice!");
            }
        }
    }

    public void viewClient() throws Exception {
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

    public void addClient() throws Exception{
        System.out.println("1. Give admin access by phone");
        System.out.println("2. Create admin");
            System.out.print("Enter new admin name:");
            String name= scanner.next();
            while (name.isEmpty() || !name.matches("[a-zA-Z ]+") ) {
                System.out.println("Invalid name");
                System.out.print("Write name correctly:");
                name=scanner.next();
            }


            System.out.print("Enter admin phone:");
            String phone=scanner.next();
            System.out.println("Enter admin login:");
            String login=scanner.next();
            System.out.println("Enter admin password:");
            String password=scanner.next();


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
    }

    public void AllClients() throws Exception{
        List<Client> clients=adminRep.getAll();
        for(Client client:clients){

            System.out.println("ID:"+client.getId());
            System.out.println("NAME:"+client.getName());
            System.out.println("PHONE:"+client.getPhone());
            System.out.println("STATUS:"+client.getStatus());
            System.out.println("==============================");
        }
    }

    public void DeleteClient() throws Exception {
        System.out.print("Enter client's ID:");
        clientRep.DeleteCl(scanner.nextInt());
        System.out.println("Successfully deleted");
    }


    public void UpdateClient() throws Exception{
        System.out.print("Enter client's ID:");
        Client client=clientRep.getByID(scanner.nextInt());

        if (client!=null){
            System.out.print("Enter client name to change:");
            String name= scanner.next();

            if (name.isEmpty() || !name.matches("[a-zA-Z ]+") ){
                System.out.println("Impossible name");
            }


            System.out.print("Enter the phone to change:");
            String phone=scanner.next();

            if (phone.isEmpty()) System.out.println("The phone number is empty");

            client=Client.builder()
                    .setName(name)
                    .setPhone(phone)
                    .build();

            clientRep.UpdateCl(client);

            System.out.println("Client updated");

        }else System.out.println("Client NOT found");

    }
}
