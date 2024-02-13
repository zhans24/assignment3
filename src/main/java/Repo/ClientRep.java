package Repo;

import Database.DB;
import category.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientRep {


    public  void AddCl(Client client) throws Exception{
        try(Connection con=DB.getconnection()) {
            String query="INSERT INTO Clients (name,phone) VALUES ( ?,?);";
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, client.getName());
            st.setString(2,client.getPhone());
            st.execute();

        }
    }

    public  void DeleteCl(int id) throws Exception{
        try (Connection con=DB.getconnection()){
            String query="DELETE FROM Clients WHERE id=?";
            PreparedStatement st=con.prepareStatement(query);
            st.setInt(1,id);
            st.execute();
        }
    }


    public  Client getByID(int id) throws Exception{
        try (Connection con=DB.getconnection()){
            String query="SELECT * FROM Clients WHERE id=?";
            PreparedStatement st=con.prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setPhone(rs.getString("phone"));

                return client;
            }else return null;
        }
    }

    public  List<Client> getAll() throws Exception{
        List<Client> clients=new ArrayList<>();
        try (Connection con=DB.getconnection()){
            String query="SELECT * FROM Clients ORDER BY id asc ;";
            PreparedStatement st=con.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                Client client=new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setPhone(rs.getString("phone"));
                clients.add(client);
            }
            return clients;
        }
    }


    public  void UpdateCl(Client client) throws Exception {
        try (Connection con=DB.getconnection()){
            String query="UPDATE clients SET name=? , phone=? WHERE id =?";
            PreparedStatement st=con.prepareStatement(query);

            st.setString(1,client.getName());
            st.setString(2,client.getPhone());
            st.setInt(3,client.getId());

            st.execute();

        }
    }

}
