package Services;

import Repo.ClientRep;

import java.util.Scanner;

public abstract class ClientServiceScanner {
    protected Scanner scanner;
    public ClientServiceScanner(ClientRep clientRep){
        scanner=new Scanner(System.in);
    };
}
