package Services;

import Repo.ClientRep;
import category.Client;

import java.util.Scanner;

public class ClientService {
    static Scanner scanner=new Scanner(System.in);
    public static void viewClient(ClientRep rep) throws Exception {
        System.out.print("Enter client's ID:");
        int id = scanner.nextInt();
        Client client=ClientRep.getByID(id);

        if (client!=null){
            System.out.println("ID:"+client.getId());
            System.out.println("NAME:"+client.getName());
            System.out.println("PHONE:"+client.getPhone());
        }else System.out.println("CLIENT NOT FOUND");
    }
}
