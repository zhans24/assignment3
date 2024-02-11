package org.example;

import Repo.ClientRep;
import Services.ClientService;
import category.Client;

public class Main {
    public static void main(String[] args) throws Exception {
        ClientRep clientRep=new ClientRep();
        ClientService.viewClient(clientRep);
    }
}