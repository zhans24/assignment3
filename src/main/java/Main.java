import Repo.AdminRep;
import Repo.ClientRep;
import Repo.MenuRep;
import Services.MainService.AdminService;
import Services.OrderService;
import category.Client;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        String message = "\u2764W E L C O M E\u2764";

        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i) + " ");
            Thread.sleep(120);
        }
        System.out.println("\n\nDo you have account?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Choose:");
        int choice=scanner.nextInt();
        while (true){
            System.out.println();
            switch (choice){
                case 1:
                    System.out.print("Login:");
                    String login=scanner.next();
                    if (login.isEmpty() || !login.matches("[a-zA-Z ]+")){
                        System.out.print("Incorrect login.Try again:");
                        login=scanner.next();
                    }
                    System.out.print("Password:");
                    String password=scanner.next();
                    if (Objects.equals(password, AdminRep.getPassword(login))){
                        if ("admin".equalsIgnoreCase(AdminRep.getStatus(login))) new AdminService(1,login);
                        else if ("director".equalsIgnoreCase(AdminRep.getStatus(login))) new AdminService(2,login);
                        else new OrderService(new MenuRep(),login);
                        break;
                    }
                    else{
                        System.out.println("\n⚠ Login or password entered incorrectly ⚠\n");
                    }
                case 2:
                    System.out.println("\n⭐ Let's create ⭐\n");
                    System.out.print("Enter name:");
                    String name= scanner.next();
                    while (name.isEmpty() || !name.matches("[a-zA-Z ]+") ) {
                        System.out.println("Invalid name");
                        System.out.print("Write name correctly:");
                        name=scanner.next();
                    }

                    System.out.print("Enter phone:");
                    String phone=scanner.next();

                    System.out.print("Enter login:");
                    String log=scanner.next();
                    while (AdminRep.isLoginOccupied(log)){
                        System.out.println("☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢☢");
                        System.out.println("☢ Login is occupied.Enter another login,please! ☢\n");

                        System.out.print("Enter login:");
                        log = scanner.next();
                    }
                    System.out.print("Enter password:");
                    String pw=scanner.next();
                    System.out.print("Enter the password again:");
                    String password1=scanner.next();
                    while (!Objects.equals(password1, pw)){
                        System.out.print("\n☢ Passwords are not the SAME ☢\nEnter again password:");
                        password1=scanner.next();
                    }
                    new AdminRep().AddAdm(Client.builder()
                                    .setName(name)
                                    .setPhone(phone)
                                    .setLogin(log)
                                    .setPassword(pw)
                                    .setStatus("client")
                                    .build());
                    System.out.println("****************\n");
                    System.out.println(" Account created\n");
                    System.out.println("****************\n");

                    choice=1;
                    break;
                default:
                    System.out.println("Incorrectly choice.Try again:");
            }

        }
    }
}