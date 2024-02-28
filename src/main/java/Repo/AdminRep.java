package Repo;

import Database.DB;
import category.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminRep {
    public static String getPassword(String login) throws Exception{
        try(Connection connection=DB.getconnection()){
            String query="SELECT * FROM Clients WHERE Login = ?";
            PreparedStatement st=connection.prepareStatement(query);
            st.setString(1,login);
            ResultSet rs=st.executeQuery();
            if (rs.next()) return rs.getString("Password");
            else return "";
        }
    }

    public static String getStatus(String login) throws Exception{
        try(Connection connection=DB.getconnection()){
            String query="SELECT * FROM Clients WHERE Login = ?";
            PreparedStatement st=connection.prepareStatement(query);
            st.setString(1,login);
            ResultSet rs=st.executeQuery();
            if (rs.next()) return rs.getString("status");
            else return null;
        }
    }

    public static int clientid(String login) throws Exception{
        try(Connection connection=DB.getconnection()){
            String query="SELECT * FROM Clients WHERE Login = ?";
            PreparedStatement st=connection.prepareStatement(query);
            st.setString(1,login);
            ResultSet rs=st.executeQuery();
            if (rs.next()) return rs.getInt("client_id");
            else return 0;
        }
    }


    public void AddAdm(Client client) throws Exception{
        try(Connection con=DB.getconnection()) {
            String query="INSERT INTO Clients (name,phone,login,password,status) VALUES ( ?,?,?,?,?);";
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, client.getName());
            st.setString(2,client.getPhone());
            st.setString(3,client.getLogin());
            st.setString(4,client.getPassword());
            st.setString(5,client.getStatus());
            st.execute();

        }
    }

    public List<Client> getAll() throws Exception {
        List<Client> admins = new ArrayList<>();
        try (Connection con = DB.getconnection()) {
            String query = "SELECT * FROM Clients WHERE LOWER(status)='admin' ORDER BY client_id asc ;";
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Client client = Client.builder().setId(rs.getInt("client_id"))
                        .setName(rs.getString("name"))
                        .setPhone(rs.getString("phone"))
                        .setStatus(rs.getString("status"))
                        .build();
                admins.add(client);
            }
            return admins;
        }
    }



    public void DeleteAdm(int id) throws Exception{
        try (Connection con=DB.getconnection()){
            String query="DELETE FROM Clients WHERE client_id=? and LOWER(status)='admin'";
            PreparedStatement st=con.prepareStatement(query);
            st.setInt(1,id);
            st.execute();
        }
    }


    public Client getByID(int id) throws Exception{
        try (Connection con=DB.getconnection()){
            String query="SELECT * FROM Clients WHERE client_id=? and LOWER(status)='admin'";
            PreparedStatement st=con.prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs=st.executeQuery();
            if (rs.next()) {

                return Client.builder()
                        .setId(rs.getInt("client_id"))
                        .setName(rs.getString("name"))
                        .setPhone(rs.getString("phone"))
                        .setLogin(rs.getString("login"))
                        .setStatus(rs.getString("status"))
                        .build();
            }else return null;
        }
    }




    public static void UpdateAdm(Client client) throws Exception {
        try (Connection con=DB.getconnection()){
            String query="UPDATE clients SET name=? , phone=? WHERE client_id =?";
            PreparedStatement st=con.prepareStatement(query);

            st.setString(1,client.getName());
            st.setString(2,client.getPhone());
            st.setInt(3,client.getId());

            st.execute();

        }
    }
}
